package org.great;

import org.great.datasource.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class, MultipartAutoConfiguration.class })
@SpringBootApplication
@Import({ DynamicDataSourceRegister.class }) // 注册动态多数据源
@MapperScan("org.great.web.mapper")
// 扫描：该包下相应的class,主要是MyBatis的持久化类.
public class App extends WebMvcConfigurerAdapter {

	// @Bean
	// @ConfigurationProperties(prefix = "spring.datasource")
	// public DataSource dataSource() {
	// return new org.apache.tomcat.jdbc.pool.DataSource();
	// }
	//
	// @Bean
	// public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
	//
	// SqlSessionFactoryBean sqlSessionFactoryBean = new
	// SqlSessionFactoryBean();
	// sqlSessionFactoryBean.setDataSource(dataSource());
	//
	// PathMatchingResourcePatternResolver resolver = new
	// PathMatchingResourcePatternResolver();
	//
	// sqlSessionFactoryBean.setMapperLocations(resolver
	// .getResources("classpath:/mybatis/*.xml"));
	//
	// return sqlSessionFactoryBean.getObject();
	// }

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	// 显示声明CommonsMultipartResolver为mutipartResolver
	@Bean(name = "multipartResolver")
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setResolveLazily(true);// resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
		resolver.setMaxInMemorySize(1024 * 6);
		// resolver.setMaxUploadSize(50 * 1024 * 1024 * 1024);// 上传文件大小 50M
		System.out.println("MultipartResolver初始化");
		return resolver;
	}
}
