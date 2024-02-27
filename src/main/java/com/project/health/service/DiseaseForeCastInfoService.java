package com.project.health.service;

import com.project.health.dto.DiseaseForeCastInfoDto;
import com.project.health.entity.DiseaseForecastInfo;
import com.project.health.repository.DiseaseForeCastInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("DiseaseForeCastInfo")
@RequiredArgsConstructor
@Slf4j
public class DiseaseForeCastInfoService {

    private final DiseaseForeCastInfoRepository diseaseForeCastInfoRepository;

    /***
     * 질병예상정보 저장
     * @param itemList
     */
    public void saveDissForeCastInfo(List<Map<String, Object>> itemList) {
        int cnt = diseaseForeCastInfoRepository.countDissInfoList();
        log.debug("saveDissForeCastInfo cnt : " + cnt);
        if(cnt == 0) cnt = 1;

        for (Map<String, Object> itemMap : itemList) {
            DiseaseForecastInfo diseaseForecastInfo = DiseaseForecastInfo.builder()
                    .id(cnt)
                    .dissCd(itemMap.get("dissCd").toString())
                    .dt(itemMap.get("dt").toString())
                    .znCd(itemMap.get("znCd").toString())
                    .lowrnkZnCd(itemMap.get("lowrnkZnCd").toString())
                    .cnt(Integer.parseInt(itemMap.get("cnt").toString()))
                    .risk(Integer.parseInt(itemMap.get("risk").toString()))
                    .dissRiskXpln(itemMap.get("dissRiskXpln").toString()).build();

            cnt++;
            log.debug("diseaseForecastInfo :::::::: " + diseaseForecastInfo.toString());
            diseaseForeCastInfoRepository.save(diseaseForecastInfo);
        }
    }


    /***
     * 질병예상정보 리스트 호출
     * @param prmDissCd
     * @param prmZnCd
     * @return
     */
    public List<DiseaseForeCastInfoDto> getDissForeCastInfoList(String prmDissCd, String prmZnCd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date now = new Date();
        String nowTime = sdf.format(now);

        List<DiseaseForeCastInfoDto> testList = diseaseForeCastInfoRepository.searchDissInfoList(prmDissCd, prmZnCd, nowTime);
        log.debug("testList ::: "+testList.toString());
        return testList;
    }

    /***
     * 질병예상정보 중복 체크
     * @param prmDissCd
     * @param prmZnCd
     * @return
     */
    public int getDissForeCastInfoCount(String prmDissCd, String prmZnCd){
        return diseaseForeCastInfoRepository.countDissInfo(prmDissCd, prmZnCd);
    }

}
