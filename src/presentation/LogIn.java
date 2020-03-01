package presentation;

import java.awt.event.*;
import java.io.FileInputStream;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class LogIn extends JFrame {

	// Panel for message

	// create buttons and labels
	private JButton jbtLogin = new JButton("Log In");
	private JButton jbtBack = new JButton("Back");
	private JLabel lblWelcome = new JLabel("Welcome back!");
	private JLabel lblUsername = new JLabel("Username: ");
	private JLabel lblPassword = new JLabel("Password: ");
	private JTextField txtUsername = new JTextField();
	private JPasswordField txtPassword = new JPasswordField();
	private JLabel lblSpace = new JLabel(" ");
	private JLabel lblSpace1 = new JLabel(" ");
	private JRadioButton doctor = new JRadioButton("Doctor");
	private JRadioButton admin = new JRadioButton("Admin");
	private JRadioButton patient = new JRadioButton("Patient");

	public LogIn() {
		// Background color

		// Labels
		JPanel jpRegistration = new JPanel();
		jpRegistration.setLayout(new GridLayout(4, 2, 10, 10));

		jpRegistration.add(lblSpace);
		jpRegistration.add(lblSpace1);

		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		jpRegistration.add(lblUsername);

		txtUsername.setBackground(Color.WHITE);
		txtUsername.setToolTipText("Type your username");
		jpRegistration.add(txtUsername);

		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		jpRegistration.add(lblPassword);

		txtPassword.setBackground(Color.WHITE);
		txtPassword.setToolTipText("Your password");
		jpRegistration.add(txtPassword);

		// Panel jpButtons to unite 2 buttons
		JPanel jpButtons = new JPanel();
		jpButtons.setLayout(new FlowLayout());
		
		ButtonGroup group = new ButtonGroup();
	    group.add(doctor);
	    group.add(admin);
	    group.add(patient);
	    jpButtons.add(doctor);
	    jpButtons.add(admin);
	    jpButtons.add(patient);
	    
	    jpButtons.add(jbtLogin);
		jpButtons.add(jbtBack);
		// Keyboard mnemonics
		jbtLogin.setMnemonic('L');
		jbtBack.setMnemonic('B');

		// Tips on buttons
		jbtLogin.setToolTipText("If you have an account, press Log In button");
		jbtBack.setToolTipText("If you want go back to the Welcome page, press Back button");

		// Place panels in the Frame
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(jpRegistration, BorderLayout.CENTER);
		getContentPane().add(jpButtons, BorderLayout.SOUTH);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setVerticalAlignment(SwingConstants.TOP);
		lblWelcome.setFont(new Font("Times New Roman", Font.BOLD, 18));

		getContentPane().add(lblWelcome, BorderLayout.NORTH);

		// Listeners
		jbtLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//Default User
				String myUser = txtUsername.getText();
				String myPassword = new String(txtPassword.getPassword());
				String adminPassword = "admin";
				String adminUser = "admin";
				// Default user is admin. Checking for other users in else block.
				if(isValidData()) {
					if(myUser.equals(adminUser) && (myPassword.equals(adminPassword))) {
					String result = "Welcome back, " + myUser;
					JOptionPane.showMessageDialog(null, result, "LogIn", JOptionPane.INFORMATION_MESSAGE);
					if(admin.isSelected())
						new AdminInternalFrame().setVisible(true);
					else if(doctor.isSelected())
						new DoctorInternalFrame().setVisible(true);
					else
						new PatientInternalFrame().setVisible(true);
					dispose();
					}else {
						//searchUserPassword checks for existing user in the users.txt file
						if(searchUserPassword(myUser,myPassword)) {
							String result = "Welcome back, " + myUser;
							JOptionPane.showMessageDialog(null, result, "LogIn", JOptionPane.INFORMATION_MESSAGE);
							new MainFrame().setVisible(true);
							dispose();
						}
						else {
							txtUsername.setText("");
							txtPassword.setText("");
							JOptionPane.showMessageDialog(null, "Not correct Login or Password" + "\n" + "Please, try again.",
									"Error", JOptionPane.ERROR_MESSAGE);	
						}
					}
				}

			}
			public boolean isValidData() {
				if(!Validator.isPresent(txtUsername, "Username")) return false;
				if(!Validator.isPresent(txtPassword, "Password")) return false;
				return true;
				}

		});
		
		

		jbtBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome.main(null);
				dispose();
			}
		});
	}
	
	private boolean searchUserPassword(String userName,String password) {
		try  
		{
			//read users from users.txt file
			FileInputStream fis=new FileInputStream("users.txt");       
			@SuppressWarnings("resource")
			Scanner sc=new Scanner(fis);
			while(sc.hasNextLine())  
			{  
				//get users
				String[] userRecord = sc.nextLine().split(",");
				//check if username == entered username
				if(userName.equalsIgnoreCase((userRecord[1].split(":"))[1].trim())) {
					//check if password == entered password
					if(password.equalsIgnoreCase((userRecord[2].split(":"))[1].trim())) {
						return true;
					}
				}
			}
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		LogIn frame = new LogIn();
		frame.setTitle("Log In page");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 450);
		frame.setVisible(true);
		frame.setResizable(true);
	}
}
