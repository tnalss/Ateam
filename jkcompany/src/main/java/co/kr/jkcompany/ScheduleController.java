package co.kr.jkcompany;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import common.CommonService;
import employee.EmployeeVO;


@Controller
public class ScheduleController {
	@Autowired @Qualifier("hanul")
	SqlSession sql;
	@Autowired
	private CommonService common;


	@RequestMapping(value= "/list.sche" , produces="text/html;charset=utf-8")
	public String emp_list(HttpSession session, Model model) {
		
		//각 컨트롤러 입장 메소드는 category에 속성을 넣어주세요!
		session.setAttribute("cate", "sche" );

		
		EmployeeVO info = common.tempLogin("3");
		
		//임시로그인
		session.setAttribute("loginInfo", info);
		
		sql.selectList("sche.compPeriod");
		return "schedule/list";
	}
	
	
	@ResponseBody
	@RequestMapping(value= "/calendars" , produces="text/html;charset=utf-8")
	public String calendars(HttpSession session, Model model,String id) {
		
		//sql.selectList("sche.compPeriod");
		return new Gson().toJson(getEventList());
	}
	

	public List<Map<String, Object>> getEventList() {
        Map<String, Object> event = new HashMap<String, Object>();
        List<Map<String, Object>> eventList = new ArrayList<Map<String, Object>>();
        Date dates = new Date();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       String date = sdf.format(dates);
        event.put("start", date);
        event.put("title", "부서회식");
        event.put("end",date);
        eventList.add(event);
        event = new HashMap<String, Object>();
        event.put("start",date);
        event.put("title", "납품일");
        event.put("end",date);
        eventList.add(event);
        return eventList;
    }
}
