package data.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.dto.BoardDto;
import data.mapper.BoardMapper;
import data.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService service;
	
	
	//전체 글의 수
	@GetMapping("/board/list")
	public ModelAndView boardList() {
		
		ModelAndView mview = new ModelAndView();
		
		int totalCount = service.getTotalCount();
		
		mview.addObject("totalCount", totalCount);
		mview.setViewName("/board/boardlist");
		
		return mview;
	}
	
	
	//폼으로 이동
	@GetMapping("/board/form")
	public String form() {
		
		return "/board/writeform";
	}
	
	
	//insert
	@PostMapping("/board/insert")
	public String insert(@ModelAttribute BoardDto dto, HttpSession session) {
		
		//만료됐을수도 있으므로 다시한번 loginok 얻는다
		String loginok = (String)session.getAttribute("loginok");
		
		if(loginok==null) {
			
			return "/board/loginmsg";
		}
		
		
		//업로드 할 폴더지정
		String path= session.getServletContext().getRealPath("/photo");
		
		//업로드할 파일명
		if(dto.getUpload().getOriginalFilename().equals(""))
			dto.setUploadfile("no");	//db에 no라고 저장
		
		else {	//업로드 한경우
			
			String uploadfile = dto.getUpload().getOriginalFilename();
			dto.setUploadfile(uploadfile);
			
			//실제 업로드
			try {
				dto.getUpload().transferTo(new File(path + "\\" + uploadfile));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//세션에서 아이디를 얻어서 dto에 저장
		String myid = (String)session.getAttribute("myid");
		dto.setMyid(myid);
		
		
		//insert
		service.insertBoard(dto);
		
		//Content로 갈때 Max Num
		int num = service.getMaxNum();
		
		return "redirect:content?num="+num;
		
	}
	
	
	//content로 이동
	@GetMapping("/board/content")
	public ModelAndView content(@RequestParam String num) {
		
		ModelAndView mview = new ModelAndView();
		
		//하나의 num 해당 하는 data 가져옴
		BoardDto dto = service.getData(num);
		
		mview.addObject("dto",dto);
		
		//포워드
		mview.setViewName("/board/content");
		
		return mview;
	}
}
