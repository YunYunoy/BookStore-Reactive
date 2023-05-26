package com.bookstorereactive.service;

import com.bookstorereactive.domain.BookGenre;
import com.bookstorereactive.dto.BookDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface BookService {


    Flux<BookDTO> findAllByBookGenre(BookGenre bookGenre);

    Flux<BookDTO> findAllByAuthorsContainsIgnoreCase(String author);

    Flux<BookDTO> findAllByPriceIsBetween(BigDecimal bigDecimalLow, BigDecimal bigDecimalHigh);

    Flux<BookDTO> getAllBooks();

    Mono<BookDTO> findFirstByTitle(String title);

    Mono<BookDTO> createBook(BookDTO bookDTO);

    Mono<BookDTO> getBookById(String id);

    Mono<BookDTO> updateBook(String id, BookDTO bookDTO);

    Mono<Void> deleteBookById(String id);

}
