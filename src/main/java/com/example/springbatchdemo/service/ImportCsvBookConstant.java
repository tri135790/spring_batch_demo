package com.example.springbatchdemo.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ImportCsvBookConstant {

    public static final String IMPORT_CSV_BOOK_STEP = "importCsvBookStep";

    public static final String IMPORT_CSV_BOOK_JOB = "importCsvBookJob";

    public static final String CSV_HEADER_LINE = "bookID,title,authors,average_rating,isbn," +
        "isbn13,language_code,  num_pages,ratings_count,text_reviews_count,publication_date,publisher";

    public static final String CSV_FILENAME = "csvFilename";

    public static final String JOB_PARAM_CSV_FILENAME =
        "#{jobParameters['" + CSV_FILENAME + "']}";

    public static final class FieldConstant {

        public static final String BOOK_ID = "bookId";

        public static final String TITLE = "title";

        public static final String AUTHORS = "authors";

        public static final String RATING = "average_rating";

        public static final String ISBN = "isbn";

        public static final String ISBN13 = "isbn13";

        public static final String LANGUAGE_CODE = "language_code";

        public static final String NUM_PAGES = "  num_pages";

        public static final String RATING_COUNT = "ratings_count";

        public static final String REVIEW_COUNT = "text_reviews_count";

        public static final String PUBLICATION_DATE = "publication_date";

        public static final String PUBLISHER = "publisher";

        public static final String[] FIELD_NAMES = new String[] {
            BOOK_ID,
            TITLE,
            AUTHORS,
            RATING,
            ISBN,
            ISBN13,
            LANGUAGE_CODE,
            NUM_PAGES,
            RATING_COUNT,
            REVIEW_COUNT,
            PUBLICATION_DATE,
            PUBLISHER
        };
    }


}
