package dutyroster.ui.dto;


import dutyroster.ui.validator.MultipartFileUploaded;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by apo on 12.03.2015.
 */
@Data
public class DutyRosterImportFormDto {

    @MultipartFileUploaded
    private MultipartFile file;

    @NotNull
    @Min(0)
    @Max(11)
    private Integer month;

    @NotNull
    @Min(2000)
    @Max(2050)
    private Integer year;

    private Boolean dryRun;

    private Boolean createCsv;
}
