package com.example.springbatchdemo.entity;

import static com.example.springbatchdemo.service.batch.flatfilereader.BookCsvParser.parseAuthors;
import static com.example.springbatchdemo.service.batch.flatfilereader.BookCsvParser.parseRating;
import static com.example.springbatchdemo.service.batch.flatfilereader.BookCsvParser.parseTitle;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.springbatchdemo.service.batch.flatfilereader.BookCsvFieldSet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String authors;

    private Float rating;

    public static Book valueOf(BookCsvFieldSet fieldSet) {
        return Book.builder()
            .title(parseTitle(fieldSet))
            .authors(parseAuthors(fieldSet))
            .rating(parseRating(fieldSet))
            .build();
    }
}
