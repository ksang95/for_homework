package vo;

public class LoanAccountVO extends BankAccountVO {
	int loan;
	int monthly;
	int interest;
	double rate;
	
	public LoanAccountVO() {
		super();
	}
	
	public LoanAccountVO(String name, int term, int loan) {
		super(name, term);
		this.loan = loan;
		this.rate= 12.5;
		this.interest = (int)(loan*rate/100);
		this.monthly = (loan+interest)/term;
	}
	
	public LoanAccountVO(String name, int term, int loan, double rate) {
		super(name, term);
		this.loan = loan;
		this.rate= rate;
		this.interest = (int)(loan*rate/100);
		this.monthly = (loan+interest)/term;
	}

	public int getLoan() {
		return loan;
	}
	public void setLoan(int loan) {
		this.loan = loan;
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
	@Override
	public String toString() {
		return "LoanAccountVO [loan=" + loan + ", monthly=" + monthly + ", interest=" + interest + ", rate=" + rate
				+ ", name=" + name + ", term=" + term + ", accountnum=" + accountnum + "]";
	}
	
	
}
