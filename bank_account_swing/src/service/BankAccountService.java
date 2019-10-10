package service;

import vo.BankAccountVO;

public interface BankAccountService {
	String createAccount(BankAccountVO vo);
	BankAccountVO getAccount(String accountnum);
}
