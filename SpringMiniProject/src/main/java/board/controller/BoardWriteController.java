package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import board.data.BoardDao;
import board.data.BoardDto;

@Controller
public class BoardWriteController {

	@Autowired
	BoardDao dao;
	
	//���� �α��� ���̶� HttpSession ���� ����
	@GetMapping("/board/form")
	public ModelAndView form(HttpSession session) {
		
		ModelAndView mview = new ModelAndView();
		
		//���ǿ� ����� ���̵�
		String id=(String)session.getAttribute("myid");
		

		//���̵� �ش��ϴ� �̸�
		String name = dao.searchName(id);
		
		mview.addObject("name",name);
		
		//������
		mview.setViewName("/board/writeform");
		
		return mview;
	}
	
	
	//insert
	//actionó��
	//ArrayList<MultipartFile>�� �������� ���� ���ε��
	//HttpSession - session �α��ο�
	//currentPage - 
	@PostMapping("/board/insert")
	public String insert(
			@ModelAttribute BoardDto dto,
			@RequestParam ArrayList<MultipartFile> upload,
			HttpSession session
			//@RequestParam String currentPage
			) {
		
		
		//���ε��� �������
		String path = session.getServletContext().getRealPath("/WEB-INF/photo");
		
		String photo="";
		
		
		//���� ���� ������ ��� photo�� no��
		if(upload.get(0).getOriginalFilename().equals(""))
			photo="no";
		
		//�������� ��� ,�� ����
		else {
			
			//�� ���ε�� �����鸶�� �����̸� ������
			for(MultipartFile f:upload) {
				
				String fName = f.getOriginalFilename();
				
				//���ε�� ���� �̸��� ,�� �̿��� �̾���
				photo += fName + ",";
				
				
				try {
					//���ε�� �� ���� ����
					f.transferTo(new File(path+"\\"+fName));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//�� photo���� ������ ,�� ���ذ�
			photo=photo.substring(0, photo.length()-1);
		}
		
		
		//��� �̾��� photo�� �־���
		dto.setPhoto(photo);
		
		//dao�� insert ȣ��
		dao.insertBoard(dto);
		
		//������� �������� ���뺸��� �̵� - ���� ����
		//int num = dao.getMaxNum();
		
		return "/board/boardlist";
	}
}
