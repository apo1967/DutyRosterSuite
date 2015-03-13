package dutyroster.ui.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Custom validator that checks, if a {@link org.springframework.web.multipart.MultipartFile} has been chosen for upload.
 * <p>
 * Attention: needs a custom validation message bundle in the class path,
 * see <a href="http://docs.jboss.org/hibernate/validator/4.0.1/reference/en/html/validator-usingvalidator.html#section-message-interpolation">Hibernate message interpolation</a>.
 * <p>
 * Created by apo on 13.03.2015.
 */

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {MultipartFileUploadedValidator.class})
public @interface MultipartFileUploaded {

    String message() default "{dutyroster.ui.validator.MultipartFileUploaded.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
