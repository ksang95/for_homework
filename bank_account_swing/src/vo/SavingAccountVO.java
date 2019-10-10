package vo;

public class SavingAccountVO extends BankAccountVO {
	int monthly;
	int interest;
	double rate;
	int total;
	int refund;
	
	public SavingAccountVO() {
		super();
	}
	
	public SavingAccountVO(String name, int term, int monthly) {
		super(name, term);
		this.monthly = monthly;
		this.rate = 5.7;
		this.interest = (int)(monthly*rate/100);
		this.total = monthly*term;
		this.refund = total+interest*term;
	}

	public SavingAccountVO(String name, int term, int monthly, double rate) {
		super(name, term);
		this.monthly = monthly;
		this.rate = rate;
		this.interest = (int)(monthly*rate/100);
		this.total = monthly*term;
		this.refund = total+interest*term;
	}

	public int getMonthly() {
		return monthly;
	}

	public void setMonthly(int monthly) {
		this.monthly = monthly;
	}

	public int getInterest() {
		return interest;
	}

	public void setInterest(int interest) {
		this.interest = interest;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRefund() {
		return refund;
	}

	public void setRefund(int refund) {
		this.refund = refund;
	}

	@Override
	public String toString() {
		return "SavingAccountVO [monthly=" + monthly + ", interest=" + interest + ", rate=" + rate + ", total=" + total
				+ ", refund=" + refund + ", name=" + name + ", term=" + term + ", accountnum=" + accountnum + "]";
	}
	
	
}
