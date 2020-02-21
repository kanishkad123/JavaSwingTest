package presentation;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PersonInternalFrame extends JInternalFrame {
	public PersonInternalFrame(JPanel panel) {
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Internal GUI");
		this.setIconifiable(true);
		this.setClosable(true);
		this.setSize(600, 450);
	}
}
