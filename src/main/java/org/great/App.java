package org.great;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration(exclude = { MultipartAutoConfiguration.class })
@SpringBootApplication
@MapperScan("org.great.web.mapper")
// 扫描：该包下相应的class,主要是MyBatis的持久化类.
public class App extends WebMvcConfigurerAdapter {
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);

		/*
		 * 1、需要先定义一个 convert 转换消息的对象; 2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
		 * 3、在convert中添加配置信息. 4、将convert添加到converters当中.
		 */

		// 1、需要先定义一个 convert 转换消息的对象;
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

		// 2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

		// 3、在convert中添加配置信息.
		fastConverter.setFastJsonConfig(fastJsonConfig);

		// 4、将convert添加到converters当中.
		converters.add(fastConverter);
	}

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
		resolver.setMaxInMemorySize(40960);
		resolver.setMaxUploadSize(50 * 1024 * 1024);// 上传文件大小 50M 50*1024*1024
		System.out.println("MultipartResolver");
		return resolver;
	}
}
