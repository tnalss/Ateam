package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("common")
public class CommonService {

	
	public String fileUpload(String category, MultipartFile file, HttpServletRequest request) {
		
		String path = "d://app"+request.getContextPath();
		String upload = "/upload/"+category+"/"+ new SimpleDateFormat("yyyy_MM_dd").format(new Date()) ;
		
		path+=upload;
		

		File folder = new File(path);
		if( ! folder.exists() ) folder.mkdirs();
		

		String filename =  UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		try {
			file.transferTo( new File(path,filename) );
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return appURL(request)+upload+"/"+filename;

	}
	
	//요청 url의 contextpath
	public String appURL(HttpServletRequest request) {
		return request.getRequestURL().toString().replace(request.getServletPath(),"");
	}
	
	//랜덤 번호 생성
	public String rand6num() {
		Random random = new Random(System.currentTimeMillis());

		int range = (int) Math.pow(10, 6);
		int trim = (int) Math.pow(10, 6 - 1);
		int result = random.nextInt(range) + trim;

		if (result > range) {
			result = result - trim;
		}

		return String.valueOf(result);
	}
		
	

}
