package com.example.demo.api.controller;

import com.example.demo.api.dto.BillDto;
import com.example.demo.api.dto.ExpenseDto;
import com.example.demo.api.dto.response.CreatedResponse;
import com.example.demo.application.mapper.BillMapper;
import com.example.demo.application.service.BillService;
import com.example.demo.domain.model.Bill;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bills")
@AllArgsConstructor
public class BillController {

    private final BillService billService;
    private final BillMapper billMapper;

    @PostMapping
    public ResponseEntity<CreatedResponse> saveBill(@RequestBody BillDto billDto){
        String resourceId = billService.saveBill(billMapper.toDomain(billDto)).getId();
        return ResponseEntity.created(URI.create(resourceId)).body(CreatedResponse.withResourceId(resourceId));
    }


    @GetMapping
    public ResponseEntity<List<BillDto>> getAll(){
        return ResponseEntity.ok(billService.getAll().stream().map(billMapper::toDto).toList());
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreatedResponse> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CreatedResponse.withError("File is empty"));
        }

        try {
            String filename = "bills-" + UUID.randomUUID().toString();
            File uploadedFile = new File(System.getProperty("user.dir") + "/saver-docs/bills/"  + filename);
            file.transferTo(uploadedFile);
            return ResponseEntity.created(URI.create(filename)).body(CreatedResponse.withFileName(filename));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CreatedResponse.withError("Failed to upload file"));
        }
    }

    @GetMapping("/by-month")
    public ResponseEntity<List<BillDto>> getBillsByMonthAndYear(@RequestParam int month, @RequestParam int year) {
        return ResponseEntity
                .ok(billService.getBillsByMonthAndYear(month, year).stream().map(billMapper::toDto).toList());
    }

}
