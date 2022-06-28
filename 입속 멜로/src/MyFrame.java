import java.applet.Applet;
import java.applet.AudioClip;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class MyFrame 
{
	JFrame frame=new JFrame();
	
	final int ALL_WIDTH = 1300; 		// ��ü frame�� ��
	final int ALL_HEIGHT = 836; 	// ��ü frame�� ����
	final int M_WIDTH=1430; 	// ���� ������ �۵��ϴ� ����
	final int M_HEIGHT=760;
	
	boolean run = true;	
	boolean floor = true;
	boolean jump = false;
	boolean Djump = false;
	boolean death = false;
	boolean firstRound = true;
	boolean secondRound = false;
	boolean thirdRound = false;
	boolean isHuddle = false;
	boolean isOpening = true;
	boolean isHowTo = true;
	boolean isClear = false;
	boolean isrestart = false;
	
	JPanel controlPanel=new JPanel();		// ���� ��Ʈ�Ѱ� �ð�, ����� ���÷��̰� �� �г�

	JLayeredPane lp = new JLayeredPane();	// ȭ���� ������ ��ġ�� ���� PaneL ���̾�
	GamePanel  gamepanel=new GamePanel();	// ���� ����
	StartPanel startpanel = new StartPanel();
	endPanel endpanel = new endPanel();
	
	Timer goGround; //���� ��ֹ� ������
	
	Timer goChar;	//ĳ���� ������
	Timer goTime;	//Ÿ�̸�
	
	ClockListener clockListener; //�ð��� �޾ƿ��� ���� ������
		
	URL url1 = getClass().getClassLoader().getResource("music/firstmusic.wav"); // ���� ���� ��ġ
	URL url2 = getClass().getClassLoader().getResource("music/secondmusic.wav");
	URL url3 = getClass().getClassLoader().getResource("music/thirdmusic.wav");
	
	//AudioClip firstmusic = Applet.newAudioClip(url1); //���� ����
	//AudioClip secondmusic = Applet.newAudioClip(url2);
	//AudioClip thirdmusic = Applet.newAudioClip(url3);
	
	ImageIcon startscreen = new ImageIcon("src/image/startBackground.png");
	
	JButton startbutton = new JButton(new ImageIcon("src/image/startbotton.png"));
	JButton howtobutton = new JButton(new ImageIcon("src/image/howtobotton.png"));
	JButton restartbutton = new JButton(new ImageIcon("src/image/skipbutton.png"));
	
	ImageIcon opening1 = new ImageIcon("src/image/opening_1.png");
	ImageIcon opening2 = new ImageIcon("src/image/opening_2.png");
	ImageIcon opening3 = new ImageIcon("src/image/opening_3.png");
	ImageIcon opening4 = new ImageIcon("src/image/opening_4.png");
	ImageIcon opening5 = new ImageIcon("src/image/opening_5.png");
	
	ImageIcon howto = new ImageIcon("src/image/howto.png");
	
	ImageIcon ending1 = new ImageIcon("src/image/goodEnd_1.png");
	ImageIcon ending2 = new ImageIcon("src/image/goodEnd_2.png");
	ImageIcon ending3 = new ImageIcon("src/image/goodEnd_3.png");
	ImageIcon ending4 = new ImageIcon("src/image/goodEnd_4.png");
	ImageIcon ending5 = new ImageIcon("src/image/goodEnd_5.png");
	ImageIcon ending6 = new ImageIcon("src/image/badEnd_S1.png");
	ImageIcon ending7 = new ImageIcon("src/image/badEnd_S2.png");
	ImageIcon ending8 = new ImageIcon("src/image/badEnd_S3.png");
	
	ImageIcon endingbackground = new ImageIcon("src/image/finalBackground.png");
	
	ImageIcon firstBack = new ImageIcon("src/image/background1.png"); //1�������� ���
	ImageIcon secondBack = new ImageIcon("src/image/background2.png"); //2�������� ���
	ImageIcon thirdBack = new ImageIcon("src/image/background3.png"); //3�������� ���
	ImageIcon change = new ImageIcon("src/image/change.png"); //�������� ��ȯ�� ������ ���� ȭ��
	ImageIcon changemessage1 = new ImageIcon("src/image/stagemessage1.png");
	ImageIcon changemessage2 = new ImageIcon("src/image/stagemessage2.png");
	ImageIcon childface = new ImageIcon("src/image/childface.png");
	
	ImageIcon g_character1 = new ImageIcon("src/image/char1.png"); //ĳ���� �׸���
	ImageIcon g_character2 = new ImageIcon("src/image/char2.png");
	ImageIcon g_character3 = new ImageIcon("src/image/char3.png");
	
	ImageIcon secondcharacter1 = new ImageIcon("src/image/secondchar1.png");
	ImageIcon secondcharacter2 = new ImageIcon("src/image/secondchar2.png");
	ImageIcon secondcharacter3 = new ImageIcon("src/image/secondchar3.png");
	
	ImageIcon j_char0 = new ImageIcon("src/image/jump1.png"); //���������� ĳ���� �׸���
	ImageIcon j_char1 = new ImageIcon("src/image/jump2.png");
	ImageIcon j_char2 = new ImageIcon("src/image/jump3.png");
	
	ImageIcon secondjump1 = new ImageIcon("src/image/secondjump1.png");
	ImageIcon secondjump2 = new ImageIcon("src/image/secondjump2.png");
	ImageIcon secondjump3 = new ImageIcon("src/image/secondjump3.png");
	
	ImageIcon first1 = new ImageIcon("src/image/1st_1.jpg"); //���� (���� �ȳ���)
	ImageIcon first2 = new ImageIcon("src/image/1st_2.jpg");
	ImageIcon first3 = new ImageIcon("src/image/1st_3.jpg");
	ImageIcon first4 = new ImageIcon("src/image/1st_4.jpg");
	
	ImageIcon second1 = new ImageIcon("src/image/2nd.png");
	ImageIcon second2 = new ImageIcon("src/image/2nd.png");
	ImageIcon second3 = new ImageIcon("src/image/2nd.png");
	ImageIcon second4 = new ImageIcon("src/image/2nd.png");
	
	ImageIcon third1 = new ImageIcon("src/image/3rd.png");
	ImageIcon third2 = new ImageIcon("src/image/3rd.png");
	ImageIcon third3 = new ImageIcon("src/image/3rd.png");
	ImageIcon third4 = new ImageIcon("src/image/3rd.png");
	
	huddle Round1_obs1 = new huddle("src/image/obs1-1.png", 600, 661, 54, 108); //��ֹ�
	huddle Round1_obs2 = new huddle("src/image/obs1-2.png", 600, 661, 54, 108);
	huddle Round1_obs3 = new huddle("src/image/obs1-3.png", 600, 571, 90, 180);
	huddle Round1_obs4 = new huddle("src/image/obs1-4.png", 600, 661, 54, 108);
	huddle Round1_obs5 = new huddle("src/image/obs1-5.png", 600, 661, 54, 108);
	
	huddle Round2_obs1 = new huddle("src/image/obs2-1.png", 600, 661, 108, 108);
	huddle Round2_obs2 = new huddle("src/image/obs2-2.png", 600, 661, 108, 108);
	huddle Round2_obs3 = new huddle("src/image/obs2-3.png", 600, 661, 54, 108);
	huddle Round2_obs4 = new huddle("src/image/obs2-4.png", 600, 661, 54, 108);
	huddle Round2_obs5 = new huddle("src/image/obs2-5.png", 600, 661, 54, 108);
	huddle Round2_obs6 = new huddle("src/image/obs2-6.png", 600, 661, 54, 108);
	
	huddle Round3_obs1 = new huddle("src/image/obs3-1.png", 600, 571, 90, 180);
	huddle Round3_obs2 = new huddle("src/image/obs3-2.png", 600, 661, 250, 500);
	huddle Round3_obs3 = new huddle("src/image/obs3-3.png", 600, 661, 180, 90);
	huddle Round3_obs4 = new huddle("src/image/obs3-4.png", 600, 661, 180, 180); // ������� ��ֹ�
	
	int openingcnt = 0;
	int endingcnt = 0;
	
	int first1Px = 0; // ���� x��ǥ��
	int first2Px = 8070;
	int first3Px = 14340;
	int first4Px = 26010;
	
	int first1Py = 756; // ���� y��ǥ��
	int first2Py = 756;
	int first3Py = 756;
	int first4Py = 756;
	
	int second1Px = 0; // ���� x��ǥ��
	int second2Px = 8070;
	int second3Px = 14340;
	int second4Px = 26010;
	
	int second1Py = 0; // ���� y��ǥ��
	int second2Py = 0;
	int second3Py = 0;
	int second4Py = 0;
	
	int third1Px = 0; // ���� x��ǥ��
	int third2Px = 8070;
	int third3Px = 14340;
	int third4Px = 26010;
	
	int third1Py = 756; // ���� y��ǥ��
	int third2Py = 756;
	int third3Py = 756;
	int third4Py = 756;
	
	int childX = 34000;
	
	int Randomobs = 0; //��ֹ� �������� �޾ƿ���
	int stepHuddle=400; //��ֹ��� �ʱ� ��ġ
	
	int hurddleReturnX=0; //��ֹ��� ���� x��ǥ �޾ƿ�
	int hurddleReturnX2=0; 
	int hurddleReturnX3=0;
	
	ArrayList<ImageIcon> generalChar; //ĳ���� �׸����� �迭�� ����
	ArrayList<ImageIcon> jumpChar;	//������ ĳ���� �׸����� �迭�� ����
	ArrayList<ImageIcon> secondChar;
	ArrayList<ImageIcon> secondjump;
	
	int deathcnt = 0;
	int Cokiecnt = 0; //�̰ɷ� ĳ���� ������ ����
	int Cokiecnt2 = 0; // ������ �� ���� �ӵ� ����
	double jump_rate; // �������� �� ���� �ִ� y��ǥ �޾ƿ�
	
	int back1X = 0; //���� x��ǥ
	int back2X = 0;
	int back3X = 0;
	int changeX = 1300; //��ȯ�Ҷ� ���� ȭ��� �ʱ�x��ǥ
	int change2X = 1300;
	
	int CharPx = 120; //ĳ���� �ʱ� x,y ��ǥ
	int CharPy = 636;
	int secondCharPx = 120;
	int secondCharPy = 50;
	
	int times = 0; //�ð�
	
	public static void main(String[] args)
	{
		
		MyFrame myframe = new MyFrame(); //������ ����
		myframe.go();	//�����Լ��� �ִ��� ª�� �ϴ°� �������� �����Ͻ� ^��^
	}
	
	public void go()
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lp.add(startpanel, new Integer(2));
		startpanel.setSize(ALL_WIDTH, 806);
		lp.add(gamepanel, new Integer(1)); 						// Integer�� ���̾� ���� ����
		gamepanel.setSize(M_WIDTH, 836);						//���ΰ��� ������
		lp.add(endpanel,new Integer(0)); 						// �гε� ��ȯ
		endpanel.setSize(M_WIDTH, 836);
		frame.add(lp); 
		frame.setTitle("�Լ� ���");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(ALL_WIDTH, ALL_HEIGHT);					//������ ũ��

		goGround = new Timer(25,new GroundListener() );	//���� ��ֹ� ������
		goChar = new Timer(25,new CookieListener());	 //ĳ���� ������
		goTime = new Timer(30,new ClockListener());		//1�� ������ ���� ���ŵ�
		
		gamepanel.addKeyListener(new DirectionListener());	//Ű ������ ����
		startbutton.addMouseListener(new buttonListener()); //��ư�� ���콺 ������
		howtobutton.addMouseListener(new HowToListener());
		restartbutton.addMouseListener(new endListener());
		
		generalChar = new ArrayList<ImageIcon>(); //ĳ���� ������ �迭
		{
			generalChar.add(g_character1);//0
			generalChar.add(g_character2);//1
			generalChar.add(g_character3);//2recipe
		}
		
		jumpChar = new ArrayList<ImageIcon>(); // ���� ĳ���� �迭
		{
			jumpChar.add(j_char0);
			jumpChar.add(j_char1);
			jumpChar.add(j_char2);
		}
		
		secondChar = new ArrayList<ImageIcon>();
		{
			secondChar.add(secondcharacter1);
			secondChar.add(secondcharacter2);
			secondChar.add(secondcharacter3);
		}
		
		secondjump = new ArrayList<ImageIcon>();
		{
			secondjump.add(secondjump1);
			secondjump.add(secondjump2);
			secondjump.add(secondjump3);
			
		}
		
	}
	
	private class ClockListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{
			times++; //���� �������� �ҷ���
		}
	}
	
	public class GroundListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			if (firstRound == true)
			{
				first1Px -= 9; //���� ������
				first2Px -= 9;
				first3Px -= 9;
				first4Px -= 9;
			}
			else if(secondRound == true)
			{
				second1Px -= 14; //���� ������
				second2Px -= 14;
				second3Px -= 14;
				second4Px -= 14;
			}
			else if (thirdRound == true)
			{
				third1Px -= 18; //���� ������
				third2Px -= 18;
				third3Px -= 18;
				third4Px -= 18;
				childX -= 18;
			}
			if(changeX > -3900 && changeX < 1300) //ȭ�� ��ȯ�� �� ��ֹ� �ȳ����� ��
			{
				isHuddle = true;
			}
			else if(change2X > -3900 && change2X < 1000) //ȭ�� ��ȯ�� �� ��ֹ� �ȳ����� ��
			{
				isHuddle = true;
			}
			
			if(secondRound == true)
			{
				if(hurddleReturnX < -250) //ȭ�� ��ȯ ������ �ٽ� ��ֹ� ����
				{
					isHuddle = false;
				}
			}
			else if(thirdRound == true)
			{
				if(hurddleReturnX < -220)
				{
					isHuddle = false;
				}
			}
			else if(hurddleReturnX<-350) //��ֹ��� ���� ��ǥ �ڷ� ���� ���� ��ֹ��� ����
			{
				isHuddle = false;
			}
			
			if(Round1_obs1.getX()>-400) // ù��° �������� ��ֹ� ����
			{
				Round1_obs1.go(9);// ��ֹ��� �̵��ӵ�
			}
			if(Round1_obs2.getX()>-400)
			{
				Round1_obs2.go(9);
			}
			if(Round1_obs3.getX()>-400)
			{
				Round1_obs3.go(9);
			}
			if(Round1_obs4.getX()>-400)
			{
				Round1_obs4.go(9);
			}
			if(Round1_obs5.getX()>-400)
			{
				Round1_obs5.go(9);
			}
			if(Round2_obs1.getX()>-400)// �ι�° �������� ��ֹ� ����
			{
				Round2_obs1.go(14);// ��ֹ��� �̵��ӵ�
			}
			if(Round2_obs2.getX()>-400)
			{
				Round2_obs2.go(14);
			}
			if(Round2_obs3.getX()>-400)
			{
				Round2_obs3.go(14);
			}
			if(Round2_obs4.getX()>-400)
			{
				Round2_obs4.go(14);
			}
			if(Round2_obs5.getX()>-400)
			{
				Round2_obs5.go(14);
			}
			if(Round2_obs6.getX()>-400)
			{
				Round2_obs6.go(14);
			}
			if(Round3_obs1.getX()>-400) // ����° �������� ��ֹ� ����
			{
				Round3_obs1.go(18);// ��ֹ��� �̵��ӵ�
			}
			if(Round3_obs2.getX()>-400)
			{
				Round3_obs2.go(18);
			}
			if(Round3_obs3.getX()>-400)
			{
				Round3_obs3.go(18);
			}
			if(Round3_obs4.getX()>-400)
			{
				Round3_obs4.go(18);
			}

			if (firstRound == true)
			{
				back1X -= 1; //��� ������
			}
			else if (secondRound == true)
			{
				back2X -= 2; //��� ������
			}
			else if (thirdRound == true)
			{
				back3X -= 2; //��� ������
			}
			
			if(back2X <= -3900) //��ȯ ȭ�� ������ �ϴ°�
			{
				change2X -= 30;
				if(change2X <= -1000)
				{
					firstRound = false;
					secondRound = false;
					thirdRound = true;
				}
				if(change2X <= -3900 && change2X >= -3950)
				{
					//secondmusic.stop();
					//thirdmusic.play();
					isHuddle = false;
				}
			}
			else if(back1X <= -2000) //��ȯ ȭ�� ������ �ϴ°�
			{
				changeX -= 30;
				if(changeX <= -1000)
				{
					firstRound = false;
					secondRound = true;
					thirdRound = false;
				}
				if(changeX <= -3900 && changeX >= -3950)
				{
					//firstmusic.stop();
					//secondmusic.play();
					isHuddle = false;
				}
			}
			
			frame.repaint(); // 1/40�ʸ��� ���ŵ�
			frame.setVisible(true);
		}
	}
	
	public class CookieListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			if(childX <= 500)
			{
				CharPx += 18;
			}
			
			if(run == true) //���� ���ϰ� ���� ��
				{
					if(death == false)
					{
						Cokiecnt++; //0, 1, 2 ��� ���ư��� ����� ĳ���� ������ ����
						if(Cokiecnt == 3)
						{
							Cokiecnt = 0;
						}
					}
					else //�׾��� �� ���߰� ��
					{
						Cokiecnt += 0;
					}
				}
			
			else if(jump == true) //�������� ��
			{
				if(firstRound == true || thirdRound == true)
				{
					if(Cokiecnt2 == 1)
					{
						CharPy -= 20; //�ð��� �����鼭 Cokiecnt2�� �����Կ� ���� ���� �ӵ� ����
					}	
					else if(Cokiecnt2 == 2)
					{
						CharPy -= 8;
					}
					else if(Cokiecnt2 == 3)
					{
						CharPy -= 5;
					}
					else if(Cokiecnt2 == 4)
					{
						CharPy -= 3;
					}
					else if(Cokiecnt2 == 5)
					{
						CharPy -= 1;
					}
					else if(Cokiecnt2 == 6)
					{
						CharPy += 4;
					}
					else if(Cokiecnt2 == 7)
					{
						CharPy += 8;
					}
					else if(Cokiecnt2 == 8)
					{
						CharPy += 15;
					}
					else if(Cokiecnt2 == 9)
					{
						CharPy += 10;
					}
					Cokiecnt++;
					
					if(Cokiecnt == 3)
					{
						Cokiecnt2++; //3�������� ������ �ϳ��� ����
						if(Cokiecnt2 == 10) //10�� �Ǹ� ������ ĳ���� ��� ����
						{
							Cokiecnt = 3;
						}
						else
						{
							Cokiecnt = 0;
						}
					}
					else if(Cokiecnt > 4)// ������ ������
					{
						Cokiecnt=0;
						Cokiecnt2=0;
						jump=false; //�ٽ� ������ false
						Djump=false;
						run=true; //���� �ٽ� true
						CharPy=636;
					}
				}
				if(secondRound == true)
				{
					if(Cokiecnt2 == 1)
					{
						secondCharPy += 20; //�ð��� �����鼭 Cokiecnt2�� �����Կ� ���� ���� �ӵ� ����
					}	
					else if(Cokiecnt2 == 2)
					{
						secondCharPy += 8;
					}
					else if(Cokiecnt2 == 3)
					{
						secondCharPy += 5;
					}
					else if(Cokiecnt2 == 4)
					{
						secondCharPy += 3;
					}
					else if(Cokiecnt2 == 5)
					{
						secondCharPy += 1;
					}
					else if(Cokiecnt2 == 6)
					{
						secondCharPy -= 4;
					}
					else if(Cokiecnt2 == 7)
					{
						secondCharPy -= 8;
					}
					else if(Cokiecnt2 == 8)
					{
						secondCharPy -= 15;
					}
					else if(Cokiecnt2 == 9)
					{
						secondCharPy -= 10;
					}
					Cokiecnt++;
					
					if(Cokiecnt == 3)
					{
						Cokiecnt2++; //3�������� ������ �ϳ��� ����
						if(Cokiecnt2 == 10) //10�� �Ǹ� ������ ĳ���� ��� ����
						{
							Cokiecnt = 3;
						}
						else
						{
							Cokiecnt = 0;
						}
					}
					else if(Cokiecnt > 4)// ������ ������
					{
						Cokiecnt=0;
						Cokiecnt2=0;
						jump=false; //�ٽ� ������ false
						Djump=false;
						run=true; //���� �ٽ� true
						secondCharPy=50;
					}
				}
			}
			else if (Djump == true) //�������� ��
			{	
				if(firstRound == true || thirdRound == true)
				{
					if(Cokiecnt2 == 1)
					{
						CharPy -= 20; //������ ����
					}	
					else if(Cokiecnt2 == 2)
					{
						CharPy -= 8;
					}
					else if(Cokiecnt2 == 3)
					{
						CharPy -= 5;
					}
					else if(Cokiecnt2 == 4)
					{
						CharPy -= 3;
					}
					else if(Cokiecnt2 == 5)
					{
						CharPy -= 1;
					}
					else if(Cokiecnt2 == 6)
					{
						CharPy += 4;
					}
					else if(Cokiecnt2 == 7)
					{
						CharPy += 8;
					}
					else if(Cokiecnt2 == 8)
					{
						CharPy += 15;
					}
					else if(Cokiecnt2 == 9)
					{
						CharPy += 10;
					}
					else if(Cokiecnt2 == 10)
					{
						CharPy += (jump_rate / 9); //���������� �����ߴ� �κк��� ������ ���Ͻ�Ŵ
					}
					else if(Cokiecnt2 == 11)
					{
						CharPy += (jump_rate / 9);
					}
					else if(Cokiecnt2 == 12)
					{
						CharPy += (jump_rate / 9);
					}
					Cokiecnt++;
					
					if(Cokiecnt == 3)//������ ����
					{
						Cokiecnt2++;
						if(Cokiecnt2 == 13)
						{
							Cokiecnt = 3;
						}
						else
						{
							Cokiecnt = 0;
						}
					}
					if(Cokiecnt > 4) //������ ����
					{
						Cokiecnt=0;
						Cokiecnt2=0;
						Djump=false;
						jump = false;
						run=true;
						CharPy=636;
					}
				}
				if(secondRound == true)
				{
					if(Cokiecnt2 == 1)
					{
						secondCharPy += 20; //������ ����
					}	
					else if(Cokiecnt2 == 2)
					{
						secondCharPy += 8;
					}
					else if(Cokiecnt2 == 3)
					{
						secondCharPy += 5;
					}
					else if(Cokiecnt2 == 4)
					{
						secondCharPy += 3;
					}
					else if(Cokiecnt2 == 5)
					{
						secondCharPy += 1;
					}
					else if(Cokiecnt2 == 6)
					{
						secondCharPy -= 4;
					}
					else if(Cokiecnt2 == 7)
					{
						secondCharPy -= 8;
					}
					else if(Cokiecnt2 == 8)
					{
						secondCharPy -= 15;
					}
					else if(Cokiecnt2 == 9)
					{
						secondCharPy -= 10;
					}
					else if(Cokiecnt2 == 10)
					{
						secondCharPy -= (jump_rate / 9); //���������� �����ߴ� �κк��� ������ ���Ͻ�Ŵ
					}
					else if(Cokiecnt2 == 11)
					{
						secondCharPy -= (jump_rate / 9);
					}
					else if(Cokiecnt2 == 12)
					{
						secondCharPy -= (jump_rate / 9);
					}
					Cokiecnt++;
					
					if(Cokiecnt == 3)//������ ����
					{
						Cokiecnt2++;
						if(Cokiecnt2 == 13)
						{
							Cokiecnt = 3;
						}
						else
						{
							Cokiecnt = 0;
						}
					}
					if(Cokiecnt > 4) //������ ����
					{
						Cokiecnt=0;
						Cokiecnt2=0;
						Djump=false;
						jump = false;
						run=true;
						secondCharPy=50;
					}
				}
			}
			if(firstRound == true) //ù��° �������� ��ֹ� �΋H�� ����
			{
				if(Randomobs == 3) //2ĭ¥�� ��ֹ�
				{
					if(hurddleReturnX - CharPx < 75 && hurddleReturnX - CharPx > -38 && CharPy >= 480)
					{
						death = true;
					}
				}
				else // �ٸ� ��ֹ�
				{
					if(hurddleReturnX - CharPx < 75 && hurddleReturnX - CharPx > -20 && CharPy >= 540)
					{
						death = true;
					}
				}
			}
			else if(secondRound == true) //�ι�° �������� ��ֹ� �΋H�� ����
			{
				if(Randomobs == 1 || Randomobs == 2)
				{
					if(hurddleReturnX - secondCharPx < 75 && hurddleReturnX - secondCharPx > -38 && secondCharPy <= 120)
					{
						death = true;
					}
				}
				else
				{
					if(hurddleReturnX - secondCharPx < 75 && hurddleReturnX - secondCharPx > -25 && secondCharPy <= 120)
					{
						death = true;
					}
				}
			}
			else if(thirdRound == true)
			{
				if(Randomobs == 1)
				{
					if(hurddleReturnX - CharPx < 75 && hurddleReturnX - CharPx > -38 && CharPy >= 480)
					{
						death = true;
					}
				}
				else if(Randomobs == 2)
				{
					if(hurddleReturnX - CharPx < 75 && hurddleReturnX - CharPx > -38 && CharPy <= 480)
					{
						death = true;
					}
				}
				else if(Randomobs == 3)
				{
					if(hurddleReturnX - CharPx < 75 && hurddleReturnX - CharPx > -38 && CharPy >= 560)
					{
						death = true;
					}
				}
				else if(Randomobs == 4)
				{
					if(hurddleReturnX - CharPx < 75 && hurddleReturnX - CharPx > -38 && CharPy >= 480)
					{
						death = true;
					}
				}
			}
			//����° �������� ��ֹ� �΋H�� ����
			if(firstRound == true)
			{
				if ((first1Px - CharPx <= -7920 && first1Px - CharPx >= -8070) || CharPy > 636) 		//���� ������ ������ ����
				{
					floor = false;
				}
				else if((first2Px - CharPx <= -6120 && first2Px - CharPx >= -6270) || CharPy > 636)
				{
					floor = false;
				}
				else if((first3Px - CharPx <= -11520 && first3Px - CharPx >= -11670) || CharPy > 636)
				{
					floor = false;
				}
				else
				{
					floor = true;
				}
			}
			else if(secondRound == true)
			{
				if ((second1Px - secondCharPx <= -7920 && second1Px - secondCharPx >= -8070) || CharPy < 50) 		//���� ������ ������ ����
				{
					floor = false;
				}
				else if((second2Px - secondCharPx <= -6120 && second2Px - secondCharPx >= -6270) || CharPy < 50)
				{
					floor = false;
				}
				else if((second3Px - secondCharPx <= -11520 && second3Px - secondCharPx >= -11670) || CharPy < 50)
				{
					floor = false;
				}
				else
				{
					floor = true;
				}
			}
			else if(thirdRound == true)
			{
				if ((third1Px - CharPx <= -7920 && third1Px - CharPx >= -8070) || CharPy > 636) 		//���� ������ ������ ����
				{
					floor = false;
				}
				else if((third2Px - CharPx <= -6120 && third2Px - CharPx >= -6270) || CharPy > 636)
				{
					floor = false;
				}
				else if((third3Px - CharPx <= -11520 && third3Px - CharPx >= -11670) || CharPy > 636)
				{
					floor = false;
				}
				else
				{
					floor = true;
				}
			}
			if(run == true && floor == false) // ���� ������ ������
			{
				if(secondRound == true)
				{
					secondCharPy -= 12;
				}
				else
				{
					
					CharPy += 12;
				}
				
			}
			
			if(secondRound == true)
			{
				if(secondCharPy <= -130) // �������� �� ���� ����
				{
					death = true;
				}
			}
			else
			{
				if(CharPy >= 837) // �������� �� ���� ����
				{
					death = true;
				}
			}
			
			if(death == true) //������ ��� ����
			{
				if(changeX > -3800 && changeX < 1300) //ȭ�� ��ȯ�� �� ��ֹ� �ȳ����� ��
				{
					death = false;
				}
				else if(change2X > -3000 && change2X < 1300) //ȭ�� ��ȯ�� �� ��ֹ� �ȳ����� ��
				{
					death = false;
				}
				else
				{
					if(isHowTo == false)
					{
						stopAll(); //������ ��� ����
						deathcnt++;
						if(deathcnt == 40)
						{
							isrestart = false;
							lp.setLayer(endpanel, 5);
							endpanel.setFocusable(true);					// endPanel�� ��Ŀ�̵� �� �ְ� ��
							endpanel.requestFocus();
						}
					}
				}
			}
			
			if(childX <= 500) //���� ���� ��� ���� ĳ���Ͱ� �̵��ϰ�
			{
				goGround.stop();
			}
			
			if(CharPx >= 750)
			{
				stopAll();
				isClear = true;
				lp.setLayer(endpanel, 5);
				endpanel.setFocusable(true);					// endPanel�� ��Ŀ�̵� �� �ְ� ��
				endpanel.requestFocus();
			}
			frame.repaint();
			frame.setVisible(true);
		}
	}
	
	public class StartPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			Graphics2D g2d = (Graphics2D)g;
			
			startpanel.setLayout(null);
			startbutton.setBounds(800, 60, 400, 300); //���۹�ư
			startbutton.setOpaque(false);
			startbutton.setContentAreaFilled(false);
			startbutton.setBorderPainted(false);
			
			howtobutton.setBounds(450, 510, 377, 300); // ���ӹ�� �� ���� ��ư
			howtobutton.setOpaque(false);
			howtobutton.setContentAreaFilled(false);
			howtobutton.setBorderPainted(false);
			
			startpanel.add(startbutton);
			
			if(isOpening == true) // ��ư�� ������ false�� ��(��ư�� ������ ������ ����ȭ�� ���)
			{
				g.drawImage(startscreen.getImage(), 0, 0, 1300, 810, this);
			}
			else //��ư�� ������ ������ ���丮 ���
			{	
				if (openingcnt == 1500)
				{
					openingcnt += 0;
				}
				else
				{
					openingcnt++;
				}
				
				if(openingcnt >= 0 && openingcnt < 300)
				{
					g.drawImage(opening1.getImage(), 0, 0, 1300, 810, this);
				}
				else if(openingcnt >= 300 && openingcnt < 600)
				{
					g.drawImage(opening2.getImage(), 0, 0, 1300, 810, this);
				}
				else if(openingcnt >= 600 && openingcnt < 900)
				{
					g.drawImage(opening3.getImage(), 0, 0, 1300, 810, this);
				}
				else if(openingcnt >= 900 && openingcnt < 1200)
				{
					g.drawImage(opening4.getImage(), 0, 0, 1300, 810, this);
				}
				else if(openingcnt >= 1200 && openingcnt < 1500)
				{
					g.drawImage(opening5.getImage(), 0, 0, 1300, 810, this);
				}
				else if(openingcnt >= 1500) //���� ��� ��ư
				{
					g.drawImage(howto.getImage(), 0, 0, 1300, 810, this);
					startpanel.add(howtobutton);
				}
				
				if(isHowTo == false) //���� ��� ��ư�� ������ ���� ����
				{
					lp.setLayer(gamepanel, 3); // gamepanel�� ��Ŀ���� �� �� �ְ� ��
					startpanel.remove(howtobutton);
					gamepanel.setFocusable(true);
					gamepanel.requestFocus();
				}
				startpanel.remove(startbutton);
			}
			
			frame.repaint();
			frame.setVisible(true);
		}
	}
	
	public class GamePanel extends JPanel //���� ����
	{
		public void paintComponent(Graphics g)
		{
			Graphics2D g2d = (Graphics2D) g;
			
			if(firstRound == true) //ù��° �������� ���
			{
				g.drawImage(firstBack.getImage(), back1X, 0, 3900, 836, this);
			}
			else if(secondRound == true)//�ι�° �������� ���
			{
				g.drawImage(secondBack.getImage(), back2X, 0, 5200, 836, this);
			}
			else if(thirdRound == true)//����° �������� ���
			{
				g.drawImage(thirdBack.getImage(), back3X, 0, 5200, 836, this);
			}
			
			obstaclelocation(g2d); //��ֹ� ��ġ
			if(firstRound == true || thirdRound == true)
			{
				if(run == true) //ĳ���� ��ġ
				{
					g.drawImage(generalChar.get(Cokiecnt).getImage(), CharPx, CharPy, 130, 130, this);
				}
				else if(jump == true)//ĳ���� ��ġ
				{
					g.drawImage(jumpChar.get(Cokiecnt).getImage(), CharPx, CharPy, 130, 130, this);
				}
				else if(Djump == true)//ĳ���� ��ġ
				{
					g.drawImage(jumpChar.get(Cokiecnt).getImage(), CharPx, CharPy, 130, 130, this);
				}
			}
			else if(secondRound == true)
			{
				if(run == true) //ĳ���� ��ġ
				{
					g.drawImage(secondChar.get(Cokiecnt).getImage(), secondCharPx, secondCharPy, 130, 130, this);
				}
				else if(jump == true)//ĳ���� ��ġ
				{
					g.drawImage(secondjump.get(Cokiecnt).getImage(), secondCharPx, secondCharPy, 130, 130, this);
				}
				else if(Djump == true)//ĳ���� ��ġ
				{
					g.drawImage(secondjump.get(Cokiecnt).getImage(), secondCharPx, secondCharPy, 130, 130, this);
				}
			}
			if(firstRound == true)//���� ��ġ
			{
				g.drawImage(first1.getImage(), first1Px, first1Py, 7920, 55, this);
				g.drawImage(first2.getImage(), first2Px, first2Py, 6120, 55, this);
				g.drawImage(first3.getImage(), first3Px, first3Py, 11520, 55, this);
				g.drawImage(first4.getImage(), first4Px, first4Py, 8820, 55, this);
			}
			else if(secondRound == true)
			{
				g.drawImage(second1.getImage(), second1Px, second1Py, 7920, 60, this);
				g.drawImage(second2.getImage(), second2Px, second2Py, 6120, 60, this);
				g.drawImage(second3.getImage(), second3Px, second3Py, 11520, 60, this);
				g.drawImage(second4.getImage(), second4Px, second4Py, 8820, 60, this);
			}
			else if(thirdRound == true)
			{
				g.drawImage(third1.getImage(), third1Px, third1Py, 7920, 55, this);
				g.drawImage(third2.getImage(), third2Px, third2Py, 6120, 55, this);
				g.drawImage(third3.getImage(), third3Px, third3Py, 11520, 55, this);
				g.drawImage(third4.getImage(), third4Px, third4Py, 8820, 55, this);
				g.drawImage(childface.getImage(), childX, 0, 800, 800, this);
			}
			
			if(firstRound == true || thirdRound == true)
			{
				g2d.setFont(new Font("�������", Font.BOLD, 30)); //���� �ð�(����) ǥ�� 
				g2d.setColor(Color.BLACK);
				g2d.drawString("����: " + times, 420,30);
			}
			else
			{
				g2d.setFont(new Font("�������", Font.BOLD, 30)); //���� �ð�(����) ǥ��
				g2d.setColor(Color.BLACK);
				g2d.drawString("����: " + times, 420,780);
			}
			
			if(back2X <= -1300) //ȭ�� ��ȯ ȿ��(�� �������� �ϹǷ� ���� ���߿� ��ġ)
			{
				g.drawImage(change.getImage(), change2X, 0, 2610, 836, this);
				if(change2X <= 0 && change2X >= -1500)
				{
					g.drawImage(changemessage2.getImage(), 450, 268, 400, 300, this); //ȭ�� ��ȯ �� �޼��� ���
				}
			}
			else if(back1X <= -1300)//ȭ�� ��ȯ ȿ��(�� �������� �ϹǷ� ���� ���߿� ��ġ)
			{
				g.drawImage(change.getImage(), changeX, 0, 2610, 836, this);
				if(changeX <= 0 && changeX >= -1500)
				{
					g.drawImage(changemessage1.getImage(), 450, 268, 400, 300, this);//ȭ�� ��ȯ �� �޼��� ���
				}
			}
			
			if(isHowTo == false || isrestart == true) //ó�� �����ϰų� ����� ���� ��
			{
				if(times == 0)
				{
					//firstmusic.play();
				}
				goChar.start(); //Ű���� ������ ����
				goTime.start(); //Ÿ�̸� ������ ����
				goGround.start(); //���, ���� ������ ����
			}
			frame.repaint();
			frame.setVisible(true);
		}
	}
	
	public void stopAll()//��� ����
	{
		goChar.stop();
		goGround.stop();
		goTime.stop();
		run = true; //���� true�� ������ ���������� ����
		jump = false;
		Djump = false;
		if(firstRound == true || thirdRound == true)
		{
			CharPy += 10;
		}
		else if(secondRound == true)
		{
			secondCharPy -= 10;
		}
	}
	
	public void obstaclelocation(Graphics2D g2d)
	{
		if(firstRound == true)
		{
			if(isHuddle == false) //��ֹ� ������ ������ ��
			{
				Round1_obs1.setX(600 + (stepHuddle * 2));
				Round1_obs1.setY(648);
				Round1_obs2.setX(600 + (stepHuddle * 2));
				Round1_obs2.setY(648);
				Round1_obs3.setX(600 + (stepHuddle * 2));
				Round1_obs3.setY(578);
				Round1_obs4.setX(600 + (stepHuddle * 2));
				Round1_obs4.setY(648);
				Round1_obs5.setX(600 + (stepHuddle * 2));
				Round1_obs5.setY(648);
				Randomobs = (int)(Math.random() * 5 + 1); //1~5 �������� �޾ƿ�
				isHuddle = true; //��ֹ��� �����Ǹ� �ٽ� true
			}
			switch(Randomobs)//�������� �޾ƿͼ� ��ֹ� ����
			{
				case 1:
					Round1_obs1.drawjump(g2d); //��ֹ� ��ġ
					hurddleReturnX = Round1_obs1.getX(); //�������� ������ ���� x��ǥ �޾ƿ�
					break;
				case 2:
					Round1_obs2.drawjump(g2d);
					hurddleReturnX = Round1_obs2.getX();
					break;
				case 3:
					Round1_obs3.drawjump(g2d);
					hurddleReturnX = Round1_obs3.getX();
					break;
				case 4:
					Round1_obs4.drawjump(g2d);
					hurddleReturnX = Round1_obs4.getX();
					break;
				case 5:
					Round1_obs5.drawjump(g2d);
					hurddleReturnX = Round1_obs5.getX();
					break;
			}
		}
		else if(secondRound == true) //�ι�° �������� ��ֹ� �������� ��ġ
		{
			if(isHuddle == false)
			{
				Round2_obs1.setX(600 + (stepHuddle * 2));
				Round2_obs1.setY(55);
				Round2_obs2.setX(600 + (stepHuddle * 2));
				Round2_obs2.setY(55);
				Round2_obs3.setX(600 + (stepHuddle * 2));
				Round2_obs3.setY(55);
				Round2_obs4.setX(600 + (stepHuddle * 2));
				Round2_obs4.setY(55);
				Round2_obs5.setX(600 + (stepHuddle * 2));
				Round2_obs5.setY(55);
				Round2_obs6.setX(600 + (stepHuddle * 2));
				Round2_obs6.setY(55);
				Randomobs = (int)(Math.random() * 6 + 1);
				isHuddle = true;
			}
			switch(Randomobs)
			{
				case 1:
					Round2_obs1.drawjump(g2d);
					hurddleReturnX = Round2_obs1.getX();
					break;
				case 2:
					Round2_obs2.drawjump(g2d);
					hurddleReturnX = Round2_obs2.getX();
					break;
				case 3:
					Round2_obs3.drawjump(g2d);
					hurddleReturnX = Round2_obs3.getX();
					break;
				case 4:
					Round2_obs4.drawjump(g2d);
					hurddleReturnX = Round2_obs4.getX();
					break;
				case 5:
					Round2_obs5.drawjump(g2d);
					hurddleReturnX = Round2_obs5.getX();
					break;
				case 6:
					Round2_obs6.drawjump(g2d);
					hurddleReturnX = Round2_obs6.getX();
					break;
			}
		}
		else if(thirdRound == true)
		{
			if(isHuddle == false) //��ֹ� ������ ������ ��
			{
				Round3_obs1.setX(600 + (stepHuddle * 2));
				Round3_obs1.setY(578);
				Round3_obs2.setX(600 + (stepHuddle * 2));
				Round3_obs2.setY(0);
				Round3_obs3.setX(600 + (stepHuddle * 2));
				Round3_obs3.setY(668);
				Round3_obs4.setX(600 + (stepHuddle * 2));
				Round3_obs4.setY(578);
				Randomobs = (int)(Math.random() * 4 + 1); //1~5 �������� �޾ƿ�
				isHuddle = true; //��ֹ��� �����Ǹ� �ٽ� true
			}
			switch(Randomobs)//�������� �޾ƿͼ� ��ֹ� ����
			{
				case 1:
					Round3_obs1.drawjump(g2d); //��ֹ� ��ġ
					hurddleReturnX = Round3_obs1.getX(); //�������� ������ ���� x��ǥ �޾ƿ�
					break;
				case 2:
					Round3_obs2.drawjump(g2d);
					hurddleReturnX = Round3_obs2.getX();
					break;
				case 3:
					Round3_obs3.drawjump(g2d);
					hurddleReturnX = Round3_obs3.getX();
					break;
				case 4:
					Round3_obs4.drawjump(g2d);
					hurddleReturnX = Round3_obs4.getX();
					break;
			}
		}
	}
	class endPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			Graphics2D g2d = (Graphics2D) g;
			if(isClear == false)
			{
				endpanel.setLayout(null);
				restartbutton.setBounds(450, 550, 400, 300); //����� ��ư
				restartbutton.setOpaque(false);
				restartbutton.setContentAreaFilled(false);
				restartbutton.setBorderPainted(false);
				
				if(firstRound == true) //���� ���������� ���� �ٸ� ȭ�� ���
				{
					//firstmusic.stop();
					g2d.drawImage(ending6.getImage(), 0, 0, 1300, 810, this);
				}
				else if(secondRound == true)
				{
					//secondmusic.stop();
					g2d.drawImage(ending7.getImage(), 0, 0, 1300, 810, this);
				}
				else if(thirdRound == true)
				{
					//thirdmusic.stop();
					g2d.drawImage(ending8.getImage(), 0, 0, 1300, 810, this);
				}
				endpanel.add(restartbutton);
				
				if(isrestart == true) //����۽� �ʱⰪ���� �缳��
				{
					run = true;	
					floor = true;
					jump = false;
					Djump = false;
					death = false;
					firstRound = true;
					secondRound = false;
					thirdRound = false;
					isHuddle = false;
					isClear = false;
					
					first1Px = 0; 
					first2Px = 8070;
					first3Px = 14340;
					first4Px = 26010;
					
					second1Px = 0; 
					second2Px = 8070;
					second3Px = 14340;
					second4Px = 26010;
					
					third1Px = 0; 
					third2Px = 8070;
					third3Px = 14340;
					third4Px = 26010;
					
					childX = 34000;
					
					Randomobs = 0; 
					stepHuddle=400; 
					
					hurddleReturnX=0; 
					hurddleReturnX2=0; 
					hurddleReturnX3=0;
					
					deathcnt = 0;
					Cokiecnt = 0; 
					Cokiecnt2 = 0; 
										
					back1X = 0;
					back2X = 0;
					back3X = 0;
					changeX = 1300; 
					change2X = 1300;
					
					CharPx = 120; 
					CharPy = 636;
					secondCharPx = 120;
					secondCharPy = 50;
					
					times = 0;
					
					lp.setLayer(endpanel, 0);
					endpanel.remove(restartbutton);
					gamepanel.setFocusable(true);
					gamepanel.requestFocus();
				}
			}
			else //Ŭ���� �� Ŭ���� ȭ�� ���
			{
				if (endingcnt == 1500)
				{
					endingcnt += 0;
				}
				else
				{
					endingcnt++;
				}
				
				if(endingcnt >= 0 && endingcnt < 300)
				{
					g2d.drawImage(ending1.getImage(), 0, 0,1300, 810, this);
				}
				else if(endingcnt >= 300 && endingcnt < 600)
				{
					g2d.drawImage(ending2.getImage(), 0, 0, 1300, 810, this);
				}
				else if(endingcnt >= 600 && endingcnt < 900)
				{
					g2d.drawImage(ending3.getImage(), 0, 0, 1300, 810, this);
				}
				else if(endingcnt >= 900 && endingcnt < 1200)
				{
					g2d.drawImage(ending4.getImage(), 0, 0, 1300, 810, this);
				}
				else if(endingcnt >= 1200 && endingcnt < 1500)
				{
					g2d.drawImage(ending5.getImage(), 0, 0, 1300, 810, this);
				}
				else if(endingcnt >= 1500)
				{
					g2d.drawImage(endingbackground.getImage(), 0, 0, 1300, 810, this);
			
				}
				frame.repaint();
				frame.setVisible(true);
			}
		}
	}
	
	class buttonListener implements MouseListener //���� ��ư
	{
		public void mouseClicked(MouseEvent arg0)
		{
			isOpening = false; //false�� �Ǹ� ������ ����
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	class HowToListener implements MouseListener //���� ��� ��ư
	{
		public void mouseClicked(MouseEvent arg0)
		{
			isHowTo = false; //false�� �Ǹ� ���� ������
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
	class endListener implements MouseListener //����� ��ư
	{
		public void mouseClicked(MouseEvent arg0) 
		{
			isrestart = true; //true�� �Ǹ� �������
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	class DirectionListener implements KeyListener
	{
		public void keyPressed (KeyEvent event) {}
		public void keyTyped (KeyEvent event) {} //keyTyped�� �ҷ� �ߴµ� Ű�� �ȸ��� �Ф�(�̰� �����̰� ���µ�....)
		public void keyReleased (KeyEvent event) //�׷��� ��¿ �� ���� keyReleased�� ��
		{
			int keycode = event.getKeyCode();
			
			if(keycode == KeyEvent.VK_SPACE) //�����̽��� ������ ����
			{
				if(jump == false && Djump == false)
				{
					run=false;
					Cokiecnt=0;
					jump=true;
					Djump = false;
				}
				else if (jump == true && Djump == false)
				{
					if(firstRound == true || thirdRound == true)
					{
						jump_rate = 636 - CharPy;
					}
					else if(secondRound == true)
					{
						jump_rate = secondCharPy - 50;
					}
					Cokiecnt = 0;
					Cokiecnt2 = 1;
					run=false;
					Cokiecnt=0;
					jump=false;
					Djump = true;
				}
			}
		}
	}
}
