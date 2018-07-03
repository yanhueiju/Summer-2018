package com.zyh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyh.po.Info;
import com.zyh.service.InfoService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

@Controller
@RequestMapping("/info")
public class InfoController {
	@Autowired
	private InfoService infoService;
	
	@RequestMapping("/add")
	public String addInfo(Info info, HttpServletRequest request, HttpServletResponse response) {
		String info_category = request.getParameter("info_category");
		info.setInfo_category(info_category);
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String time = dateFormat.format(date);
		info.setInfo_time(time);
		info.setInfo_status(1);
		infoService.addInfo(info);
		return "pages/info_manager/article-list";
	}
	
	@RequestMapping("/update")
	public String updateInfo(Info info, HttpServletRequest request, HttpServletResponse response) {
		String info_category = request.getParameter("info_category");
		info.setInfo_category(info_category);
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String time = dateFormat.format(date);
		info.setInfo_time(time);
		info.setInfo_status(1);
		String item = request.getParameter("info_id");
		int info_id = Integer.parseInt(item);
		info.setInfo_id(info_id);
//		System.out.println(info.toString());
		infoService.updateInfo(info);
		return "pages/inf_manager/article-list";
	}
	
	@RequestMapping("/all")
	public String findAllInfo(Model model) {
		List<Info> infos = infoService.findAllInfo();
//		toExcel(infos);
		int infoCount = infos.size();
		model.addAttribute("infoCount", infoCount);
		model.addAttribute("infos", infos);
		return "pages/info_manager/article-list";
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
		HashMap<String, String> info = new HashMap<>();
		info.put("info_category", info_category);
		info.put("begin", begin);
		info.put("end", end);
		info.put("info_title", "%"+info_title+"%");
		List<Info> infos = infoService.fuzzyInfo(info);
		int infoCount = infos.size();
		model.addAttribute("infoCount", infoCount);
		model.addAttribute("infos", infos);
//		System.out.println(infos);
		return "pages/info_manager/article-list";
	}
	
	@RequestMapping("/toExcel")
	public void exportToExcel(HttpServletRequest request, HttpServletResponse response) {
		List<Info> infos = new ArrayList<>();
		String items = request.getParameter("delitems");
		String[] strs = items.split(",");
 
		for (int i = 0; i < strs.length; i++) {
			try {
				Info info = infoService.findInfoById(strs[i]);
				infos.add(info);
				} catch (Exception e) {
			}
		}
		toExcel(infos);
	}
	
	public void toExcel(List<Info> infos) {
		//第一步，创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("信息表一");
        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，设置表头
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("标题");
        cell = row.createCell(2);
        cell.setCellValue("内容");
        cell = row.createCell(3);
        cell.setCellValue("信息分类");
        cell = row.createCell(4);
        cell.setCellValue("创建时间");
        cell = row.createCell(5);
        cell.setCellValue("发布状态");
        //第五步，写入实体数据，实际应用中这些数据从数据库得到,对象封装数据，集合包对象。对象的属性值对应表的每行的值
        for (int i = 0; i < infos.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            Info info = infos.get(i);
            //创建单元格设值
            row1.createCell(0).setCellValue(info.getInfo_id());
            row1.createCell(1).setCellValue(info.getInfo_title());
            row1.createCell(2).setCellValue(info.getInfo_detail());
            row1.createCell(3).setCellValue(info.getInfo_category());
            row1.createCell(4).setCellValue(info.getInfo_time());
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
//		      System.out.println(f.getAbsolutePath());
		      
		      /*f.createNewFile();
		      FileOutputStream out = new FileOutputStream(f);

		      out.write("aaaaaaaaaaaaaaaaa".getBytes());
		      out.close();*/
		      
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
				infoService.deleteInfoById(a);
				} catch (Exception e) {
			}
		}
		return "pages/info_manager/article-list";
	}
	
	@RequestMapping("/deleteInfoById")
	public String deleteInfoById(HttpServletRequest request, HttpServletResponse response) {
		String item = request.getParameter("delitems");
		int id = Integer.parseInt(item);
		infoService.deleteInfoById(id);
		return "pages/info_manager/article-list";
	}
	
	@RequestMapping("/findInfoById")
	public String findInfoById(String id, Model model) {
		Info info = infoService.findInfoById(id);
		model.addAttribute("info", info);
		return "pages/info_manager/article-update";
		
	}
	
	
	
}
