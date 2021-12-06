package data.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties.Embedded;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import data.dto.MemberDto;
import data.mapper.MemberMapper;

@Controller
public class MemberControlloer {

	@Autowired
	MemberMapper mapper;
	
	
	@GetMapping("/member/form")
	public String memberform() {
		
		//tiles에서 /*/* 이런식으로 하기로 함		
		return "/member/memberform";
	}
	
	
	//리스트로 이동
	@GetMapping("/member/list")
	public String list(Model model) {
		
		List<MemberDto> list = mapper.getAllMembers();
		
		model.addAttribute("count", list.size());
		model.addAttribute("list", list);
		
		return "/member/memberlist";
	}
	
	
	//id 체크
	//Ajax
	@GetMapping("/member/idcheck")
	public @ResponseBody Map<String, Integer> idCheckProc(@RequestParam String id){
		
		//중복 아이디 체크
		int check = mapper.getIdCheck(id);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		//0 or 1
		map.put("check", check);
		
		return map;
	}
	
	
	//insert
	@PostMapping("/member/insert")
	public String memberInsert(@ModelAttribute MemberDto dto) {
		
		//이메일 형식으로 넣어주기
		dto.setEmail(dto.getEmail1() + "@" +dto.getEmail2());
		
		//insert 호출
		mapper.insertMember(dto);
		
		//list로
		return "redirect:list";
	}
	
	
	//updatepassform 나오도록
	@GetMapping("/member/updatepassform")
	public String updatePassForm(@RequestParam String num, Model model) {
		
		model.addAttribute("num", num);
		
		return "/member/updatepassform";
	}
	
	
	//id와 비번 체크 updatepass로 이동
	@PostMapping("/member/updatepass")
	public String updatePass(@RequestParam String num,
			@RequestParam String pass) {
		
		//db로부터 비번맞나 체크
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("num", num);
		map.put("pass", pass);
		
		int check=mapper.getCheckPass(map);
		
		//비번이 맞는 경우
		if(check==1) {
			
			//num에 해당하는 dto 가져와야 함
			return "redirect:updateform?num="+num;
		
		} else {
			
			return "/member/passfail";
		}
	}
	
	
	//비번이 맞을 경우 updateform으로 이동
	@GetMapping("/member/updateform")
	public ModelAndView updateForm(@RequestParam String num) {
		
		ModelAndView mview = new ModelAndView();
		
		//db로부터 num에 해당하는 dto 얻기
		MemberDto dto = mapper.getMember(num);
		
		//이메일 다시 분리한 후, dto에 담기
		String [] em = dto.getEmail().split("@");
		
		dto.setEmail1(em[0]);
		dto.setEmail2(em[1]);
		
		mview.addObject("dto",dto);
		
		mview.setViewName("/member/updateform");
		
		return mview;
	}
	
	
	
	//실제 업데이트
	@PostMapping("/member/update")
	public String update(@ModelAttribute MemberDto dto) {
		
		dto.setEmail(dto.getEmail1() + "@" + dto.getEmail2());
		
		mapper.updateMember(dto);
		
		return "redirect:list";
	}
}
