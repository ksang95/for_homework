package service;

import org.json.simple.JSONObject;

import vo.BankAccountVO;

public interface BankAccountService {
	String createAccount(BankAccountVO vo);
	String getAccount(String accountnum);
}
