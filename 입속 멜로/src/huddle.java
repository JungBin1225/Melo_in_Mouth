import java.awt.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class huddle extends Object //��ֹ� ��ġ�ϴ� Ŭ����
{
	int initX, initY; //ó�� ��ǥ
	int x, y; //���ŵǴ� ��ǥ
	int sizeX, sizeY; //ũ��
	
	public huddle(String img, int x, int y, int sizeX, int sizeY)//ó���� �̹��� �ּҿ� ��ǥ, ũ�⸦ �޾ƿ�
	{
		super(img, x, y, sizeX, sizeY);//�̹��� ����

		this.x=x; //��ǥ, ũ�⼳��
		this.y=y;
		this.initX=x;
		this.initY=y;
		this.sizeX=sizeX;
		this.sizeY=sizeY;
	}
	
	public void drawjump(Graphics2D g2d) //��ֹ� ��ġ
	{
		g2d.drawImage(this.getImage(),x, y, sizeX,sizeY, null);
	}
	
	public int getX() //��ֹ��� x��ǥ ����
	{
		return x;
	}
	
	public void setX(int x)//��ֹ��� x��ǥ �缳��
	{
		this.x = x;
	}
	
	public int getY()//��ֹ��� y��ǥ ����
	{
		return y;
	}
	
	public void setY(int y)//��ֹ��� y��ǥ �缳��
	{
		this.y = y;
	}

	public void go(int x)//��ֹ� �̵�
	{
		this.x-=x;
	}
}
