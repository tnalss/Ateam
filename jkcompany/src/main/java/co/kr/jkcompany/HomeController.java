package co.kr.jkcompany;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = "/", produces="text/html;charset=utf-8")
	public String home(HttpSession session) {
		
		session.removeAttribute("category");
		return "home";
	}
	
	@RequestMapping(value = "/about" , produces="text/html;charset=utf-8")
	public String about(HttpSession session, Model model) {
		
		session.removeAttribute("category");
		return "about";
	}
	

}
