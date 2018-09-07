package org.lwt.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwt.entity.Departments;
import org.lwt.entity.JavaBeanPerson;
import org.lwt.service.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jxl.Workbook;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sun.misc.BASE64Decoder;

@Controller
@RequestMapping("/mvc")
@CrossOrigin("*")
public class DepartmentsController {
  @Autowired
  private DepartmentsService departmentsService;
  
  @RequestMapping(value="/all",method=RequestMethod.GET)
  @ResponseBody
  public Map<String, Object> selectAllDepartments(){
    Map<String, Object> map = new HashMap<>();
    List<Departments> list = departmentsService.selectAll();
    //Departments dept = departmentsService.getOne();
    map.put("msg", "success");
    map.put("results", list);
    return map;
  }
  
  public String report(Model model) {
 // 报表数据源  
    JRDataSource jrDataSource = new JRBeanCollectionDataSource(JavaBeanPerson.getList());  
    // 动态指定报表模板url  
    model.addAttribute("url", "/WEB-INF/jasper/MvcIReportExample.jasper");  
    model.addAttribute("format", "pdf"); // 报表格式  
    model.addAttribute("jrMainDataSource", jrDataSource);  
    return "iReportView";   // 对应jasper-defs.xml中的bean id  
  }
  @RequestMapping(value="/exports", method=RequestMethod.POST)
  @ResponseBody
  public void exportImg(@RequestBody Map<String, String> map) {
    String imgUrl = map.get("imgUrl");
    String enUrl = imgUrl.split(",")[1];
    // 获取到base64加密的图片url，然后导出图片
    System.out.println("base64加密的url地址："+enUrl);
    String path = getClass().getClassLoader().getResource("").getPath();
    path = path.substring(1, path.length());
    System.out.println(path);
    
    
    byte[] bytes;
    WritableWorkbook workBook = null;
    try {
      bytes = new BASE64Decoder().decodeBuffer(enUrl);
      File file = new File("C:\\Users\\Administrator\\Documents\\test\\"+System.currentTimeMillis()+".png");
      try(FileOutputStream out = new FileOutputStream(file)){
        out.write(bytes);
        out.flush();
      }catch(Exception e) {
        e.printStackTrace();
      }
      File excelFile = new File("C:\\Users\\Administrator\\Documents\\test\\"+"test001.xls");
      if(excelFile.exists()) {
        excelFile.createNewFile();
      }
      workBook = Workbook.createWorkbook(
          new FileOutputStream(excelFile));
      WritableSheet sheet = workBook.createSheet("sheet1", 0);
      WritableImage image = new WritableImage(5, 5, 6,6, file);
      sheet.addImage(image);
      workBook.write();
    } catch (IOException e1) {
      e1.printStackTrace();
    }finally {
      if(workBook != null) {
        try {
          workBook.close();
        } catch (WriteException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
   
    System.out.println("保存成功：");
  }
  
}
