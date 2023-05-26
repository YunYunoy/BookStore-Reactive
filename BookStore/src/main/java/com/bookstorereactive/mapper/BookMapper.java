package com.bookstorereactive.mapper;

import com.bookstorereactive.domain.Book;
import com.bookstorereactive.dto.BookDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface BookMapper {

    BookDTO toDto(Book book);

    Book toEntity(BookDTO bookDto);
}
