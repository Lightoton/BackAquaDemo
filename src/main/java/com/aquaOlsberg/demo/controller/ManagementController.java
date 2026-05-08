package com.aquaOlsberg.demo.controller;

import com.aquaOlsberg.demo.entity.Price;
import com.aquaOlsberg.demo.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ManagementController {

    private final PriceRepository priceRepository;

    @GetMapping("/public/prices")
    @Cacheable("prices")
    public List<Price> getAllPrices() {
        System.out.println("📦 ---> ЗАПРОС В БАЗУ ДАННЫХ: Загружаем цены из PostgreSQL <---");
        return priceRepository.findAll();
    }

    @PutMapping("/admin/prices/{id}")
    @CacheEvict(value = "prices", allEntries = true)
    public ResponseEntity<Price> updatePrice(@PathVariable Long id, @RequestBody Price priceDetails) {
        return priceRepository.findById(id)
                .map(price -> {
                    price.setPrice2h(priceDetails.getPrice2h());
                    price.setPrice3h(priceDetails.getPrice3h());
                    price.setPriceDay(priceDetails.getPriceDay());
                    return ResponseEntity.ok(priceRepository.save(price));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}