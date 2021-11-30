package sist.hello;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/my/hello")
	public HashMap<String, String> hello(){
		
		HashMap<String, String> data = new HashMap<String, String>();
		
		data.put("message", "Hello~~ Welcome!!");
		
		return data;
	}
}
