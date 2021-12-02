package boot.market.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import boot.market.data.MarketDto;
import boot.market.data.MysqlMarketMapper;

@Controller
public class MarketController {

	//인터페이스 호출
	@Autowired
	MysqlMarketMapper mapper;
	
	
	//리스트 출력
	@GetMapping({"/","/list"})
	public ModelAndView list() {
		
		ModelAndView mview = new ModelAndView();
		
		//db로부터 총갯수 얻기
		int totalCount = mapper.getTotalCount();
		
		//값저장
		mview.addObject("totalCount",totalCount);
		
		//전체출력 추가
		List<MarketDto> list = mapper.getAllDatas();
		mview.addObject("list",list);
		
		//포워드
		mview.setViewName("marketlist");
		
		return mview;
	}
	
	
	//인서트 폼으로 이동
	@GetMapping("/form")
	public String form() {
		
		return "addform";
	}
	
	
	//실제 인서트
	//여기서 테이블과 이름을 맞춘다
	@PostMapping("/insert")
	public String insert(@ModelAttribute MarketDto dto,
			@RequestParam MultipartFile photo,
			HttpSession session) {
		
		//저장 경로
		String path = session.getServletContext().getRealPath("/photo");
		
		//여기서 이름을 똑같이 맞춰준다.
		String fileName = photo.getOriginalFilename();
		
		//dto에 저장
		dto.setPhotoname(fileName);
		
		
		try {
			//분할 저장
			photo.transferTo(new File(path+"\\"+fileName));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//실제 인서트
		mapper.insertMarket(dto);
		
		return "redirect:list";
	}
	
	
	//업데이트 폼으로 이동
	@GetMapping("/updateform")
	public ModelAndView updateForm(@RequestParam String num) {
		
		ModelAndView mview = new ModelAndView();
		
		//하나의 num에 해당하는 데이터 꾸러미
		MarketDto dto = mapper.getData(num);
		
		//담기
		mview.addObject("dto",dto);
		
		//포워드
		mview.setViewName("updateform");
		
		return mview;
	}
	
	
	//실제 업데이트(insert 복사)
	@PostMapping("/update")
	public String update(@ModelAttribute MarketDto dto,
			@RequestParam MultipartFile photo,
			HttpSession session) {
		
		//저장 경로
		String path = session.getServletContext().getRealPath("/photo");
		
		
		//비어있으면 포토네임 no라고 하기로
		if (photo.getOriginalFilename().equals(""))
			dto.setPhotoname("no");
		else {
		
			//여기서 이름을 똑같이 맞춰준다.
			String fileName = photo.getOriginalFilename();
			
			//dto에 저장
			dto.setPhotoname(fileName); 
			
			
			try {
				//분할 저장
				photo.transferTo(new File(path+"\\"+fileName));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		//실제 인서트
		mapper.updateMarket(dto);
		
		return "redirect:list";
	}
	
	
	//실제삭제
	@GetMapping("/delete")
	public String delete(@RequestParam String num) {
		
		mapper.deleteMarket(num);
		
		return "redirect:list";
	}
}
