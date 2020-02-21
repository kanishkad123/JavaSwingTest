package data;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import business.Test;

public class TestDAOBinary implements TestDAO {
	private File testFile = null;

	public TestDAOBinary() {
		testFile = new File(TestConstants.FILENAME_BIN);
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

	@Override
	public ArrayList<Test> getTests() {
		DataInputStream in = null;
		ArrayList<Test> tests = new ArrayList<Test>();
		try {
			in = new DataInputStream(new FileInputStream(testFile));
			while (in.available() > 0) {
				String testname = in.readUTF();
				double marksObtained = in.readDouble();
				double maxMarks = in.readDouble();
				Test t = new Test(testname, marksObtained, maxMarks);
				tests.add(t);
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found ------");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return tests;
	}

	@Override
	public boolean addTest(Test test) {
		ArrayList<Test> tests = this.getTests();
		tests.add(test);
		return this.saveTest(tests);
	}

	private void checkFile() throws IOException {
		if (!testFile.exists()) {
			testFile.createNewFile();
		}
	}

	private boolean saveTest(ArrayList<Test> tests) {
		DataOutputStream out = null;
		try {
			this.checkFile();
			out = new DataOutputStream(new FileOutputStream(testFile));
			for (Test t : tests) {
				out.writeUTF(t.getTestName());
				out.writeDouble(t.getScoreObtained());
				out.writeDouble(t.getMaxScore());
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
}
