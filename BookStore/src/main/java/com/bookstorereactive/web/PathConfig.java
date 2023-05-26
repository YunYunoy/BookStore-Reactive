package com.bookstorereactive.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
@RequiredArgsConstructor
public class PathConfig {

    @Value("${book.path}")
    private String BOOK_PATH;
    @Value("${book.path.id}")
    private String BOOK_PATH_ID;

    private final BookHandler bookHandler;


    @Bean
    public RouterFunction<ServerResponse> bookPaths() {
        return route()
                .GET(BOOK_PATH, bookHandler::listBooks)
                .GET(BOOK_PATH_ID, bookHandler::getBookById)
                .POST(BOOK_PATH, bookHandler::createNewBook)
                .PUT(BOOK_PATH_ID, bookHandler::updateBookById)
                .DELETE(BOOK_PATH_ID, bookHandler::deleteBookById)
                .build();
    }
}