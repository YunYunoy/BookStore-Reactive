package com.bookstorereactive.repository;

import com.bookstorereactive.domain.Book;
import com.bookstorereactive.domain.BookGenre;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    Mono<Book> findFirstByTitle(String title);

    Flux<Book> findAllByBookGenre(BookGenre bookGenre);

    Flux<Book> findAllByAuthorsContainsIgnoreCase(String author);

    Flux<Book> findAllByPriceIsBetween(BigDecimal bigDecimalLow, BigDecimal bigDecimalHigh);
}
