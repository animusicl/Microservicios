package com.academy.digitallab.store.customer.controller;

import com.academy.digitallab.store.customer.entity.Region;

import com.academy.digitallab.store.customer.service.RegionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/region")
public class RegionController {

    private RegionService regionService;
    public RegionController (RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping
    public ResponseEntity<Region> createCategory (@RequestBody Region newRegion) {
        return ResponseEntity.ok(regionService.createRegion(newRegion));
    }

    @GetMapping
    public ResponseEntity <List<Region>> getAllRegions(){
        return ResponseEntity.ok(regionService.findAllRegions());
    }

    @PatchMapping("/{id}")
    public ResponseEntity <Region> updateRegion (
            @PathVariable("id") Long id,
            @RequestBody Region region) {
        region.setId(id);
        Region regionToUpdate = regionService.updateRegion(region);
        return (regionToUpdate == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(regionToUpdate);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity <Region> deleteRegion (@PathVariable("id") Long id) {
        Region regionToDelete = regionService.getRegion(id);
        return (regionToDelete == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(regionToDelete);
    }
}
