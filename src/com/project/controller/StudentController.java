package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.project.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
}