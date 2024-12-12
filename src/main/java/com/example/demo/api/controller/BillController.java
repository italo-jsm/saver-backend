package com.example.demo.api.controller;

import com.example.demo.api.dto.BillDto;
import com.example.demo.api.dto.ExpenseDto;
import com.example.demo.application.service.BillService;
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
@RequestMapping("/bills")
@AllArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping
    public ResponseEntity<?> saveBill(@RequestBody BillDto billDto){
        return ResponseEntity.created(URI.create(billService.saveBill(billDto))).build();
    }


    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(billService.getAll());
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin("*")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        try {
            String filename = "bills-" + UUID.randomUUID().toString();
            File uploadedFile = new File(System.getProperty("user.dir") + "/saver-docs/bills/"  + filename);
            file.transferTo(uploadedFile);
            return ResponseEntity.created(URI.create(filename)).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    @GetMapping("/by-month")
    @CrossOrigin("*")
    public ResponseEntity<List<BillDto>> getBillsByMonthAndYear(@RequestParam int month, @RequestParam int year) {
        return ResponseEntity
                .ok(billService.getBillsByMonthAndYear(month, year));
    }

}
