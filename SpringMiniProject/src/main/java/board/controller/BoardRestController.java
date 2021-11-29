package board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import board.data.BoardDao;
import board.data.BoardDto;

@RestController
public class BoardRestController {

	@Autowired
	BoardDao dao;
	
	
	@Autowired
	JavaMailSender mailSender;
	
	
	//제이슨으로 나오는지 확인
	@GetMapping("/ajax/list2")
	public List<BoardDto> list1(){
		
		return dao.getPhotoDatas();
	}
	
	
	@GetMapping("/ajax/list1")
	public List<BoardDto> list2(){
		
		return dao.getAllDatas();
	}
	
	
	//메일 전송
	@PostMapping("/mail/message")
	public Map<String, String> mailsend(
			@RequestParam String addr,
			@RequestParam String message){
		
		Map<String, String> map = new HashMap<String, String>();
		
		MimeMessage mes = mailSender.createMimeMessage();
		

		try {
			//메일 제목
			mes.setSubject("스프링으로부터 온 메일");
			mes.setText(message);	//메일본문
			mes.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(addr));	//받는 사람
			
			//메일전송
			mailSender.send(mes);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			map.put("mes",addr+"로 메일보내기 성공!!!");
		}
		
		
		return map;
	}
}
