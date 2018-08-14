package com.itschool.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * springMVC配置
 * @author maming
 * @date 2018/7/30
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	@Value("${web.upload-path}")
	private String path;

	
	
	/**
	 * 文件上传相关配置
	 * @return
	 */
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		// 允许最大上传10M
		multipartResolver.setMaxUploadSize(10 * 1024 * 1024);
		return multipartResolver;
	}
	
	
	
	/**
	 * 重写静态资源访问
	 */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + path + "ueditor/");
        super.addResourceHandlers(registry);
    }
	
	
	/**
	 *	拦截器配置
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		super.addInterceptors(registry);
	}
	
	
	/**
	 * 配置跳转视图
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("/index");
		registry.addViewController("/list").setViewName("/list");
		registry.addViewController("/vip").setViewName("/vip");
		registry.addViewController("/faq").setViewName("/faq");
		registry.addViewController("/faq_detail").setViewName("/faq_detail");
		registry.addViewController("/course").setViewName("/course");
		registry.addViewController("/ucenter").setViewName("/ucenter");
		registry.addViewController("/myInfo").setViewName("/my_info");
		registry.addViewController("/resetpwd").setViewName("/resetpwd");
	}
	
	
	@Bean
	StringHttpMessageConverter stringHttpMessageConverter(){
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(new MediaType("text", "plain", Charset.forName("UTF-8")));
		supportedMediaTypes.add(new MediaType("text", "html", Charset.forName("UTF-8")));
		supportedMediaTypes.add(new MediaType("application", "json", Charset.forName("UTF-8")));
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		stringHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
		return stringHttpMessageConverter;
	}
	
	
	/**
	 * 重载HttpMessageConverter
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(stringHttpMessageConverter());
	}
	
}
