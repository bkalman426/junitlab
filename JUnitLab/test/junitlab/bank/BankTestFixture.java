package junitlab.bank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import junitlab.bank.impl.FirstNationalBank;

public class BankTestFixture {
	Bank bank;
	String num1;
	String num2;
	
	@Before
	public void setUp() throws AccountNotExistsException {
		bank = new FirstNationalBank();
		
		num1 = bank.openAccount();
		bank.deposit(num1, 1500);
		
		num2 = bank.openAccount();
		bank.deposit(num2, 12000);
	}
	
	@Test
	public void testTransfer() throws AccountNotExistsException, NotEnoughFundsException {
		bank.transfer(num2, num1, 3456);
		
		Assert.assertEquals(4956, bank.getBalance(num1));
		Assert.assertEquals(8544, bank.getBalance(num2));
	}
	
	@Test(expected = NotEnoughFundsException.class)
	public void testTransferWithoutEnoughFunds() throws AccountNotExistsException, NotEnoughFundsException {
		bank.transfer(num1, num2, 3456);
	}
}
