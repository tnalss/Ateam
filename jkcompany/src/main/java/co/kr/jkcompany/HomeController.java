package co.kr.jkcompany;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = "/", produces="text/html;charset=utf-8")
	public String home(HttpSession session) {
		
		session.removeAttribute("category");
		return "home";
	}
	
	@RequestMapping(value = "/about" , produces="text/html;charset=utf-8")
	public String about(HttpSession session, Model model) {
		
		session.setAttribute("category","about");
		return "about";
	}
	

}
