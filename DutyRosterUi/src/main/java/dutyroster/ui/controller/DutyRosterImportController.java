package dutyroster.ui.controller;

import dutyroster.ui.dto.DutyRosterImportFormDto;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Calendar;

/**
 * Simple controller for the page "importForm.jsp".
 */
@Controller
public class DutyRosterImportController {

    private final Logger log = org.slf4j.LoggerFactory.getLogger(DutyRosterImportController.class);

    @RequestMapping(value = "/importForm", method = RequestMethod.HEAD)
    public ModelAndView head() throws IOException {
        return get();
    }

    @RequestMapping(value = "/importForm", method = RequestMethod.GET)
    public ModelAndView get() throws IOException {
        ModelAndView mav = new ModelAndView("importForm");
        mav.addObject("dutyRosterUploadForm", createFormDto());
        return mav;
    }

    @RequestMapping(value = "/importForm", method = RequestMethod.POST)
    public String importDutyRoster(HttpSession session,
                                   @Valid @ModelAttribute("dutyRosterUploadForm") DutyRosterImportFormDto formDto,
                                   BindingResult bindingResult) {

        log.debug("form: [{}]", formDto);

        if (bindingResult.hasErrors()) {
            log.debug("form has errors");
            return "importForm";
        }

        // TODO
        log.error("TODO....");

        session.setAttribute("formDto", formDto);
        session.setAttribute("importResult", "not implemented");

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


