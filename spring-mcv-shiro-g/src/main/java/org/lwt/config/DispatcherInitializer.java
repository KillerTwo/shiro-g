package org.lwt.config;

import javax.servlet.Filter;

import org.lwt.filter.MyFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * dipatcherServlet������ã�  ��������web.xml�е�
 * dipatcherServlet��ContextLoadListener�������
 * @author Administrator
 *
 */

public class DispatcherInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    System.out.println("����RootConfig������");
    return new Class[] {RootConfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    
    return new Class[] {WebConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
   
    return new String[] {"/"};
  }
  /**
   *  add ���һ��Filter��dipatcherServlet
   */
  /*@Override
  protected Filter[] getServletFilters() {
    
    return new Filter[] {new MyFilter()};
  }*/
  

}
