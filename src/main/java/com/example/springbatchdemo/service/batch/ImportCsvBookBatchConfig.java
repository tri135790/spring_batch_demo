package com.example.springbatchdemo.service.batch;

import static com.example.springbatchdemo.service.ImportCsvBookConstant.IMPORT_CSV_BOOK_JOB;
import static com.example.springbatchdemo.service.ImportCsvBookConstant.IMPORT_CSV_BOOK_STEP;
import static com.example.springbatchdemo.service.ImportCsvBookConstant.JOB_PARAM_CSV_FILENAME;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.example.springbatchdemo.entity.Book;
import com.example.springbatchdemo.service.batch.flatfilereader.BookCsvFieldSet;
import com.example.springbatchdemo.util.FileUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class ImportCsvBookBatchConfig {

    private final ImportCsvBook importCsvBook;

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final BookCsvSkipPolicy skipPolicy;

    @Bean
    @StepScope
    public FlatFileItemReader<BookCsvFieldSet> importCsvBookFlatItemReaderBean(
        @NonNull @Value(JOB_PARAM_CSV_FILENAME) String filename
    ) {
        Resource resource = FileUtil.getFileFromDisk("/book/csv/", filename);

        return importCsvBook.getFlatFileItemReader(resource);
    }

    @Bean
    @StepScope
    public ItemReader<Book> importCsvBookItemReaderBean() {
        return importCsvBook.getItemReader();
    }

    @Bean
    @StepScope
    public ItemWriter<Book> importCsvBookItemWriterBean() {
        return importCsvBook.getItemWriter();
    }

    @Bean
    public Step importCsvBookStep(
        ItemReader<Book> importCsvBookItemReaderBean,
        ItemWriter<Book> importCsvBookItemWriterBean,
        FlatFileItemReader<BookCsvFieldSet> importCsvBookFlatItemReaderBean
    ) {
        return stepBuilderFactory.get(IMPORT_CSV_BOOK_STEP)
            .<Book, Book>chunk(1000)
            .reader(importCsvBookItemReaderBean)
            .writer(importCsvBookItemWriterBean)
            .faultTolerant()
            .skipPolicy(skipPolicy)
            .stream(importCsvBookFlatItemReaderBean)
            .build();
    }

    @Bean
    public Job importCsvBookJob(
        Step importCsvBookStep
    ) {
        return jobBuilderFactory.get(IMPORT_CSV_BOOK_JOB)
            .incrementer(new RunIdIncrementer())
            .start(importCsvBookStep)
            .build();
    }
}
