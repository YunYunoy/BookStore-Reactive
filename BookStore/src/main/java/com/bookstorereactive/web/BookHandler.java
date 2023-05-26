package com.bookstorereactive.web;

import com.bookstorereactive.domain.BookGenre;
import com.bookstorereactive.dto.BookDTO;
import com.bookstorereactive.service.BookService;
import com.bookstorereactive.utils.BookNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class BookHandler {

    private final BookService bookService;
    private final Validator validator;

    private void validate(BookDTO bookDTO) {
        Errors errors = new BeanPropertyBindingResult(bookDTO, "bookDTO");
        validator.validate(bookDTO, errors);
        if (errors.hasErrors()) {
            throw new ServerWebInputException(errors.toString());
        }
    }

    public Mono<ServerResponse> deleteBookById(ServerRequest request) {
        return bookService.getBookById(request.pathVariable("id"))
                .switchIfEmpty(Mono.error(new BookNotFound("Book not found with ID: " + request.queryParam("id"))))
                .flatMap(bookDTO -> bookService.deleteBookById(bookDTO.getId()))
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> updateBookById(ServerRequest request) {
        return request.bodyToMono(BookDTO.class)
                .doOnNext(this::validate)
                .flatMap(bookDTO -> bookService
                        .updateBook(request.pathVariable("id"), bookDTO))
                .switchIfEmpty(Mono.error(new BookNotFound("Book not found with ID: " + request.queryParam("id"))))
                .flatMap(savedDto -> ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> createNewBook(ServerRequest request) {
        return request.bodyToMono(BookDTO.class)
                .doOnNext(this::validate)
                .flatMap(bookService::createBook)
                .flatMap(createdBook -> ServerResponse.created(UriComponentsBuilder
                                .fromPath("/books/{id}")
                                .build(createdBook.getId()))
                        .build())
                .onErrorResume(e -> ServerResponse.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(Collections.singletonMap("error", e.getMessage())));
    }

    public Mono<ServerResponse> getBookById(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(bookService.getBookById(request.pathVariable("id"))
                                .switchIfEmpty(Mono.error(new BookNotFound("Book not found with ID: " + request.queryParam("id")))),
                        BookDTO.class);
    }

    public Mono<ServerResponse> listBooks(ServerRequest request) {
        Flux<BookDTO> response;

        if (request.queryParam("bookGenre").isPresent()) {
            response = bookService.findAllByBookGenre(BookGenre.valueOf(request.queryParam("bookGenre").get()));
        } else if (request.queryParam("authors").isPresent()) {
            response = bookService.findAllByAuthorsContainsIgnoreCase(request.queryParam("authors").get());
        } else if (request.queryParam("bigDecimalLow").isPresent() && request.queryParam("bigDecimalHigh").isPresent()) {
            BigDecimal low = new BigDecimal(request.queryParam("bigDecimalLow").get());
            BigDecimal high = new BigDecimal(request.queryParam("bigDecimalHigh").get());
            response = bookService.findAllByPriceIsBetween(low, high);
        } else {
            response = bookService.getAllBooks();
        }

        return ServerResponse.ok()
                .body(response, BookDTO.class);
    }
}

