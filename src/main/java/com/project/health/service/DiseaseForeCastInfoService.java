package com.project.health.service;

import com.project.health.dto.DiseaseForeCastInfoDto;
import com.project.health.entity.DiseaseForecastInfo;
import com.project.health.repository.DiseaseForeCastInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("DiseaseForeCastInfo")
@RequiredArgsConstructor
@Slf4j
public class DiseaseForeCastInfoService {

    private final DiseaseForeCastInfoRepository diseaseForeCastInfoRepository;

    public void saveDissForeCastInfo(List<Map<String, Object>> itemList){
        for (Map<String, Object> itemMap : itemList) {
            DiseaseForecastInfo diseaseForecastInfo = DiseaseForecastInfo.builder()
                    .dissCd(itemMap.get("dissCd").toString())
                    .dt(itemMap.get("dt").toString())
                    .znCd(itemMap.get("znCd").toString())
                    .lowrnkZnCd(itemMap.get("lowrnkZnCd").toString())
                    .cnt(Integer.parseInt(itemMap.get("cnt").toString()))
                    .risk(Integer.parseInt(itemMap.get("risk").toString()))
                    .dissRiskXpln(itemMap.get("dissRiskXpln").toString()).build();

            log.debug("diseaseForecastInfo :::::::: " + diseaseForecastInfo.toString());
            diseaseForeCastInfoRepository.save(diseaseForecastInfo);
        }
    }
    public List<DiseaseForecastInfo> getDissForeCastInfoList(String dissCd){
        List<DiseaseForecastInfo> diseaseForeCastInfoDtoList = diseaseForeCastInfoRepository.searchDissInfoList(dissCd);
        log.debug("getDissForeCastInfoList =========== " + diseaseForeCastInfoDtoList);
        return diseaseForeCastInfoDtoList;
    }
}
