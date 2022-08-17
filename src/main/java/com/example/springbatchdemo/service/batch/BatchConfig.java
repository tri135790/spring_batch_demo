package com.example.springbatchdemo.service.batch;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

import io.sentry.spring.SentryTaskDecorator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private static final String IMPORT_CSV_BOOK_THREAD_NAME = "batch-import-csv-book";

    private final JobRepository jobRepository;

    public ThreadPoolTaskExecutor buildExecutor(String threadName) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setMaxPoolSize(1000);
        taskExecutor.setCorePoolSize(1000);
        taskExecutor.setQueueCapacity(1000);
        taskExecutor.setTaskDecorator(new SentryTaskDecorator());
        taskExecutor.setThreadNamePrefix(threadName);
        taskExecutor.initialize();

        return taskExecutor;
    }

    @Bean
    public JobLauncher importCsvBookJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        ThreadPoolTaskExecutor threadPoolTaskExecutor = buildExecutor(IMPORT_CSV_BOOK_THREAD_NAME);
        DelegatingSecurityContextAsyncTaskExecutor taskExecutor =
            new DelegatingSecurityContextAsyncTaskExecutor(threadPoolTaskExecutor);
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(taskExecutor);
        jobLauncher.afterPropertiesSet();

        return jobLauncher;
    }
}
