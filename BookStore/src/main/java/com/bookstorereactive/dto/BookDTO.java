package com.bookstorereactive.dto;

import com.bookstorereactive.domain.BookGenre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

    private String id;

    @ISBN
    private String isbn;

    @NotBlank
    private String title;

    @Positive
    private BigDecimal price;

    public BookGenre bookGenre;

    @NotBlank
    private Set<String> authors = new HashSet<>();

    private String publisher;
}
