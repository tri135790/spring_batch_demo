package com.example.springbatchdemo.service.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.Resource;

import com.example.springbatchdemo.entity.Book;
import com.example.springbatchdemo.service.batch.flatfilereader.BookCsvFieldSet;

public interface ImportCsvBook {

    FlatFileItemReader<BookCsvFieldSet> getFlatFileItemReader(Resource csvResource);

    ItemReader<Book> getItemReader();

    ItemWriter<Book> getItemWriter();
}
