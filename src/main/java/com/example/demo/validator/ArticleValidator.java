package com.example.demo.validator;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * 서버단에서 유효성검사 사용법 숙지 필요
 */
@Component
public class ArticleValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ArticleForm.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ArticleForm articleForm = (ArticleForm) obj;
        if(StringUtils.isEmpty(articleForm.getTitle())){ //StringUtils 메소드 ieEmpty isBlank
            errors.rejectValue("title","key","제목을 입력하세요.");
        }
        if(StringUtils.isEmpty(articleForm.getContent())){
            errors.rejectValue("content","key","내용을 입력하세요.");
        }
        if(StringUtils.isEmpty(articleForm.getAuthor())){
            errors.rejectValue("author","key","작성자를 입력하세요.");
        }
    }
}
