package data;

public class DAOFactory {

	public static TestDAO getTestDAO() {
		//TestDAO tDAO = new TestDAOText();
		//TestDAO tDAO = new TestDAOBinary();
		TestDAO tDAO = new TestDAORandomAccess();
		return tDAO;
	}
}
