package com.upskillcareer.StudentManagement.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.upskillcareer.StudentManagement.model.Student;
import com.upskillcareer.StudentManagement.repository.StudentRepository;
import com.upskillcareer.StudentManagement.service.DBSequenceService;

@Controller
public class StudentController {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	DBSequenceService service;
	
	@PostMapping("/save")
	public Student createStudent(@RequestBody Student student) throws Exception {
		student.setId(service.generateSequence("studseq"));
		studentRepository.save(student);
		return student;
	}
	
	@GetMapping("/getAll")
	public List<Student> getAllStudents() {
		
		return studentRepository.findAll();
	}
	
	
	@GetMapping(value = {"/", "/index"})
	public String showUserList(Model model) {
	    model.addAttribute("students", studentRepository.findAll());
	    return "index";
	}
	
	
	  @GetMapping("/signup")
	    public String showSignUpForm(Student user) {
	        return "Add-Student";
	    }
	    
	    @PostMapping("/adduser")
	    public String addUser(Student user, BindingResult result, Model model) throws Exception {
	        if (result.hasErrors()) {
	            return "Add-Student";
	        }
	        createStudent(user);
	       // studentRepository.save(user);
	        return "redirect:/index";
	   }
	    
	    @PostMapping("/edit/{id}")
	    public String EditStudent(@PathVariable(value = "id") Long id, Model model) throws Exception {
	    	//studentRepository.deleteById(id);
	        return "redirect:/index";
	   }
	    
	    @GetMapping("/delete/{id}")
	    public String delStudent(@PathVariable(value = "id") Long id, Model model) throws Exception {
	    	studentRepository.deleteById(id);
	        return "redirect:/index";
	   }
}
