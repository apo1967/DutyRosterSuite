package dutyroster.ui.validator;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by apo on 13.03.2015.
 */
public class MultipartFileUploadedValidator implements ConstraintValidator<MultipartFileUploaded, MultipartFile> {

    @Override
    public void initialize(MultipartFileUploaded constraintAnnotation) {
        // nothing to do
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return value != null && !StringUtils.isEmpty(value.getOriginalFilename());
    }
}
