package gameController;

import java.awt.Color;
import java.awt.ComponentOrientation;
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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import game1.FindMine;
import game2.SelPic;
import game3.SetPic;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
public class GameView extends JFrame {
	private JPanel contentPane;
	private JButton btnFindMine;
	int e_Score;
	boolean b_Login;
	
	int game1Score=-1;
	int game2Score=-1;
	int game3Score=-1;
	DBConnection db = new DBConnection();
	GameView gv = this;
	private JTextField tfGame1;
	private JTextField tfGame2;
	private JTextField tfGame3;
	private JTextField tfScore;
	private JComboBox comboBox;
	private JLabel lbisLogin;
	public String userID;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public void getScore(int type, int score) {
		if(type == 0) {
			game1Score = score;
			tfGame1.setText(game1Score+"");
		}else if(type == 1) {
			game2Score = score;
			tfGame2.setText(game2Score+"");
		}else if(type == 2) {
			game3Score = score;
			tfGame3.setText(game3Score+"");
		}
		if(game1Score >= 0 && game2Score >= 0 && game3Score >= 0) {//3게임이 끝나면
			e_Score = game1Score+game2Score+game3Score;
			tfScore.setText(e_Score+"");
			if(b_Login)
			db.setScore(userID,e_Score);
			new MessageBox(3,gv,db);
		}
	}
	public GameView() {
		setBackground(Color.YELLOW);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 687, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pTop = new JPanel();
		pTop.setBounds(5, 5, 661, 45);
		contentPane.add(pTop);
		pTop.setLayout(null);
		
		JLabel lbTitle = new JLabel("미 니 게 임");
		lbTitle.setBounds(248, 5, 164, 35);
		lbTitle.setFont(new Font("굴림", Font.BOLD, 30));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pTop.add(lbTitle);
		pTop.add(getComboBox());
		
		lbisLogin = new JLabel("");
		lbisLogin.setBounds(15, 15, 164, 25);
		pTop.add(lbisLogin);
		
		JPanel pBottom = new JPanel();
		pBottom.setBounds(5, 354, 661, 45);
		contentPane.add(pBottom);
		pBottom.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblb = new JLabel("총 점수");
		pBottom.add(lblb);
		lblb.setHorizontalAlignment(SwingConstants.CENTER);
		lblb.setFont(lblb.getFont().deriveFont(lblb.getFont().getStyle() | Font.BOLD));
		
		tfScore = new JTextField();
		tfScore.setHorizontalAlignment(SwingConstants.CENTER);
		tfScore.setFont(new Font("굴림", Font.BOLD, 30));
		pBottom.add(tfScore);
		tfScore.setColumns(10);
		
		
		
		JPanel pCenter = new JPanel();
		pCenter.setBounds(5, 50, 661, 306);
		pCenter.setBackground(Color.PINK);
		contentPane.add(pCenter);
		pCenter.setLayout(null);
		
		btnFindMine = new JButton("");
		btnFindMine.setIcon(new ImageIcon(".\\Img\\findMine.jpg"));
		btnFindMine.setBorder(new TitledBorder(new LineBorder(new Color(105, 105, 105), 3, true), "\uC9C0\uB8B0\uCC3E\uAE30", TitledBorder.CENTER, TitledBorder.TOP, null, UIManager.getColor("Button.darkShadow")));
		btnFindMine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new FindMine(gv).setVisible(true);
			}
		});
		btnFindMine.setBounds(10, 10, 200, 244);
		pCenter.add(btnFindMine);
		
		JButton btnFindPic = new JButton("");
		btnFindPic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new SelPic(gv).setVisible(true);
			}
		});
		btnFindPic.setIcon(new ImageIcon(".\\Img\\findPic.jpg"));
		btnFindPic.setBorder(new TitledBorder(new LineBorder(new Color(105, 105, 105), 3, true), "\uAC19\uC740\uADF8\uB9BC\uCC3E\uAE30", TitledBorder.CENTER, TitledBorder.TOP, null, UIManager.getColor("Button.disabledForeground")));
		btnFindPic.setBounds(230, 10, 200, 244);
		pCenter.add(btnFindPic);
		
		JButton btnSetPic = new JButton("");
		btnSetPic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new SetPic(gv).setVisible(true);
			}
		});
		btnSetPic.setIcon(new ImageIcon(".\\Img\\setPic.jpg"));
		btnSetPic.setBorder(new TitledBorder(new LineBorder(new Color(105, 105, 105), 3, true), "\uADF8\uB9BC\uB9DE\uCD94\uAE30", TitledBorder.CENTER, TitledBorder.TOP, null, UIManager.getColor("Button.darkShadow")));
		btnSetPic.setBounds(450, 10, 200, 244);
		pCenter.add(btnSetPic);
		
		JLabel lblNewLabel = new JLabel("점수");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 267, 50, 24);
		pCenter.add(lblNewLabel);
		
		JLabel label = new JLabel("점수");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.BOLD, 16));
		label.setBounds(230, 267, 50, 24);
		pCenter.add(label);
		
		JLabel label_1 = new JLabel("점수");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("굴림", Font.BOLD, 16));
		label_1.setBounds(450, 267, 50, 24);
		pCenter.add(label_1);
		
		tfGame1 = new JTextField();
		tfGame1.setFont(new Font("굴림", Font.BOLD, 20));
		tfGame1.setHorizontalAlignment(SwingConstants.CENTER);
		tfGame1.setBounds(77, 268, 133, 23);
		pCenter.add(tfGame1);
		tfGame1.setColumns(10);
		
		tfGame2 = new JTextField();
		tfGame2.setFont(new Font("굴림", Font.BOLD, 20));
		tfGame2.setHorizontalAlignment(SwingConstants.CENTER);
		tfGame2.setColumns(10);
		tfGame2.setBounds(285, 268, 145, 23);
		pCenter.add(tfGame2);
		
		tfGame3 = new JTextField();
		tfGame3.setFont(new Font("굴림", Font.BOLD, 20));
		tfGame3.setHorizontalAlignment(SwingConstants.CENTER);
		tfGame3.setColumns(10);
		tfGame3.setBounds(502, 268, 148, 23);
		pCenter.add(tfGame3);
		
	}
	public void login() {
		lbisLogin.setText(userID+"님 환영합니다");
	}
	public void logout() {
		lbisLogin.setText("");
		userID = "";
		b_Login = false;
	}
	private JComboBox getComboBox() {
		int index =-1;
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	int index = comboBox.getSelectedIndex();
					new MessageBox(index,gv,db);
		        }
		    });
			comboBox.setBackground(Color.WHITE);
			comboBox.setForeground(Color.BLACK);
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"로그인", "회원가입","로그아웃","랭크보기"}));
			comboBox.setBounds(554, 11, 96, 21);
		}
		return comboBox;
	}
}
