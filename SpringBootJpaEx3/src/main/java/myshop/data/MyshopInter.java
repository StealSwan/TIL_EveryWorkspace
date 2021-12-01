package myshop.data;

import org.springframework.data.jpa.repository.JpaRepository;


//2번째 방식 - 뒤는 primary key의 형태
public interface MyshopInter extends JpaRepository<MyshopDto, Integer>{

}
