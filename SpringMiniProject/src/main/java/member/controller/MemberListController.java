package member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import member.data.MemberDao;
import member.data.MemberDto;

@Controller
public class MemberListController {
	
	@Autowired
	MemberDao dao;
	
	
	//list�� �̵� - ����� ��ҵ� ����ֱ�
	@GetMapping("/member/list")
	public ModelAndView list() {
		
		ModelAndView model = new ModelAndView();
		
		//��� ������ List��
		List<MemberDto> list = dao.getAllMembers();
		
		//���� ����� list�� model Object �������� ��´�
		model.addObject("list",list);
		
		//������ - return���� ���� - model ���� ����
		model.setViewName("/member/memberlist");
		
		//return�����δ� model
		return model;
	}
	
	
	
}
