package data.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("sdto")
public class ShopDto {

	private String num;
	private String sangpum;
	private String photoname;
	private int price;
	private Timestamp ipgoday;
}
