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

import com.zyh.po.Admin;
import com.zyh.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/add")
	public String addAdmin(Admin admin, HttpServletRequest request, HttpServletResponse response) {
//		response.setHeader("Content-type", "text/html;charset=UTF-8");
		String gender = (String) request.getParameter("admin_gender");
		String role = (String) request.getParameter("admin_role");
		admin.setAdmin_gender(gender);
		admin.setAdmin_role(role);
		admin.setCtime(new Date());
		adminService.addAdmin(admin);
		return "pages/admin_manager/admin-list";
	}
	
	@RequestMapping("/update")
	public String updateAdmin(Admin admin, HttpServletRequest request, HttpServletResponse response) {
		String gender = (String) request.getParameter("admin_gender");
		String role = (String) request.getParameter("admin_role");
		admin.setAdmin_gender(gender);
		admin.setAdmin_role(role);
		admin.setCtime(new Date());
		String item = request.getParameter("admin_id");
		int id = Integer.parseInt(item);
		admin.setAdmin_id(id);
		adminService.updateAdmin(admin);
		return "pages/admin_manager/admin-list";
	}
	
	@RequestMapping("/all")
	public String findAllAdmin(Model model) {
		List<Admin> admins = adminService.findAllAdmin();
		int adminCount = admins.size();
		model.addAttribute("adminCount", adminCount);
		model.addAttribute("admins", admins);
		return "pages/admin_manager/admin-list";
	}
	
	@RequestMapping("/fuzzyInfo")
	public String fuzzyInfo(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		String time = request.getParameter("info_date");
		String begin = "";
		String end = "";
		if(time!="") {
			String[] part = time.split(" ");
			begin = part[0]+" "+part[1];
			end = part[3]+" "+part[4];
		}
		
		String likeName = request.getParameter("likeName");
//		System.out.println(info_category +" "+ begin +" "+ end +" "+ info_title);
		HashMap<String, String> admin = new HashMap<>();
		admin.put("begin", begin);
		admin.put("end", end);
		admin.put("likeName", "%"+likeName+"%");
		List<Admin> admins = adminService.fuzzyInfo(admin);
//		List<Tax> infos = taxService.fuzzyInfo("%"+info_category+"%", begin, end, info_title);
		int adminCount = admins.size();
		model.addAttribute("adminCount", adminCount);
		model.addAttribute("admins", admins);
		return "pages/admin_manager/admin-list";
	}
	
	@RequestMapping("/toExcel")
	public void exportToExcel(HttpServletRequest request, HttpServletResponse response) {
		List<Admin> admins = new ArrayList<>();
		String items = request.getParameter("delitems");
		String[] strs = items.split(",");
 
		for (int i = 0; i < strs.length; i++) {
			try {
				Admin admin = adminService.findAdminById(strs[i]);
				admins.add(admin);
				} catch (Exception e) {
			}
		}
		toExcel(admins);
	}
	
	public void toExcel(List<Admin> admins) {
		//第一步，创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("管理员表一");
        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，设置表头
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("登录名");
        cell = row.createCell(2);
        cell.setCellValue("手机");
        cell = row.createCell(3);
        cell.setCellValue("邮箱");
        cell = row.createCell(4);
        cell.setCellValue("角色");
        cell = row.createCell(5);
        cell.setCellValue("加入时间");

        //第五步，写入实体数据，实际应用中这些数据从数据库得到,对象封装数据，集合包对象。对象的属性值对应表的每行的值
        for (int i = 0; i < admins.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            Admin admin = admins.get(i);
            //创建单元格设值
            Date ctime = admin.getCtime();
            SimpleDateFormat f= new SimpleDateFormat("yyyy年MM月dd日 E kk时mm分ss秒");
            String t = f.format(ctime);
            row1.createCell(0).setCellValue(admin.getAdmin_id());
            row1.createCell(1).setCellValue(admin.getAdmin_name());
            row1.createCell(2).setCellValue(admin.getAdmin_phone());
            row1.createCell(3).setCellValue(admin.getAdmin_email());
            row1.createCell(4).setCellValue(admin.getAdmin_role());
            row1.createCell(5).setCellValue(t);
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
				adminService.deleteAdminById(a);
				} catch (Exception e) {
			}
		}
		return "pages/admin_manager/admin-list";
	}
	
	@RequestMapping("/deleteAdminById")
	public String deleteAdminById(HttpServletRequest request, HttpServletResponse response) {
		String item = request.getParameter("delitems");
		int id = Integer.parseInt(item);
		adminService.deleteAdminById(id);
		return "pages/admin_manager/admin-list";
	}
	
	@RequestMapping("/findAdminById")
	public String findAdminById(String id, Model model) {
		Admin admin = adminService.findAdminById(id);
		model.addAttribute("admin", admin);
		return "pages/admin_manager/admin-update";
		
	}
}
