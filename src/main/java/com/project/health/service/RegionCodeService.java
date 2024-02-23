package com.project.health.service;

import com.project.health.dto.RegionCodeDto;
import com.project.health.entity.RegionCode;
import com.project.health.repository.RegionCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("RegionCode")
@RequiredArgsConstructor
@Slf4j
public class RegionCodeService {
    private final RegionCodeRepository regionCodeRepository;

    public List<RegionCodeDto> getRegionCodes(String znCd){
        log.debug("getRegionCodes znCd "+znCd);
//        log.debug("sss : "+regionCodeRepository.findAllRegionInfo(znCd));
        List<RegionCode> regionCodes = regionCodeRepository.findAllRegionInfo(znCd);
//        log.debug("regionCodes :: "+regionCodes.toString());
        return regionCodes.stream().map(RegionCodeDto::from).collect(Collectors.toList());
    }
}
