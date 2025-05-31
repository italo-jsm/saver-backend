package com.example.demo.api.controller;

import com.example.demo.api.dto.BillDto;
import com.example.demo.api.dto.ExpenseDto;
import com.example.demo.api.dto.response.CreatedResponse;
import com.example.demo.application.mapper.BillMapper;
import com.example.demo.application.service.BillService;
import com.example.demo.application.service.FileStorageService;
import com.example.demo.domain.model.Bill;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bills")
@AllArgsConstructor
public class BillController {

    private final BillService billService;
    private final FileStorageService fileStorageService;
    private final BillMapper billMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreatedResponse> saveBill(@RequestPart(value = "file", required = false) MultipartFile file, @RequestPart("billDto") BillDto billDto) throws IOException {
        String resourceId = "";

        if (file != null && !file.isEmpty()) {
            String filePath = fileStorageService.saveFile(file);
            billDto.setBankSlipFilePath(filePath);
        }

        resourceId = billService.saveBill(billMapper.toDomain(billDto)).getId();

        return ResponseEntity.created(URI.create(resourceId)).body(CreatedResponse.withResourceId(resourceId));
    }


    @GetMapping
    public ResponseEntity<List<BillDto>> getAll(){
        return ResponseEntity.ok(billService.getAll().stream().map(billMapper::toDto).toList());
    }

    @GetMapping("/by-month")
    public ResponseEntity<List<BillDto>> getBillsByMonthAndYear(@RequestParam int month, @RequestParam int year) {
        return ResponseEntity
                .ok(billService.getBillsByMonthAndYear(month, year).stream().map(billMapper::toDto).toList());
    }

    @PatchMapping
    public ResponseEntity<CreatedResponse> setBillState(@RequestBody BillDto billDto){
        String resourceId = billService.setBillState(billMapper.toDomain(billDto)).getId();
        return ResponseEntity.created(URI.create(resourceId)).body(CreatedResponse.withResourceId(resourceId));
    }

    @GetMapping("/files/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
        Path path = Paths.get(System.getProperty("user.dir") + "/saver-docs/bills/" + filename);
        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new UrlResource(path.toUri());
        String contentType = Files.probeContentType(path);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + path.getFileName().toString() + "\"")
                .body(resource);
    }
}
