package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controller.CreateAccountController;
import controller.GetAccountController;
import vo.BankAccountVO;
import vo.LoanAccountVO;
import vo.SavingAccountVO;

public class MyBankApp extends JFrame {
	JPanel panel, center, top, subtop1, subtop2;
	JPanel[] subcen;
	JButton home, confirm, saving, loan, search, home2, confirm2;
	JLabel title, subt, name, term, money, accountnum, msg, subm;
	JTextField namet, termt, moneyt, accountnumt;
	JTextArea textArea;

	public MyBankApp() {
		panel = new JPanel();
		panel.setSize(600, 600);
		panel.setLayout(new BorderLayout(10, 20));

		// center와 top 만들기
		center = new JPanel();
		center.setLayout(new FlowLayout());
		top = new JPanel();
		top.setLayout(new BorderLayout());
		top.setPreferredSize(new Dimension(panel.getWidth(), 100));

		// top 세팅
		subtop1 = new JPanel();
		subtop2 = new JPanel();
		home = new JButton("홈");
		title = new JLabel("환영합니다!");
		subt = new JLabel("이용하실 서비스를 선택해 주세요");
		topSetting();

		// center 공간 설정 (subcen[3]-[6]가 각 화면 나타냄)
		subcen = new JPanel[7];
		for (int i = 0; i < 7; i++) {
			subcen[i] = new JPanel();
		}
		for (int i = 3; i < 7; i++) {
			subcen[i].setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
			subcen[i].setPreferredSize(new Dimension(400, 400));
		}

		// 통장 개설 화면
		name = new JLabel("이름");
		term = new JLabel("기간");
		money = new JLabel("월 입금액");
		namet = new JTextField(15);
		termt = new JTextField(15);
		moneyt = new JTextField(15);
		confirm = new JButton("개설하기");
		createAccountUI();

		// 홈 화면
		saving = new JButton("적금 통장 개설");
		loan = new JButton("대출 통장 개설");
		search = new JButton("내 통장 확인");
		homeUI();

		// 내 통장 검색 화면
		msg=new JLabel("계좌 번호를 입력하세요.");
		subm=new JLabel("'-' 포함");
		accountnum = new JLabel("계좌 번호");
		accountnumt = new JTextField(15);
		confirm2= new JButton("확인");
		searchAccountUI();

		// 통장 정보 확인 화면
		textArea = new JTextArea(10, 20);
		home2 = new JButton("홈으로");
		accountInfoUI();

		// 프레임에 전체 넣기
		panel.add(center, BorderLayout.CENTER);
		panel.add(top, BorderLayout.NORTH);
		setTitle("상희 은행");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		setSize(panel.getSize());
		setVisible(true);
	}

	void topSetting() {
		subtop1.setLayout(new GridLayout(2, 1));
		top.add(subtop1, BorderLayout.SOUTH);
		subtop2.setLayout(new FlowLayout(FlowLayout.LEFT));
		top.add(subtop2, BorderLayout.NORTH);
		home.setFont(new Font("Times", Font.BOLD, 20));
		home.setPreferredSize((new Dimension(60, 60)));
		home.addActionListener(new miniActionListener());
		subtop2.add(home);
		home.setVisible(false);
		labelFontSetting(title, Font.BOLD, 25);
		labelFontSetting(subt, Font.PLAIN, 20);
		subtop1.add(title);
		subtop1.add(subt);
	}

	void createAccountUI() {
		labelFontSetting(name, Font.BOLD, 20);
		labelFontSetting(term, Font.BOLD, 20);
		labelFontSetting(money, Font.BOLD, 20);
		namet.setFont(new Font("Times", Font.BOLD, 20));
		termt.setFont(new Font("Times", Font.BOLD, 20));
		moneyt.setFont(new Font("Times", Font.BOLD, 20));
		subcen[0].add(name);
		subcen[0].add(namet);
		subcen[1].add(term);
		subcen[1].add(termt);
		subcen[2].add(money);
		subcen[2].add(moneyt);
		for (int i = 0; i < 3; i++) {
			subcen[3].add(subcen[i]);
		}
		confirm.setFont(new Font("Times", Font.BOLD, 20));
		confirm.addActionListener(new miniActionListener());
		subcen[3].add(confirm);
		center.add(subcen[3]);
		subcen[3].setVisible(false);
	}

	void homeUI() {
		homeButtonSetting(saving);
		homeButtonSetting(loan);
		homeButtonSetting(search);
		subcen[4].add(saving);
		subcen[4].add(loan);
		subcen[4].add(search);
		center.add(subcen[4]);
	}

	void searchAccountUI() {
		labelFontSetting(accountnum, Font.BOLD, 20);
		labelFontSetting(msg, Font.BOLD, 20);
		labelFontSetting(subm, Font.BOLD, 20);
		subm.setForeground(Color.red);
		accountnumt.setFont(new Font("Times", Font.BOLD, 20));
		confirm2.setFont(new Font("Times", Font.BOLD, 20));
		confirm2.addActionListener(new miniActionListener());
		subcen[5].add(msg);
		subcen[5].add(subm);
		subcen[5].add(accountnum);
		subcen[5].add(accountnumt);
		subcen[5].add(confirm2);
		center.add(subcen[5]);
		subcen[5].setVisible(false);
	}

