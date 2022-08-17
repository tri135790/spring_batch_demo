package com.example.springbatchdemo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.validation.ValidationException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.springbatchdemo.exception.BookException;
import com.example.springbatchdemo.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImportCsvBookFacade {

    private final ImportCsvBookService importCsvBookService;

    @SneakyThrows(FileNotFoundException.class)
    public void importCsv(MultipartFile file) {
        if (file.isEmpty()) {
            log.warn("IMPORT_CSV file is empty, filename: {}", file.getOriginalFilename());
            throw new IllegalArgumentException("File CSV is empty");
        }

        String header = getHeaderFromCsv(file);

        if (!ImportCsvBookConstant.CSV_HEADER_LINE.equals(header)) {
            log.error("HEADER of csv file is INVALID, filename: {}", file.getOriginalFilename());
            throw new ValidationException("File csv header is invalid");
        }
        FileUtil.copyFileToDisk(file, "/book/csv/");

        importCsvBookService.processImport(file);
    }

    private static String getHeaderFromCsv(MultipartFile file) {
        try (BufferedReader reader =
                 new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            return reader.readLine();
        } catch (IOException e) {
            throw new BookException(e.getMessage(), e);
        }
    }
}
