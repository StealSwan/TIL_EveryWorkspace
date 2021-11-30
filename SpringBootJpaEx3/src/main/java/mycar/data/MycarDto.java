package mycar.data;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "mycar")
@Data
public class MycarDto {

	@Id //각 엔티티를 구별할 수 있도록 식별아이디 갖게 설계 (시퀀스)
	@GeneratedValue(strategy = GenerationType.AUTO) //자동증가
	private long num;
	
	@Column(name = "carname") //각각의 컬럼이라고 표시 - 같으면 name 생략가능
	private String carname;
	
	@Column
	private String carprice;

	@Column
	private String carcolor;	
	
	@Column
	private String carguip;

	@CreationTimestamp //엔티티가 생성되는 그 시점의 타임을 자동등록해준다
	private Timestamp guipday;
}