	void accountInfoUI() {
		textArea.setFont(new Font("Times", Font.PLAIN, 20));
		home2.setFont(new Font("Times", Font.BOLD, 20));
		home2.addActionListener(new miniActionListener());
		subcen[6].add(textArea);
		subcen[6].add(home2);
		center.add(subcen[6]);
		subcen[6].setVisible(false);
	}

	void labelFontSetting(JLabel label, int style, int size) {
		label.setFont(new Font("Times", style, size));
		label.setHorizontalAlignment(JLabel.CENTER);
	}

	void homeButtonSetting(JButton btn) {
		btn.setPreferredSize(new Dimension(300, 60));
		btn.setFont(new Font("Times", Font.BOLD, 20));
		btn.addActionListener(new miniActionListener());
	}

	class miniActionListener implements ActionListener {

		void menu(int i) {
			subcen[4].setVisible(false);
			home.setVisible(true);
			subcen[i].setVisible(true);
		}

		void clear() {
			title.setText("환영합니다!");
			subt.setVisible(true);
			namet.setText("");
			termt.setText("");
			moneyt.setText("");
			textArea.setText("");
			subcen[3].setVisible(false);
			home.setVisible(false);
			subcen[4].setVisible(true);
		}

		void confirmLoanAccount(BankAccountVO result) {
			LoanAccountVO loanResult = (LoanAccountVO) result;
			textArea.append("계좌번호: " + loanResult.getAccountnum() + "\n");
			textArea.append("이름: " + loanResult.getName() + "\n");
			textArea.append("기간: " + loanResult.getTerm() + "\n");
			textArea.append("이율: " + loanResult.getRate() + "%\n");
			textArea.append("대출금액: " + loanResult.getLoan() + "원\n");
			textArea.append("이자액: " + loanResult.getInterest() + "원\n");
			textArea.append("월 상환액: " + loanResult.getMonthly() + "원\n");
			title.setText("대출 통장");
			subcen[3].setVisible(false);
			subcen[5].setVisible(false);
			subcen[6].setVisible(true);
		}

		void confirmSavingAccount(BankAccountVO result) {
			SavingAccountVO savingResult = (SavingAccountVO) result;
			textArea.append("계좌번호: " + savingResult.getAccountnum() + "\n");
			textArea.append("이름: " + savingResult.getName() + "\n");
			textArea.append("기간: " + savingResult.getTerm() + "\n");
			textArea.append("이율: " + savingResult.getRate() + "%\n");
			textArea.append("월 입금액: " + savingResult.getMonthly() + "원\n");
			textArea.append("이자액: " + savingResult.getInterest() + "원\n");
			textArea.append("총 납입금액: " + savingResult.getTotal() + "원\n");
			textArea.append("만기시 환급액: " + savingResult.getRefund() + "원\n");
			title.setText("적금 통장");
			subcen[3].setVisible(false);
			subcen[5].setVisible(false);
			subcen[6].setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String comm = e.getActionCommand();
			Map<String, String> map = new HashMap<String, String>();
			BankAccountVO result = null;
			switch (comm) {
			case "적금 통장 개설":
				title.setText("적금 통장 개설");
				subt.setVisible(false);
				name.setText("이름");
				term.setText("기간(월)");
				money.setText("월 입금액");
				menu(3);
				break;
			case "대출 통장 개설":
				title.setText("대출 통장 개설");
				subt.setVisible(false);
				name.setText("이름");
				term.setText("기간(월)");
				money.setText("대출금액");
				menu(3);
				break;
			case "내 통장 확인":
				title.setText("내 통장 확인");
				subt.setVisible(false);
				menu(5);
				break;
			case "홈":
			case "홈으로":
				clear();
				break;
			case "개설하기":
				switch (title.getText()) {
				case "적금 통장 개설":
					map.put("type", "saving");
					map.put("name", namet.getText());
					map.put("term", termt.getText());
					map.put("monthly", moneyt.getText());
					result = CreateAccountController.service(map);
					confirmSavingAccount(result);
					break;
				case "대출 통장 개설":
					map.put("type", "loan");
					map.put("name", namet.getText());
					map.put("term", termt.getText());
					map.put("loan", moneyt.getText());
					result = CreateAccountController.service(map);
					confirmLoanAccount(result);
					break;
				}
				break;
			case "확인":
				result = GetAccountController.service(accountnumt.getText());
				if(result==null || result.getAccountnum()==null)
					title.setText("존재하지 않는 통장");
				else if (result instanceof SavingAccountVO)
					confirmSavingAccount(result);
				else if (result instanceof LoanAccountVO)
					confirmLoanAccount(result);
				break;
			}
		}
	}

	public static void main(String[] args) {
		new MyBankApp();
	}

}
