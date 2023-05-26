package com.bookstorereactive.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Book {

    @Id
    private String id;

    private String isbn;
    private String title;
    private BigDecimal price;
    public BookGenre bookGenre;
    private Set<String> authors = new HashSet<>();
    private String publisher;
}
