package service;

import dao.BankAccountDAO;
import vo.BankAccountVO;

public class BankAccountServiceImpl implements BankAccountService{

	BankAccountDAO dao;
	
	public BankAccountServiceImpl() {}
	
	public BankAccountServiceImpl(BankAccountDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public String createAccount(BankAccountVO vo) {
		// TODO Auto-generated method stub
		return dao.createAccount(vo);
	}

	@Override
	public BankAccountVO getAccount(String accountnum) {
		// TODO Auto-generated method stub
		return dao.getAccount(accountnum);
	}

}
