package dao;

import vo.BankAccountVO;

public interface BankAccountDAO {
	String createAccount(BankAccountVO vo);
	BankAccountVO getAccount(String accountnum);	
}
