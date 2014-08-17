package xanderpost.entity.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PostRatingNotAuthorValidator.class)
public @interface PostRatingNotAuthor {
    String message() default "{xanderpost.post_rating.user.is_author}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
