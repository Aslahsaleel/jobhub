package com.aslah.jobportal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Set;
import java.util.UUID;

@Service
public class FileStorageService {
    private static final Set<String> ALLOWED = Set.of("application/pdf");
    private final Path root;
    public FileStorageService(@Value("${app.upload-dir:uploads}") String dir) throws IOException {
        root = Paths.get(dir).toAbsolutePath().normalize(); Files.createDirectories(root);
    }
    public String storePdf(MultipartFile file) throws IOException {
        if(file.isEmpty() || !ALLOWED.contains(file.getContentType())) throw new IllegalArgumentException("Only PDF resumes are allowed");
        if(file.getSize() > 5 * 1024 * 1024) throw new IllegalArgumentException("Resume must be 5 MB or smaller");
        String name = UUID.randomUUID() + ".pdf";
        Files.copy(file.getInputStream(), root.resolve(name), StandardCopyOption.REPLACE_EXISTING);
        return name;
    }
}
