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
		
		//������
		mview.setViewName("deletepassform");
		
		return mview;
	}
	
	
	//���� ����
	@PostMapping("/board/delete")
	public String delete(
			@RequestParam String num,
			@RequestParam String currentPage,
			@RequestParam String pass,
			HttpSession session) {
	
		//����� �´��� üũ
		int check=dao.getCheckPass(num, pass);
		
		if(check==0) {
			
			return "passfail";
		} else {	//1: ����� �´�
			
			//photo ���� ���� �����
			String photo = dao.getData(Integer.parseInt(num)).getPhoto();
			
			if(!photo.equals("no")) {
				
				//,�� �и�
				String [] fName = photo.split(",");
				
				//�������ε� ���
				String path = session.getServletContext().getRealPath("/WEB-INF/photo");
				
				for(String f:fName) {
					
					File file = new File(path+"//"+f);
					file.delete();
				}
			}
		}
		
		//db����
		dao.deleteBoard(check);
		
		
		//������� �����̷�Ʈ
		return "redirect:list?currentPage="+currentPage;
	}
	
}
