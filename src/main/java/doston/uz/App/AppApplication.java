package doston.uz.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
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
