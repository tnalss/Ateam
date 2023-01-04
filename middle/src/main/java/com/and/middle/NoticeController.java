package com.and.middle;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {

	@RequestMapping(value= "/notice.no", produces="text/html;charset=utf-8")
	public String notice() {
		
		return "";
	}


	
}
