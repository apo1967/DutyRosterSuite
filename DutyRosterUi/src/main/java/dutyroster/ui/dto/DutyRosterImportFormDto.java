package dutyroster.ui.dto;


import dutyroster.ui.validator.MultipartFileUploaded;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by apo on 12.03.2015.
 */
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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getDryRun() {
        return dryRun;
    }

    public void setDryRun(Boolean dryRun) {
        this.dryRun = dryRun;
    }

    public Boolean getCreateCsv() {
        return createCsv;
    }

    public void setCreateCsv(Boolean createCsv) {
        this.createCsv = createCsv;
    }

    @Override
    public String toString() {
        return "DutyRosterImportFormDto{" +
                "file=" + (file != null ? file.getOriginalFilename() : file) +
                ", month=" + month +
                ", year=" + year +
                ", dryRun=" + dryRun +
                ", createCsv=" + createCsv +
                '}';
    }
}
