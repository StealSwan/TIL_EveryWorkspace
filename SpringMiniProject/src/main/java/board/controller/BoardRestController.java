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
	
	
	//���̽����� �������� Ȯ��
	@GetMapping("/ajax/list2")
	public List<BoardDto> list1(){
		
		return dao.getPhotoDatas();
	}
	
	
	@GetMapping("/ajax/list1")
	public List<BoardDto> list2(){
		
		return dao.getAllDatas();
	}
	
	
	//���� ����
	@PostMapping("/mail/message")
	public Map<String, String> mailsend(
			@RequestParam String addr,
			@RequestParam String message){
		
		Map<String, String> map = new HashMap<String, String>();
		
		MimeMessage mes = mailSender.createMimeMessage();
		

		try {
			//���� ����
			mes.setSubject("���������κ��� �� ����");
			mes.setText(message);	//���Ϻ���
			mes.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(addr));	//�޴� ���
			
			//��������
			mailSender.send(mes);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			map.put("mes",addr+"�� ���Ϻ����� ����!!!");
		}
		
		
		return map;
	}
}
