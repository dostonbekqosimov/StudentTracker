package doston.uz.App.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/forms/teacher-form-update").setViewName("forward:/forms/teacher-form-update.html");
        registry.addViewController("/forms/group-form-update").setViewName("forward:/forms/group-form-update.html");
        registry.addViewController("/forms/student-form-update").setViewName("forward:/forms/student-form-update.html");
        registry.addViewController("/forms/group-form-update").setViewName("forward:/forms/group-form-update.html");
        registry.addViewController("/forms/enrollment-form").setViewName("forward:/forms/enrollment-form.html");
        registry.addViewController("/lists/list-teachers").setViewName("forward:/lists/list-teachers.html");
        registry.addViewController("/lists/list-enrollments").setViewName("forward:/lists/list-enrollments.html");
    }
}