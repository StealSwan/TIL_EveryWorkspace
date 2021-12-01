package mycar.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MycarController {

	@Autowired
	MycarDao dao;
	
	@GetMapping("/carform")
	public String form() {
		
		return "caraddform";
	}
	
	
	@PostMapping("/insert")
	public String add(@ModelAttribute MycarDto dto) {
		
		dao.insertCar(dto);
		
		return "redirect:list";
	}
	
	
	//루트나 리스트일때 실행되도록
	//로컬 호스트 9003을 열면 list가 보여야함
	@GetMapping({"/","/list"})
	public ModelAndView list() {
		
		ModelAndView mview = new ModelAndView();
		
		List<MycarDto> list = dao.getAllDatas();

		//저장해서 넘기기
		mview.addObject("list",list);
		mview.addObject("count",list.size());
		
		//포워드
		mview.setViewName("carlist");
		
		return mview;
	}
	
	
	//수정폼 포워드 - Num에 해당하는 데이타 세팅
	@GetMapping("/updateform")
	public ModelAndView updateform(Long num){
		
		ModelAndView mview = new ModelAndView();
		
		MycarDto dto = dao.getData(num);
		
		mview.addObject("dto",dto);
		
		//포워드
		mview.setViewName("carupdateform");
		
		return mview;
	}
	
	
	//실제 수정
	@PostMapping("/update")
	public String update(@ModelAttribute MycarDto dto) {
		
		dao.updateCar(dto);
		
		return "redirect:list";
	}
	
	
	//삭제 후 리스트 이동
	@GetMapping("/delete")
	public String carDelete(Long num) {
		
		dao.deleteCar(num);
		
		return "redirect:list";
	}
}
