import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/** A class to test the Account class
 */
public class TestAccount {

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
		try {
			a.deposit(-10);
		}
		catch (Exception ex) { }
		assertEquals(bal, a.getBalance(), delta);

		// Deposit $50.01
		double amt = 50.01;
		bal = amt;
		a.deposit(amt);
		assertEquals(bal, a.getBalance(), delta);

		// Attempt to deposit a negative amount, again.
		try {
			a.deposit(-10);
		}
		catch (Exception ex) { }
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
		try {
			a.withdraw(-10);
		}
		catch (Exception ex) { }
		assertEquals(bal, a.getBalance(), delta);

		double amt = 40.25;
		bal = amt;
		a.deposit(amt);
		assertEquals(bal, a.getBalance(), delta);

		// Attempt to withdraw a negative amount, again.
		try {
			a.withdraw(-10);
		}
		catch (Exception ex) { }
		assertEquals(bal, a.getBalance(), delta);

		// Withdraw $10.15
		amt = 10.15;
		bal -= amt;
		a.withdraw(amt);
		assertEquals(bal, a.getBalance(), delta);

		// Attempt to withdraw $40.00
		amt = 40;
		try {
			a.withdraw(amt);
		}
		catch (Exception ex) { }
		assertEquals(bal, a.getBalance(), delta);

		// Withdraw the remaining balance.
		amt = bal;
		bal = 0;
		a.withdraw(amt);
		assertEquals(bal, a.getBalance(), delta);

		// Attempt to withdraw another $30.00,
		// even though the balance is zero.
		amt = 30;
		try {
			a.withdraw(amt);
		}
		catch (Exception ex) { }
		assertEquals(bal, a.getBalance(), delta);
	}
}
