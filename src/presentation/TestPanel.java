package presentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import business.Test;
import data.DAOFactory;
import data.TestDAO;

@SuppressWarnings("serial")
public class TestPanel extends JPanel {

	private JLabel lblTestName, lblScoreObtained, lblMaxScore;
	private JTextField txtTestName, txtScoreObtained, txtMaxScore;
	private JButton btnSave, btnFind;
	private JTextArea txtAreaList;
	private TestDAO tDao = DAOFactory.getTestDAO();

	// Initialize GUI for the application

	private void initialize() {
		lblTestName = new JLabel();
		lblTestName.setText("Test Name");

		lblScoreObtained = new JLabel("Score Obtained");
		lblMaxScore = new JLabel("Max. Score");

		txtTestName = new JTextField();
		txtScoreObtained = new JTextField();
		txtMaxScore = new JTextField();

		txtAreaList = new JTextArea();

		btnSave = new JButton("Save");
		btnFind = new JButton("Find");

		this.setLayout(new GridLayout(5, 2));
		this.add(lblTestName);
		this.add(txtTestName);
		this.add(lblMaxScore);
		this.add(txtMaxScore);
		this.add(lblScoreObtained);
		this.add(txtScoreObtained);
		this.add(btnSave);
		this.add(btnFind);
		this.add(txtAreaList);
	}

	public TestPanel() {
		initialize();
		btnSave.addActionListener(new SaveButtonHandler());
		btnFind.addActionListener(new FindButtonHandler());
	}

	private class SaveButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!isValidData())
				return;
			String testName = txtTestName.getText();
			double scoreObtained = Double.parseDouble(txtScoreObtained.getText());
			double maxScore = Double.parseDouble(txtMaxScore.getText());
			if (tDao.addTest(new Test(testName, scoreObtained, maxScore))) {
				String result = "Test: " + testName + "\n Score: " + scoreObtained + "\n Max Score: " + maxScore;
				JOptionPane.showMessageDialog(null, result, "Save Test", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Not Saved!", "Save Test", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private class FindButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String testName = txtTestName.getText();
			if (testName.isBlank()) {
				String txt = "";
				for (Test test : tDao.getTests()) {
					txt += "\n" + test.toString();
				}
				txtAreaList.setText(txt);
			} else {
				Test test = tDao.getTest(testName);
				if (test == null) {
					JOptionPane.showMessageDialog(null, "Test Not Found", "Find Test", JOptionPane.ERROR_MESSAGE);
				} else {
					txtTestName.setText(test.getTestName());
					txtScoreObtained.setText("" + test.getScoreObtained());
					txtMaxScore.setText("" + test.getMaxScore());
				}
			}
		}
	}

	private boolean isValidData() {
		if (!Validator.isPresent(txtTestName, " Test Name "))
			return false;
		if (!Validator.isInteger(txtScoreObtained, " Score Obtained "))
			return false;
		if (!Validator.isInteger(txtMaxScore, " Max Score "))
			return false;
		return true;
	}
}
