package presentation;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
	 
	public class AdmintabbedPanel extends JPanel{

	    public AdmintabbedPanel() {
	        this.setLayout(new GridLayout(1, 1));
	        Font  f2  = new Font(Font.DIALOG,  Font.BOLD, 12);

	         
	        JTabbedPane tabbedPane = new JTabbedPane();
	        ImageIcon icon = createImageIcon("../Assets/medical.jpeg");
	        
	        tabbedPane.addTab("Add Doctors", icon, new AddDoctor(),
	                "Does nothing");
	        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
	         
	        JComponent panel2 = makeTextPanel("Panel #2");
	        tabbedPane.addTab("Add Patient", icon, new AddPatient(),
	                "Does twice as much nothing");
	        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
	         
	        JComponent panel3 = makeTextPanel("Panel #3");
	        tabbedPane.addTab("Add Facilities", icon, new AddFacilities(),
	                "Still does nothing");
	        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
	         
	        JComponent panel4 = makeTextPanel(
	                "Panel #4 (has a preferred size of 410 x 50).");
	        panel4.setPreferredSize(new Dimension(410, 50));
	        tabbedPane.addTab("Schedule", icon, new DatePicker("Calender"),
	                "Does nothing at all");
	        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
	         
	       
	        add(tabbedPane);
	        tabbedPane.setFont(f2);
	        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	    }
	     
	    protected JComponent makeTextPanel(String text) {
	        JPanel panel = new JPanel(false);
	        JLabel filler = new JLabel(text);
	        filler.setHorizontalAlignment(JLabel.CENTER);
	        panel.setLayout(new GridLayout(1, 1));
	        panel.add(filler);
	        return panel;
	    }
	     
	    /** Returns an ImageIcon, or null if the path was invalid. */
	    protected static ImageIcon createImageIcon(String path) {
	        java.net.URL imgURL = AdmintabbedPanel.class.getResource(path);
	        if (imgURL != null) {
	            return new ImageIcon(imgURL);
	        } else {
	            System.err.println("Couldn't find file: " + path);
	            return null;
	        }
	    }
}