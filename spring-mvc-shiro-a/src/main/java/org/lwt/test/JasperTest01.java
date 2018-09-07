package org.lwt.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterContext;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class JasperTest01 {

  public static void main(String[] args) throws Exception {
    JasperTest01 jp = new JasperTest01();
//    jp.testCompile();
//    jp.testFill();
    //jp.testExportToExcel();
    //jp.testExportToPdf();
    jp.testExportToHtml();
  }

  public void testCompile() throws Exception {
    String path = getClass().getClassLoader().getResource("jasper/").getPath();
    path = path.substring(1, path.length());
    System.out.println(path);
    JasperCompileManager.compileReportToFile(path+"Demo01.jrxml",path+"Demo01.jasper");
    //System.out.println(getClass().getClassLoader().getResource("jasper/Demo01.jasper").getPath());
    
  }

  public void testFill() throws Exception {
    String path = getClass().getClassLoader().getResource("jasper/").getPath();
    path = path.substring(1, path.length());
    Map<String, Object> map = new HashMap<>();
    map.put("title", "动态标题");
    JasperFillManager.fillReportToFile(path+"Demo01.jasper", map, getConnection());
  }

  private Connection getConnection() throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees?useSSL=false&serverTimezone=UTC&nullCatalogMeansCurrent=true",
        "root", "123456root");
    return con;
  }
  
  public void testExportToExcel() throws Exception {    
    String path = getClass().getClassLoader().getResource("jasper/").getPath();
    path = path.substring(1, path.length());
    /*JasperPrint jp = (JasperPrint) JRLoader.loadObjectFromFile(path+"Demo01.jrprint");    
    JRPdfExporter exporter = new JRPdfExporter();     //指定要导出的jrprit数据    
    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);     //指定导出文件的文件名  
    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path+"test.xls");     //实现报表的导出     
    exporter.exportReport();*/ 
    JasperPrint jp = (JasperPrint) JRLoader.loadObjectFromFile(path+"Demo01.jrprint");    
    JRXlsExporter exporter = new JRXlsExporter();    
    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);    
    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path+"test.xls");    
    exporter.exportReport(); 
  }
  public void testExportToPdf() throws Exception {
    String path = getClass().getClassLoader().getResource("jasper/").getPath();
    path = path.substring(1, path.length());
    JasperPrint jp = (JasperPrint) JRLoader.loadObjectFromFile(path+"Demo01.jrprint");    
    JRPdfExporter exporter = new JRPdfExporter();     //指定要导出的jrprit数据    
    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);     //指定导出文件的文件名  
    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path+"test.pdf");     //实现报表的导出     
    exporter.exportReport();
  }
  public void testExportToHtml() throws Exception { 
    String path = getClass().getClassLoader().getResource("jasper/").getPath();
    path = path.substring(1, path.length());
    
    JasperPrint jp = (JasperPrint) JRLoader.loadObjectFromFile(path+"Demo01.jrprint");    
    HtmlExporter exporter = new HtmlExporter();    
    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);    
    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path+"test.html");    
    exporter.exportReport(); 
  }
  
  public void testExportToWord() throws Exception { 
    String path = getClass().getClassLoader().getResource("jasper/").getPath();
    path = path.substring(1, path.length());
    
    JasperPrint jp = (JasperPrint) JRLoader.loadObjectFromFile(path+"Demo01.jrprint");    
    JRDocxExporter exporter = new JRDocxExporter();     
    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);    
    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path+"test.doc");    
    exporter.exportReport(); 
  }
}
