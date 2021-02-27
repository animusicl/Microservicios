package com.academy.digitallab.store.customer.service;


import com.academy.digitallab.store.customer.entity.Region;

import java.util.List;

public interface RegionService {
    public List<Region> findAllRegions();
    public Region createRegion(Region region);
    public Region getRegion(Long id);
    public Region updateRegion(Region region);
    public void deleteRegion(Region region);

}
