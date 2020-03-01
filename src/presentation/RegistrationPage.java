package presentation;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.PrintWriter;

@SuppressWarnings("serial")
public class RegistrationPage extends JFrame {

	private JLabel lblFirstName = new JLabel("First Name:");
	private JLabel lblLastName = new JLabel("Last Name:");
	private JLabel lblUsername = new JLabel("Enter Unique Username:");
	private JLabel lblPassword = new JLabel("Enter Password:");
	private JLabel lblConfirmPass = new JLabel("Confirm Password:");
	private JLabel lblEmail = new JLabel("Enter Email:");
	private JLabel lblPhone = new JLabel("Enter Phone Number:");
	private JTextField txtFirstName = new JTextField();
	private JTextField txtLastName = new JTextField();
	private JTextField txtUsername = new JTextField();
	private JPasswordField txtPassword = new JPasswordField();
	private JPasswordField txtConfirmPass = new JPasswordField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtPhone = new JTextField();
	private JButton butRegister = new JButton("Register");
	private JButton butBack = new JButton("Back");

	private void initialize() {

		getContentPane().setLayout(new GridLayout(8, 2, 10, 10));
		getContentPane().add(lblFirstName);
		getContentPane().add(txtFirstName);
		getContentPane().add(lblLastName);
		getContentPane().add(txtLastName);
		getContentPane().add(lblUsername);
		getContentPane().add(txtUsername);
		getContentPane().add(lblPassword);
		getContentPane().add(txtPassword);
		getContentPane().add(lblConfirmPass);
		getContentPane().add(txtConfirmPass);
		getContentPane().add(lblEmail);
		getContentPane().add(txtEmail);
		getContentPane().add(lblPhone);
		getContentPane().add(txtPhone);
		getContentPane().add(butBack);
		getContentPane().add(butRegister);
	}

	public RegistrationPage() {
		this.initialize();
		butRegister.addActionListener(new RegisterButtonHandler());
		butBack.addActionListener(new BackButtonHandler());

		butRegister.setMnemonic('R');
		butBack.setMnemonic('B');
		

		
	}

	private class RegisterButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String firstName = txtFirstName.getText();
			String lastName = txtLastName.getText();
			String userName = txtUsername.getText();
			String password = new String(txtPassword.getPassword());
			String confirm = new String(txtConfirmPass.getPassword());
			String email = txtEmail.getText();
			String phone = txtPhone.getText();

			String result = "Your passwords should be same.";
			// JOptionPane.showMessageDialog(null, result + "Your Account is created", "Save
			// test", JOptionPane.INFORMATION_MESSAGE);
			
			if(isValidData()) {
				if(password.equals(confirm)) {
				// enter valid data in external text file
				saveData(firstName,lastName,userName,password,email,phone);
				JOptionPane.showMessageDialog(null, "Registration completed!" + "\n" + "Your user name: " + userName , "Success", JOptionPane.INFORMATION_MESSAGE);
				LogIn.main(null);
				dispose();
				}else {
					txtPassword.setText("");
					txtConfirmPass.setText("");
					JOptionPane.showMessageDialog(null, "Your passwords should be the same", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
 
		}
	}
	// Save entered registration data into an external file called users.txt
	private void saveData(String fname, String lname,String uname,String password,String email, String phone) {
		try {
			FileWriter fileWriter = new FileWriter("users.txt", true);
			PrintWriter writer = new PrintWriter(fileWriter);
			//Format the string so that it can be read easily for login validation.
			writer.println(String.format("Name : %1$s %2$s, Username : %3$s, Password : %4$s, Email : %5$s, Phone : %6$s", fname,lname,uname,password,email,phone));
			writer.close();
		}
		catch(Exception e) {
			e.printStackTrace();	
		}
	}
	
	public boolean isValidData() {
		if(!Validator.isPresent(txtFirstName, "First name")) return false;
		//if(!Validator.isFirstName(txtFirstName, "First name")) return false;
		if(!Validator.isPresent(txtLastName, "Last name")) return false;
		//if(!Validator.isLastName(txtLastName, "Last name")) return false;
		if(!Validator.isPresent(txtUsername, "Username")) return false;
		//if(!Validator.isUsername(txtUsername, "Username")) return false;
		if(!Validator.isPresent(txtPassword, "Password")) return false;
		if(!Validator.isPresent(txtConfirmPass, "Confirm Password")) return false;
		if(!Validator.isPresent(txtEmail, "Email")) return false;
		if(!Validator.isPresent(txtPhone, "Phone")) return false;
		//if(!Validator.isPhoneNumber(txtPhone, "Phone")) return false;
		return true;
		}

	private class BackButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Welcome.main(null);
			dispose();
		}
	}

	public static void main(String[] args) {
		RegistrationPage frame = new RegistrationPage();
		frame.setTitle("Registration page");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setVisible(true);
		frame.setResizable(true);

	}

}
