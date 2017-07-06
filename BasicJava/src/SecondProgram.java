import javax.swing.JOptionPane;

public class SecondProgram {
	public static void main(String[] args) {
		// Open a popup window that asks a user for her name.
		String name = JOptionPane.showInputDialog("Please enter your name:");

		// Use string concatenation to make a message for the user.
		String greeting = "Hello " + name;

		// Open a popup window that says "Hello _____"
		JOptionPane.showMessageDialog(null, greeting);
	}
}
