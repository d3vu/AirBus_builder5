package com.example.demo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;

import antlr.collections.List;

@RestController

public class Controller implements NameConfig {

	@Autowired
	private UserRepository userRepo;
	private String frontend = null;
	private String backend = null;

	@GetMapping("/selectedTech")
	public ModelAndView getPage2(@RequestParam("f") String f, @RequestParam("b") String b) {

		System.out.println("Hello");
		frontend = f;
		backend = b;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("selectedTech.jsp");
		mv.addObject("frontend", frontend);
		mv.addObject("backend", backend);
		return mv;

	}

	@GetMapping("/index")
	public ModelAndView getPage() {

		System.out.println("Hello");

		ModelAndView mv = new ModelAndView();
		mv.setViewName("index.html");
		mv.addObject("name", "name");
		return mv;

	}

	@GetMapping("/download")
	public ModelAndView download(HttpServletResponse response) throws IOException {

		ModelAndView mv = new ModelAndView();

		String path = NameConfig.project;
		if (frontend.equalsIgnoreCase("HTML") && backend.equalsIgnoreCase("php")) {
			path += NameConfig.HTML_PHP;

		} else {
			mv.setViewName("fail.jsp");
			return mv;
		}

		File file = new File(path);

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

		mv.setViewName("Success.hmtl");
		mv.addObject("text", "hi");
		return mv;
	}

	/*------------------------------DataBase Config--------------------------*/
	@GetMapping("/checkAuth")
	public ModelAndView checkAuth(@RequestParam("name") String name, @RequestParam("password") String password) {
		ArrayList<User> list = (ArrayList<User>) userRepo.findAll();
		ModelAndView mv = new ModelAndView();
		for (User u : list) {
			if (u.getFirstName().equalsIgnoreCase(name) && u.getpassowrd().equalsIgnoreCase(password)) {
				mv.setViewName("dashboard.html");
				return mv;
			}
		}

		mv.setViewName("index.html");
		return mv;

	}

	@GetMapping("/register")
	public ModelAndView register(@RequestParam("name") String name,

			@RequestParam("password") String password, @RequestParam("email") String email,

			@RequestParam("phone") String phone) {

		User user = new User(name, email, password, phone);
		userRepo.save(user);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("index.html");
		mv.addObject("name", "User");
		return mv;
	}

}
