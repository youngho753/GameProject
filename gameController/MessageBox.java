package gameController;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.security.interfaces.RSAKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class MessageBox extends JFrame{
	private JLabel label;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JTextField tfID;
	private JLabel label_1;
	private JButton btnLogin;
	private JLabel label_2;
	private JLabel label_3;
	private JTextField tfJoinID;
	private JPasswordField tfJoinPW;
	private JLabel label_4;
	private JButton btnJoin;
	private JLabel lblPW;
	private JPasswordField tfJoinPWRepeat;
	private JLabel lbMsg;
	private DBConnection db;
	private JButton btnCheck;
	private JPasswordField tfPassWord;
	GameView gv;
	private JLabel label_5;
	private JPanel panel_1;
	private JLabel label_6;
	private JTextField tfrank1;
	private JLabel label_7;
	private JTextField tfrank2;
	private JLabel label_8;
	private JTextField tfrank3;
	private JLabel label_9;
	private JTextField tfrank4;
	private JLabel label_10;
	private JTextField[] tfrankscore = new JTextField[5];
	private JTextField[] tfrankid = new JTextField[5];
	public MessageBox(int type,GameView gv,DBConnection db) {//type 0 로그인 1 회원가입 2 로그아웃 3메시지박스
		this.db = db;
		this.gv = gv;
		getContentPane().setLayout(null);
		if(type == 0) {
			setTitle("로그인");
			showLogin();
		}else if(type == 1){
			setTitle("회원가입");
			showJoin();
		}else if(type == 2){
			gv.logout();
			return;
		}else if(type == 3) {
			showRank();
		}

		setSize(452,300);
		setVisible(true);
	}
	public void showLogin() {
		getContentPane().setLayout(null);
		getContentPane().add(getLabel());
		getContentPane().add(getPanel());
		//db.login()
	}
	public void showJoin() {
		getContentPane().add(getLabel_2());
		getContentPane().add(getLabel_3());
		getContentPane().add(getTfJoinID());
		getContentPane().add(getTfJoinPW());
		getContentPane().add(getLabel_4());
		getContentPane().add(getBtnJoin());
		getContentPane().add(getLblPW());
		getContentPane().add(getTfJoinPWRepeat());
		getContentPane().add(getBtnCheck());
	}
	public void showRank() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getLabel_5(), BorderLayout.NORTH);
		getContentPane().add(getPanel_1(), BorderLayout.CENTER);
		ResultSet rs = db.viewRank();
		try {
			int whilecnt =0;
			while(rs.next()) {
				if(whilecnt >= 5)
					break;
				tfrankid[whilecnt].setText(rs.getString("userid"));
				tfrankscore[whilecnt].setText(rs.getInt("score")+"");
				whilecnt++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public MessageBox() {
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("로 그 인");
			label.setBounds(0, 0, 436, 35);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("굴림", Font.BOLD, 30));
		}
		return label;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 35, 436, 226);
			panel.setLayout(null);
			panel.add(getLblNewLabel());
			panel.add(getTfID());
			panel.add(getLabel_1());
			panel.add(getBtnLogin());
			panel.add(getTfPassWord());
		}
		return panel;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("I D");
			lblNewLabel.setBounds(56, 21, 70, 28);
			lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}
	private JTextField getTfID() {
		if (tfID == null) {
			tfID = new JTextField();
			tfID.setBounds(143, 21, 219, 28);
			tfID.setFont(new Font("굴림", Font.BOLD, 20));
			tfID.setHorizontalAlignment(SwingConstants.RIGHT);
			tfID.setColumns(10);
		}
		return tfID;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("P W");
			label_1.setBounds(56, 71, 70, 28);
			label_1.setHorizontalAlignment(SwingConstants.CENTER);
			label_1.setFont(new Font("굴림", Font.BOLD, 20));
		}
		return label_1;
	}
	private JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton("로그인");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gv.b_Login = db.userLogin(tfID.getText(), tfPassWord.getText());
					if(gv.b_Login) {
						gv.userID = tfID.getText();
						gv.login();
						dispose();
					}else {
						tfID.setText("회원정보 틀림");
						tfPassWord.setText("");
					}
				}
			});
			btnLogin.setBounds(56, 171, 306, 34);
		}
		return btnLogin;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("I D");
			label_2.setHorizontalAlignment(SwingConstants.CENTER);
			label_2.setFont(new Font("굴림", Font.BOLD, 20));
			label_2.setBounds(56, 56, 70, 28);
		}
		return label_2;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("P W");
			label_3.setHorizontalAlignment(SwingConstants.CENTER);
			label_3.setFont(new Font("굴림", Font.BOLD, 20));
			label_3.setBounds(56, 106, 70, 28);
		}
		return label_3;
	}
	private JTextField getTfJoinID() {
		if (tfJoinID == null) {
			tfJoinID = new JTextField();
			tfJoinID.setHorizontalAlignment(SwingConstants.RIGHT);
			tfJoinID.setFont(new Font("굴림", Font.BOLD, 20));
			tfJoinID.setColumns(10);
			tfJoinID.setBounds(143, 56, 219, 28);
		}
		return tfJoinID;
	}
	private JTextField getTfJoinPW() {
		if (tfJoinPW == null) {
			tfJoinPW = new JPasswordField();
			tfJoinPW.setHorizontalAlignment(SwingConstants.RIGHT);
			tfJoinPW.setFont(new Font("굴림", Font.BOLD, 20));
			tfJoinPW.setColumns(10);
			tfJoinPW.setBounds(143, 106, 219, 28);
		}
		return tfJoinPW;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("회 원 가 입");
			label_4.setHorizontalAlignment(SwingConstants.CENTER);
			label_4.setFont(new Font("굴림", Font.BOLD, 30));
			label_4.setBounds(0, 0, 434, 35);
		}
		return label_4;
	}
	private JButton getBtnJoin() {
		if (btnJoin == null) {
			btnJoin = new JButton("회원가입");
			btnJoin.setEnabled(false);
			btnJoin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getTfJoinPW().getText().equals(getTfJoinPWRepeat().getText())) {
						db.userInsert(getTfJoinID().getText(), getTfJoinPW().getText());
						gv.userID = getTfJoinID().getText();
						gv.login();
						dispose();
					}else {
						getTfJoinPWRepeat().setText("비밀번호 틀림.");
					}
				}
			});
			btnJoin.setBounds(35, 206, 327, 34);
		}
		return btnJoin;
	}
	private JLabel getLblPW() {
		if (lblPW == null) {
			lblPW = new JLabel("P W확인");
			lblPW.setHorizontalAlignment(SwingConstants.CENTER);
			lblPW.setFont(new Font("굴림", Font.BOLD, 20));
			lblPW.setBounds(35, 156, 91, 28);
		}
		return lblPW;
	}
	private JTextField getTfJoinPWRepeat() {
		if (tfJoinPWRepeat == null) {
			tfJoinPWRepeat = new JPasswordField();
			tfJoinPWRepeat.setHorizontalAlignment(SwingConstants.RIGHT);
			tfJoinPWRepeat.setFont(new Font("굴림", Font.BOLD, 20));
			tfJoinPWRepeat.setColumns(10);
			tfJoinPWRepeat.setBounds(143, 156, 219, 28);
		}
		return tfJoinPWRepeat;
	}
	private JLabel getLbMsg() {
		if (lbMsg == null) {
			lbMsg = new JLabel("");
			lbMsg.setFont(new Font("굴림", Font.BOLD, 20));
		}
		return lbMsg;
	}
	private JButton getBtnCheck() {
		if (btnCheck == null) {
			btnCheck = new JButton("중복검사");
			btnCheck.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean b_userCheck = db.userCheck(tfJoinID.getText());
					if(b_userCheck) {
						btnJoin.setEnabled(true);
						tfJoinID.setEnabled(false);
					}else
						tfJoinID.setText("중복된 아이디입니다");
				}
			});
			btnCheck.setBounds(362, 56, 81, 28);
		}
		return btnCheck;
	}
	private JPasswordField getTfPassWord() {
		if (tfPassWord == null) {
			tfPassWord = new JPasswordField();
			tfPassWord.setHorizontalAlignment(SwingConstants.RIGHT);
			tfPassWord.setBounds(143, 70, 219, 28);
		}
		return tfPassWord;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("랭 킹");
			label_5.setHorizontalAlignment(SwingConstants.CENTER);
			label_5.setFont(new Font("굴림", Font.BOLD, 30));
		}
		return label_5;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.add(getLabel_6());
			panel_1.add(getLabel_7());
			panel_1.add(getLabel_8());
			panel_1.add(getLabel_9());
			panel_1.add(getLabel_10());
			
			JTextField[] tfid = gettfrankid();
			JTextField[] tfscore = gettfrankscore();
			for(int i=0;i<5;i++) {
				panel_1.add(tfid[i]);
				panel_1.add(tfscore[i]);
			}
		
			
		}
		return panel_1;
	}
	private JLabel getLabel_6() {
		if (label_6 == null) {
			label_6 = new JLabel("1");
			label_6.setFont(new Font("굴림", Font.BOLD, 25));
			label_6.setHorizontalAlignment(SwingConstants.CENTER);
			label_6.setBounds(12, 10, 53, 35);
		}
		return label_6;
	}
	
	private JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel("2");
			label_7.setFont(new Font("굴림", Font.BOLD, 25));
			label_7.setHorizontalAlignment(SwingConstants.CENTER);
			label_7.setBounds(12, 51, 53, 35);
		}
		return label_7;
	}
	
	private JLabel getLabel_8() {
		if (label_8 == null) {
			label_8 = new JLabel("3");
			label_8.setFont(new Font("굴림", Font.BOLD, 25));
			label_8.setHorizontalAlignment(SwingConstants.CENTER);
			label_8.setBounds(12, 94, 53, 35);
		}
		return label_8;
	}
	
	private JLabel getLabel_9() {
		if (label_9 == null) {
			label_9 = new JLabel("4");
			label_9.setFont(new Font("굴림", Font.BOLD, 25));
			label_9.setHorizontalAlignment(SwingConstants.CENTER);
			label_9.setBounds(12, 138, 53, 35);
		}
		return label_9;
	}
	
	private JLabel getLabel_10() {
		if (label_10 == null) {
			label_10 = new JLabel("5");
			label_10.setFont(new Font("굴림", Font.BOLD, 25));
			label_10.setHorizontalAlignment(SwingConstants.CENTER);
			label_10.setBounds(12, 180, 53, 35);
		}
		return label_10;
	}
	private JTextField[] gettfrankscore() {
		for(int i=0;i<5;i++) {
			if (tfrankscore[i] == null) {
				tfrankscore[i] = new JTextField();
				tfrankscore[i].setFont(new Font("굴림", Font.BOLD, 25));
				tfrankscore[i].setHorizontalAlignment(SwingConstants.CENTER);
				tfrankscore[i].setColumns(10);
				tfrankscore[i].setBounds(207, 10+i*41, 215, 35);
			}
		}
		return tfrankscore;
	}
	private JTextField[] gettfrankid() {
		for(int i=0;i<5;i++) {
			if (tfrankid[i] == null) {
				tfrankid[i] = new JTextField();
				tfrankid[i].setHorizontalAlignment(SwingConstants.CENTER);
				tfrankid[i].setFont(new Font("굴림", Font.BOLD, 25));
				tfrankid[i].setColumns(10);
				tfrankid[i].setBounds(54, 10+i*41, 141, 35);
			}
		}
		return tfrankid;
	}

}
