package com.example.demo.validator;

import com.example.demo.entity.Article;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class ArticleValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Article.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Article b = (Article) obj;
        if(StringUtils.isEmpty(b.getTitle())){
            errors.rejectValue("title","key","제목을 입력하세요.");
        }
        if(StringUtils.isEmpty(b.getContent())){
            errors.rejectValue("content","key","내용을 입력하세요.");
        }
        if(StringUtils.isEmpty(b.getAuthor())){
            errors.rejectValue("content","key","작성자를 입력하세요.");
        }
    }
}
