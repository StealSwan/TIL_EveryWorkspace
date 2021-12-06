package data.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("mdto")
public class MemberDto {

	private String num;
	private String name;
	private String id;
	private String pass;
	private String hp;
	private String addr;
	private String email;
	private String email1;
	private String email2;
	private Timestamp gaipday;

}
