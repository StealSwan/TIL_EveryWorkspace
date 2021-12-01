package myshop.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyshopController {

	@Autowired
	MyshopDao dao;
	
	//입력폼
	@GetMapping("/shopform")
	public String form() {
		
		return "myshop/shopaddform";
	}
	
	
	//전체출력
	@GetMapping("/shoplist")
	public ModelAndView list() {
		
		ModelAndView mview = new ModelAndView();
		
		//2차 추가 범주
		List<MyshopDto> list = dao.getAllDatas();
		
		//저장해서 넘기기
		mview.addObject("list",list);
		mview.addObject("count",list.size());
		
		//포워드
		mview.setViewName("/myshop/shoplist");
		
		//정보를 담은 객체 전달
		return mview;
	}
	
	
	//입력
	@PostMapping("/shopinsert")
	public String add(@ModelAttribute MyshopDto dto) {
		
		dao.insertShop(dto);
		
		return "redirect:shoplist";
	}
	
	
	//업데이트폼 이동
	@GetMapping("/shopupdateform")
	public ModelAndView updateform(int num) {
		
		ModelAndView mview = new ModelAndView();
		
		MyshopDto dto = dao.getData(num);
		
		mview.addObject("dto",dto);
		
		//포워드
		mview.setViewName("myshop/shopupdateform");
		
		return mview;
	}
	
	
	//실제 업데이트
	@PostMapping("/shopupdate")
	public String update(@ModelAttribute MyshopDto dto) {
		
		dao.updateShop(dto);
		
		return "redirect:shoplist";
	}
	
	
	//실제 삭제 후 리스트로
	@GetMapping("/shopdelete")
	public String delete(int num) {
		
		dao.deleteShop(num);
		
		return "redirect:shoplist";
	}
}
