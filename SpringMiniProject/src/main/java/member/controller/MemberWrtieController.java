package member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import member.data.MemberDao;
import member.data.MemberDto;

@Controller
public class MemberWrtieController {
	
	@Autowired
	MemberDao dao;
	
	
	//������ �̵�
	@GetMapping("/member/form")
	public String writeform() {
		
		return "/member/memberform";	//1(������)/2(���ϸ�)
	}
	
	
	//�Ϲ� ��Ʈ�ѷ����� JSON���·� �ѱ�� - id �ߺ�üũ
	@GetMapping("/member/idcheck")
	public @ResponseBody Map<String, Integer> idCheck(
			@RequestParam String id){ //id�� �޾ƿ���
		
		//map�� �������̽� �̹Ƿ� ���� X - HashMap���� ��
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		//dao ȣ��
		int count = dao.getIdCount(id);
		
		//0���� 1����
		map.put("count", count); 
		
		return map;
	}
	
	
	//insert ��Ű�� - form ���� [���Ⱑ action] 
	//return���� ������ �����带 ���� ��
	@PostMapping("/member/insert")
	public String insert(@ModelAttribute MemberDto dto) {
		
		//dao ȣ���ؼ� insert �۾��� �̷����
		dao.insertMember(dto);
		
		//������
		return "redirect:list";
	}	
}
