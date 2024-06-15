package doston.uz.App.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {



    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/forms/teacher-form-update.html");
        registry.addViewController("/").setViewName("forward:/forms/student-form-update.html");
        registry.addViewController("/").setViewName("forward:/forms/group-form-update.html");


    }


}