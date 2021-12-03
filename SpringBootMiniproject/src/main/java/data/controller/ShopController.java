package data.controller;

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

import data.dto.ShopDto;
import data.mapper.MysqlShopMapper;

@Controller
public class ShopController {

	@Autowired
	MysqlShopMapper mapper;

	
	//루트 설정
	@GetMapping("/")
	public String home() {
		
		return "/layout/main";
	}
	
	
	@GetMapping("/shop/list")
	public ModelAndView list() {
		
		ModelAndView mview = new ModelAndView();
		
		int totalCount = mapper.getTotalCount();
		
		mview.addObject("totalCount",totalCount);
		
		
		//전체 리스트 추가
		List<ShopDto> list = mapper.getAllSangpums();
		
		//mview에 더하기
		mview.addObject("list", list);
		
		
		//포워드 - jsp
		//mview.setViewName("shoplist");
		
		//tiles 경로로 포워드
		mview.setViewName("/shop/shoplist");
		
		return mview;
	}
	
	
	//입력폼으로 이동
	@GetMapping("/shop/shopform")
	public String form() {
		
		//기존 jsp경로
		//return "shopform";

		//tiles 경로로 변경
		return "/shop/shopform";
	}
	
	
	//insert
	@PostMapping("/shop/insert")
	public String insert(@ModelAttribute ShopDto dto,
			@RequestParam MultipartFile upload,
			HttpSession session) {
		
		//실제 경로
		String path = session.getServletContext().getRealPath("/photo");
		
		//실제 파일이름
		String photoName = upload.getOriginalFilename();
		
		//dto 세팅
		dto.setPhotoname(photoName);
		

		try {
			//실제업로드
			upload.transferTo(new File(path+"\\"+photoName));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//인서트
		mapper.insertShop(dto);
		
		return "redirect:list";
	}
}
