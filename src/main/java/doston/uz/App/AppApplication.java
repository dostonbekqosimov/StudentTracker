package doston.uz.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@EnableWebSecurity

public class AppApplication {

	@Bean
	public FilterRegistrationBean<HiddenHttpMethodFilter> hiddenHttpMethodFilter() {
		FilterRegistrationBean<HiddenHttpMethodFilter> filterRegBean = new FilterRegistrationBean<>();
		filterRegBean.setFilter(new HiddenHttpMethodFilter());
		return filterRegBean;
	}

	public static void main(String[] args) {

		SpringApplication.run(AppApplication.class, args);
		System.out.println("Working perfectly...");
	}

}
