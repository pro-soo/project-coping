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
//        log.debug("RegionCodeDto");
        return RegionCodeDto.builder()
                .znCd(regionCode.getZnCd())
                .znCdNm(regionCode.getZnCdNm())
                .lowrnkZnCd(regionCode.getLowrnkZnCd())
                .lowrnkZnCdNm(regionCode.getLowrnkZnCdNm())
                .build();
    }

    public static List<RegionCodeDto> fromZnCdList(List<Tuple> regionList) {
        // 새로운 리스트 생성
        List<RegionCodeDto> newList = new ArrayList<>();
//        log.debug("fromList - regionList ::: "+regionList.toString());
        // for문 돌려서 tuple 값 꺼내기
        for (Tuple tuple : regionList) {
            newList.add(RegionCodeDto.builder()
                    .znCd(tuple.get(QRegionCode.regionCode.znCd))
                    .znCdNm(tuple.get(QRegionCode.regionCode.znCdNm))
                    .lowrnkZnCd("")
                    .lowrnkZnCdNm("")
                    .build());
        }
//        log.debug("fromList - newList ::: "+newList.toString());
        return newList;
    }
}
