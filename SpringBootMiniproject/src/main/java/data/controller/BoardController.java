package data.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.dto.BoardDto;
import data.mapper.BoardMapper;
import data.mapper.MemberMapper;
import data.service.BoardAnswerService;
import data.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService service;
	
	@Autowired
	MemberMapper memMapper;
	
	@Autowired
	BoardAnswerService aservice;
	
	
	//리스트 출력 - currentPage 페이징 처리
	@GetMapping("/board/list")
	public ModelAndView boardList(
			@RequestParam(value = "currentPage",defaultValue = "1") int currentPage
			)
	{
		
		ModelAndView mview=new ModelAndView();
		
		int totalCount=service.getTotalCount();
		
		//페이징처리에 필요한 변수
		
				int totalPage; //총페이지수
				int startPage; //각블럭의 시작페이지
				int endPage; //각블럭의 끝페이지
				int start; //각페이지의 시작번호
				int perPage=5; //한페이지에 보여질 글의개수
				int perBlock=5; //한페이지에 보여지는 페이지 개수
				
				//총페이지개수구하기
				totalPage=totalCount/perPage+(totalCount%perPage==0?0:1);

				//각블럭의 시작페이지
				//예: 현재페이지:3 startPage:1, endpge=5 
				//예: 현재페이지:6 startPage:6, endpge=10
				startPage=(currentPage-1)/perBlock*perBlock+1;

				endPage=startPage+perBlock-1;

				//만약 총페이지 수가 8일경우 
				//2번재 블럭은 st:6 endPage:10이 되야한다
				//이때는 endPage를 8로 수정해 주어야 한다
					if(endPage>totalPage)
						endPage=totalPage;

				//각페이지에서 불러올시작번호
				//현재페이지가  1 일경우 start는 1,2일경우 6....
				start=(currentPage-1)*perPage;

				//각페이지에서 필요한 게시글 가져오기...dao에서 만들었음
				List<BoardDto> list=service.getList(start, perPage);
				
				
				//이름 보내기
				for(BoardDto d:list) {
					
					String name = memMapper.getName(d.getMyid());
					
					d.setName(name);
					
					//num에 해당하는 댓글들에 관련된 사이즈
					d.setAcount(aservice.getAllAnswers(d.getNum()).size());
				}
				
				
				//각글앞에 붙힐 시작번호 구하기
				//총글이 20개일겨웅 1페이지 20,2페이지 15부터
				//출력해서 1씩 감소해가면서 출력할것
				int	no=totalCount-(currentPage-1)*perPage;
					
				//출력에 필요한 변수들을 request 에 저장
						mview.addObject("list",list);
						mview.addObject("startPage",startPage);
						mview.addObject("endPage",endPage);
						mview.addObject("totalPage",totalPage);
						mview.addObject("totalCount",totalCount);
						mview.addObject("no",no);
						mview.addObject("currentPage",currentPage);
						
						
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
	public ModelAndView content(@RequestParam String num,
			@RequestParam(value = "currentPage",defaultValue = "1") int currentPage) {
		
		ModelAndView mview = new ModelAndView();
		

		//readcount 호출
		service.updateReadCount(num);
		
		
		//하나의 num 해당 하는 data 가져옴
		BoardDto dto = service.getData(num);
		
		
		//dto의 name 작성자 이름 넣기
		String name = memMapper.getName(dto.getMyid());
		dto.setName(name);
		
		
		//업로드 파일의 확장자 얻기
		int dotLoc= dto.getUploadfile().lastIndexOf('.');	//마지막 .의 위치
		String ext = dto.getUploadfile().substring(dotLoc+1);	//다음글자부터 끝가지 추출
		
		
		//이미지일 경우에만 bupload
		if(ext.equalsIgnoreCase("jpg")||ext.equalsIgnoreCase("gif")||ext.equalsIgnoreCase("jpeg")||ext.equalsIgnoreCase("png"))
			mview.addObject("bupload", true);
		else
			mview.addObject("bupload", false);
		
		
		mview.addObject("dto",dto);
		
		mview.addObject("currentPage", currentPage);
		
		//포워드
		mview.setViewName("/board/content");
		
		return mview;
	}
	
	
	//업데이트폼 이동
	@GetMapping("/board/updateform")
	public ModelAndView updateForm(String num, String currentPage) {
		
		ModelAndView mview = new ModelAndView();
		BoardDto dto = service.getData(num);
		
		mview.addObject("dto",dto);
		mview.addObject("currentPage", currentPage);
		
		//포워드
		mview.setViewName("/board/updateform");
		
		return mview;
	}
	
	
	//실제수정
	@PostMapping("/board/update")
	public String update(@ModelAttribute BoardDto dto, HttpSession session,
			String currentPage) {
		
		//만료됐을수도 있으므로 다시한번 loginok 얻는다
		String loginok = (String)session.getAttribute("loginok");
		
		if(loginok==null) {
			
			return "/board/loginmsg";
		}
		
		
		//업로드 할 폴더지정
		String path= session.getServletContext().getRealPath("/photo");
		
		//업로드할 파일명
		if(dto.getUpload().getOriginalFilename().equals(""))
			dto.setUploadfile(null);
		
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
		
		
		//update
		service.updateBoard(dto);
				
		return "redirect:content?num="+dto.getNum()+"&currentPage="+currentPage;
	}
	
	
	
	//삭제
	@GetMapping("/board/delete")
	public String deleteString(String num, String currentPage,
			HttpSession session) {
		
		//실제 업로드 폴더
		String path = session.getServletContext().getRealPath("/photo");
		
		//해당 num에 해당되는 사진 삭제
		String uploadfile = service.getData(num).getUploadfile();
		
		File file = new File(path+"\\"+uploadfile);
		
		file.delete();
		
		service.deleteBoard(num);
		
		return "redirect:list?currentPage="+currentPage;
	}
	
}
