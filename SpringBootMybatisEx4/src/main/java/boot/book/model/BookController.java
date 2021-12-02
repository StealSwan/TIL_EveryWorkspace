package boot.book.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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



@Controller
public class BookController {

	//인터페이스 호출
	@Autowired
	BookMapper mapper;
	
	
	//리스트 보이게
	@GetMapping("/booklist")
	public ModelAndView list() {
		
		ModelAndView mview = new ModelAndView();
		
		//db로부터 총갯수 출력
		int totalCount = mapper.getTotalCount();
		
		//값저장
		mview.addObject("totalCount",totalCount);
		
		
		//전체출력 추가
		//dao가 mapper - interface
		List<BookDto> list = mapper.getAllDatas();
		mview.addObject("list",list);
		
		
		//포워드
		mview.setViewName("/seojum/booklist");
		
		return mview;
	}
	
	
	//인서트폼으로 이동
	@GetMapping("/bookform")
	public String form() {
		
		return "/seojum/bookaddform";
	}
	
	
	//실제 인서트 - action 처리
	//여러 파일 업로드
	@PostMapping("/bookinsert")
	public String insert(@ModelAttribute BookDto dto,
			@RequestParam ArrayList<MultipartFile> upload,
			HttpSession session) {
		
		//저장경로
		String path = session.getServletContext().getRealPath("/bookimage");
		
		//여러 photo값을 담을 빈 문자열 선언
		String photo = "";
		
		
		//사진 선택을 안했을 경우, photo를 no로 설정
		if (upload.get(0).getOriginalFilename().equals("")) 
			photo="no";
		
		//선택했을경우
		else {
			
			//각 업로드된 사진들마다 파일이름 가져옴
			for(MultipartFile f: upload) {
				
				//개별 photo 이름
				String fName = f.getOriginalFilename();
				
				//개별 포토 이름 ,로 이어서 길게 만든다
				photo += fName + ",";
				
				try {
					
					//파일 경로와 이름 합친 풀네임
					//transferTo가 파일을 업로드해주는 과정
					f.transferTo(new File(path+"\\"+fName));
				
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//긴 photo에서 마지막 ,를 제거해준 것
			photo= photo.substring(0,photo.length()-1);
		}
		
		//모든 이어진 photo를 dto에 넣어줌
		dto.setBookphoto(photo);
		
		//실제 인서트
		mapper.insertBook(dto);
		
		return "redirect:booklist";
	}
	
	
	//수정폼 이동
	@GetMapping("/bookupdateform")
	public ModelAndView updateform(@RequestParam String num) {
		
		ModelAndView mview = new ModelAndView();
		
		//하나의 num에 해당하는 데이터 꾸러미
		BookDto dto = mapper.getData(num);
		
		//담기
		mview.addObject("dto",dto);
		
		//포워드
		mview.setViewName("/seojum/bookupdateform");
		
		return mview;
	}
	
	
	//실제 수정(insert 복사)
	@PostMapping("/bookupdate")
	public String update(@ModelAttribute BookDto dto,
			@RequestParam ArrayList<MultipartFile> upload,
			HttpSession session) {
		
		//저장경로
		String path = session.getServletContext().getRealPath("/bookimage");
		
		//여러 photo값을 담을 빈 문자열 선언
		String photo = "";
		
		
		//사진 선택을 안했을 경우, photo를 no로 설정
		if (upload.get(0).getOriginalFilename().equals("")) 
			photo="no";
		
		//선택했을경우
		else {
			
			//각 업로드된 사진들마다 파일이름 가져옴
			for(MultipartFile f: upload) {
				
				//개별 photo 이름
				String fName = f.getOriginalFilename();
				
				//개별 포토 이름 ,로 이어서 길게 만든다
				photo += fName + ",";
				
				try {
					
					//파일 경로와 이름 합친 풀네임
					//transferTo가 파일을 업로드해주는 과정
					f.transferTo(new File(path+"\\"+fName));
				
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//긴 photo에서 마지막 ,를 제거해준 것
			photo= photo.substring(0,photo.length()-1);
		}
		
		//모든 이어진 photo를 dto에 넣어줌
		dto.setBookphoto(photo);
		
		//실제 인서트
		mapper.updateBook(dto);
		
		return "redirect:booklist";
	}
	
	
	//삭제
	@GetMapping("/bookdelete")
	public String delete(@RequestParam String num) {
		
		mapper.deleteBook(num);
		
		return "redirect:booklist";
	}
}
