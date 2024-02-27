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
    public String getOpenApi(Model model) {

        try {
            /*URL*/
            List<DiseaseForeCastInfoDto> itemList = diseaseForeCastInfoService.getAPIList("","");
            String prmZnCdNm= itemList.get(0).getZnCdNm();
            //
            model.addAttribute("result", itemList);
            model.addAttribute("region", regionCodeService.getRegionCodes(prmZnCdNm));
        } catch (Exception e) {
            log.error("ERROR 발생!");
            e.printStackTrace();
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
    public String getOpenApi (Model model, @RequestParam("znCd") String prmZnCd, @RequestParam("dissCd") String prmDissCd){
        log.debug("post 검색 조회 ::: "+prmZnCd);
        try {
            model.addAttribute("result", diseaseForeCastInfoService.getAPIList(prmDissCd,prmZnCd));
            model.addAttribute("region", regionCodeService.getRegionCodes(prmZnCd));
        } catch (Exception e) {
            log.error("ERROR 발생!");
            e.printStackTrace();
        }

        return "DiseaseInfoList";
    }

}
