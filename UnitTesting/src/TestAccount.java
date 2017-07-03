import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/** TestAccount is a class that uses JUnit to test the Account class. */
public class TestAccount {
	// A JUnit test is simply a function that is annotated with @Test
	// and that takes no parameters. This class contains four JUnit
	// tests.  Within a test function, a developer uses the assert*
	// functions to verify that his code is working correctly. Notice
	// that in the testDeposit and testWithdraw functions, the
	// assertEquals function is used to verify that the balance of an
	// account is correct after .deposit and .withdraw are executed by
	// the computer.


	/** Verifies that account identification numbers are assigned
	 * starting with 1, sequentially in the order that the accounts
	 * were created. */
	@Test
	public void testAccountIds() {
		int id = 1;
		for (int i = 0;  i < 3;  ++i) {
			Account a = new Account(0.015);
			assertEquals(id, a.getId());
			id++;
		}
	}


	/** Verifies that the date opened attribute is correctly
	 * assigned the date when an account object was created. */
	@Test
	public void testDateOpened() {
		double delta = 15;
		Account a = new Account(0.015);
		long now = new Date().getTime();
		assertEquals(now, a.getDateOpened().getTime(), delta);
	}


	/** Verifies that deposit() works correctly. */
	@Test
	public void testDeposit() {
		double delta = 0.001;
		Account a = new Account(0.015);

		// Balance of a new account should be $0
		double bal = 0;
		assertEquals(bal, a.getBalance(), delta);

		// Attempt to deposit a negative amount.
		boolean thrown = false;
		try {
			a.deposit(-10);
		}
		catch (Exception ex) {
			thrown = true;
		}
		assertTrue(thrown);
		assertEquals(bal, a.getBalance(), delta);

		// Deposit $50.01
		double amt = 50.01;
		bal = amt;
		a.deposit(amt);
		assertEquals(bal, a.getBalance(), delta);

		// Attempt to deposit a negative amount, again.
		try {
			thrown = false;
			a.deposit(-10);
		}
		catch (Exception ex) {
			thrown = true;
		}
		assertTrue(thrown);
		assertEquals(bal, a.getBalance(), delta);

		// Deposit $30.31
		amt = 30.31;
		bal += amt;
		a.deposit(amt);
		assertEquals(bal, a.getBalance(), delta);
	}


	/** Verifies that withdraw() works correctly. */
	@Test
	public void testWithdraw() {
		double delta = 0.001;
		Account a = new Account(0.015);
		double bal = 0;

		// Attempt to withdraw a negative amount.
		boolean thrown = false;
		try {
			a.withdraw(-10);
		}
		catch (Exception ex) {
			thrown = true;
		}
		assertTrue(thrown);
		assertEquals(bal, a.getBalance(), delta);

		double amt = 40.25;
		bal = amt;
		a.deposit(amt);
		assertEquals(bal, a.getBalance(), delta);

		// Attempt to withdraw a negative amount, again.
		try {
			thrown = false;
			a.withdraw(-10);
		}
		catch (Exception ex) {
			thrown = true;
		}
		assertTrue(thrown);
		assertEquals(bal, a.getBalance(), delta);

		// Withdraw $10.15
		amt = 10.15;
		bal -= amt;
		a.withdraw(amt);
		assertEquals(bal, a.getBalance(), delta);

		// Attempt to withdraw $40.00
		try {
			amt = 40;
			thrown = false;
			a.withdraw(amt);
		}
		catch (Exception ex) {
			thrown = true;
		}
		assertTrue(thrown);
		assertEquals(bal, a.getBalance(), delta);

		// Withdraw the remaining balance.
		amt = bal;
		bal = 0;
		a.withdraw(amt);
		assertEquals(bal, a.getBalance(), delta);

		// Attempt to withdraw another $30.00,
		// even though the balance is zero.
		try {
			amt = 30;
			thrown = false;
			a.withdraw(amt);
		}
		catch (Exception ex) {
			thrown = true;
		}
		assertTrue(thrown);
		assertEquals(bal, a.getBalance(), delta);
	}
}
