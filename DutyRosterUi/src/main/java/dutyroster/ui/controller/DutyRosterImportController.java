package dutyroster.ui.controller;

import dutyroster.ui.dto.DutyRosterImportFormDto;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple controller for the page "importForm.jsp".
 */
@Controller
public class DutyRosterImportController {

    private static final String API_URL = "http://localhost:8089/api/";
    private static final String PING_URL = API_URL + "ping";
    private static final String SERVICE_URL = API_URL + "convertAndImport/{originalFilename}/{year}/{month}/{dryRun}/{createCsv}";

    private final Logger log = org.slf4j.LoggerFactory.getLogger(DutyRosterImportController.class);

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/importForm", method = RequestMethod.HEAD)
    public ModelAndView head() {
        return get();
    }

    @RequestMapping(value = "/importForm", method = RequestMethod.GET)
    public ModelAndView get() {
        ModelAndView mav = new ModelAndView("importForm");
        mav.addObject("dutyRosterUploadForm", createFormDto());
        return mav;
    }

    @RequestMapping(value = "/importForm", method = RequestMethod.POST)
    public String importDutyRoster(HttpSession session,
                                   @Valid @ModelAttribute("dutyRosterUploadForm") DutyRosterImportFormDto formDto,
                                   BindingResult bindingResult) throws Exception {

        log.debug("form: [{}]", formDto);

        if (bindingResult.hasErrors()) {
            log.debug("form has errors");
            return "importForm";
        }

        String pong = restTemplate.getForObject(PING_URL, String.class);
        log.info("ping? [{}]", pong);

        File tempFile = File.createTempFile("dutyrosterimport_", ".docx");
        formDto.getFile().transferTo(tempFile);
        tempFile.deleteOnExit();

        log.info("file size: [{}]", formDto.getFile().getSize());
        log.info("file copied to temp file [{}]", tempFile.getAbsolutePath());

        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("originalFilename", formDto.getFile().getOriginalFilename());
        requestParams.put("year", formDto.getYear());
        requestParams.put("month", formDto.getMonth());
        requestParams.put("dryRun", formDto.getDryRun());
        requestParams.put("createCsv", formDto.getCreateCsv());

        String result = restTemplate.postForObject(SERVICE_URL, tempFile.getAbsolutePath(), String.class, requestParams);

        session.setAttribute("formDto", formDto);
        result = StringEscapeUtils.escapeHtml(result);
        result = StringUtils.replace(result, "\n", "<br/>");
        session.setAttribute("importResult", result);

        return "redirect:/importResult";
    }

    @RequestMapping(value = "/importResult")
    public String importResult() {
        return "importResult";
    }

    private DutyRosterImportFormDto createFormDto() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        if (month == Calendar.DECEMBER) {
            month = Calendar.JANUARY;
            year++;
        }

        DutyRosterImportFormDto formDto = new DutyRosterImportFormDto();
        formDto.setDryRun(true);
        formDto.setCreateCsv(false);
        formDto.setYear(year);
        formDto.setMonth(month);

        return formDto;
    }

}


