package xanderpost.entity.validation;

import xanderpost.entity.PostRating;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PostRatingNotAuthorValidator implements ConstraintValidator<PostRatingNotAuthor, PostRating> {
    public void initialize(PostRatingNotAuthor constraint) {
    }

    public boolean isValid(PostRating rating, ConstraintValidatorContext context) {
        return !(rating.getUser()
                .equals(rating
                        .getPost()
                        .getAuthor()));
    }
}
