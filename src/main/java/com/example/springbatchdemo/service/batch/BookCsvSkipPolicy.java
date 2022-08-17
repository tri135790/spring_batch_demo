package com.example.springbatchdemo.service.batch;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

@Component
public class BookCsvSkipPolicy implements SkipPolicy {

    private static final Integer MAX_SKIP_COUNT = 10;

    @Override
    public boolean shouldSkip(Throwable throwable, int i) throws SkipLimitExceededException {
        if (throwable instanceof FlatFileParseException && i < MAX_SKIP_COUNT) {
            return true;
        }
        return false;
    }
}
