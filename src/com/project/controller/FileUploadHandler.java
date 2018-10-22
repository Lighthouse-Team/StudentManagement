package com.project.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.project.beans.User;
import com.project.service.StudentCourseService;
import com.project.tools.InsertIntoDB;

@Controller
public class FileUploadHandler {

	@Autowired
	private StudentCourseService studentCourseService;

	@RequestMapping("/redirectUploadPage")
	public String redirectUploadPage(HttpSession session) {

		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login.jsp";
		}

		return "upload";
	}

	// @RequestMapping("/fileUpload")
	// public String fileUpload(HttpSession session, @RequestParam("file")
	// CommonsMultipartFile file,
	// HttpServletRequest request) throws IOException {
	//
	// User user = (User) session.getAttribute("user");
	// if (user == null) {
	// return "redirect:/login.jsp";
	// }
	//
	// if(file == null || file.getOriginalFilename().equals("")) {
	// System.out.println("请选择文件！");
	// return "index";
	// }
	//
	// // 获取项目目录作为上传文件的目录
	// String strUploadPath = System.getProperty("user.dir");
	// strUploadPath = strUploadPath + File.separator + "uploadFile";
	// System.out.println("uploadPath:" + strUploadPath);
	//
	// // 判断存放文件的目录是否存在，如果不存在，创建目录
	// File uploadPath = new File(strUploadPath);
	// if (!uploadPath.exists()) {
	// uploadPath.mkdirs();
	// }
	//
	// // 得到需要上传的文件
	// String strUploadFile = strUploadPath + File.separator +
	// file.getOriginalFilename();
	// File uploadFile = new File(strUploadFile);
	// if (uploadFile.exists()) {
	// System.out.println("文件" + uploadFile.getName() + "已存在！");
	// } else {
	// // 通过CommonsMultipartFile的方法直接写文件
	// file.transferTo(uploadFile);
	// System.out.println("写入文件成功！");
	//
	// // 通过获得文件中的学期和数据库中的学期比较，判断文件是否已经被导入数据库
	// boolean insertFlag =
	// studentCourseService.isFileInsertedByFilePath(uploadFile.getPath());
	// if (insertFlag == true) {
	// System.out.println("文件" + uploadFile.getName() + "已经被导入数据库或文件存在错误，请尝试重新上传！");
	// } else {
	// System.out.println("正在导入数据库，请等待！");
	// // new InsertIntoDB().Insert(uploadFile.getPath());
	// System.out.println("导入操作暂时被注释，导入完成！");
	// }
	//
	// // 导入成功后，删除上传的文件
	// File deleteFile = new File(strUploadFile);
	// boolean deleteFlag = deleteFile.delete();
	// if(deleteFlag) {
	// System.out.println("删除成功！");
	// }else {
	// System.out.println("删除失败！");
	// }
	// }
	//
	// return "index";
	// }

	@RequestMapping("/fileUpload")
	public String fileUpload(HttpSession session, @RequestParam("files") CommonsMultipartFile[] files,
			HttpServletRequest request) throws IOException {
		// 实现最多4个成绩文件同时上传，正好是一个学期的4个年级

		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login.jsp";
		}

		if (files == null) {
			System.out.println("请选择文件！");
			return "index";
		}
		if (files[0].getOriginalFilename().equals("") && files[1].getOriginalFilename().equals("")
				&& files[2].getOriginalFilename().equals("") && files[3].getOriginalFilename().equals("")) {
			System.out.println("请选择文件！");
			return "index";
		}

		for (CommonsMultipartFile file : files) {
			if (file == null || file.getOriginalFilename().equals("")) {
				continue;
			}

			// 获取项目目录作为上传文件的目录
			String strUploadPath = System.getProperty("user.dir");
			strUploadPath = strUploadPath + File.separator + "uploadFile";
			System.out.println("uploadPath:" + strUploadPath);

			// 判断存放文件的目录是否存在，如果不存在，创建目录
			File uploadPath = new File(strUploadPath);
			if (!uploadPath.exists()) {
				uploadPath.mkdirs();
			}

			// 得到需要上传的文件
			String strUploadFile = strUploadPath + File.separator + file.getOriginalFilename();
			File uploadFile = new File(strUploadFile);
			if (uploadFile.exists()) {
				System.out.println("文件" + uploadFile.getName() + "已存在！");
			} else {
				// 通过CommonsMultipartFile的方法直接写文件
				file.transferTo(uploadFile);
				System.out.println("写入文件成功！");

				// 通过获得文件中的学期和数据库中的学期比较，判断文件是否已经被导入数据库
				boolean insertFlag = studentCourseService.isFileInsertedByFilePath(uploadFile.getPath());
				if (insertFlag == true) {
					System.out.println("文件" + uploadFile.getName() + "已经被导入数据库或文件存在错误，请尝试重新上传！");
				} else {
					System.out.println("正在导入数据库，请等待！");
					// new InsertIntoDB().Insert(uploadFile.getPath());
					System.out.println("导入操作暂时被注释，导入完成！");
				}

				// 导入成功后，删除上传的文件
				File deleteFile = new File(strUploadFile);
				boolean deleteFlag = deleteFile.delete();
				if (deleteFlag) {
					System.out.println("删除成功！");
				} else {
					System.out.println("删除失败！");
				}
			}
		}

		return "index";
	}
	
	@RequestMapping("/retToMain")
	public String retToMain(HttpSession session) {

		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login.jsp";
		}

		return "index";
	}

}
