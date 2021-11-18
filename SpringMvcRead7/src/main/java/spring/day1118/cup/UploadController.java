package spring.day1118.cup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {

	@GetMapping("/board/uploadform1")
	public String form1()
	{
		return "board/uploadform1";
	}
	
	@PostMapping("/board/upload1")
	public ModelAndView read1(
			@RequestParam String title,
			@RequestParam MultipartFile photo,
			HttpServletRequest request
			)
	{
		ModelAndView mview=new ModelAndView();
		//���ε��� ������α��ϱ�
		String path=request.getSession().getServletContext().getRealPath("/WEB-INF/image");
		String fileName=photo.getOriginalFilename(); //���ε��� ���ϸ�
		
		
		if(!fileName.equals("")) //���ε��� ������ �ִٸ�
		{
			//path�� ���ε��ϱ�
			try {
				photo.transferTo(new File(path+"\\"+fileName));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			fileName="no";  //���ε� ���������
		}
		
		mview.addObject("fileName", fileName);
		mview.addObject("title",title);
		mview.addObject("path",path); //�������
		
		mview.setViewName("board/uploadresult1");
		return mview;
	}
	
	@GetMapping("/board/uploadform2")
	public String form2()
	{
		return "board/uploadform2";
	}
	
	@PostMapping("/board/upload2")
	public ModelAndView read1(
			@RequestParam String title,
			@RequestParam ArrayList<MultipartFile> photo,
			HttpServletRequest request
			)
	{
		ModelAndView mview=new ModelAndView();
		//���ε��� ������α��ϱ�
		String path=request.getSession().getServletContext().getRealPath("/WEB-INF/image");
		
		ArrayList<String> files=new ArrayList<String>();
		
		//���ϸ� ���
		for(MultipartFile f:photo)
		{
			String fileName=f.getOriginalFilename();
			files.add(fileName);
			
			//���ε�
			try {
				f.transferTo(new File(path+"\\"+fileName));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		mview.addObject("files", files);
		mview.addObject("title",title);
		mview.addObject("path",path); //�������
		
		mview.setViewName("board/uploadresult2");
		return mview;
	}
	
}
