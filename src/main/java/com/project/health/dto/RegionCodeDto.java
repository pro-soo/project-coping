package com.project.health.dto;

import com.project.health.entity.QRegionCode;
import com.project.health.entity.RegionCode;
import com.querydsl.core.Tuple;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

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

        return RegionCodeDto.builder()
                .znCd(regionCode.getZnCd())
                .znCdNm(regionCode.getZnCdNm())
                .lowrnkZnCd(regionCode.getLowrnkZnCd())
                .lowrnkZnCdNm(regionCode.getLowrnkZnCdNm())
                .build();
    }

    public static List<RegionCodeDto> fromZnCdList(List<Tuple> regionList) {
        List<RegionCodeDto> newList = new ArrayList<>();

        for (Tuple tuple : regionList) {
            newList.add(RegionCodeDto.builder()
                    .znCd(tuple.get(QRegionCode.regionCode.znCd))
                    .znCdNm(tuple.get(QRegionCode.regionCode.znCdNm))
                    .lowrnkZnCd("")
                    .lowrnkZnCdNm("")
                    .build());
        }

        return newList;
    }
}
