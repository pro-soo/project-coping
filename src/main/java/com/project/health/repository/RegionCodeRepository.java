package com.project.health.repository;

import com.project.health.entity.RegionCode;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RegionCodeRepository extends JpaRepository<RegionCode, String>, RegionCodeRepositoryCustom {

}
