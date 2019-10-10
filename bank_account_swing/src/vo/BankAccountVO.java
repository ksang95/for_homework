package vo;

public class BankAccountVO {
	String name;
	int term;
	String accountnum;
	
	public BankAccountVO() {
		super();
	}
	
	public BankAccountVO(String name, int term) {
		super();
		this.name = name;
		this.term = term;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	
	public String getAccountnum() {
		return accountnum;
	}
	public void setAccountnum(String accountnum) {
		this.accountnum = accountnum;
	}
	
	@Override
	public String toString() {
		return "BankAccountVO [name=" + name + ", term=" + term + ", accountnum=" + accountnum + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountnum == null) ? 0 : accountnum.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccountVO other = (BankAccountVO) obj;
		if (accountnum == null) {
			if (other.accountnum != null)
				return false;
		} else if (!accountnum.equals(other.accountnum))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
