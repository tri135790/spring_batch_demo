package com.example.springbatchdemo.service.batch.flatfilereader;

import org.springframework.batch.item.file.transform.DefaultFieldSet;
import org.springframework.batch.item.file.transform.FieldSet;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BookCsvFieldSet extends DefaultFieldSet {

    private FieldSet fieldSet;

    private String line;

    private int lineNumber;

    public BookCsvFieldSet(String[] tokens) {
        super(tokens);
    }
}
