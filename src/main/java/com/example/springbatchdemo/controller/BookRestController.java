package com.example.springbatchdemo.controller;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.springbatchdemo.Routes;
import com.example.springbatchdemo.service.ImportCsvBookFacade;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final ImportCsvBookFacade importCsvBookFacade;

    @PostMapping(value = Routes.BOOK_IMPORT_CSV)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void importCsvBook(@NotNull @RequestParam("file")MultipartFile file) {
        importCsvBookFacade.importCsv(file);
    }
}
