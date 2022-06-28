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
	
	final int ALL_WIDTH = 1300; 		// 전체 frame의 폭
	final int ALL_HEIGHT = 836; 	// 전체 frame의 높이
	final int M_WIDTH=1430; 	// 실제 게임이 작동하는 넓이
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
	
	JPanel controlPanel=new JPanel();		// 게임 컨트롤과 시간, 사용자 디스플레이가 들어갈 패널

	JLayeredPane lp = new JLayeredPane();	// 화면을 여러장 겹치기 위한 PaneL 레이어
	GamePanel  gamepanel=new GamePanel();	// 메인 게임
	StartPanel startpanel = new StartPanel();
	endPanel endpanel = new endPanel();
	
	Timer goGround; //땅과 장애물 움직임
	
	Timer goChar;	//캐릭터 움직임
	Timer goTime;	//타이머
	
	ClockListener clockListener; //시간을 받아오기 위한 리스너
		
	URL url1 = getClass().getClassLoader().getResource("music/firstmusic.wav"); // 음악 파일 위치
	URL url2 = getClass().getClassLoader().getResource("music/secondmusic.wav");
	URL url3 = getClass().getClassLoader().getResource("music/thirdmusic.wav");
	
	//AudioClip firstmusic = Applet.newAudioClip(url1); //음악 파일
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
	
	ImageIcon firstBack = new ImageIcon("src/image/background1.png"); //1스테이지 배경
	ImageIcon secondBack = new ImageIcon("src/image/background2.png"); //2스테이지 배경
	ImageIcon thirdBack = new ImageIcon("src/image/background3.png"); //3스테이지 배경
	ImageIcon change = new ImageIcon("src/image/change.png"); //스테이지 전환시 나오는 검은 화면
	ImageIcon changemessage1 = new ImageIcon("src/image/stagemessage1.png");
	ImageIcon changemessage2 = new ImageIcon("src/image/stagemessage2.png");
	ImageIcon childface = new ImageIcon("src/image/childface.png");
	
	ImageIcon g_character1 = new ImageIcon("src/image/char1.png"); //캐릭터 그림들
	ImageIcon g_character2 = new ImageIcon("src/image/char2.png");
	ImageIcon g_character3 = new ImageIcon("src/image/char3.png");
	
	ImageIcon secondcharacter1 = new ImageIcon("src/image/secondchar1.png");
	ImageIcon secondcharacter2 = new ImageIcon("src/image/secondchar2.png");
	ImageIcon secondcharacter3 = new ImageIcon("src/image/secondchar3.png");
	
	ImageIcon j_char0 = new ImageIcon("src/image/jump1.png"); //점프했을때 캐릭터 그림들
	ImageIcon j_char1 = new ImageIcon("src/image/jump2.png");
	ImageIcon j_char2 = new ImageIcon("src/image/jump3.png");
	
	ImageIcon secondjump1 = new ImageIcon("src/image/secondjump1.png");
	ImageIcon secondjump2 = new ImageIcon("src/image/secondjump2.png");
	ImageIcon secondjump3 = new ImageIcon("src/image/secondjump3.png");
	
	ImageIcon first1 = new ImageIcon("src/image/1st_1.jpg"); //발판 (아직 안넣음)
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
	
	huddle Round1_obs1 = new huddle("src/image/obs1-1.png", 600, 661, 54, 108); //장애물
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
	huddle Round3_obs4 = new huddle("src/image/obs3-4.png", 600, 661, 180, 180); // 여기까지 장애물
	
	int openingcnt = 0;
	int endingcnt = 0;
	
	int first1Px = 0; // 발판 x좌표들
	int first2Px = 8070;
	int first3Px = 14340;
	int first4Px = 26010;
	
	int first1Py = 756; // 발판 y좌표들
	int first2Py = 756;
	int first3Py = 756;
	int first4Py = 756;
	
	int second1Px = 0; // 발판 x좌표들
	int second2Px = 8070;
	int second3Px = 14340;
	int second4Px = 26010;
	
	int second1Py = 0; // 발판 y좌표들
	int second2Py = 0;
	int second3Py = 0;
	int second4Py = 0;
	
	int third1Px = 0; // 발판 x좌표들
	int third2Px = 8070;
	int third3Px = 14340;
	int third4Px = 26010;
	
	int third1Py = 756; // 발판 y좌표들
	int third2Py = 756;
	int third3Py = 756;
	int third4Py = 756;
	
	int childX = 34000;
	
	int Randomobs = 0; //장애물 랜덤으로 받아오기
	int stepHuddle=400; //장애물의 초기 위치
	
	int hurddleReturnX=0; //장애물의 현재 x좌표 받아옴
	int hurddleReturnX2=0; 
	int hurddleReturnX3=0;
	
	ArrayList<ImageIcon> generalChar; //캐릭터 그림들을 배열로 저장
	ArrayList<ImageIcon> jumpChar;	//점프시 캐릭터 그림들을 배열로 저장
	ArrayList<ImageIcon> secondChar;
	ArrayList<ImageIcon> secondjump;
	
	int deathcnt = 0;
	int Cokiecnt = 0; //이걸로 캐릭터 움직임 구현
	int Cokiecnt2 = 0; // 점프할 때 낙하 속도 지정
	double jump_rate; // 더블점프 시 원래 있던 y좌표 받아옴
	
	int back1X = 0; //배경들 x좌표
	int back2X = 0;
	int back3X = 0;
	int changeX = 1300; //전환할때 검은 화면들 초기x좌표
	int change2X = 1300;
	
	int CharPx = 120; //캐릭터 초기 x,y 좌표
	int CharPy = 636;
	int secondCharPx = 120;
	int secondCharPy = 50;
	
	int times = 0; //시간
	
	public static void main(String[] args)
	{
		
		MyFrame myframe = new MyFrame(); //프레임 생성
		myframe.go();	//메인함수를 최대한 짧게 하는걸 교수님이 좋아하심 ^오^
	}
	
	public void go()
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lp.add(startpanel, new Integer(2));
		startpanel.setSize(ALL_WIDTH, 806);
		lp.add(gamepanel, new Integer(1)); 						// Integer로 레이어 순서 정함
		gamepanel.setSize(M_WIDTH, 836);						//메인게임 사이즈
		lp.add(endpanel,new Integer(0)); 						// 패널들 변환
		endpanel.setSize(M_WIDTH, 836);
		frame.add(lp); 
		frame.setTitle("입속 멜로");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(ALL_WIDTH, ALL_HEIGHT);					//프레임 크기

		goGround = new Timer(25,new GroundListener() );	//땅과 장애물 움직임
		goChar = new Timer(25,new CookieListener());	 //캐릭터 움직임
		goTime = new Timer(30,new ClockListener());		//1초 지날때 마다 갱신됨
		
		gamepanel.addKeyListener(new DirectionListener());	//키 리스너 적용
		startbutton.addMouseListener(new buttonListener()); //버튼들 마우스 리스너
		howtobutton.addMouseListener(new HowToListener());
		restartbutton.addMouseListener(new endListener());
		
		generalChar = new ArrayList<ImageIcon>(); //캐릭터 움직임 배열
		{
			generalChar.add(g_character1);//0
			generalChar.add(g_character2);//1
			generalChar.add(g_character3);//2recipe
		}
		
		jumpChar = new ArrayList<ImageIcon>(); // 점프 캐릭터 배열
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
			times++; //몇초 지났는지 불러옴
		}
	}
	
	public class GroundListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			if (firstRound == true)
			{
				first1Px -= 9; //발판 움직임
				first2Px -= 9;
				first3Px -= 9;
				first4Px -= 9;
			}
			else if(secondRound == true)
			{
				second1Px -= 14; //발판 움직임
				second2Px -= 14;
				second3Px -= 14;
				second4Px -= 14;
			}
			else if (thirdRound == true)
			{
				third1Px -= 18; //발판 움직임
				third2Px -= 18;
				third3Px -= 18;
				third4Px -= 18;
				childX -= 18;
			}
			if(changeX > -3900 && changeX < 1300) //화면 전환할 때 장애물 안나오게 함
			{
				isHuddle = true;
			}
			else if(change2X > -3900 && change2X < 1000) //화면 전환할 때 장애물 안나오게 함
			{
				isHuddle = true;
			}
			
			if(secondRound == true)
			{
				if(hurddleReturnX < -250) //화면 전환 끝나면 다시 장애물 생성
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
			else if(hurddleReturnX<-350) //장애물이 일정 좌표 뒤로 가면 새로 장애물을 생성
			{
				isHuddle = false;
			}
			
			if(Round1_obs1.getX()>-400) // 첫번째 스테이지 장애물 생성
			{
				Round1_obs1.go(9);// 장애물의 이동속도
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
			if(Round2_obs1.getX()>-400)// 두번째 스테이지 장애물 생성
			{
				Round2_obs1.go(14);// 장애물의 이동속도
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
			if(Round3_obs1.getX()>-400) // 세번째 스테이지 장애물 생성
			{
				Round3_obs1.go(18);// 장애물의 이동속도
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
				back1X -= 1; //배경 움직임
			}
			else if (secondRound == true)
			{
				back2X -= 2; //배경 움직임
			}
			else if (thirdRound == true)
			{
				back3X -= 2; //배경 움직임
			}
			
			if(back2X <= -3900) //전환 화면 나오게 하는거
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
			else if(back1X <= -2000) //전환 화면 나오게 하는거
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
			
			frame.repaint(); // 1/40초마다 갱신됨
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
			
			if(run == true) //점프 안하고 있을 때
				{
					if(death == false)
					{
						Cokiecnt++; //0, 1, 2 계속 돌아가게 만들어 캐릭터 움직임 구현
						if(Cokiecnt == 3)
						{
							Cokiecnt = 0;
						}
					}
					else //죽었을 때 멈추게 함
					{
						Cokiecnt += 0;
					}
				}
			
			else if(jump == true) //점프했을 때
			{
				if(firstRound == true || thirdRound == true)
				{
					if(Cokiecnt2 == 1)
					{
						CharPy -= 20; //시간이 지나면서 Cokiecnt2가 증가함에 따라 낙하 속도 지정
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
						Cokiecnt2++; //3프레임이 지나면 하나씩 더함
						if(Cokiecnt2 == 10) //10이 되면 점프시 캐릭터 출력 중지
						{
							Cokiecnt = 3;
						}
						else
						{
							Cokiecnt = 0;
						}
					}
					else if(Cokiecnt > 4)// 점프를 끝내면
					{
						Cokiecnt=0;
						Cokiecnt2=0;
						jump=false; //다시 점프가 false
						Djump=false;
						run=true; //런은 다시 true
						CharPy=636;
					}
				}
				if(secondRound == true)
				{
					if(Cokiecnt2 == 1)
					{
						secondCharPy += 20; //시간이 지나면서 Cokiecnt2가 증가함에 따라 낙하 속도 지정
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
						Cokiecnt2++; //3프레임이 지나면 하나씩 더함
						if(Cokiecnt2 == 10) //10이 되면 점프시 캐릭터 출력 중지
						{
							Cokiecnt = 3;
						}
						else
						{
							Cokiecnt = 0;
						}
					}
					else if(Cokiecnt > 4)// 점프를 끝내면
					{
						Cokiecnt=0;
						Cokiecnt2=0;
						jump=false; //다시 점프가 false
						Djump=false;
						run=true; //런은 다시 true
						secondCharPy=50;
					}
				}
			}
			else if (Djump == true) //더블점프 시
			{	
				if(firstRound == true || thirdRound == true)
				{
					if(Cokiecnt2 == 1)
					{
						CharPy -= 20; //점프와 동일
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
						CharPy += (jump_rate / 9); //더블점프를 시작했던 부분부터 땅으로 낙하시킴
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
					
					if(Cokiecnt == 3)//점프와 동일
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
					if(Cokiecnt > 4) //점프와 동일
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
						secondCharPy += 20; //점프와 동일
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
						secondCharPy -= (jump_rate / 9); //더블점프를 시작했던 부분부터 땅으로 낙하시킴
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
					
					if(Cokiecnt == 3)//점프와 동일
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
					if(Cokiecnt > 4) //점프와 동일
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
			if(firstRound == true) //첫번째 스테이지 장애물 부딫힘 판정
			{
				if(Randomobs == 3) //2칸짜리 장애물
				{
					if(hurddleReturnX - CharPx < 75 && hurddleReturnX - CharPx > -38 && CharPy >= 480)
					{
						death = true;
					}
				}
				else // 다른 장애물
				{
					if(hurddleReturnX - CharPx < 75 && hurddleReturnX - CharPx > -20 && CharPy >= 540)
					{
						death = true;
					}
				}
			}
			else if(secondRound == true) //두번째 스테이지 장애물 부딫힘 판정
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
			//세번째 스테이지 장애물 부딫힘 판정
			if(firstRound == true)
			{
				if ((first1Px - CharPx <= -7920 && first1Px - CharPx >= -8070) || CharPy > 636) 		//발판 없을때 떨어짐 판정
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
				if ((second1Px - secondCharPx <= -7920 && second1Px - secondCharPx >= -8070) || CharPy < 50) 		//발판 없을때 떨어짐 판정
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
				if ((third1Px - CharPx <= -7920 && third1Px - CharPx >= -8070) || CharPy > 636) 		//발판 없을때 떨어짐 판정
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
			if(run == true && floor == false) // 발판 없을때 떨어짐
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
				if(secondCharPy <= -130) // 떨어졌을 때 죽음 판정
				{
					death = true;
				}
			}
			else
			{
				if(CharPy >= 837) // 떨어졌을 때 죽음 판정
				{
					death = true;
				}
			}
			
			if(death == true) //죽으면 모두 멈춤
			{
				if(changeX > -3800 && changeX < 1300) //화면 전환할 때 장애물 안나오게 함
				{
					death = false;
				}
				else if(change2X > -3000 && change2X < 1300) //화면 전환할 때 장애물 안나오게 함
				{
					death = false;
				}
				else
				{
					if(isHowTo == false)
					{
						stopAll(); //죽으면 모두 멈춤
						deathcnt++;
						if(deathcnt == 40)
						{
							isrestart = false;
							lp.setLayer(endpanel, 5);
							endpanel.setFocusable(true);					// endPanel이 포커싱될 수 있게 함
							endpanel.requestFocus();
						}
					}
				}
			}
			
			if(childX <= 500) //엔딩 볼때 배경 말고 캐릭터가 이동하게
			{
				goGround.stop();
			}
			
			if(CharPx >= 750)
			{
				stopAll();
				isClear = true;
				lp.setLayer(endpanel, 5);
				endpanel.setFocusable(true);					// endPanel이 포커싱될 수 있게 함
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
			startbutton.setBounds(800, 60, 400, 300); //시작버튼
			startbutton.setOpaque(false);
			startbutton.setContentAreaFilled(false);
			startbutton.setBorderPainted(false);
			
			howtobutton.setBounds(450, 510, 377, 300); // 게임방법 후 시작 버튼
			howtobutton.setOpaque(false);
			howtobutton.setContentAreaFilled(false);
			howtobutton.setBorderPainted(false);
			
			startpanel.add(startbutton);
			
			if(isOpening == true) // 버튼을 누르면 false가 됨(버튼을 누르기 전까지 시작화면 출력)
			{
				g.drawImage(startscreen.getImage(), 0, 0, 1300, 810, this);
			}
			else //버튼을 누르면 오프닝 스토리 출력
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
				else if(openingcnt >= 1500) //게임 방법 버튼
				{
					g.drawImage(howto.getImage(), 0, 0, 1300, 810, this);
					startpanel.add(howtobutton);
				}
				
				if(isHowTo == false) //게임 방법 버튼을 누르면 게임 시작
				{
					lp.setLayer(gamepanel, 3); // gamepanel이 포커싱이 될 수 있게 함
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
	
	public class GamePanel extends JPanel //메인 게임
	{
		public void paintComponent(Graphics g)
		{
			Graphics2D g2d = (Graphics2D) g;
			
			if(firstRound == true) //첫번째 스테이지 배경
			{
				g.drawImage(firstBack.getImage(), back1X, 0, 3900, 836, this);
			}
			else if(secondRound == true)//두번째 스테이지 배경
			{
				g.drawImage(secondBack.getImage(), back2X, 0, 5200, 836, this);
			}
			else if(thirdRound == true)//세번째 스테이지 배경
			{
				g.drawImage(thirdBack.getImage(), back3X, 0, 5200, 836, this);
			}
			
			obstaclelocation(g2d); //장애물 배치
			if(firstRound == true || thirdRound == true)
			{
				if(run == true) //캐릭터 배치
				{
					g.drawImage(generalChar.get(Cokiecnt).getImage(), CharPx, CharPy, 130, 130, this);
				}
				else if(jump == true)//캐릭터 배치
				{
					g.drawImage(jumpChar.get(Cokiecnt).getImage(), CharPx, CharPy, 130, 130, this);
				}
				else if(Djump == true)//캐릭터 배치
				{
					g.drawImage(jumpChar.get(Cokiecnt).getImage(), CharPx, CharPy, 130, 130, this);
				}
			}
			else if(secondRound == true)
			{
				if(run == true) //캐릭터 배치
				{
					g.drawImage(secondChar.get(Cokiecnt).getImage(), secondCharPx, secondCharPy, 130, 130, this);
				}
				else if(jump == true)//캐릭터 배치
				{
					g.drawImage(secondjump.get(Cokiecnt).getImage(), secondCharPx, secondCharPy, 130, 130, this);
				}
				else if(Djump == true)//캐릭터 배치
				{
					g.drawImage(secondjump.get(Cokiecnt).getImage(), secondCharPx, secondCharPy, 130, 130, this);
				}
			}
			if(firstRound == true)//발판 배치
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
				g2d.setFont(new Font("나눔고딕", Font.BOLD, 30)); //위에 시간(점수) 표시 
				g2d.setColor(Color.BLACK);
				g2d.drawString("점수: " + times, 420,30);
			}
			else
			{
				g2d.setFont(new Font("나눔고딕", Font.BOLD, 30)); //위에 시간(점수) 표시
				g2d.setColor(Color.BLACK);
				g2d.drawString("점수: " + times, 420,780);
			}
			
			if(back2X <= -1300) //화면 전환 효과(다 가려져야 하므로 가장 나중에 배치)
			{
				g.drawImage(change.getImage(), change2X, 0, 2610, 836, this);
				if(change2X <= 0 && change2X >= -1500)
				{
					g.drawImage(changemessage2.getImage(), 450, 268, 400, 300, this); //화면 전환 시 메세지 출력
				}
			}
			else if(back1X <= -1300)//화면 전환 효과(다 가려져야 하므로 가장 나중에 배치)
			{
				g.drawImage(change.getImage(), changeX, 0, 2610, 836, this);
				if(changeX <= 0 && changeX >= -1500)
				{
					g.drawImage(changemessage1.getImage(), 450, 268, 400, 300, this);//화면 전환 시 메세지 출력
				}
			}
			
			if(isHowTo == false || isrestart == true) //처음 시작하거나 재시작 했을 때
			{
				if(times == 0)
				{
					//firstmusic.play();
				}
				goChar.start(); //키릭터 움직임 시작
				goTime.start(); //타이머 움직임 시작
				goGround.start(); //배경, 발판 움직임 시작
			}
			frame.repaint();
			frame.setVisible(true);
		}
	}
	
	public void stopAll()//모두 멈춤
	{
		goChar.stop();
		goGround.stop();
		goTime.stop();
		run = true; //런이 true가 되지만 움직이지는 않음
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
			if(isHuddle == false) //장애물 생성이 가능할 때
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
				Randomobs = (int)(Math.random() * 5 + 1); //1~5 랜덤으로 받아옴
				isHuddle = true; //장애물이 생성되면 다시 true
			}
			switch(Randomobs)//랜덤으로 받아와서 장애물 생성
			{
				case 1:
					Round1_obs1.drawjump(g2d); //장애물 배치
					hurddleReturnX = Round1_obs1.getX(); //프레임이 지날때 마다 x좌표 받아옴
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
		else if(secondRound == true) //두번째 스테이지 장애물 랜덤으로 배치
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
			if(isHuddle == false) //장애물 생성이 가능할 때
			{
				Round3_obs1.setX(600 + (stepHuddle * 2));
				Round3_obs1.setY(578);
				Round3_obs2.setX(600 + (stepHuddle * 2));
				Round3_obs2.setY(0);
				Round3_obs3.setX(600 + (stepHuddle * 2));
				Round3_obs3.setY(668);
				Round3_obs4.setX(600 + (stepHuddle * 2));
				Round3_obs4.setY(578);
				Randomobs = (int)(Math.random() * 4 + 1); //1~5 랜덤으로 받아옴
				isHuddle = true; //장애물이 생성되면 다시 true
			}
			switch(Randomobs)//랜덤으로 받아와서 장애물 생성
			{
				case 1:
					Round3_obs1.drawjump(g2d); //장애물 배치
					hurddleReturnX = Round3_obs1.getX(); //프레임이 지날때 마다 x좌표 받아옴
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
				restartbutton.setBounds(450, 550, 400, 300); //재시작 버튼
				restartbutton.setOpaque(false);
				restartbutton.setContentAreaFilled(false);
				restartbutton.setBorderPainted(false);
				
				if(firstRound == true) //죽은 스테이지에 따라 다른 화면 출력
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
				
				if(isrestart == true) //재시작시 초기값으로 재설정
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
			else //클리어 시 클리어 화면 출력
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
	
	class buttonListener implements MouseListener //시작 버튼
	{
		public void mouseClicked(MouseEvent arg0)
		{
			isOpening = false; //false가 되면 오프닝 나옴
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	class HowToListener implements MouseListener //게임 방법 버튼
	{
		public void mouseClicked(MouseEvent arg0)
		{
			isHowTo = false; //false가 되면 게임 시작함
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
	class endListener implements MouseListener //재시작 버튼
	{
		public void mouseClicked(MouseEvent arg0) 
		{
			isrestart = true; //true가 되면 재시작함
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	class DirectionListener implements KeyListener
	{
		public void keyPressed (KeyEvent event) {}
		public void keyTyped (KeyEvent event) {} //keyTyped로 할려 했는데 키가 안먹힘 ㅠㅠ(이게 딜레이가 없는디....)
		public void keyReleased (KeyEvent event) //그래서 어쩔 수 없이 keyReleased로 함
		{
			int keycode = event.getKeyCode();
			
			if(keycode == KeyEvent.VK_SPACE) //스페이스바 누르면 실행
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
