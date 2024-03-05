package com.project.health;

import com.project.health.dto.DiseaseForeCastInfoDto;
import com.project.health.service.DiseaseForeCastInfoService;
import com.project.health.service.RegionCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public String getOpenApi(Model model) throws Exception {

        try {
            /*URL*/
            //
//            model.addAttribute("result", diseaseForeCastInfoService.getAPIList("", ""));
        } catch (NullPointerException e) {
            log.error("NullPointerException 발생!");
        }

        return "DiseaseInfoList";
    }

    /***
     * 검색
     * @param model
     * @param prmZnCd
     * @param prmDissCd
     * @return String
     */
    @PostMapping("/api")
    public String getOpenApi(Model model, @RequestParam("znCd") String prmZnCd, @RequestParam("dissCd") String prmDissCd) throws Exception {
//        log.debug("post 검색 조회 ::: "+prmZnCd);
        try {

            for (int i = 1; i < 5; i++) {
                model.addAttribute("result", diseaseForeCastInfoService.getAPIList(i+"", prmZnCd));
            }
        } catch (NullPointerException e) {
            log.error("NullPointerException 발생!");
        }

        return "DiseaseInfoList";
    }

}
