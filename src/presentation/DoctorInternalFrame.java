package presentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DoctorInternalFrame extends JFrame {
	MainFrame parentFrame;
	private JButton btnLogout;
	
	public DoctorInternalFrame() {
		initialize();
		//this.parentFrame = parent;
	}
	
	private void initialize() {
		this.setSize(1000,600);
		this.setLayout(new GridLayout(3,2));
		this.add(new DoctorTabbedPanel());
		btnLogout = new JButton("Logout");
		this.add(btnLogout);
		btnLogout.addActionListener(new LogoutButtonHandler(this));
	}
	
	private class LogoutButtonHandler implements ActionListener {
		private DoctorInternalFrame outer;
		public LogoutButtonHandler(DoctorInternalFrame outer) {
			this.outer = outer;
		}
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Logout Successful!","Logout",JOptionPane.INFORMATION_MESSAGE);				
			//this.outer.parentFrame.setInternalFrame("Login");
			}
		}
}
