package com.project.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.project.beans.User;

@Controller
public class FileUploadHandler {
	
	@RequestMapping("/redirectUploadPage")
	public String redirectUploadPage(HttpSession session) { 
		
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login.jsp";
		}
		
		return "upload";
	}

	@RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
		String uploadPath = "D:/uploadFile";
		
        //String path = "E:/" + file.getOriginalFilename();
        File uploadFile = new File(uploadPath);
		
		if(!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(new File("D:/uploadFile/" + file.getOriginalFilename()));
        return "index"; 
    }

}
