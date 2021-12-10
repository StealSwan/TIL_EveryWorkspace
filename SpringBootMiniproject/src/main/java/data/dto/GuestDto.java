package data.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("gdto")
public class GuestDto {

   private String num;
   private String nickname;
   private String photo;
   private String content;
   private int likes;
   private Timestamp writeday;
}