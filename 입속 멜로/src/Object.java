import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.*;


public class Object extends ImageIcon //huddle Ŭ������ �θ� Ŭ����
{
	int initX, initY; //ó�� ��ǥ
	int x, y; //���ŵǴ� ��ǥ
	int sizeX, sizeY; //ũ��
	
	public Object(String img, int x, int y, int sizeX, int sizeY)//ó���� �̹��� �ּҿ� ��ǥ, ũ�⸦ �޾ƿ�
	{
		super(img);//�̹��� ����

		this.x=x; //��ǥ, ũ�⼳��
		this.y=y;
		this.sizeX=sizeX;
		this.sizeY=sizeY;
	}
	
	public void drawjump(Graphics2D g2d) //��ֹ� ��ġ
	{
		g2d.drawImage(this.getImage(),x, y, sizeX,sizeY, null);
	}
	
	public void setX(int x)//��ֹ��� x��ǥ �缳��
	{
		this.x = x;
	}
	
	public void setY(int y)//��ֹ��� y��ǥ �缳��
	{
		this.y = y;
	}
}
