package data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import data.dto.ShopDto;

@Mapper
public interface MysqlShopMapper {

	//총 갯수 - 이름이 Sql의 아이디가 됨
	public int getTotalCount();
	
	//인서트
	public void insertShop(ShopDto dto);
	
	//전체출력
	public List<ShopDto> getAllSangpums();
	
	//하나의 num에 해당하는 것 출력
	public ShopDto getData(String num);
	
	//업데이트
	public void updateShop(ShopDto dto);
	
	//삭제
	public void deleteShop(String num);
}
