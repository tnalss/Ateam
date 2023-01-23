package co.kr.jkcompany;
import java.util.HashMap; 

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import login.LoginVO;



@Controller
public class LoginController {
	@Autowired private SqlSession sql;
	//로그인처리 요청
		@ResponseBody @RequestMapping("/jklogin")
		public boolean loginIn(String id, String pw, HttpSession session) {
			HashMap<String, String> map = new HashMap<String, String>();	
			map.put("id", id);
			map.put("pw", pw);
			LoginVO vo = sql.selectOne("lo.login", map);
			session.setAttribute("loginInfo", vo);
			
			return vo==null ? false : true;
		}
	
	// 로그인 화면요청
	@RequestMapping("/login")
	public String login(HttpSession session) {
		session.setAttribute("category", "login");
		return "login/login";
	}
}
