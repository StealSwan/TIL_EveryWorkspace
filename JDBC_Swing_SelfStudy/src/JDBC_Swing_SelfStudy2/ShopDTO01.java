package JDBC_Swing_SelfStudy2;

import java.util.Date;

public class ShopDTO01 {
	
	//������ ���� ��ü(data transfer object, DTO)�� ���μ��� ���� �����͸� �����ϴ� ��ü�̴�.
	
	//Oracle�� ������ ����
	private String num;
	private String sangpum;
	private String photo;
	private int su;
	private int dan;
	private Date guipday;
	
	
	//worker �޼���
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getSangpum() {
		return sangpum;
	}
	public void setSangpum(String sangpum) {
		this.sangpum = sangpum;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getSu() {
		return su;
	}
	public void setSu(int su) {
		this.su = su;
	}
	public int getDan() {
		return dan;
	}
	public void setDan(int dan) {
		this.dan = dan;
	}
	public Date getGuipday() {
		return guipday;
	}
	public void setGuipday(Date guipday) {
		this.guipday = guipday;
	}
	
	
	

}
