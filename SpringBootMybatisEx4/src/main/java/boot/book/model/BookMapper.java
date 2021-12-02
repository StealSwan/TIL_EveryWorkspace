package boot.book.model;

import java.util.List;

public interface BookMapper {

	//총숫자
	public int getTotalCount();
	
	//전체출력
	public List<BookDto> getAllDatas();
		
	//인서트
	public void insertBook(BookDto dto);
	
	//num에 해당하는 데이터
	public BookDto getData(String num);
	
	//업데이트
	public void updateBook(BookDto dto);
	
	//딜리트
	public void deleteBook(String num);

}
