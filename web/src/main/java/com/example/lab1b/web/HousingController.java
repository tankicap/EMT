package com.example.lab1b.web;

import com.example.lab1b.model.Category;
import com.example.lab1b.model.Housing;
import com.example.lab1b.model.dto.HousingDto;
import com.example.lab1b.service.HousingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/housing")
public class HousingController {
    private final HousingService housingService;

    public HousingController(HousingService housingService) {
        this.housingService = housingService;
    }

    @GetMapping
    public List<Housing> findAll(){
        return housingService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Housing> findById(@PathVariable Long id) {
        return this.housingService.findById(id)
                .map(housing -> ResponseEntity.ok().body(housing))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Housing> addAccommodation(@RequestBody HousingDto housingDto)
    {
        return housingService.create(housingDto.getName(),
                        housingDto.getCategory(),
                        housingDto.getHostId(),
                        housingDto.getNumRooms())
                .map(housing -> ResponseEntity.ok().body(housing))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Housing> deleteAccommodation(@PathVariable Long id)
    {
        return housingService.delete(id)
                .map(housing -> ResponseEntity.ok().body(housing))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Housing> editAccommodation(@PathVariable Long id, @RequestBody HousingDto housingDto)
    {
        return housingService.edit(id,
                        housingDto.getName(),
                        housingDto.getCategory(),
                        housingDto.getHostId(),
                        housingDto.getNumRooms())
                .map(housing -> ResponseEntity.ok().body(housing))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/bookmark/{id}")
    public ResponseEntity<Housing> bookmark(@PathVariable Long id){
        return housingService.bookmark(id)
                .map(housing -> ResponseEntity.ok().body(housing))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/rent/{id}")
    public ResponseEntity<Housing> rent(@PathVariable Long id){
        return housingService.rent(id)
                .map(housing -> ResponseEntity.ok().body(housing))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return List.of(Category.values());
    }

}
