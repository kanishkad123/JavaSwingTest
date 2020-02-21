package data;

import java.util.ArrayList;

public interface TestReader {
	business.Test getTest(String testName);
	ArrayList<business.Test> getTests();
}
