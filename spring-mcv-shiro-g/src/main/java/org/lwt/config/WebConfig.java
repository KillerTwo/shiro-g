package org.lwt.config;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.transform.Source;

import org.lwt.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * springMVC��������
 * 
 * 
 * 
 * @author Administrator
 *
 */
@Configuration
@EnableWebMvc     // ����mvc
@ComponentScan(basePackages= {"org.lwt.controller"})    // ɨ��ָ�����µ��ཫ��ע��Ϊspring��һ��bean
public class WebConfig extends WebMvcConfigurerAdapter {
  
  // ��ͼ������
  @Bean
  public ViewResolver viewResolver(){
      InternalResourceViewResolver resolver =
              new InternalResourceViewResolver();

      resolver.setPrefix("/WEB-INF/");
      resolver.setSuffix(".jsp");
      resolver.setExposeContextBeansAsAttributes(true);

      return resolver;
  }

  /**
   * me �����Ϣת����
   */
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
        .indentOutput(true)
        .dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        //.modulesToInstall(new ParameterNamesModule());
    
    converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    converters.add(new MappingJackson2XmlHttpMessageConverter(builder.createXmlMapper(true).build()));
    // Ĭ�ϵ���Ϣת��������������������ת������Ĭ�ϵ�ת���������ᱻ��ӡ�
    converters.add(new ByteArrayHttpMessageConverter());
    converters.add(new StringHttpMessageConverter());
    converters.add(new SourceHttpMessageConverter<Source>());
    converters.add(new AllEncompassingFormHttpMessageConverter());
  }

  /**
   * in �������������
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new MyInterceptor())
    .addPathPatterns("/**")             // ��������·��
    .excludePathPatterns("/login");     // �ų�����·��
  }

  // ���þ�̬��Դ�Ĵ���
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    super.configureDefaultServletHandling(configurer);
  }
}
