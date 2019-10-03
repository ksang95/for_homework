package dao;

import vo.BankAccountVO;

public interface BankAccountDAO {
	String createAccount(BankAccountVO vo);
	String getAccount(String accountnum);	
}
