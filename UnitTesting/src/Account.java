import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;

/** A class that simulates a simple bank account.
 */
public class Account {
	/* Formatting objects used in toString() */
	private static final DateFormat dateFmtr = DateFormat.getDateInstance(DateFormat.MEDIUM);
	private static final NumberFormat percFmtr = NumberFormat.getPercentInstance();
	private static final NumberFormat currFmtr = NumberFormat.getCurrencyInstance();

	/** Holds the identification number that will be
	 * assigned to the next account that is created. */
	private static int nextId = 1;

	/** This account's identification number */
	private int id;

	/** The date that this account was opened */
	private Date dateOpened;

	/** Annual interest rate for this account */
	private double annualRate;

	/** Balance of this account */
	protected double balance;

	/** Initializes the attributes of an account. */
	protected Account(double annualRate) {
		annualRate = Math.abs(annualRate);
		this.id = nextId++;
		this.dateOpened = new Date();
		this.annualRate = annualRate;
		this.balance = 0;
	}

	/** Returns the account ID of this account. */
	public final int getId() {
		return this.id;
	}

	/** Returns the date this account was opened. */
	public final Date getDateOpened() {
		return this.dateOpened;
	}

	/** Returns the annual interest rate of this account. */
	public final double getAnnualRate() {
		return this.annualRate;
	}

	/** Changes the annual interest rate for this account. */
	public final void setAnnualRate(double annualRate) {
		this.annualRate = annualRate;
	}

	/** Returns the current balance of this account. */
	public final double getBalance() {
		return this.balance;
	}

	/** Adds a monthly interest amount rounded to the
	 * penny using banker's rounding to this account. */
	public final void addMonthlyInterest() {
		double interest = balance * (annualRate / 12.0);
		balance += Math.rint(interest * 100.0) / 100.0;
	}

	/** Adds a deposit to this account's balance. */
	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be positive: " + amount);
		}
		this.balance += amount;
	}

	/** Subtracts an amount from this account's balance. */
	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be positive: " + amount);
		}
		if (this.balance < amount) {
			throw new RuntimeException("insufficient funds:  balance=" +
					this.balance + "  " + "amount=" + amount);
		}
		this.balance -= amount;
	}

	/** Returns a String that includes this account's id, type, date opened,
	 * annual interest rate, and balance of this account in that order. */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getId()).append(' ')
				.append(dateFmtr.format(getDateOpened())).append(' ')
				.append(percFmtr.format(getAnnualRate())).append(' ')
				.append(currFmtr.format(getBalance()));
		return sb.toString();
	}
}
