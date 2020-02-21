package business;

public class Test {

	private String testName;
	private double scoreObtained,maxScore;

	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public double getScoreObtained() {
		return scoreObtained;
	}
	public void setScoreObtained(double scoreObtained) {
		this.scoreObtained = scoreObtained;
	}
	public double getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}
	
	public Test(String testName, double scoreObtained, double maxScore) {
		this.testName = testName;
		this.scoreObtained = scoreObtained;
		this.maxScore = maxScore;
	}
	
	public String toString() {
		return "Test : " + this.testName + " Score : " + this.scoreObtained + " / " + this.maxScore ;
	}
}
