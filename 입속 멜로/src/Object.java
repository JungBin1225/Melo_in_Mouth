import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.*;


public class Object extends ImageIcon //huddle 클래스의 부모 클래스
{
	int initX, initY; //처음 좌표
	int x, y; //갱신되는 좌표
	int sizeX, sizeY; //크기
	
	public Object(String img, int x, int y, int sizeX, int sizeY)//처음에 이미지 주소와 좌표, 크기를 받아옴
	{
		super(img);//이미지 적용

		this.x=x; //좌표, 크기설정
		this.y=y;
		this.sizeX=sizeX;
		this.sizeY=sizeY;
	}
	
	public void drawjump(Graphics2D g2d) //장애물 배치
	{
		g2d.drawImage(this.getImage(),x, y, sizeX,sizeY, null);
	}
	
	public void setX(int x)//장애물의 x좌표 재설정
	{
		this.x = x;
	}
	
	public void setY(int y)//장애물의 y좌표 재설정
	{
		this.y = y;
	}
}
