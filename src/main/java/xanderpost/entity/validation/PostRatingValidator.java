package xanderpost.entity.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import xanderpost.entity.PostRating;

public class PostRatingValidator implements Validator {
    public boolean supports(Class c) {
        return PostRating.class.isAssignableFrom(c);
    }

    public void validate(Object o, Errors e) {
        PostRating rating=(PostRating)o;
        if(rating.getUser().equals(rating.getPost().getAuthor())){
            e.rejectValue("user","user.is_author");
        }
    }
}
