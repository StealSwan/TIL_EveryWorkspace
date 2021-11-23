package spring.mvc.reboard;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.data.BoardDao;

@Controller
public class BoardDeleteController {

	@Autowired
	BoardDao dao;
	
	
	@GetMapping("/board/deletepassform")
	public ModelAndView updatePassFrom(
			@RequestParam String num,
			@RequestParam String currentPage) {
		
		ModelAndView mview = new ModelAndView();
		
		mview.addObject("num", num);
		mview.addObject("currentPage", currentPage);
		
		//포워드
		mview.setViewName("deletepassform");
		
		return mview;
	}
	
	
	//실제 삭제
	@PostMapping("/board/delete")
	public String delete(
			@RequestParam String num,
			@RequestParam String currentPage,
			@RequestParam String pass,
			HttpSession session) {
	
		//비번이 맞는지 체크
		int check=dao.getCheckPass(num, pass);
		
		if(check==0) {
			
			return "passfail";
		} else {	//1: 비번이 맞는
			
			//photo 폴더 사진 지우기
			String photo = dao.getData(Integer.parseInt(num)).getPhoto();
			
			if(!photo.equals("no")) {
				
				//,로 분리
				String [] fName = photo.split(",");
				
				//실제업로드 경로
				String path = session.getServletContext().getRealPath("/WEB-INF/photo");
				
				for(String f:fName) {
					
					File file = new File(path+"//"+f);
					file.delete();
				}
			}
		}
		
		//db삭제
		dao.deleteBoard(check);
		
		
		//목록으로 리다이렉트
		return "redirect:list?currentPage="+currentPage;
	}
	
}
