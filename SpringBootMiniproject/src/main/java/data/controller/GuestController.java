package data.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import data.dto.GuestDto;
import data.mapper.GuestMapper;

@Controller
public class GuestController {

	@Autowired
	GuestMapper mapper;
	
	@GetMapping("/guest/list")
	public ModelAndView list(@RequestParam(defaultValue = "1") int currentPage)
	{
		ModelAndView mview=new ModelAndView();
		
		int totalCount=mapper.getTotalCount();

		//페이징 처리에 필요한 변수선언
		int perPage=3;//한페이지에 보여질 글의 갯수
		int totalPage;//총 페이지수
		int start;//각페이지에서 불러올 db 의 시작번호
		int perBlock=5;//몇개의 페이지번호씩 표현할것인가
		int startPage; //각 블럭에 표시할 시작페이지
		int endPage; //각 블럭에 표시할 마지막페이지		

		//총 페이지 갯수 구하기
		totalPage=totalCount/perPage+(totalCount%perPage==0?0:1); 
		//각 블럭의 시작페이지
		startPage=(currentPage-1)/perBlock*perBlock+1;
		endPage=startPage+perBlock-1;
		if(endPage>totalPage)
			endPage=totalPage;
		//각 페이지에서 불러올 시작번호
		start=(currentPage-1)*perPage;
		
		//각페이지에서 필요한 게시글 가져오기
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		map.put("start", start);
		map.put("perpage", perPage);
		
		List<GuestDto>list=mapper.getList(map);
		
		//각페이지에 출력할 시작번호
		int no=totalCount-(currentPage-1)*perPage;

		//출력에 필요한 변수들을 request 에 저장
		mview.addObject("list",list);
		mview.addObject("startPage",startPage);
		mview.addObject("endPage",endPage);
		mview.addObject("totalPage",totalPage);
		mview.addObject("no",no);
		mview.addObject("currentPage",currentPage);
		mview.addObject("totalCount", totalCount);

		mview.setViewName("/guest/guestlist");
		return mview;
	}
	
@PostMapping("/guest/insert")
	
	public String insert(
			HttpSession session,
			@RequestParam ArrayList<MultipartFile> photos,
			@ModelAttribute GuestDto dto
			)
	{
		//이미지 저장 경로  
				
		String path=session.getServletContext().getRealPath("/photo");
		System.out.println(path);
		//이미지 저장 클래스 선언
		
		String photo="";
		if(photos.get(0).getOriginalFilename().equals(""))
			photo="no";
		else {
			for(MultipartFile f:photos)
			{
				String fName=f.getOriginalFilename();
				photo+=fName+",";
				
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
			photo=photo.substring(0, photo.length()-1);			
		}
		dto.setPhoto(photo);
		mapper.insertGuest(dto);
		
		
		return "redirect:list";
		
	}

@GetMapping("/guest/updateform")
public String updateForm
		(@RequestParam String num,Model model,
				@RequestParam int currentPage
					)
{
	
	GuestDto dto=mapper.getData(num);
	model.addAttribute("dto", dto);
	model.addAttribute("currentPage", currentPage);
	
	return "/guest/updateform";
}

@PostMapping("/guest/update")

public String update(
		HttpSession session,
		@RequestParam ArrayList<MultipartFile> photos,
		@RequestParam int currentPage,
		@ModelAttribute GuestDto dto
		)
{
	//이미지 저장 경로  
			
	String path=session.getServletContext().getRealPath("/photo");
	System.out.println(path);
	//이미지 저장 클래스 선언
	
	String photo="";
	if(photos.get(0).getOriginalFilename().equals(""))
		photo="no";
	else {
		for(MultipartFile f:photos)
		{
			String fName=f.getOriginalFilename();
			photo+=fName+",";
			
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
		photo=photo.substring(0, photo.length()-1);			
	}
	dto.setPhoto(photo);
	mapper.updateGuest(dto);
	
	
	return "redirect:list?currentPage="+currentPage;
	
}



}
