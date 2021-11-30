package mycar.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


//1번째 방식
/*public interface MycarInter extends CrudRepository<MycarDto, Long> {

}*/


//2번째방식
public interface MycarInter extends JpaRepository<MycarDto, Long>{
	
}


