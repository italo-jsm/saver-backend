package com.example.demo.application.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class FileStorageService {

    public String saveFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String extension = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        }

        String filename = UUID.randomUUID() + extension;

        File dir = new File(System.getProperty("user.dir") + "/saver-docs/bills/");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File uploadedFile = new File(dir, filename);
        file.transferTo(uploadedFile);

        return filename;
    }

}
