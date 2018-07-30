package com.revature.jdbcbankmodel;

public class RegisteredAccount {
	private int bankaccountid;
	private double accountbalan;
	private int userid;
	
	public RegisteredAccount(int userid, int bankaccountid, double accountbalan) {
		super();
		this.userid = userid;
		this.bankaccountid = bankaccountid;
		this.accountbalan = accountbalan;
		
	}
	public int getBankaccountid() {
		return bankaccountid;
	}
	public void setBankaccountid(char bankaccountid) {
		this.bankaccountid = bankaccountid;
	}
	public double getAccountbalan() {
		return accountbalan;
	}
	public double setAccountbalan(double accountbalan) {
		return this.accountbalan = accountbalan;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(char userid) {
		this.userid = userid;
	}
	
//	A user can add to or withdraw from an account. 
	
	public double depositBalan(float deposit_amount) {
		accountbalan = (double) (accountbalan + deposit_amount);
		double newaccntbalan = setAccountbalan(accountbalan);
		return newaccntbalan;
	}

	public double withdrawBalan(float withdraw_amount) {
		accountbalan = (double) (accountbalan - withdraw_amount);
		return setAccountbalan(accountbalan);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(accountbalan);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + bankaccountid;
		result = prime * result + userid;
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
		RegisteredAccount other = (RegisteredAccount) obj;
		if (Double.doubleToLongBits(accountbalan) != Double.doubleToLongBits(other.accountbalan))
			return false;
		if (bankaccountid != other.bankaccountid)
			return false;
		if (userid != other.userid)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RegisteredAccount [bankaccountid=" + bankaccountid + ", accountbalan=" + accountbalan + ", userid="
				+ userid + "]";
	}
	
	
}
