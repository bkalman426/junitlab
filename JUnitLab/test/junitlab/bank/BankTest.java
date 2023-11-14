package junitlab.bank;

import org.junit.Assert;
import org.junit.Test;
import junitlab.bank.impl.FirstNationalBank;

public class BankTest {

	@Test
	public void testOpenAccount() throws AccountNotExistsException {
		Bank bank = new FirstNationalBank();
		String num = bank.openAccount();
		long result = bank.getBalance(num);
		
		Assert.assertEquals(0, result);
	}
	
	@Test
	public void testUniqueAccount() throws AccountNotExistsException {
		Bank bank1 = new FirstNationalBank();
		Bank bank2 = new FirstNationalBank();
		
		Assert.assertEquals(false, bank1.openAccount() == bank2.openAccount());
	}
	
	@Test(expected = AccountNotExistsException.class)
	public void testInvalidAccount() throws AccountNotExistsException, NotEnoughFundsException {
		Bank bank = new FirstNationalBank();
		String result = bank.openAccount();
		bank.getBalance(result + "foo");
	}
	
	@Test
	public void testDeposit() throws AccountNotExistsException {
		Bank bank = new FirstNationalBank();
		String num = bank.openAccount();
		bank.deposit(num, 2000);
		Assert.assertEquals(2000, bank.getBalance(num));
	}
}
