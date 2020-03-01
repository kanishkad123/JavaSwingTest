package presentation;

import java.awt.event.*;

import javax.swing.*;
import java.awt.*;


	@SuppressWarnings("serial")
	public class Welcome extends JFrame{

	// 2 buttons
		private JButton jbtLogin = new JButton("Log In");
		private JButton jbtRegister = new JButton("Register");
		private JLabel lblWelcome = new JLabel("Welcome!");
		private JLabel lblNewUser = new JLabel("Are you new user? Press Register button");
		private JLabel lblOldUser = new JLabel("Do you have an account? Press Log In button");
		private JLabel lblSpace = new JLabel("");
		protected LogIn LogIn = new LogIn();
		protected RegistrationPage RegistrationPage = new RegistrationPage();
		public Welcome() {
	// Background color
			
			
	// Labels
			JPanel jpLabels = new JPanel();
			jpLabels.setLayout(new GridLayout(4,1));
			jpLabels.add(lblSpace);
			
			lblOldUser.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			lblOldUser.setHorizontalAlignment(SwingConstants.CENTER);
			jpLabels.add(lblOldUser);
			
			lblNewUser.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			lblNewUser.setHorizontalAlignment(SwingConstants.CENTER);
			jpLabels.add(lblNewUser);
			
			
	// Panel jpButtons to unite 2 buttons
			JPanel jpButtons = new JPanel();
			jpButtons.setLayout(new FlowLayout());
			jpButtons.add(jbtLogin);
			jpButtons.add(jbtRegister);
			
	//Keyboard mnemonics
			jbtLogin.setMnemonic('L');
			jbtRegister.setMnemonic('R');
			
	//Tips on buttons
			jbtLogin.setToolTipText("If you have an account, press Log In button");
			jbtRegister.setToolTipText("If you are new user, please press Register button");
			
	//Place panels in the Frame
			getContentPane().setLayout(new BorderLayout());
			getContentPane().add(jpLabels, BorderLayout.CENTER);
			getContentPane().add(jpButtons, BorderLayout.SOUTH);
			lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
			lblWelcome.setVerticalAlignment(SwingConstants.TOP);
			lblWelcome.setFont(new Font("Times New Roman", Font.BOLD, 18));
			
			getContentPane().add(lblWelcome, BorderLayout.NORTH);
			

			
			
	//Listeners
			jbtLogin.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					presentation.LogIn.main(null);
					dispose();
					
				}
			});
			jbtRegister.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					presentation.RegistrationPage.main(null);
					dispose();
				}
			});
		}
		public static void main(String[] args) {
			Welcome frame = new Welcome();
			frame.setTitle("Welcome page");
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(350, 250);
			frame.setVisible(true);
			frame.setResizable(false);
		}
	}


