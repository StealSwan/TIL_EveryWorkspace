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
public class BoardUpdateController {

	@Autowired
	BoardDao dao;
	
	
	@GetMapping("board/updateform")
	public ModelAndView updateform(
			@RequestParam int num,
			@RequestParam String currentPage) {
		
		ModelAndView mview= new ModelAndView();
		
		BoardDto dto = dao.getData(num);
		mview.addObject("dto",dto);
		mview.addObject("currentPage",currentPage);
		
		//������
		mview.setViewName("/board/updateform");
				
		return mview;
	}
	
	
	
	//������Ʈ
	@PostMapping("/board/update")
	public String insert(
			@ModelAttribute BoardDto dto,
			@RequestParam ArrayList<MultipartFile> upload,
			HttpSession session,
			@RequestParam String currentPage
			) {
		
		
		//���ε��� �������
		String path = session.getServletContext().getRealPath("/WEB-INF/photo");
		
		String photo="";
		
		
		//���� ���� ������ ��� photo�� no��
		if(upload.get(0).getOriginalFilename().equals(""))
			photo=null;
		
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
		
		
		//db�߰�
		dao.updateBoard(dto);
		
		
		//return "/board/boardlist";
		return "redirect:content?num="+dto.getNum()+"&currentPage="+currentPage;
	}
}
