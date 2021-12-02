package boot.market.data;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("mdto")
public class MarketDto {
	
	private String num;
	private String sang;
	private int dan;
	private String photoname;
	private Timestamp ipgo;

}
