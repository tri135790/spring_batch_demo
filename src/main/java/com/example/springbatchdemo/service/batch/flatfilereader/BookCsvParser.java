package com.example.springbatchdemo.service.batch.flatfilereader;

import static com.example.springbatchdemo.service.ImportCsvBookConstant.FieldConstant.AUTHORS;
import static com.example.springbatchdemo.service.ImportCsvBookConstant.FieldConstant.RATING;
import static com.example.springbatchdemo.service.ImportCsvBookConstant.FieldConstant.TITLE;

import org.apache.commons.lang3.StringUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookCsvParser {

    public static String parseTitle(BookCsvFieldSet fieldSet) {
        String title = fieldSet.getFieldSet().readString(TITLE);

        if (StringUtils.isBlank(title)) {
            return null;
        }
        return title;
    }

    public static String parseAuthors(BookCsvFieldSet fieldSet) {
        String authors = fieldSet.getFieldSet().readString(AUTHORS);

        if (StringUtils.isBlank(authors)) {
            return null;
        }
        return authors;
    }

    public static Float parseRating(BookCsvFieldSet fieldSet) {
        Float rating = fieldSet.getFieldSet().readFloat(RATING);

        return rating;
    }
}
