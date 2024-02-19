package com.project.health;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

@Controller
@Slf4j
public class ApiController {

    @RequestMapping("/api")
    public String testApi(Model model) throws IOException {

        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B550928/dissForecastInfoSvc/getDissForecastInfo"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=AU%2BlOSJ7rm7d%2BJNEl42gF48f20yYevVj6mN24iNz3Or4v1FnhgO2a2DGwe96r5mEZNhTGpaoYNoboIN3P0vq7A%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("25", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호, 미입력 시 기본값 1*/
            urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답결과의 출력 방식(xml, json), 미입력시 기본값:xml*/
            urlBuilder.append("&" + URLEncoder.encode("dissCd","UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); /*질병코드*/
            urlBuilder.append("&" + URLEncoder.encode("znCd","UTF-8") + "=" + URLEncoder.encode("11", "UTF-8")); /*시도별 지역코드, 지역코드 입력시 시군구별 데이터 응답/미입력시, 전국 및 시도별 데이터 응답*/
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
            System.out.println(sb.toString());

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
            JSONObject jsonResponseObject = (JSONObject) jsonParser.parse(jsonObject.get("response").toString());
            JSONObject jsonBodyObject = (JSONObject) jsonParser.parse(jsonResponseObject.get("body").toString());
            log.debug("json jsonObject : "+jsonObject.get("response"));
            log.debug("json jsonResponseObject : "+jsonResponseObject.get("body"));
            log.debug("json jsonBodyObject : "+jsonBodyObject.get("items"));
            model.addAttribute("result",jsonBodyObject.get("items"));
        }catch (Exception e){
            log.error("ERROR 발생! ");
        }

        return "test";
    }
}
