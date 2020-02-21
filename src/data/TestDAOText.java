package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import business.Test;

public class TestDAOText implements TestDAO {
	private File testFile = null;

	public TestDAOText() {
		testFile = new File(TestConstants.FILENAME_TEXT);
	}

	private void checkFile() throws IOException {
		if (!testFile.exists()) {
			testFile.createNewFile();
		}
	}

	private boolean saveTest(ArrayList<Test> tests) {
		PrintWriter out = null;
		try {
			this.checkFile();
			out = new PrintWriter(new BufferedWriter(new FileWriter(testFile)));
			for (Test t : tests) {
				out.print(t.getTestName() + FIELD_SEP);
				out.print(t.getScoreObtained() + FIELD_SEP);
				out.println(t.getMaxScore());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			this.close(out);
		}
		return true;
	}

	private void close(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Test getTest(String testName) {
		ArrayList<Test> tests = this.getTests();
		for (Test ts : tests) {
			if (ts.getTestName().equalsIgnoreCase(testName)) {
				return ts;
			}
		}
		return null;
	}

	public Test getTest() {
		BufferedReader in = null;
		Test t = null;
		try {
			this.checkFile();
			in = new BufferedReader(new FileReader(testFile));
			ArrayList<Test> tests = new ArrayList<Test>();
			String line = in.readLine();
			while (line != null) {
				String[] columns = line.split(FIELD_SEP);
				String testname = columns[0];
				double marksObtained = Double.parseDouble(columns[1]);
				double maxMarks = Double.parseDouble(columns[2]);
				t = new Test(testname,marksObtained,maxMarks );
				tests.add(t);
				line = in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public ArrayList<Test> getTests() {
		BufferedReader in = null;
		ArrayList<Test> tests2 = new ArrayList<Test>();
		try {
			in = new BufferedReader(new FileReader(testFile));
			String line = in.readLine();
			while (line != null) {
				String[] columns = line.split(FIELD_SEP);
				String name = columns[0];
				double marksObtained = Double.parseDouble(columns[1]);
				double maxMarks = Double.parseDouble(columns[2]);
				Test t = new Test(name,marksObtained,maxMarks);
				tests2.add(t);
				line = in.readLine();
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return tests2;
	}

	@Override
	public boolean addTest(Test test) {
		ArrayList<Test> tests = this.getTests();
		tests.add(test);
		return this.saveTest(tests);
	}
}
