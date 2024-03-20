package com.project.health;

import com.project.health.dto.DiseaseForeCastInfoDto;
import com.project.health.dto.RegionCodeDto;
import com.project.health.service.DiseaseForeCastInfoService;
import com.project.health.service.RegionCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            // 지역명, 지역코드 가져와기
            model.addAttribute("region", regionCodeService.getRegionCodes());
            // 키워드 default 값 설정
            model.addAttribute("keyword", "서울");
        } catch (NullPointerException e) {
            log.error("NullPointerException 발생!");
        }
        return "DiseaseInfoList";
    }

    /***
     * 검색
     * @param model
     * @param prmZnCd
     * @param prmLowrnkZnCd
     * @return String
     */
    @PostMapping("/api")
    public String getOpenApi(Model model, @RequestParam("znCd") String prmZnCd, @RequestParam("lowrnkZnCd") String prmLowrnkZnCd) throws Exception {
        log.debug("post 검색 조회 ::: "+prmZnCd+", "+prmLowrnkZnCd);
        try {
            List<DiseaseForeCastInfoDto> foreCastInfoDtoList = null;
            for (int i = 1; i < 5; i++) {
                // api 호출, 저장 또는 조회
                foreCastInfoDtoList = diseaseForeCastInfoService.getAPIList(i + "", prmZnCd, prmLowrnkZnCd);
            }
            String keyWord = foreCastInfoDtoList.get(0).getZnCdNm() + " " + foreCastInfoDtoList.get(0).getLowrnkZnCdNm();

            // 셀렉트박스 지역코드, 지역명 가져오기
            model.addAttribute("region", regionCodeService.getRegionCodes());
            // 조회 결과 가져오기
            model.addAttribute("result", foreCastInfoDtoList);
            log.debug("foreCastInfoDtoList :: "+foreCastInfoDtoList.toString());
            // 지역명으로 키워드 적용
            model.addAttribute("keyword", keyWord);
        } catch (NullPointerException e) {
            log.error("NullPointerException 발생!");
        }

        return "DiseaseInfoList";
    }

    /***
     * 카카오맵 표시
     * @param model
     * @return String
     */
    @GetMapping("/map/{dissCdNm}/{znCdNm}/{lowrnkZnCdNm}")
    public String getOpenApiMap(Model model, @PathVariable("dissCdNm") String prmdissCdNm, @PathVariable("znCdNm") String prmznCdNm, @PathVariable("lowrnkZnCdNm") String prmlowrnkZnCdNm) throws Exception {
//        log.debug("prmdissCdNm :: " + prmdissCdNm);
//        log.debug("prmznCdNm :: " + prmznCdNm);
//        log.debug("prmlowrnkZnCdNm :: " + prmlowrnkZnCdNm);
        try {
            // 지역명, 하위지역명 적용한 조회 결과 가져오기
            List<DiseaseForeCastInfoDto> foreCastInfoDtoList = diseaseForeCastInfoService.getDissForeCastInfoListKaKaoMap(prmznCdNm, prmlowrnkZnCdNm);

            // 질병명에 해당하는 병원 적용
            String keyWord = getKakaoMapKeyword(prmdissCdNm, prmznCdNm, prmlowrnkZnCdNm);

            // 셀렉트박스 지역코드, 지역명 가져오기
            model.addAttribute("region", regionCodeService.getRegionCodes());
            model.addAttribute("result", foreCastInfoDtoList);
            model.addAttribute("keyword", keyWord);
            model.addAttribute("mapYn", 1);
        } catch (NullPointerException e) {
            log.error("NullPointerException 발생!");
        }

        return "DiseaseInfoList";
    }

    /***
     * 질병명에 해당하는 병원 적용
     * @param prmdissCdNm
     * @param prmznCdNm
     * @param prmlowrnkZnCdNm
     * @return
     */
    private String getKakaoMapKeyword(String prmdissCdNm, String prmznCdNm, String prmlowrnkZnCdNm) {
        String organization = "";
        switch (prmdissCdNm) {
            case "감기":
                organization = "이비인후과";
                break;
            case "눈병":
                organization = "안과";
                break;
            case "식중독":
                organization = "내과";
                break;
            case "피부염":
                organization = "피부과";
                break;
        }
        return prmznCdNm + " " + prmlowrnkZnCdNm + " " + organization;
    }

    /***
     * AJAX 호출 시 하위지역명 가져오기
     * @param prmZnCd
     * @return String
     */
    @PostMapping("/api/ajax")
    @ResponseBody
    public List<RegionCodeDto> getAjax(@RequestParam("znCd") String prmZnCd) throws Exception {
//        log.debug("getAjax ::: "+prmZnCd);
        return regionCodeService.getLowRegionCodes(prmZnCd);
    }
}
