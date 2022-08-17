package com.example.springbatchdemo.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImportCsvBookService {

    void processImport(MultipartFile file);
}
