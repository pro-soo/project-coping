package com.project.health.dto;

import com.project.health.entity.RegionCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Builder
@Slf4j
@ToString
public class RegionCodeDto {
    private String znCd; //상위 지역코드
    private String znCdNm; //지역명
    private String lowrnkZnCd; //시군구 지역코드
    private String lowrnkZnCdNm; //시군구명

    public static RegionCodeDto from(RegionCode regionCode) {
        if (regionCode == null) return null;
//        log.debug("RegionCodeDto");
        return RegionCodeDto.builder()
                .znCd(regionCode.getZnCd())
                .znCdNm(regionCode.getZnCdNm())
                .lowrnkZnCd(regionCode.getLowrnkZnCd())
                .lowrnkZnCdNm(regionCode.getLowrnkZnCdNm())
                .build();
    }


}
