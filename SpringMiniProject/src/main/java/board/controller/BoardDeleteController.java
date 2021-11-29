package board.controller;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.data.BoardDao;

@Controller
public class BoardDeleteController {

	@Autowired
	BoardDao dao;
	
	
	@GetMapping("/board/delete")
	public String delete(
			@RequestParam int num,
			@RequestParam String currentPage,
			HttpSession session) {
	
		
		//포토폴더 사진 지우기 - num에 해당하는 photo들
		String photo = dao.getData(num).getPhoto();
		
		if(!photo.equals("no")) {
			
			String [] fName = photo.split(",");
			
			String path = session.getServletContext().getRealPath("/WEB-INF/photo");
			
			
			for(String f: fName) {
				
				File file = new File(path+"\\"+f);
				file.delete();
			}
		}
		
		//db삭제
		dao.deleteBoard(num);
		
		return "redirect:list?currentPage="+currentPage;
	}
}
