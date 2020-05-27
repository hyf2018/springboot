package com.example.demo.controller;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.po.UserPO;

@Controller
public class ThymeleafController {
//	thymeleaf模板
	@GetMapping("/page")
	public String page(Model model) {
		model.addAttribute("message", "萨对付啊速度sss");
		return "index";
	}

	@GetMapping("/page2")
	public String showPage(HttpServletRequest request, HttpSession session) {

		UserPO user1 = new UserPO(1, "zhang san", "333333");
		request.setAttribute("user1", user1);

		UserPO user2 = new UserPO(2, "lisi", "444444");
		session.setAttribute("user2", user2);

		UserPO user3 = new UserPO(3, "wangwu", "555555");
		ServletContext application = request.getServletContext();
		application.setAttribute("user3", user3);

		return "index";
	}

	@GetMapping("/page3")
	public String showPage(Model model) {
		ArrayList<UserPO> users = new ArrayList<>();
		users.add(new UserPO(1, "zhangsan", "333333"));
		users.add(new UserPO(2, "lisi", "444444"));
		users.add(new UserPO(3, "wangwu", "555555"));

		model.addAttribute("users", users);

		return "index";
	}
}
