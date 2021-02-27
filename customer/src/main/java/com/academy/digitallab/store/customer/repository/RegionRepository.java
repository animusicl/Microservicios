package com.academy.digitallab.store.customer.repository;
import com.academy.digitallab.store.customer.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
