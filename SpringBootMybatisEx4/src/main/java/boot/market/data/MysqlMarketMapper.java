package boot.market.data;

import java.util.List;

public interface MysqlMarketMapper {

	
	//총숫자
	public int getTotalCount();
	
	//전체출력
	public List<MarketDto> getAllDatas();
	
	//인서트
	public void insertMarket(MarketDto dto);
	
	//하나의 num에 해당하는 데이터 출력
	public MarketDto getData(String num);
	
	//실제 업데이트
	public void updateMarket(MarketDto dto);
	
	//딜리트
	public void deleteMarket(String num);
	
}
