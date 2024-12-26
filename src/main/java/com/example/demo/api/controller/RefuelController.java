package com.example.demo.api.controller;

import com.example.demo.api.dto.RefuelDto;
import com.example.demo.api.dto.response.CreatedResponse;
import com.example.demo.application.service.RefuelService;
import com.example.demo.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/refuels")
@AllArgsConstructor
public class RefuelController {

    private RefuelService refuelService;

    @PostMapping
    public ResponseEntity<CreatedResponse> saveRefuel(@RequestBody RefuelDto refuelDto){
        String refuelId = refuelService.save(refuelDto);
        return ResponseEntity.created(URI.create(refuelId)).body(new CreatedResponse(CreatedResponse.RESOURCE_ID, refuelId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefuelDto> getRefuel(@PathVariable String id){
        return ResponseEntity.ok(refuelService.getRefuelById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found", id, RefuelDto.class.getName()))
        );
    }
}
