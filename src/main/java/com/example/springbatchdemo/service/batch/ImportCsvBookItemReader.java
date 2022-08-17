package com.example.springbatchdemo.service.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import com.example.springbatchdemo.entity.Book;
import com.example.springbatchdemo.exception.BookException;
import com.example.springbatchdemo.service.batch.flatfilereader.BookCsvFieldSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImportCsvBookItemReader implements ItemReader<Book> {

    private final ItemReader<BookCsvFieldSet> importCsvBookFlatItemReader;

    @Override
    public Book read() {
        BookCsvFieldSet csvFieldSet = getCsvFieldSet();

        if (null != csvFieldSet) {
            return processField(csvFieldSet);
        }
        return null;
    }

    private BookCsvFieldSet getCsvFieldSet() {
        try {
            return importCsvBookFlatItemReader.read();
        } catch (Exception e) {
            log.error("IMPORT_CSV caught exception while reading", e);
            throw new BookException("Format file is invalid");
        }
    }

    private Book processField(BookCsvFieldSet fieldSet) {
        try {
            return Book.valueOf(fieldSet);
        } catch (Exception e) {
            log.error("IMPORT_CSV caught exception while parsing to Book",e);
            throw new BookException("Caught exception while parsing to Book");
        }
    }
}
