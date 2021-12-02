package boot.book.model;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("bdto")
public class BookDto {

	private String num;
	private String bookname;
	private int bookprice;
	private String bookphoto;
	private Timestamp ipgoday;
}
