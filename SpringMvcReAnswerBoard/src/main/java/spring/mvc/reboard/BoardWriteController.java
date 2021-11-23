package spring.mvc.reboard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	
	//����, ��� ��� �ش�
	@GetMapping("/board/writeform")
	public ModelAndView form(
			@RequestParam Map<String, String> map) {
		
		ModelAndView mview = new ModelAndView();
		
		//5������ ����� ��츸 �Ѿ�´�(������ ��� �ȳѾ��)
		String currentPage = map.get("currentPage");
		String num = map.get("num");
		String regroup = map.get("regroup");
		String restep = map.get("restep");
		String relevel = map.get("relevel");
		
		//������ ���, null, ����� ��� ����
		//System.out.println(currentPage+","+num);
		
		//�Է����� hidden���� �־������... ��۴��
		mview.addObject("currentPage",currentPage==null?"1":currentPage);
		mview.addObject("num",num==null?"0":num);	//0�̶�� dao���� ���۷�
		mview.addObject("regroup",regroup==null?"0":regroup);
		mview.addObject("restep",restep==null?"0":restep);
		mview.addObject("relevel",relevel==null?"0":relevel);

		mview.setViewName("writeform");
		
		return mview;
	}
	
	
	//insert
	@PostMapping("/board/insert")
	public String insert(
			@ModelAttribute BoardDto dto,
			@RequestParam ArrayList<MultipartFile> upload,
			HttpSession session,
			@RequestParam String currentPage) {
		
		//���ε��� �������
		String path = session.getServletContext().getRealPath("/WEB-INF/photo");
		System.out.println(path);
		

		String photo="";
		
		//���� ������ ������ ��� no, ������� ,�� ����
		if(upload.get(0).getOriginalFilename().equals(""))
			photo="no";
		else {
			
			for(MultipartFile f:upload) {
				String fName = f.getOriginalFilename();
				photo += fName+",";
				
				//���ε�
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
			
			photo = photo.substring(0,photo.length()-1);
		}
		
		
		dto.setPhoto(photo);
		
		//dao�� insert ȣ��
		dao.insertBoard(dto);
		
		
		//������� �������� ���뺸��� ������
		int num=dao.getMaxNum();
		
		return "redirect:content?num="+num+"&currentPage="+currentPage;	//���߿� content�� �ٲ� ����
	}
}
