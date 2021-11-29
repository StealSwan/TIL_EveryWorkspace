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
public class BoardUpdateController {

	@Autowired
	BoardDao dao;
	
	
	@GetMapping("board/updateform")
	public ModelAndView updateform(
			@RequestParam int num,
			@RequestParam String currentPage) {
		
		ModelAndView mview= new ModelAndView();
		
		BoardDto dto = dao.getData(num);
		mview.addObject("dto",dto);
		mview.addObject("currentPage",currentPage);
		
		//포워드
		mview.setViewName("/board/updateform");
				
		return mview;
	}
	
	
	
	//업데이트
	@PostMapping("/board/update")
	public String insert(
			@ModelAttribute BoardDto dto,
			@RequestParam ArrayList<MultipartFile> upload,
			HttpSession session,
			@RequestParam String currentPage
			) {
		
		
		//업로드할 실제경로
		String path = session.getServletContext().getRealPath("/WEB-INF/photo");
		
		String photo="";
		
		
		//사진 선택 안했을 경우 photo를 no로
		if(upload.get(0).getOriginalFilename().equals(""))
			photo=null;
		
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
		
		
		//db추가
		dao.updateBoard(dto);
		
		
		//return "/board/boardlist";
		return "redirect:content?num="+dto.getNum()+"&currentPage="+currentPage;
	}
}
