package me.kenux.playground.jpa.web.controller;

import me.kenux.playground.jpa.web.dto.TimeTestRequest;
import me.kenux.playground.jpa.web.dto.TimeTestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/time")
public class TimeTestController {

    @PostMapping
    public ResponseEntity<?> post(@RequestBody TimeTestRequest request) {
        return ResponseEntity.ok(request);
    }

    @GetMapping
    public ResponseEntity<?> get(@ModelAttribute TimeTestRequest request) {
        return ResponseEntity.ok(request);
    }
}
