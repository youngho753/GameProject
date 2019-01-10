package game1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import gameController.GameView;

public class FindMine extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	//전역변수
	JButton[][] btnMine = new JButton[10][10];
	boolean[][] b_Mine = new boolean[10][10];
	int[][] e_State  = new int[10][10];
	int e_findMine;
	int e_GameState;
	int e_Score;
	int gameTime;
	int endTime;
	JLabel lbTimer;
	JLabel lbScore;
	int tmp;
	int tmp2;
	GameView gv;
	/**
	 * Create the frame.
	 */
	public FindMine(GameView gv) {
		this.gv = gv;
		tmp = 0;
		tmp2 = 0;
		gameTime = 0;
		e_GameState = 0;//0게임중 1스코어합산 2게임끝
		e_findMine = 0;
		e_Score = 0;
		endTime = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 687, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//state 설정
		//0:nonclick 1:lbuttonclick 2:rbuttonclick
		for(int i=0;i<10;i++)
			for(int j=0;j<10;j++)
				e_State[i][j] = 0;

		//게임 타이머
		Timer timer = new Timer();
		timeJob tj = new timeJob();
		timeScore ts = new timeScore();
		timer.schedule(tj, 1000,1000);
		timer.schedule(ts, 0,10);
		/////////////////


		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel label = new JLabel("지 뢰 찾 기");
		label.setFont(new Font("굴림", Font.BOLD, 30));
		panel.add(label);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		contentPane.add(splitPane, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new GridLayout(10, 10));

		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(null);

		JButton btnFindMineCheat = new JButton("치트");

		btnFindMineCheat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(e_GameState == 0)
					showMine();

			}
		});
		btnFindMineCheat.setBounds(0, 270, 255, 78);
		panel_2.add(btnFindMineCheat);

		JLabel lblNewLabel = new JLabel("Timer");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(lblNewLabel.getFont().getStyle() | Font.BOLD));
		lblNewLabel.setBounds(0, 10, 74, 50);
		panel_2.add(lblNewLabel);

		JLabel label_1 = new JLabel("남은 지뢰");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(label_1.getFont().deriveFont(label_1.getFont().getStyle() | Font.BOLD));
		label_1.setBounds(12, 130, 230, 50);
		panel_2.add(label_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_3.setBounds(12, 190, 230, 70);
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 5));

		JLabel lblScore = new JLabel("Score");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setFont(lblScore.getFont().deriveFont(lblScore.getFont().getStyle() | Font.BOLD));
		lblScore.setBounds(0, 70, 74, 50);
		panel_2.add(lblScore);

		lbTimer = new JLabel("");
		lbTimer.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lbTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lbTimer.setFont(new Font("굴림", Font.BOLD, 30));
		lbTimer.setBounds(77, 10, 165, 50);
		panel_2.add(lbTimer);

		lbScore = new JLabel("");
		lbScore.setText(e_Score+"");
		lbScore.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lbScore.setHorizontalAlignment(SwingConstants.CENTER);
		lbScore.setFont(lbScore.getFont().deriveFont(lbScore.getFont().getStyle() | Font.BOLD));
		lbScore.setFont(new Font("굴림", Font.BOLD, 30));
		lbScore.setBounds(77, 70, 165, 50);
		panel_2.add(lbScore);

		JLabel[] lbMine = new JLabel[10];
		for(int i=0;i<10;i++) {
			lbMine[i] = new JLabel("");
			lbMine[i].setIcon(new ImageIcon(".\\Img\\1\\mine.jpg"));
			panel_3.add(lbMine[i]);
		}

		int randX = (int) (Math.random()*9);
		int randY = (int) (Math.random()*9);
		for(int i=0;i<10;i++) {
			if(!b_Mine[randX][randY]) {
				b_Mine[randX][randY] = true;
				randX = (int) (Math.random()*9);
				randY = (int) (Math.random()*9);
			}else {
				i--;
				randX = (int) (Math.random()*9);
				randY = (int) (Math.random()*9);
			}
		}

		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				btnMine[i][j] = new JButton("");
				btnMine[i][j].setIcon(new ImageIcon(".\\Img\\1\\nonClick.jpg"));

				btnMine[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
					}
				});

				int selX = i;
				int selY = j;

				btnMine[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(e_GameState== 0) {
							if (e.getButton() == MouseEvent.BUTTON1) {//왼쪽 마우스 버튼
								if(b_Mine[selX][selY]) {
									//폭탄 눌렀을때
									if(e_State[selX][selY] != 2) {
										showMine();
										btnMine[selX][selY].setIcon(new ImageIcon(".\\Img\\1\\selBomb.jpg"));
										stateChange(1);
									}
								}else {
									//폭탄 아닌거 눌렀을때
									if(e_State[selX][selY] != 2) {
										btnMine[selX][selY].setIcon(new ImageIcon(".\\Img\\1\\mineCount"+viewMine(selX,selY)+".jpg"));
										e_State[selX][selY] = 1;
									}
								}
							} 
							else if (e.getButton() == MouseEvent.BUTTON3) {//오른쪽 마우스 버튼
								if(e_State[selX][selY] == 0) {
									if(e_findMine <= 9) {
										btnMine[selX][selY].setIcon(new ImageIcon(".\\Img\\1\\mine.jpg")); 
										lbMine[e_findMine++].setVisible(false);
										e_State[selX][selY] = 2;
									}
									//게임이 끝났으면
									if(e_findMine >= 10) {
										isEnd();
									}
								}
								else if(e_State[selX][selY] == 2) {//rbuttonclick
									btnMine[selX][selY].setIcon(new ImageIcon(".\\Img\\1\\nonClick.jpg"));
									lbMine[--e_findMine].setVisible(true);
									e_State[selX][selY] = 0;
								}

							} 
						}

					}
				});
				panel_1.add(btnMine[i][j]);
			}

		}
		



		splitPane.setDividerLocation(400);
	}
	//주변에 폭탄갯수 찾아내는 메소드
	private int viewMine(int x,int y) {
		//주변폭탄개수
		int cnt = 0;
		//왼쪽 위
		if(x > 0) if(y > 0) if(b_Mine[x-1][y-1])cnt++;
		//위
		if(y > 0)if(b_Mine[x][y-1])cnt++;
		//오른쪽위
		if(x < 9)if(y > 0)if(b_Mine[x+1][y-1])cnt++;
		//왼쪽
		if(x > 0)if(b_Mine[x-1][y])cnt++;
		//오른쪽
		if(x < 9)if(b_Mine[x+1][y])cnt++;
		//왼쪽 밑
		if(x > 0)if(y < 9)if(b_Mine[x-1][y+1])cnt++;
		//밑
		if(y < 9)if(b_Mine[x][y+1])cnt++;
		//오른쪽 밑
		if(x < 9)if(y < 9)if(b_Mine[x+1][y+1])cnt++;

		return cnt;
	}
	//게임 끝났을때 폭탄 보여주는 메소드
	private void showMine(){
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(b_Mine[i][j]) {
					btnMine[i][j].setIcon(new ImageIcon(".\\Img\\1\\bomb.jpg"));
				}
				if(e_State[i][j] == 2) {
					if(!b_Mine[i][j]) {
						btnMine[i][j].setIcon(new ImageIcon(".\\Img\\1\\nonBomb.jpg"));
					}
				}
			}
		}
	}
	//게임 끝났는지 알려주는 메소드
	private void isEnd() {
		int cnt = 0;
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(b_Mine[i][j]) {
					if(e_State[i][j] == 2) {
						cnt++;
					}
				}
			}
		}
		if(cnt >= 10)
			stateChange(1);
	}
	//스코어합산
	private void getScore() {
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {

				if(e_State[i][j] == 2) {
					if(b_Mine[i][j]) {
						e_Score += 1000;
					}
				}
			}
		}
	}
	//게임state 바꾸기
	private void stateChange(int type) {
		e_GameState = type;
		switch(type) {
		case 0:
			break;
		case 1:
			getScore();
			tmp = e_Score-gameTime*10;
			break;
		case 2:
			setVisible(false);
			gv.setVisible(true);
			gv.getScore(0, e_Score);
			break;
		}
		
	}

	class timeJob extends TimerTask{

		@Override
		public void run() {
			
			if(e_GameState==0)
				lbTimer.setText(++gameTime+"");
			if(e_GameState == 1) {
				if(endTime++ > 3)
					stateChange(2);
			}
		}

	}
	class timeScore extends TimerTask{
		@Override
		public void run() {
			if(e_GameState == 1) {
				if(tmp < 0)
					tmp =0;
				if(e_Score > tmp) {
					e_Score--;
					endTime=0;
					if(tmp2++ >= 9) {
					gameTime--;
					tmp2 = 0;
					}
				}
				lbScore.setText(e_Score+"");
				lbTimer.setText(gameTime+"");
			}
		}

	}
}