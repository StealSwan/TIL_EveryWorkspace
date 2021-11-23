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
	
	//새글, 답글 모두 해당
	@GetMapping("/board/writeform")
	public ModelAndView form(
			@RequestParam Map<String, String> map) {
		
		ModelAndView mview = new ModelAndView();
		
		//5개값은 답글인 경우만 넘어온다(새글일 경우 안넘어옴)
		String currentPage = map.get("currentPage");
		String num = map.get("num");
		String regroup = map.get("regroup");
		String restep = map.get("restep");
		String relevel = map.get("relevel");
		
		//새글인 경우, null, 답글일 경우 숫자
		//System.out.println(currentPage+","+num);
		
		//입력폼에 hidden으로 넣어줘야함... 답글대비
		mview.addObject("currentPage",currentPage==null?"1":currentPage);
		mview.addObject("num",num==null?"0":num);	//0이라야 dao에서 새글로
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
		
		//업로드할 실제경로
		String path = session.getServletContext().getRealPath("/WEB-INF/photo");
		System.out.println(path);
		

		String photo="";
		
		//사진 선택을 안했을 경우 no, 했을경우 ,로 나열
		if(upload.get(0).getOriginalFilename().equals(""))
			photo="no";
		else {
			
			for(MultipartFile f:upload) {
				String fName = f.getOriginalFilename();
				photo += fName+",";
				
				//업로드
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
		
		//dao의 insert 호출
		dao.insertBoard(dto);
		
		
		//목록으로 가지말고 내용보기로 가려면
		int num=dao.getMaxNum();
		
		return "redirect:content?num="+num+"&currentPage="+currentPage;	//나중에 content로 바꿀 예정
	}
}
