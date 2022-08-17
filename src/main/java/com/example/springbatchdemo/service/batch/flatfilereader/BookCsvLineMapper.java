package com.example.springbatchdemo.service.batch.flatfilereader;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.PassThroughFieldSetMapper;
import org.springframework.batch.item.file.transform.LineTokenizer;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookCsvLineMapper implements LineMapper<BookCsvFieldSet> {

    @NonNull
    private final LineTokenizer lineTokenizer;

    @NonNull
    private final PassThroughFieldSetMapper fieldSetMapper;

    @Override
    public BookCsvFieldSet mapLine(String line, int lineNumber) throws Exception {
        BookCsvFieldSet fieldSet = new BookCsvFieldSet(new String[]{""});
        fieldSet.setFieldSet(fieldSetMapper.mapFieldSet(lineTokenizer.tokenize(line)));
        fieldSet.setLine(line);
        fieldSet.setLineNumber(lineNumber);
        return fieldSet;
    }
}
