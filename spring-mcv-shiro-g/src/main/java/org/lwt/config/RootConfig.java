package org.lwt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * spring ������������
 * 
 * @author Administrator
 *
 */
@Configuration
// ɨ��org.lwt���µ��ཫ��ע��Ϊspring��Bean,���Ҳ�ɨ�����@EnableWebMvcע�����
@ComponentScan( basePackages={"org.lwt"}, 
               excludeFilters = { @Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)}
           )
public class RootConfig {

}
