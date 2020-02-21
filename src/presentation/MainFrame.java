package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JMenuBar mBar;
	private JMenu mFile;
	private JMenu mShow;
	private JMenuItem mItemExit;
	private JMenuItem mItemTest ;
	private JDesktopPane desktop;
	
	public MainFrame() {
		this.initialize();
		this.setTitle("Week 2");
		this.setSize(700, 550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initialize() {

		//instantiations
		mBar = new JMenuBar();
		desktop = new JDesktopPane();
		mFile = new JMenu("File");
		mShow = new JMenu("Show");
		mItemExit = new JMenuItem("Exit");
		mItemTest = new JMenuItem("Test");
		
		//linkings to parent
		this.setContentPane(desktop);
		mFile.add(mItemExit);
		mShow.add(mItemTest);
		mBar.add(mFile);
		mBar.add(mShow);
		this.setJMenuBar(mBar);
		
		//Mnemonics
		mFile.setMnemonic(KeyEvent.VK_F);
		mItemExit.setMnemonic(KeyEvent.VK_X);
		mShow.setMnemonic(KeyEvent.VK_S);
		mItemTest.setMnemonic(KeyEvent.VK_T);
		
		//Accelerators
		mItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		mItemTest.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,ActionEvent.CTRL_MASK));
		
		//Action Listeners
		mItemExit.addActionListener(new ExitEventHandler());
		mItemTest.addActionListener(new TestEventHandler());
	}
	
	private class ExitEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	private class TestEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			PersonInternalFrame pInternalFrame = new PersonInternalFrame(new TestPanel());
			pInternalFrame.setVisible(true);
			MainFrame.this.desktop.add(pInternalFrame);
		}
	}
	
	
}
