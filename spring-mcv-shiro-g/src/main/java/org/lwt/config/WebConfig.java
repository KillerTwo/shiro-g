package org.lwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
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
  

  //���þ�̬��Դ�Ĵ���
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    super.configureDefaultServletHandling(configurer);
  }
}
