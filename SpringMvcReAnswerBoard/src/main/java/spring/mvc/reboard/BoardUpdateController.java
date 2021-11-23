package spring.mvc.reboard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import board.data.BoardDao;
import board.data.BoardDto;

@Controller
public class BoardUpdateController {

	@Autowired
	BoardDao dao;
	
	
	@GetMapping("/board/updatepassform")
	public ModelAndView updatePassFrom(
			@RequestParam String num,
			@RequestParam String currentPage) {
		
		ModelAndView mview = new ModelAndView();
		
		mview.addObject("num", num);
		mview.addObject("currentPage", currentPage);
		
		//포워드
		mview.setViewName("updatepassform");
		
		return mview;
	}
	
	
	//비밀번호 체크
	@PostMapping("/board/updatepass")
	public ModelAndView updatePass(
			@RequestParam String num,
			@RequestParam String pass,
			@RequestParam String currentPage) {
		
		ModelAndView mview = new ModelAndView();
		
		
		//비번이 맞으면 수정폼으로 포워드, 틀리면 passfail로 포워드
		int check= dao.getCheckPass(num, pass);
		
		if(check==1) {
			
			//비번이 맞을경우, dto 얻는다(수정폼 가야하므로)
			BoardDto dto = dao.getData(Integer.parseInt(num));
			
			mview.addObject("dto",dto);
			mview.addObject("currentPage",currentPage);
			
			mview.setViewName("updateform");
		} else {	//반환값이 0이면
			
			mview.setViewName("passfail");
		}
		
		return mview;
	}
	
	
	
	//실제 수정
	@PostMapping("/board/update")
	public String update(
			@ModelAttribute BoardDto dto,
			@RequestParam ArrayList<MultipartFile> upload,
			HttpSession session,
			@RequestParam String currentPage) {
		
		//업로드할 실제경로
		String path = session.getServletContext().getRealPath("/WEB-INF/photo");
		System.out.println(path);
		

		String photo="";
		
		//사진 선택을 안했을 경우 no, 했을경우 ,로 나열
		if(upload.get(0).getOriginalFilename().equals(""))
			photo="no";
		else {
			
			for(MultipartFile f:upload) {
				String fName = f.getOriginalFilename();
				photo += fName+",";
				
				//업로드
				try {
					f.transferTo(new File(path+"\\"+fName));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			photo = photo.substring(0,photo.length()-1);
		}
		
		//dto의 photo에 넣어주기
		dto.setPhoto(photo);
		
		//dao의 update 호출
		dao.updateBoard(dto);
		
		
		//목록으로 가지말고 내용보기로 가려면
		int num=dao.getMaxNum();
		
		return "redirect:content?num="+num+"&currentPage="+currentPage;	//나중에 content로 바꿀 예정
	}
}
