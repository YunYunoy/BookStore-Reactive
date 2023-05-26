package com.bookstorereactive.service;

import com.bookstorereactive.domain.BookGenre;
import com.bookstorereactive.dto.BookDTO;
import com.bookstorereactive.mapper.BookMapper;
import com.bookstorereactive.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {


    private final BookMapper bookMapper;
    private final BookRepository bookRepository;


    @Override
    public Flux<BookDTO> findAllByBookGenre(BookGenre bookGenre) {
        return bookRepository.findAllByBookGenre(bookGenre)
                .map(bookMapper::toDto);
    }

    @Override
    public Flux<BookDTO> findAllByAuthorsContainsIgnoreCase(String author) {
        return bookRepository.findAllByAuthorsContainsIgnoreCase(author)
                .map(bookMapper::toDto);
    }

    @Override
    public Flux<BookDTO> findAllByPriceIsBetween(BigDecimal bigDecimalLow, BigDecimal bigDecimalHigh) {
        return bookRepository.findAllByPriceIsBetween(bigDecimalLow, bigDecimalHigh)
                .map(bookMapper::toDto);
    }

    @Override
    public Flux<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .map(bookMapper::toDto);
    }

    @Override
    public Mono<BookDTO> findFirstByTitle(String title) {
        return bookRepository.findFirstByTitle(title)
                .map(bookMapper::toDto);
    }

    @Override
    public Mono<BookDTO> createBook(BookDTO bookDTO) {
        return bookRepository.save(bookMapper.toEntity(bookDTO))
                .map(bookMapper::toDto);
    }

    @Override
    public Mono<BookDTO> getBookById(String id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto);
    }

    @Override
    public Mono<BookDTO> updateBook(String id, BookDTO bookDTO) {
        return bookRepository.findById(id)
                .map(foundBook -> {
                    if (StringUtils.hasText(bookDTO.getTitle())) {
                        foundBook.setTitle(bookDTO.getTitle());
                    }
                    if (StringUtils.hasText(bookDTO.getIsbn())) {
                        foundBook.setIsbn(bookDTO.getIsbn());
                    }
                    if (bookDTO.getPrice() != null) {
                        foundBook.setPrice(bookDTO.getPrice());
                    }
                    if (bookDTO.getBookGenre() != null) {
                        foundBook.setBookGenre(bookDTO.getBookGenre());
                    }
                    if (bookDTO.getAuthors() != null) {
                        foundBook.getAuthors().addAll(bookDTO.getAuthors());
                    }
                    if (StringUtils.hasText(bookDTO.getPublisher())) {
                        foundBook.setPublisher(bookDTO.getPublisher());
                    }

                    return foundBook;
                }).flatMap(bookRepository::save)
                .map(bookMapper::toDto);
    }

    @Override
    public Mono<Void> deleteBookById(String id) {
        return bookRepository.deleteById(id);
    }


}
