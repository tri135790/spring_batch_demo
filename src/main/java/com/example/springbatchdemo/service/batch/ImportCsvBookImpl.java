package com.example.springbatchdemo.service.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.PassThroughFieldSetMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.example.springbatchdemo.entity.Book;
import com.example.springbatchdemo.service.ImportCsvBookConstant;
import com.example.springbatchdemo.service.batch.flatfilereader.BookCsvFieldSet;
import com.example.springbatchdemo.service.batch.flatfilereader.BookCsvLineMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImportCsvBookImpl implements ImportCsvBook {

    private final ImportCsvBookItemReader itemReader;

    private final ImportCsvBookItemWriter itemWriter;

    @Override
    public FlatFileItemReader<BookCsvFieldSet> getFlatFileItemReader(
        Resource csvResource
    ) {
        BookCsvLineMapper lineMapper = getLineMapper();

        FlatFileItemReader<BookCsvFieldSet> reader = new FlatFileItemReader<>();
        reader.setLineMapper(lineMapper);
        reader.setLinesToSkip(1);
        reader.setResource(csvResource);
        reader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());

        return reader;
    }

    @Override
    public ItemReader<Book> getItemReader() {
        return itemReader;
    }

    @Override
    public ItemWriter<Book> getItemWriter() {
        return itemWriter;
    }

    private BookCsvLineMapper getLineMapper() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(ImportCsvBookConstant.FieldConstant.FIELD_NAMES);
        lineTokenizer.setDelimiter(",");

        return new BookCsvLineMapper(lineTokenizer, new PassThroughFieldSetMapper());
    }
}
