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
     * @return String
     */
    @GetMapping("/api")
    public String getOpenApi(Model model) {

        try {

            /*URL*/
            List<Map<String, Object>> itemList = diseaseForeCastInfoService.getAPIList("","");

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

    /***
     * 검색
     * @param model
     * @param prmZnCd
     * @param prmDissCd
     * @return String
     */
    @PostMapping("/api")
    public String getOpenApi (Model model, @RequestParam("znCd") String prmZnCd, @RequestParam("dissCd") String prmDissCd){
        log.debug("test2 ::: "+prmZnCd);
        try {
            /*URL*/
            List<Map<String, Object>> itemList = diseaseForeCastInfoService.getAPIList(prmDissCd,prmZnCd);
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
