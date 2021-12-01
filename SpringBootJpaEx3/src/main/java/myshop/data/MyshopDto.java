package myshop.data;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;


//테이블 등록 3종 세트
@Entity
@Table(name = "myshop")
@Data
public class MyshopDto {

	@Id	//각 엔티티를 구별, 식별할 수 있도록 함
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int num;
	
	@Column
	private String sangpum;

	@Column
	private String price;

	@Column
	private String photoname;

	@Column	//달력날짜는 String 형
	private String ipgoday;

}
