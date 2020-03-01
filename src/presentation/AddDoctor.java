package presentation;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.Dimension;
import java.awt.GridLayout;

public class AddDoctor extends JPanel {
	private boolean DEBUG = false;

	public AddDoctor() {
		this.setLayout(new GridLayout(2,1));
		Icon icon = new ImageIcon("../Assets/medical.jpeg");

		JButton button1 = new JButton("Button");
		JButton button2 = new JButton("Add");
		JButton button3 = new JButton("Cancel");

		Box box = Box.createVerticalBox();

		JTable table = new JTable(new MyTableModel());

		table.setPreferredScrollableViewportSize(new Dimension(300, 70));

		// table.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(table);

		add(scrollPane);

		box.add(button1); box.add(button2); box.add(button3);
		  
		add(box);
		
		 
	}

	class MyTableModel extends AbstractTableModel {
		private String[] columnNames = { "Doctor", "Speciality" };
		private Object[][] data = { { "Kathy Smith", "Dermatologist", }, { "John Doe", "Cardiologists", }

		};

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		public void setValueAt(Object value, int row, int col) {
			if (DEBUG) {
				System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of "
						+ value.getClass() + ")");
			}

			data[row][col] = value;
			fireTableCellUpdated(row, col);

			if (DEBUG) {
				System.out.println("New value of data:");
				printDebugData();
			}
		}

		private void printDebugData() {
			int numRows = getRowCount();
			int numCols = getColumnCount();

			for (int i = 0; i < numRows; i++) {
				System.out.print("    row " + i + ":");
				for (int j = 0; j < numCols; j++) {
					System.out.print("  " + data[i][j]);
				}
				System.out.println();
			}
			System.out.println("--------------------------");
		}
	}

}