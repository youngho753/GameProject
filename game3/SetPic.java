package game3;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gameController.GameView;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class SetPic extends JFrame {
	GameView gv;
	private JPanel contentPane;
	private JButton[] btnGame = new JButton[16];
	JLabel lbScore;
	JLabel lbTimer;
	//숫자
	private int[] type= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	private boolean[] b_type = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	private int elapedTime;
	private int find0;
	private int GameState;
	private int gameScore;
	private int gameTimer;
	private int tmp=0;
	private boolean b_End = false;
	private boolean b_tmp = false;
	private boolean b_Score = false;
	private boolean b_cheat = false;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public SetPic(GameView gv) {
		gameScore = 0;
		gameTimer = 0;
		GameState = 0;
		Timer timer= new Timer();
		timeJob tj = new timeJob();
		timer.schedule(tj, 0,10);

		this.gv = gv;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 687, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lbTitle = new JLabel("그 림  맞 추 기");
		lbTitle.setFont(new Font("굴림", Font.BOLD, 30));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbTitle, BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		contentPane.add(splitPane, BorderLayout.CENTER);



		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new GridLayout(4, 4, 0, 0));
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Timer");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD));
		label.setBounds(0, 10, 74, 50);
		panel_1.add(label);
		
		lbTimer = new JLabel("");
		lbTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lbTimer.setFont(new Font("굴림", Font.BOLD, 30));
		lbTimer.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lbTimer.setBounds(77, 10, 165, 50);
		panel_1.add(lbTimer);
		
		JLabel label_2 = new JLabel("Score");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(label_2.getFont().deriveFont(label_2.getFont().getStyle() | Font.BOLD));
		label_2.setBounds(0, 70, 74, 50);
		panel_1.add(label_2);
		
		lbScore = new JLabel("0");
		lbScore.setHorizontalAlignment(SwingConstants.CENTER);
		lbScore.setFont(new Font("굴림", Font.BOLD, 30));
		lbScore.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lbScore.setBounds(77, 70, 165, 50);
		panel_1.add(lbScore);
		
		JButton button = new JButton("치트");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!b_cheat)
				b_cheat = true;
				else
					b_cheat = false;
				
			}
		});
		button.setBounds(0, 270, 255, 78);
		panel_1.add(button);
		splitPane.setDividerLocation(400);
		picRandom();
		for(int i=0;i<16;i++) {
			if (btnGame[i] == null) {
				btnGame[i] = new JButton("");

				btnGame[i].setIcon(new ImageIcon(".\\Img\\3\\"+type[i]+".jpg"));
			}
			int selX = i;

			btnGame[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(type[selX] != 0) {
						if(selX-1 == find0 || selX+1 == find0 || selX+4 == find0 || selX-4 == find0 || b_cheat) {
							btnGame[selX].setIcon(new ImageIcon(".\\Img\\3\\"+0+".jpg"));
							btnGame[find0].setIcon(new ImageIcon(".\\Img\\3\\"+type[selX]+".jpg"));
							type[find0] = type[selX];
							find0 = selX;
							type[selX] = 0;
							if(isEnd())
								SetGameState(1);
						}
					}


				}
			});
			panel.add(btnGame[i]);
		}


	}
	private boolean isEnd() {
		b_tmp = true;
		for(int i=0;i<16;i++) {
			if(type[i] != i) {
				b_tmp = false;
			}
		}
		if(b_tmp)
			return true;
		else
			return false;
	}
	private void picRandom() {
		for(int i=0;i<16;i++) {
			int random =(int) (Math.random()*16); 
			while(true) {
				if(b_type[random]) {
					random =(int) (Math.random()*16);
				}else
					break;
			}
			type[i] = random;
			b_type[random] = true;
			if(type[i] == 0)
				find0 = i;
		}


	}
	private void runTime() {
		if(!b_Score && !b_End) {
			if(elapedTime >= 1000) {
				elapedTime = 0;
				gameTimer++;
				lbTimer.setText(gameTimer+"");
			}
		}
		if(b_Score) {
			if(++tmp >= 5) {
				tmp = 0;
				gameTimer--;
			}
			gameScore--;
			lbTimer.setText(gameTimer+"");
			lbScore.setText(gameScore+"");
			
			if(gameTimer <= 0) {
				b_Score = false;
				b_End = true;
				elapedTime = 0;
			}
		}
		if(b_End) {
			if(elapedTime > 2000) {
				elapedTime = 0;
				SetGameState(2);
			}
		}
	}
	private void SetGameState(int type) {
		switch(type) {
		case 0:
			break;
		case 1:
			//스코어합산
			b_Score = true;
			elapedTime = 0;
			gameScore += 10000;
			break;
		case 2:
			//넘어가기
			gv.setVisible(true);
			gv.getScore(2, gameScore);
			setVisible(false);
			break;
		}
	}
	class timeJob extends TimerTask{

		@Override
		public void run() {
			if(isVisible()) {
				elapedTime+=10;
				runTime();
			}
		}


	}
}
