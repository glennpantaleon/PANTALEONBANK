package com.revature.jdbcbankservice;

import java.sql.SQLException;
import java.util.List;

import com.revature.bankexception.UserDoesntExistException;
import com.revature.jdbcbankmodel.RegisteredAccount;
import com.revature.jdbcbankmodel.RegisteredUser;
import com.revature.jdbcdao.RegiUserDaoImpl;


public class JdbcBankService {
	
	private static JdbcBankService jdbcbankservice;
	
	public static JdbcBankService getBankService() {

		if(jdbcbankservice == null) {
			jdbcbankservice = new JdbcBankService();
			return jdbcbankservice;
		}
		return jdbcbankservice;
	}
	
	public static RegisteredUser getUser(String username) {
		return RegiUserDaoImpl.getDao().getUser(username);
	}
	public static RegisteredUser getUserId(int userid) {
		return RegiUserDaoImpl.getDao().getUserId(userid);
	}
	public static RegisteredAccount getAccntId(int bankaccntid) {
		return RegiUserDaoImpl.getDao().getAccntId(bankaccntid);
	}
	
	public static List<RegisteredUser> allUsers(){
		return RegiUserDaoImpl.getDao().allUsers();
	}
	
	public static List<RegisteredAccount> allAccounts(){
		return RegiUserDaoImpl.getDao().allAccnt();
	}
	
	public static boolean deleteRegiUser(RegisteredUser ru) {
		return RegiUserDaoImpl.getDao().deleteAccount(ru);
		
	}
	public boolean insertRegiUser(RegisteredUser ru) {
		return RegiUserDaoImpl.getDao().insertRegiUser(ru);
	}
	public boolean insertRegiAccnt(RegisteredAccount ra) {
		return RegiUserDaoImpl.getDao().insertRegiAccnt(ra);
	}
	public static boolean updateUser(RegisteredUser ru) throws UserDoesntExistException, SQLException {
		return RegiUserDaoImpl.getDao().updateUser(ru);
	}
	
	public static boolean updateAccnt(RegisteredAccount ra) throws SQLException {
		return RegiUserDaoImpl.getDao().updateAccnt(ra);
	}
	
	public static void depositAccntBalance(RegisteredAccount ra, float depoamnt) throws Exception {
		RegiUserDaoImpl.getDao().depositBalance(ra, depoamnt);
	}
	
	public static void withdrawAccntBalance(RegisteredAccount ra, float withdrawamnt) throws Exception{
		RegiUserDaoImpl.getDao().withdrawBalance(ra, withdrawamnt);
	}
	
	public static RegisteredAccount checkBalance(RegisteredAccount ra) throws UserDoesntExistException {
		return RegiUserDaoImpl.getDao().checkBalance(ra);
	}
	
	
	}

	

