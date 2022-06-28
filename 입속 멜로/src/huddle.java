import java.awt.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class huddle extends Object //장애물 배치하는 클래스
{
	int initX, initY; //처음 좌표
	int x, y; //갱신되는 좌표
	int sizeX, sizeY; //크기
	
	public huddle(String img, int x, int y, int sizeX, int sizeY)//처음에 이미지 주소와 좌표, 크기를 받아옴
	{
		super(img, x, y, sizeX, sizeY);//이미지 적용

		this.x=x; //좌표, 크기설정
		this.y=y;
		this.initX=x;
		this.initY=y;
		this.sizeX=sizeX;
		this.sizeY=sizeY;
	}
	
	public void drawjump(Graphics2D g2d) //장애물 배치
	{
		g2d.drawImage(this.getImage(),x, y, sizeX,sizeY, null);
	}
	
	public int getX() //장애물의 x좌표 리턴
	{
		return x;
	}
	
	public void setX(int x)//장애물의 x좌표 재설정
	{
		this.x = x;
	}
	
	public int getY()//장애물의 y좌표 리턴
	{
		return y;
	}
	
	public void setY(int y)//장애물의 y좌표 재설정
	{
		this.y = y;
	}

	public void go(int x)//장애물 이동
	{
		this.x-=x;
	}
}
