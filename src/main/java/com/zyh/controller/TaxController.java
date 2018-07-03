package com.zyh.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyh.po.Tax;
import com.zyh.service.TaxService;

@Controller
@RequestMapping("/tax")
public class TaxController {
	@Autowired
	private TaxService taxService;
	
	@RequestMapping("/add")
	public String addTax(Tax tax, HttpServletRequest request, HttpServletResponse response) {
//		response.setHeader("Content-type", "text/html;charset=UTF-8");
		String type = (String) request.getParameter("ttype");
		tax.setCreateDate(new Date());
		tax.setTtype(type);
		tax.setStatus(1);
		taxService.addTax(tax);
		return "pages/info_manager/product-list";
	}
	
	@RequestMapping("/update")
	public String updateTax(Tax tax, HttpServletRequest request, HttpServletResponse response) {
		String ttype = request.getParameter("ttype");
		tax.setTtype(ttype);
		Date date = new Date();
		tax.setCreateDate(date);
		tax.setStatus(1);
		String item = request.getParameter("id");
		int id = Integer.parseInt(item);
		tax.setId(id);
		taxService.updateTax(tax);
		return "pages/inf_manager/product-list";
	}
	
	@RequestMapping("/all")
	public String findAllTax(Model model) {
		List<Tax> taxes = taxService.findAllTax();
		int taxCount = taxes.size();
		model.addAttribute("taxCount", taxCount);
		model.addAttribute("taxes", taxes);
		return "pages/info_manager/product-list";
	}
	
	@RequestMapping("/fuzzyInfo")
	public String fuzzyInfo(Model model, HttpServletRequest request, HttpServletResponse response) {
		String info_category = request.getParameter("info_category");
		String time = request.getParameter("info_date");
		String begin = "";
		String end = "";
		if(time!="") {
			String[] part = time.split(" ");
			begin = part[0]+" "+part[1];
			end = part[3]+" "+part[4];
		}
		String info_title = request.getParameter("info_title");
//		System.out.println(info_category +" "+ begin +" "+ end +" "+ info_title);
		HashMap<String, String> tax = new HashMap<>();
		tax.put("info_category", info_category);
		tax.put("begin", begin);
		tax.put("end", end);
		tax.put("info_title", "%"+info_title+"%");
		List<Tax> taxes = taxService.fuzzyInfo(tax);
//		List<Tax> infos = taxService.fuzzyInfo("%"+info_category+"%", begin, end, info_title);
		int taxCount = taxes.size();
		model.addAttribute("taxCount", taxCount);
		model.addAttribute("taxes", taxes);
		return "pages/info_manager/product-list";
	}
	
	@RequestMapping("/toExcel")
	public void exportToExcel(HttpServletRequest request, HttpServletResponse response) {
		List<Tax> taxes = new ArrayList<>();
		String items = request.getParameter("delitems");
		String[] strs = items.split(",");
 
		for (int i = 0; i < strs.length; i++) {
			try {
				Tax tax = taxService.findTaxById(strs[i]);
				taxes.add(tax);
				} catch (Exception e) {
			}
		}
		toExcel(taxes);
	}
	
	public void toExcel(List<Tax> taxes) {
		//第一步，创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("税务信息表一");
        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，设置表头
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("纳税咨询分类");
        cell = row.createCell(2);
        cell.setCellValue("创建人");
        cell = row.createCell(3);
        cell.setCellValue("创建时间");
        cell = row.createCell(4);
        cell.setCellValue("标题");
        //第五步，写入实体数据，实际应用中这些数据从数据库得到,对象封装数据，集合包对象。对象的属性值对应表的每行的值
        for (int i = 0; i < taxes.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            Tax tax = taxes.get(i);
            Date ctime = tax.getCreateDate();
            SimpleDateFormat f= new SimpleDateFormat("yyyy年MM月dd日 E kk时mm分ss秒");
            String t = f.format(ctime);
            //创建单元格设值
            row1.createCell(0).setCellValue(tax.getId());
            row1.createCell(1).setCellValue(tax.getTtype());
            row1.createCell(2).setCellValue(tax.getUserId());
            row1.createCell(3).setCellValue(t);
            row1.createCell(4).setCellValue(tax.getTitle());
        }
        //将文件保存到指定的位置
        try {
            FileOutputStream fos = new FileOutputStream(fileChooser());
            workbook.write(fos);
//            System.out.println("写入成功");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static String fileChooser() {
		JFileChooser chooser = new JFileChooser();
		   FileNameExtensionFilter filter = new FileNameExtensionFilter("xls","csv");
		   //设置文件类型
		   chooser.setFileFilter(filter);
		   //打开选择器面板
		   int returnVal = chooser.showSaveDialog(new JPanel());  
		                      //保存文件从这里入手，输出的是文件名
		   File f = null;
		   if(returnVal == JFileChooser.APPROVE_OPTION) {
//		      System.out.println("你打开的文件夹是: " +chooser.getSelectedFile().getPath());
		      String path = chooser.getSelectedFile().getPath();
		      
		      f = new File(path+".xls");
		      
		   }
		   return f.getAbsolutePath();
		}
	
	@RequestMapping("/batchDeletes")
	public String batchDeletes(HttpServletRequest request, HttpServletResponse response) {
		String items = request.getParameter("delitems");
		String[] strs = items.split(",");
 
		for (int i = 0; i < strs.length; i++) {
			try {
				int a = Integer.parseInt(strs[i]);
				taxService.deleteTaxById(a);
				} catch (Exception e) {
			}
		}
		return "pages/info_manager/product-list";
	}
	
	@RequestMapping("/deleteTaxById")
	public String deleteTaxById(HttpServletRequest request, HttpServletResponse response) {
		String item = request.getParameter("delitems");
		int id = Integer.parseInt(item);
		taxService.deleteTaxById(id);
		return "pages/info_manager/product-list";
	}
	
	@RequestMapping("/findTaxById")
	public String findTaxById(String id, Model model) {
		Tax tax = taxService.findTaxById(id);
		model.addAttribute("tax", tax);
		return "pages/info_manager/product-update";
		
	}
}
