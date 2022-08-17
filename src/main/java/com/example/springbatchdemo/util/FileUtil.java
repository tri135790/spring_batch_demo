package com.example.springbatchdemo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {

    private static final String ROOT = System.getProperty("user.dir");

    /**
     * Copy file to disk
     *
     * @param multipartFile file to be copied.
     * @param subfolder subfolder path which the file will be located (e.g: "/book/csv/").
     */
    public static void copyFileToDisk(MultipartFile multipartFile, String subfolder) throws FileNotFoundException {
        if (multipartFile.isEmpty()) {
            throw new FileNotFoundException("File is not found " + multipartFile.getOriginalFilename());
        }

        String filename = multipartFile.getOriginalFilename();
        String path = String.format("%s%s%s", ROOT, subfolder, filename);
        transferMultipartFile(multipartFile, path);

    }

    /**
     * Get saved file on disk
     *
     * @param subfolder subfolder path which the file is located (e.g: "/book/csv/").
     * @param filename  is the file name
     * @return {@link Resource}
     */
    public static Resource getFileFromDisk(String subfolder, String filename) {
        String path = String.format("%s%s%s", ROOT, subfolder, filename);
        File file = new File(path);

        return new FileSystemResource(file);
    }

    public static void transferMultipartFile(MultipartFile file, String path) {

        File targetFile = new File(path);

        transferMultipartFile(file, targetFile);
    }

    public static void transferMultipartFile(MultipartFile file, File targetFile) {
        try {
            targetFile.getParentFile().mkdirs();
            file.transferTo(targetFile);
        } catch (IOException e) {
            log.error("TransferMultipartFile failed, caused by: ", e);
        }
    }
}
