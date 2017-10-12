package org.great.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		// 登录拦截器
		InterceptorRegistration loginir = registry.addInterceptor(new LoginInterceptor());
		// 拦截路径
		loginir.addPathPatterns("/**");
		// 不拦截路径
		loginir.excludePathPatterns("/**/toNewMain.html");
		loginir.excludePathPatterns("/**/login.html");
		// 不拦截路径(测试)
		loginir.excludePathPatterns("/**/toMain2.html");
		registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
		System.out.println("拦截器初始化");
	}

}
