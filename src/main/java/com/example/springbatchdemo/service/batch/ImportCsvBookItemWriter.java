package com.example.springbatchdemo.service.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbatchdemo.entity.Book;
import com.example.springbatchdemo.repository.BookJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImportCsvBookItemWriter implements ItemWriter<Book> {

    private final BookJpaRepository repository;

    @Override
    @Transactional
    public void write(List<? extends Book> list) {
        list.forEach(repository::save);
    }
}
