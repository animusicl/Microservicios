package com.academy.digitallab.store.customer.service;

import com.academy.digitallab.store.customer.entity.Region;
import com.academy.digitallab.store.customer.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImp implements RegionService {

    public RegionServiceImp(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    private RegionRepository regionRepository;

    @Override
    public List<Region> findAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public Region createRegion(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public Region getRegion(Long id) {

        return regionRepository.findById(id).orElse(null);
    }

    @Override
    public Region updateRegion(Region region) {
        Region regionDB = getRegion(region.getId());
        if (regionDB == null){
            return null;
        }
        regionDB.setName(region.getName());
        return regionRepository.save(regionDB);
    }

    @Override
    public void deleteRegion(Region region) {
        regionRepository.delete(region);
    }
}
