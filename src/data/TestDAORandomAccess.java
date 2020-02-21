package data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import business.Test;

public class TestDAORandomAccess implements TestDAO {

	private File testFile = null;

	public TestDAORandomAccess() {
		testFile = new File(TestConstants.FILENAME_BIN_FIXED);
	}

	@Override
	public Test getTest(String testName) {
		int i = Integer.parseInt(testName);
		RandomAccessFile dataFile;
		int count;
		try {
			dataFile = new RandomAccessFile(testFile, "rw");
			count = (int) dataFile.length() / TestConstants.RECORD_SIZE;
			if (i <= count) {
				dataFile.seek((i - 1) * TestConstants.RECORD_SIZE);
				Test test = new Test(readString(dataFile, TestConstants.TEST_NAME_SIZE), dataFile.readDouble(),
						dataFile.readDouble());
				return test;
			}
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Test> getTests() {
		RandomAccessFile dataFile;
		int count;
		ArrayList<Test> tests = new ArrayList<Test>();
		try {
			dataFile = new RandomAccessFile(testFile, "rw");
			count = (int) dataFile.length() / TestConstants.RECORD_SIZE;
			for (int i = 0; i < count; i++) {
				dataFile.seek(i * TestConstants.RECORD_SIZE);
				Test test = new Test(readString(dataFile, TestConstants.TEST_NAME_SIZE), dataFile.readDouble(),
						dataFile.readDouble());
				tests.add(test);
			}
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return tests;
	}

	@Override
	public boolean addTest(Test test) {
		RandomAccessFile dataFile;
		int count;
		try {
			dataFile = new RandomAccessFile(testFile, "rw");
			count = (int) dataFile.length() / TestConstants.RECORD_SIZE;
			dataFile.seek(count * TestConstants.RECORD_SIZE);
			writeString(dataFile, TestConstants.TEST_NAME_SIZE, test.getTestName());
			dataFile.writeDouble(test.getScoreObtained());
			dataFile.writeDouble(test.getMaxScore());
			return true;
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	private static void writeString(DataOutput out, int length, String s) throws IOException {
		for (int i = 0; i < length; i++) {
			if (i < s.length())
				out.writeChar(s.charAt(i));
			else
				out.writeChar(0);
		}
	}

	private static String readString(DataInput in, int length) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			char c = in.readChar();
			if (c != 0) {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
