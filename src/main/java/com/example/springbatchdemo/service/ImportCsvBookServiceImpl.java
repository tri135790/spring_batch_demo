package com.example.springbatchdemo.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.springbatchdemo.exception.BookException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImportCsvBookServiceImpl implements ImportCsvBookService {

    private final JobLauncher jobLauncher;

    private final Job job;

    @Override
    public void processImport(MultipartFile file) {
        JobParameters jobParameters = getJobParameters(file);

        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException | JobParametersInvalidException | JobRestartException e) {
            log.error("IMPORT_CSV fail to start job, filename: {}", file.getOriginalFilename(), e);

            throw new BookException("IMPORT_CSV fail to start job, filename: {}" +
                file.getOriginalFilename());
        }
    }

    private static JobParameters getJobParameters(MultipartFile file) {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString("random", UUID.randomUUID().toString());
        jobParametersBuilder.addString(ImportCsvBookConstant.CSV_FILENAME,
            Objects.requireNonNull(file.getOriginalFilename())
        );

        return jobParametersBuilder.toJobParameters();
    }
}
