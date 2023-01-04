package com.and.middle;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class HomeController {

	@ResponseBody
	@RequestMapping(value = "/")
	public String home(HttpServletResponse response) {
		return "home";//응답화면을 home
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/login") public String login(String id, String pw) { if (
	 * id.equals("admin") && pw.equals("admin1234") ) {
	 * 
	 * return "login"; }else { return "비밀번호 틀림"; }
	 */
		
	//}
	
	
	
	//json 
	//[여러개],{한개}
	//차이?
	
	
	@ResponseBody
	@RequestMapping("/jsontest")
	public String jsonTest() {

		int[] data = {1,2,3,4,5,6,7,8,9,0};
		TempDTO dto = new TempDTO("data123");
		
		ArrayList<TempDTO> list = new ArrayList<TempDTO>();
		list.add(new TempDTO("asdfasdf"));
		list.add(new TempDTO("asdfasdf"));
		list.add(new TempDTO("asdfasdf"));
		list.add(new TempDTO("asdfasdf"));
		Gson gson = new Gson();
		
		
		return gson.toJson(list);
	}
	
	
	
	@ResponseBody
	@RequestMapping ("/andTest")
	public String andTest(String id) {
		System.out.println(id);
		return "asdf";
	}
	
	
	
	
	
	
	public class TempDTO{
		String data;
		public TempDTO(String data) {
			this.data = data;
		}
	}
}
