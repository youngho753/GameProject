package game2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import gameController.GameView;

public class SelPic extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JSplitPane splitPane;
	private JPanel panel;
	private JButton[] btnGame = new JButton[16];
	private ImageIcon[] img; 
	private int[] type;
	private int[] state;
	private boolean b_isSelect;//그림한개 선택중
	private boolean b_notduojwefi;
	private int selNum1;
	private int selNum2;
	private boolean b_1sec;
	private boolean b_once;//
	int elapedTime = 0;//흐른시간
	int gameTimer;
	int gameScore;
	int tmp;
	boolean b_End = false;
	private JPanel panel_1;
	private JLabel label;
	private JLabel label_1;
	private JLabel lbTimer;
	private JLabel lbScore;
	boolean b_checkScore;
	int tenCount;
	GameView gv;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public SelPic(GameView gv) {
		this.gv = gv;
		tenCount = 0;
		b_checkScore = false;
		gameScore = 10000;
		tmp = 0;
		b_End=false;
		b_checkScore = false;
		b_once = true;
		gameTimer = 0;
		Timer timer= new Timer();;
		timeJob tj = new timeJob();;
		timer.schedule(tj, 0,10);
		b_1sec = false;
		selNum1 = -1;
		selNum2 = -1;
		b_isSelect = false;

		img = new ImageIcon[8];
		type = new int[16];
		state = new int[16];
		for(int i=0;i<8;i++) {
			//type 0 nonclick 1 click
			img[i] = new ImageIcon(".\\Img\\2\\"+(i-1)+".jpg");

		}
		for(int i=0;i<16;i++) {
			type[i] = 0;
			state[i] = 1;
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 687, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getLblNewLabel(), BorderLayout.NORTH);
		contentPane.add(getSplitPane(), BorderLayout.CENTER);

		setPictrue();
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("같 은 그 림 찾 기");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
		}
		return lblNewLabel;
	}
	private JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setEnabled(false);;
			splitPane.setLeftComponent(getPanel());
			splitPane.setRightComponent(getPanel_1());
			splitPane.setDividerLocation(400);
		}
		return splitPane;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(4, 4, 0, 0));
			JButton[] GameButton = getBtnGame();
			for(int i=0;i<GameButton.length;i++) {
				panel.add(GameButton[i]);
			}

		}
		return panel;
	}
	private JButton[] getBtnGame() {
		for(int i=0;i<16;i++) {
			if (btnGame[i] == null) {
				btnGame[i] = new JButton("");
			}
			int selX = i;
			btnGame[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(state[selX] == 0) {
						if(!b_1sec)
						flip(selX);
					}
				}
			});
		}
		return btnGame;

	}
	//초기 랜덤 설정
	private void setPictrue() {
		int rand = (int) (Math.random()*8);
		int[] randCnt = new int[8];
		//초기화
		for(int i=0;i<8;i++)
			randCnt[i] = 0;
		for(int i=0;i<16;i++) {
			//rand값 다시설정
			while(true) {
				if(randCnt[rand] >= 2) {
					rand = (int) (Math.random()*8);

				}else
					break;
			}
			btnGame[i].setIcon(new ImageIcon(".\\Img\\2\\"+(rand+1)+".jpg"));
			type[i] = rand+1;
			randCnt[rand]++;
			rand = (int) (Math.random()*8);
		}
	}
	private void flip(int num) {
		//우선 사라지게만 함
		//nonclick
		if(state[num] == 0) {
			btnGame[num].setIcon(new ImageIcon(".\\Img\\2\\"+type[num]+".jpg"));
			state[num] = 1;
			if(!b_isSelect) {//선택중이 아니라면
				selNum1 = num;
				b_isSelect = true;
			}else {//선택중이라면
				selNum2 = num;
				isDuple();
			}
			//click
		}else{
			state[num] = 0;
			btnGame[num].setIcon(null);

		}

	}
	private void isDuple() {
		if(type[selNum1] == type[selNum2]) {//그림이 같다면
			state[selNum1] = 1;
			state[selNum2] = 1;
			b_isSelect = false;
		}else {//그림이 다르다면
			b_1sec = true;
			elapedTime = 0;
		}
	}
	private void runTime() {
		//맨처음
		if(b_once) {
			if(elapedTime > 3000) {
				elapedTime = 0;
				b_once = false;
				for(int i=0;i<16;i++) {
					state[i] = 0;
					btnGame[i].setIcon(null);	
				}
			}
		}
		if(b_checkScore) {
			if(gameTimer > 0) {
				if(++tenCount >= 10) {
					tenCount = 0;
					--gameTimer;
				}
				if(gameScore > 0)
				gameScore-=10;
				lbTimer.setText(gameTimer+"");
				lbScore.setText(gameScore+"");
			}else {
				b_End = true;
				b_checkScore = false;
				elapedTime = 0;
			}
		}
		if(b_End) {
			if(elapedTime > 3000){
				elapedTime = 0;
				
				gv.setVisible(true);
				gv.getScore(1, gameScore);
				setVisible(false);
				dispose();
			}
		}else {
			if(!b_once && !b_checkScore && !b_End) {
				if(elapedTime > 1000) {
					if(b_1sec) {
						elapedTime = 0;
						b_1sec = false;
						state[selNum1] = 0;
						btnGame[selNum1].setIcon(null);
						state[selNum2] = 0;
						btnGame[selNum2].setIcon(null);
						b_isSelect = false;
					}
				}
			}
		}
		if(gameTimer > 3) {
			boolean tmp5 = true;
			for(int i=0;i<16;i++) {
				if(state[i] == 0) {
					tmp5 = false;
				}


			}
			if(tmp5) {
				b_checkScore = true;
				elapedTime = 0;
			}
		}

	}
	class timeJob extends TimerTask{

		@Override
		public void run() {
			if(isVisible())
			elapedTime+=10;
			if(!b_once) {
				tmp+=10;
				if(tmp >= 1000) {
					if(!b_checkScore && !b_End) {
						tmp = 0;
						++gameTimer;
						lbTimer.setText(gameTimer+"");
					}
				}
			}
			runTime();
		}


	}


	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.add(getLabel());
			panel_1.add(getLabel_1());
			panel_1.add(getLbTimer());
			panel_1.add(getLbScore());
		}
		return panel_1;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Timer");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD));
			label.setBounds(0, 10, 74, 50);
		}
		return label;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Score");
			label_1.setHorizontalAlignment(SwingConstants.CENTER);
			label_1.setFont(label_1.getFont().deriveFont(label_1.getFont().getStyle() | Font.BOLD));
			label_1.setBounds(0, 70, 74, 50);
		}
		return label_1;
	}
	private JLabel getLbTimer() {
		if (lbTimer == null) {
			lbTimer = new JLabel("");
			lbTimer.setHorizontalAlignment(SwingConstants.CENTER);
			lbTimer.setFont(new Font("굴림", Font.BOLD, 30));
			lbTimer.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			lbTimer.setBounds(77, 10, 165, 50);
		}
		return lbTimer;
	}
	private JLabel getLbScore() {
		if (lbScore == null) {
			lbScore = new JLabel("10000");
			lbScore.setHorizontalAlignment(SwingConstants.CENTER);
			lbScore.setFont(new Font("굴림", Font.BOLD, 30));
			lbScore.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			lbScore.setBounds(77, 70, 165, 50);
		}
		return lbScore;
	}
}
