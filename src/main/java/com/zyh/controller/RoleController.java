package com.zyh.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.zyh.po.Role;
import com.zyh.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/add")
	public String addRole(Role role) {
		
		roleService.addRole(role);
		return "pages/admin_manager/admin-role-add";
	}
	
	@RequestMapping("/update")
	public String updateRole(Role role, HttpServletRequest request, HttpServletResponse response) {
		Date date = new Date();
		role.setCreateDate(date);
		String item = request.getParameter("roleId");
		int roleId = Integer.parseInt(item);
		role.setRoleId(roleId);
//		System.out.println(info.toString());
		roleService.updateRole(role);
		return "pages/inf_manager/article-list";
	}
	
	@RequestMapping("/all")
	public String findAllRole(Model model) {
		List<Role> roles = roleService.findAllRole();
		int roleCount = roles.size();
		model.addAttribute("roleCount", roleCount);
		model.addAttribute("roles", roles);
		return "pages/admin_manager/admin-role";
	}
	
	@RequestMapping("/toExcel")
	public void exportToExcel(HttpServletRequest request, HttpServletResponse response) {
		List<Role> roles = new ArrayList<>();
		String items = request.getParameter("delitems");
		String[] strs = items.split(",");
 
		for (int i = 0; i < strs.length; i++) {
			try {
				Role role = roleService.findRoleById(strs[i]);
				roles.add(role);
				} catch (Exception e) {
			}
		}
		toExcel(roles);
	}
	
	public void toExcel(List<Role> roles) {
		//第一步，创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("角色表一");
        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，设置表头
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("Id");
        cell = row.createCell(1);
        cell.setCellValue("角色名");
        cell = row.createCell(2);
        cell.setCellValue("描述");
        cell = row.createCell(3);
        cell.setCellValue("创建时间");
        //第五步，写入实体数据，实际应用中这些数据从数据库得到,对象封装数据，集合包对象。对象的属性值对应表的每行的值
        for (int i = 0; i < roles.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            Role role = roles.get(i);
            Date ctime = role.getCreateDate();
            SimpleDateFormat f= new SimpleDateFormat("yyyy年MM月dd日 E kk时mm分ss秒");
            String t = f.format(ctime);
            //创建单元格设值
            row1.createCell(0).setCellValue(role.getRoleId());
            row1.createCell(1).setCellValue(role.getRoleName());
            row1.createCell(2).setCellValue(role.getRoleDescription());
            row1.createCell(3).setCellValue(t);
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
				roleService.deleteRoleById(a);
				} catch (Exception e) {
			}
		}
		return "pages/admin_manager/admin-role";
	}
	
	@RequestMapping("/deleteRoleById")
	public String deleteRoleById(HttpServletRequest request, HttpServletResponse response) {
		String item = request.getParameter("delitems");
//		System.out.println(item);
		int id = Integer.parseInt(item);
		roleService.deleteRoleById(id);
		return "pages/admin_manager/admin-role";
	}
	
	@RequestMapping("/findRoleById")
	public String findRoleById(String id, Model model) {
		Role role = roleService.findRoleById(id);
		model.addAttribute("role", role);
		return "pages/admin_manager/admin-role-update";
		
	}
}
