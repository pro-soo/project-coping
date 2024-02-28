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

    /***
     * 카카오 맵 api에 사용될 지역정보 조회
     * @param prmZnCdNm
     * @return
     */
    public List<RegionCodeDto> getRegionCodes(String prmZnCdNm){
//        log.debug("getRegionCodes prmZnCdNm "+prmZnCdNm);
        List<RegionCode> regionCodes = regionCodeRepository.findAllRegionInfo(prmZnCdNm);
        return regionCodes.stream().map(RegionCodeDto::from).collect(Collectors.toList());
    }
}
