package com.jds.dividend.dividendtool.controller;

import com.jds.dividend.dividendtool.model.Dividend;
import com.jds.dividend.dividendtool.repository.DividendRepository;
import com.jds.dividend.dividendtool.service.DividendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dividends")
@RequiredArgsConstructor
public class DividendController {

    private final DividendService dividendService;

    @GetMapping
    public ResponseEntity<List<Dividend>> getAllDividends() {
        return ResponseEntity.ok(dividendService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dividend> getDividendById(@PathVariable Long id) {
        return dividendService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Dividend> createDividend(@RequestBody Dividend dividend) {
        return ResponseEntity.ok(dividendService.save(dividend));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dividend> updateDividend(@PathVariable Long id, @RequestBody Dividend dividend) {
        return dividendService.update(id, dividend)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDividend(@PathVariable Long id) {
        dividendService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/scrape/{ticker}")
    public ResponseEntity<Dividend> scrapeDividendData(@PathVariable String ticker) {
        try {
            dividendService.scrapeDividendData(ticker);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

