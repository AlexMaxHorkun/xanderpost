package xanderpost.entity.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import xanderpost.entity.Post;

public class PostValidator implements Validator{
    public boolean supports(Class c){
        return Post.class.isAssignableFrom(c);
    }

    public void validate(Object o, Errors e){
        Post post=(Post)o;
        ValidationUtils.rejectIfEmpty(e,"title","title.empty");
        ValidationUtils.rejectIfEmpty(e,"text","text.empty");
        if(post.getTitle().length()>255){
            e.rejectValue("title","title.too_long");
        }
        if(post.getTitle().length()<3){
            e.rejectValue("title","title.too_short");
        }
        if(post.getText().length()>4000){
            e.rejectValue("text","text.too_long");
        }
        if(post.getText().length()<3){
            e.rejectValue("text","text.too_short");
        }
    }
}
