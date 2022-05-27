package com.example.demo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;

import antlr.collections.List;

@RestController

public class Controller {

	
	
	@Autowired
	private UserRepository userRepo;

	
	@GetMapping("/index")
	public ModelAndView getPage() {

		System.out.println("Hello");

		ModelAndView mv = new ModelAndView();
		mv.setViewName("index.html");
		mv.addObject("name", "name");
		return mv;

	}

	

	
	

	@GetMapping("/download")
	public void download(HttpServletResponse response) throws IOException {
		File file = new File("File//php.zip");

		response.setContentType("applicaton/octet-strem");

		String heardKey = "Content-Disposition";
		String heardValue = "attachment; filename=" + file.getName();

		response.setHeader(heardKey, heardValue);

		ServletOutputStream out = response.getOutputStream();

		BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));

		byte[] buffer = new byte[8192];

		int byteRead = -1;

		while ((byteRead = inputStream.read(buffer)) != -1) {
			out.write(buffer, 0, byteRead);
		}

		inputStream.close();
		out.close();
	}

	/*------------------------------DataBase Config--------------------------*/
	@GetMapping("/checkAuth")
	public ModelAndView checkAuth(@RequestParam("name") String name, @RequestParam("password") String password) {
		ArrayList<User> list  = (ArrayList<User>) userRepo.findAll();
		ModelAndView mv = new ModelAndView();
		for(User u : list)
		{
			if(u.getFirstName().equalsIgnoreCase(name) && u.getpassowrd().equalsIgnoreCase(password))
			{
				mv.setViewName("dashboard.html");
				return mv;
			}
		}
		
		
		mv.setViewName("index.html");
		return mv;
		
	}

	
	
	
	@GetMapping("/register")
	public ModelAndView register(@RequestParam("name") String name,
			  
			  @RequestParam("password") String password, @RequestParam("email") String
			  email,
			  
			  @RequestParam("phone") String phone)
	{
		
		
		User user = new User(name, email, password, phone); 
		userRepo.save(user);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index.html");
		mv.addObject("name", "User");
		return mv;
	}
	
	
	
}




