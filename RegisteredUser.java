package com.revature.jdbcbankmodel;

public class RegisteredUser {	
	
	private int userid;
	private String fullname;
	private String username;
	private String password;
	private boolean approveuser;
	private boolean superuser;
	
//	A user can create an account. 
	
	public RegisteredUser(int userid,String fullname, String username, String password) {
		super();
		this.userid = userid;
		this.fullname = fullname;
		this.username = username;
		this.password = password;
	}
	
	public  RegisteredUser(int userid,String fullname, String username, String password,boolean superuser) {
		this.userid = userid;
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.superuser = superuser;
	}
	
	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		 this.userid = userid;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isApproveuser() {
		return approveuser;
	}

	public void setApporveuser(boolean apporveuser) {
		this.approveuser = apporveuser;
	}
	
	public boolean isSuperuser() {
		return superuser;
	}
	public void setSuperuser(boolean superuser) {
		this.superuser = superuser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + (superuser ? 1231 : 1237);
		result = prime * result + userid;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		RegisteredUser other = (RegisteredUser) obj;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (superuser != other.superuser)
			return false;
		if (userid != other.userid)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RegisteredUser [userid=" + userid + ", fullname=" + fullname + ", username=" + username + ", password="
				+ password + ", superuser=" + superuser + "]";
	}


	

	


	
	

}
