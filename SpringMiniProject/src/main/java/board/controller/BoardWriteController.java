package board.controller;

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
public class BoardWriteController {

	@Autowired
	BoardDao dao;
	
	//현재 로그인 중이라 HttpSession 값도 받음
	@GetMapping("/board/form")
	public ModelAndView form(HttpSession session) {
		
		ModelAndView mview = new ModelAndView();
		
		//세션에 저장된 아이디
		String id=(String)session.getAttribute("myid");
		

		//아이디에 해당하는 이름
		String name = dao.searchName(id);
		
		mview.addObject("name",name);
		
		//포워드
		mview.setViewName("/board/writeform");
		
		return mview;
	}
	
	
	//insert
	//action처리
	//ArrayList<MultipartFile>은 여러개의 사진 업로드용
	//HttpSession - session 로그인용
	//currentPage - 
	@PostMapping("/board/insert")
	public String insert(
			@ModelAttribute BoardDto dto,
			@RequestParam ArrayList<MultipartFile> upload,
			HttpSession session
			//@RequestParam String currentPage
			) {
		
		
		//업로드할 실제경로
		String path = session.getServletContext().getRealPath("/WEB-INF/photo");
		
		String photo="";
		
		
		//사진 선택 안했을 경우 photo를 no로
		if(upload.get(0).getOriginalFilename().equals(""))
			photo="no";
		
		//선택했을 경우 ,로 나열
		else {
			
			//각 업로드된 사진들마다 파일이름 가져옴
			for(MultipartFile f:upload) {
				
				String fName = f.getOriginalFilename();
				
				//업로드된 파일 이름을 ,를 이용해 이어줌
				photo += fName + ",";
				
				
				try {
					//업로드된 각 사진 개별
					f.transferTo(new File(path+"\\"+fName));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//긴 photo에서 마지막 ,를 빼준것
			photo=photo.substring(0, photo.length()-1);
		}
		
		
		//모든 이어진 photo를 넣어줌
		dto.setPhoto(photo);
		
		//dao의 insert 호출
		dao.insertBoard(dto);
		
		//목록으로 가지말고 내용보기로 이동 - 아직 안함
		//int num = dao.getMaxNum();
		
		return "/board/boardlist";
	}
}
