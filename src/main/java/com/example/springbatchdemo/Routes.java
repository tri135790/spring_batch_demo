package com.example.springbatchdemo;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Routes {

    public static final String API = "/api";
    public static final String BOOK = "/book";
    public static final String BOOK_API = API + BOOK;
    public static final String BOOK_IMPORT_CSV = BOOK_API + "/csv";
}
