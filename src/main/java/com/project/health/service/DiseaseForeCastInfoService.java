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

@Service
@RequiredArgsConstructor
@Slf4j
public class DiseaseForeCastInfoService {

    private final DiseaseForeCastInfoRepository diseaseForeCastInfoRepository;

    /***
     * 질병예상정보 저장
     * @param itemList
     */
    public void saveDissForeCastInfo(List<Map<String, Object>> itemList) {
        int cnt = diseaseForeCastInfoRepository.allCountDissInfoList();
        if (cnt == 0) cnt = 1;
        for (Map<String, Object> itemMap : itemList) {
            int lowrnkZnCdCnt = diseaseForeCastInfoRepository.duplCountLowrnkZnCdInfo(itemMap.get("dissCd").toString(), itemMap.get("lowrnkZnCd").toString(), itemMap.get("dt").toString());
            if (lowrnkZnCdCnt == 0) {
                if (itemMap.get("dt").toString().equals(getNowTime())) {
                    DiseaseForecastInfo diseaseForecastInfo = DiseaseForecastInfo.builder()
                            .id(cnt)
                            .dissCd(itemMap.get("dissCd").toString())
                            .dt(itemMap.get("dt").toString())
                            .lowrnkZnCd(itemMap.get("lowrnkZnCd").toString())
                            .cnt(Integer.parseInt(itemMap.get("cnt").toString()))
                            .risk(Integer.parseInt(itemMap.get("risk").toString())).build();
                    cnt++;
                    diseaseForeCastInfoRepository.save(diseaseForecastInfo);
                }
            }
        }
    }

    /***
     * 오늘 날짜 변환 (yyyyMMdd 형식으로)
     * @return
     */
    private static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();

        return sdf.format(now);
    }

    /***
     * 질병예상정보 리스트 호출
     * @param prmLowrnkZnCd
     * @param prmZnCd
     * @return
     */
    public List<DiseaseForeCastInfoDto> getDissForeCastInfoList(String prmZnCd, String prmLowrnkZnCd) {
        List<Tuple> list = diseaseForeCastInfoRepository.searchDissInfoList(prmZnCd, prmLowrnkZnCd, getNowTime());

        return DiseaseForeCastInfoDto.fromList(list);
    }

    /***
     * 카카오맵 표시를 위한 질병예상정보 리스트 조회
     * @param prmZnCdNm
     * @param prmLowrnkZnCdNm
     * @return
     */
    public List<DiseaseForeCastInfoDto> getDissForeCastInfoListKaKaoMap(String prmZnCdNm, String prmLowrnkZnCdNm) {
        List<Tuple> list = diseaseForeCastInfoRepository.searchDissInfoListKaKaoMap(prmZnCdNm, prmLowrnkZnCdNm, getNowTime());

        return DiseaseForeCastInfoDto.fromList(list);
    }

    /***
     * 질병예상정보 중복 체크
     * @param prmDissCd
     * @param prmZnCd
     * @return
     */
    public int getDissForeCastInfoCount(String prmDissCd, String prmZnCd) {
        return diseaseForeCastInfoRepository.duplCountDissInfo(prmDissCd, prmZnCd, getNowTime());
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

        int infoCnt = getDissForeCastInfoCount(dissCd, znCd);

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

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
            JSONObject jsonResponseObject = (JSONObject) jsonParser.parse(jsonObject.get("response").toString());
            JSONObject jsonBodyObject = (JSONObject) jsonParser.parse(jsonResponseObject.get("body").toString());

            List<Map<String, Object>> itemList = (List<Map<String, Object>>) jsonBodyObject.get("items");

            saveDissForeCastInfo(itemList);
        }
        diseaseForeCastInfoDto = getDissForeCastInfoList(znCd, prmLowrnkZnCd);

        return diseaseForeCastInfoDto;
    }


    /***
     * open api 호출 및 저장
     * @param dissCd
     * @param znCd
     * @return
     */
    public void getAPIList(String dissCd, String znCd) throws Exception {
        int infoCnt = getDissForeCastInfoCount(dissCd, znCd);

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
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
            JSONObject jsonResponseObject = (JSONObject) jsonParser.parse(jsonObject.get("response").toString());
            JSONObject jsonBodyObject = (JSONObject) jsonParser.parse(jsonResponseObject.get("body").toString());

            List<Map<String, Object>> itemList = (List<Map<String, Object>>) jsonBodyObject.get("items");

            saveDissForeCastInfo(itemList);
        }
    }
}
