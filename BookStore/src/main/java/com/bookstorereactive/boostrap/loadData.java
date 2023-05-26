package com.bookstorereactive.boostrap;

import com.bookstorereactive.domain.Book;
import com.bookstorereactive.domain.BookGenre;
import com.bookstorereactive.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class loadData implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {

//        Book book1 = Book.builder()
//                .id("5")
//                .bookGenre(BookGenre.FANTASY)
//                .title("Test Book 1")
//                .isbn("978-1-234567-89-0")
//                .authors(new HashSet<>(Arrays.asList("Author 1", "Author 2")))
//                .price(BigDecimal.valueOf(10.99))
//                .publisher("Test Publisher 1")
//                .build();
//
//        Book book2 = Book.builder()
//                .id("6")
//                .bookGenre(BookGenre.HORROR)
//                .title("Test Book 2")
//                .isbn("978-2-345678-90-1")
//                .authors(new HashSet<>(Arrays.asList("Author 3", "Author 4")))
//                .price(BigDecimal.valueOf(20.99))
//                .publisher("Test Publisher 2")
//                .build();
//
//        Book book3 = Book.builder()
//                .id("7")
//                .bookGenre(BookGenre.PSYCHOLOGICAL)
//                .title("Test Book 3")
//                .isbn("978-3-456789-01-2")
//                .authors(new HashSet<>(Arrays.asList("Author 5", "Author 6")))
//                .price(BigDecimal.valueOf(30.99))
//                .publisher("Test Publisher 3")
//                .build();
//
//        bookRepository.saveAll(Arrays.asList(book1, book2, book3))
//                .subscribe();
    }
}
