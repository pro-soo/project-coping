package com.project.health;

import com.project.health.dto.DiseaseForeCastInfoDto;
import com.project.health.service.DiseaseForeCastInfoService;
import com.project.health.service.RegionCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ApiController {

    private final DiseaseForeCastInfoService diseaseForeCastInfoService;
    private final RegionCodeService regionCodeService;

    /***
     * 메인화면
     * @param model
     * @return
     */
    @GetMapping("/api")
    public String getOpenApi(Model model) {

        try {

            /*URL*/
            List<Map<String, Object>> itemList = getAPIList("3","29");

            //            log.debug("znCd :::::::: " + itemList.get(0).get("znCd").toString());
//            log.debug("regionCodeService.getRegionCodes :::::::: " + regionCodeService.getRegionCodes(itemList.get(0).get("znCd").toString()));
            String prmZnCd= itemList.get(0).get("znCd").toString();
            String prmDissCd= itemList.get(0).get("dissCd").toString();

            model.addAttribute("result", diseaseForeCastInfoService.getDissForeCastInfoList(prmDissCd, prmZnCd));
            model.addAttribute("region", regionCodeService.getRegionCodes(prmZnCd));
        } catch (Exception e) {
            log.error("ERROR 발생!");
            e.printStackTrace();
        }

        return "test";
    }

    private List<Map<String, Object>> getAPIList(String dissCd, String znCd) throws IOException, ParseException {

        if(StringUtils.isEmpty(dissCd)){
            dissCd = "1";
        }
        if (StringUtils.isEmpty(znCd)){
            znCd = "11";
        }
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
        log.debug("Response code: " + conn.getResponseCode());

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
        log.debug(sb.toString());

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
        JSONObject jsonResponseObject = (JSONObject) jsonParser.parse(jsonObject.get("response").toString());
        JSONObject jsonBodyObject = (JSONObject) jsonParser.parse(jsonResponseObject.get("body").toString());
        log.debug("json jsonObject : " + jsonObject.get("response"));
        log.debug("json jsonResponseObject : " + jsonResponseObject.get("body"));
        log.debug("json jsonBodyObject : " + jsonBodyObject.get("items"));

        List<Map<String, Object>> itemList = (List<Map<String, Object>>) jsonBodyObject.get("items");


        String prmZnCd= itemList.get(0).get("znCd").toString();
        String prmDissCd= itemList.get(0).get("dissCd").toString();

        int infoCnt = diseaseForeCastInfoService.getDissForeCastInfoCount(prmDissCd, prmZnCd);
        log.debug("중복 체크 - infoCnt : " + infoCnt);

        if (infoCnt == 0){
            // OPEN API 정보 저장
            diseaseForeCastInfoService.saveDissForeCastInfo(itemList);;
        }

        return itemList;
    }

    /***
     * 검색
     * @param model
     * @param prmZnCd
     * @return
     */
    @PostMapping("/api")
    public String getOpenApi (Model model, @RequestParam("znCd") String prmZnCd, @RequestParam("dissCd") String prmDissCd){
        log.debug("test2 ::: "+prmZnCd);
        try {
            /*URL*/
            List<Map<String, Object>> itemList = getAPIList(prmDissCd,prmZnCd);
            log.debug("json itemList : " + itemList.toString());

            model.addAttribute("result", diseaseForeCastInfoService.getDissForeCastInfoList(prmDissCd, prmZnCd));
            model.addAttribute("region", regionCodeService.getRegionCodes(prmZnCd));
        } catch (Exception e) {
            log.error("ERROR 발생!");
            e.printStackTrace();
        }

        return "test";
    }
}
