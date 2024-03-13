package com.project.health.service;

import com.project.health.dto.DiseaseForeCastInfoDto;
import com.project.health.entity.DiseaseForecastInfo;
import com.project.health.repository.DiseaseForeCastInfoRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.springframework.util.StringUtils.isEmpty;

@Service("DiseaseForeCastInfo")
@RequiredArgsConstructor
@Slf4j
public class DiseaseForeCastInfoService {

    private final DiseaseForeCastInfoRepository diseaseForeCastInfoRepository;
    private final RegionCodeService regionCodeService;

    /***
     * 질병예상정보 저장
     * @param itemList
     */
    public void saveDissForeCastInfo(List<Map<String, Object>> itemList) {
        // 전체 카운트
        int cnt = diseaseForeCastInfoRepository.allCountDissInfoList();
//        log.debug("saveDissForeCastInfo cnt : " + cnt);
        if (cnt == 0) cnt = 1;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date now = new Date();
        String nowTime = sdf.format(now);

        for (Map<String, Object> itemMap : itemList) {
            if (itemMap.get("dt").toString().equals(nowTime)){
            DiseaseForecastInfo diseaseForecastInfo = DiseaseForecastInfo.builder()
                    .id(cnt)
                    .dissCd(itemMap.get("dissCd").toString())
                    .dt(itemMap.get("dt").toString())
                    .lowrnkZnCd(itemMap.get("lowrnkZnCd").toString())
                    .cnt(Integer.parseInt(itemMap.get("cnt").toString()))
                    .risk(Integer.parseInt(itemMap.get("risk").toString()))
                    .dissRiskXpln(itemMap.get("dissRiskXpln").toString()).build();

            cnt++;
            log.debug("diseaseForecastInfo :::::::: " + diseaseForecastInfo.toString());
            diseaseForeCastInfoRepository.save(diseaseForecastInfo);
            }
        }
    }

    /***
     * 질병예상정보 리스트 호출
     * @param prmLowrnkZnCd
     * @param prmZnCd
     * @return
     */
    public List<DiseaseForeCastInfoDto> getDissForeCastInfoList(String prmZnCd, String prmLowrnkZnCd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date now = new Date();
        String nowTime = sdf.format(now);
        List<Tuple> list = diseaseForeCastInfoRepository.searchDissInfoList(prmZnCd, prmLowrnkZnCd, nowTime);
//        log.debug("getDissForeCastInfoList - list >>>>>> "+list.toString());
        return DiseaseForeCastInfoDto.fromList(list);
    }

    /***
     * 카카오맵 표시를 위한 질병예상정보 리스트 조회
     * @param prmZnCdNm
     * @param prmLowrnkZnCdNm
     * @return
     */
    public List<DiseaseForeCastInfoDto> getDissForeCastInfoListKaKaoMap(String prmZnCdNm, String prmLowrnkZnCdNm) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date now = new Date();
        String nowTime = sdf.format(now);
        List<Tuple> list = diseaseForeCastInfoRepository.searchDissInfoListKaKaoMap(prmZnCdNm, prmLowrnkZnCdNm, nowTime);
//        log.debug("getDissForeCastInfoList - list >>>>>> "+list.toString());
        return DiseaseForeCastInfoDto.fromList(list);
    }

    /***
     * 질병예상정보 중복 체크
     * @param prmDissCd
     * @param prmZnCd
     * @return
     */
    public int getDissForeCastInfoCount(String prmDissCd, String prmZnCd) {
        return diseaseForeCastInfoRepository.duplCountDissInfo(prmDissCd, prmZnCd);
    }

    /***
     * open api 호출 및 저장
     * @param dissCd
     * @param znCd
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public List<DiseaseForeCastInfoDto> getAPIList(String dissCd, String znCd, String prmLowrnkZnCd) throws IOException, ParseException {
        List<DiseaseForeCastInfoDto> diseaseForeCastInfoDto = null;

        // 중복 값 체크
        int infoCnt = getDissForeCastInfoCount(dissCd, znCd);
        log.debug("중복 체크 - infoCnt : " + infoCnt);
        // 중복 값 없으면 OPEN API 조회 후 저장
        if (infoCnt == 0) {
            String urlBuilder = "http://apis.data.go.kr/B550928/dissForecastInfoSvc/getDissForecastInfo" + "?" + URLEncoder.encode("serviceKey", "UTF-8") + "=AU%2BlOSJ7rm7d%2BJNEl42gF48f20yYevVj6mN24iNz3Or4v1FnhgO2a2DGwe96r5mEZNhTGpaoYNoboIN3P0vq7A%3D%3D" + /*Service Key*/
                    "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("25", "UTF-8") + /*한 페이지 결과 수*/
                    "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") + /*페이지 번호, 미입력 시 기본값 1*/
                    "&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8") + /*응답결과의 출력 방식(xml, json), 미입력시 기본값:xml*/
                    "&" + URLEncoder.encode("dissCd", "UTF-8") + "=" + URLEncoder.encode(dissCd, "UTF-8") + /*질병코드*/
                    "&" + URLEncoder.encode("znCd", "UTF-8") + "=" + URLEncoder.encode(znCd, "UTF-8"); /*시도별 지역코드, 지역코드 입력시 시군구별 데이터 응답/미입력시, 전국 및 시도별 데이터 응답*/
            URL url = new URL(urlBuilder);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
//            log.debug("Response code: " + conn.getResponseCode());

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
//            log.debug(sb.toString());

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
            JSONObject jsonResponseObject = (JSONObject) jsonParser.parse(jsonObject.get("response").toString());
            JSONObject jsonBodyObject = (JSONObject) jsonParser.parse(jsonResponseObject.get("body").toString());
//            log.debug("json jsonObject : " + jsonObject.get("response"));
//            log.debug("json jsonResponseObject : " + jsonResponseObject.get("body"));
            log.debug("json jsonBodyObject : " + jsonBodyObject.get("items"));

            List<Map<String, Object>> itemList = (List<Map<String, Object>>) jsonBodyObject.get("items");

            // OPEN API 정보 저장
            saveDissForeCastInfo(itemList);
        }

        // DB 조회
        diseaseForeCastInfoDto = getDissForeCastInfoList(znCd, prmLowrnkZnCd);
        return diseaseForeCastInfoDto;
    }

}
