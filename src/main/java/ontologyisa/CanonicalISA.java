package ontologyisa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CanonicalISA {

	static List<List<String>> finlist = new ArrayList<List<String>>();
	static HashMap<String, List<String>> map1 = new HashMap<String, List<String>>();
	static List<String> diseaselist = new ArrayList<String>();
	static List<String> medicinelist = new ArrayList<String>();
	static List<String> minagelist = new ArrayList<String>();
	static List<String> maxagelist = new ArrayList<String>();
	static List<String> studydesignlist = new ArrayList<String>();
	static List<String> countrycodelist = new ArrayList<String>();
	static List<String> countrylist = new ArrayList<String>();
	static List<String> statuslist = new ArrayList<String>();


	static List<String> studytypelist = new ArrayList<String>();
	static List<String> numgroupslist = new ArrayList<String>();
	static List<String> phaselist = new ArrayList<String>();
	static List<String> prevelantlist = new ArrayList<String>();

	private static Connection conn = null;
	private static Statement stmt = null;
	private static String url = "jdbc:mysql://localhost:3306/test";
	private static String user = "yogesh";
	private static String password = "hydesing123";
	private static ArrayList<String> namelist = new ArrayList<String>();
	private static Map<String, List<String>> finmap = new LinkedHashMap<String, List<String>>();
	public static int numOfCols;

	public static void main(String[] args) throws SQLException {
		Map<String, List<String>> map1 = calculateFinlist();
		// obtain a result set
		// ResultSet rs = stmt.executeQuery("SELECT * FROM clinicaltrials");

	}

	public static Map<String, List<String>> calculateFinlist() throws SQLException {
		findCanonicalCountryCode();
		findCanonicalCountry();
		findCanonicalDisease();
		findCanonicalMedicine();
		findCanonicalStatus();
		findCanodicalStudyDesign();
		findCanonicalStudyType();
		findCanonicalPhase();
//		findCanonicalMinAge();
//		findCanonicalMaxAge();
//		findCanonicalNumGroups();
		finlist.add(countrycodelist);
		finlist.add(countrylist);
		finlist.add(diseaselist);
		finlist.add(medicinelist);
		finlist.add(statuslist);
		finlist.add(studydesignlist);
		finlist.add(studytypelist);
		finlist.add(phaselist);
//		finlist.add(minagelist);
//		finlist.add(maxagelist);
//		finlist.add(numgroupslist);

		// Add to map
		finmap.put("countrycode", countrycodelist);
		finmap.put("country", countrylist);
		finmap.put("disease", diseaselist);
		finmap.put("medicine", medicinelist);
		finmap.put("status", statuslist);
		finmap.put("studydesign", studydesignlist);
		finmap.put("studytype", studytypelist);
		finmap.put("phase", phaselist);
//		finmap.put("minage", minagelist);
//		finmap.put("maxage", maxagelist);
//		finmap.put("numgroups", numgroupslist);


		return finmap;

		// return finlist;
	}

	private static void findCanodicalStudyDesign() throws SQLException {
		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT studydesign FROM clinicaltrials_isa_8_100";

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column personname
			String selstudydesigne = res1.getString("studydesign");
			studydesignlist.add(selstudydesigne);
		}

		namelist.clear();
	}

	public static void findCanonicalDisease() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT disease FROM clinicaltrials_isa_8_100";

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column personname
			String seldisease = res1.getString("disease");
			diseaselist.add(seldisease);
		}

		namelist.clear();
	}

	private static void setConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			// if (conn != null)
			// System.out.println("Connected with the database");
			// else
			// System.out.println("Failed to make connection!");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
	}

	private static void findCanonicalMedicine() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT medicine FROM clinicaltrials_isa_8_100";

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column disease
			String selmedicine = res1.getString("medicine");
			medicinelist.add(selmedicine);
		}
		namelist.clear();
	}

	public static void findCanonicalMinAge() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT minage FROM clinicaltrials_isa_8_100";

		ResultSet res1 = stmt.executeQuery(sql1);
		while (res1.next()) {
			// Retrieve by column car
			String selminage = res1.getString("minage");
			minagelist.add(selminage);
		}

		namelist.clear();

	}
	
	public static void findCanonicalMaxAge() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT maxage FROM clinicaltrials_isa_8_100";

		ResultSet res1 = stmt.executeQuery(sql1);
		while (res1.next()) {
			// Retrieve by column pets
			String selmaxage = res1.getString("maxage");
			maxagelist.add(selmaxage);
		}

		namelist.clear();

	}
	
	public static void findCanonicalCountryCode() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT countrycode FROM clinicaltrials_isa_8_100";

		ResultSet res1 = stmt.executeQuery(sql1);
		while (res1.next()) {
			// Retrieve by column pets
			String selcountrycode = res1.getString("countrycode");
			countrycodelist.add(selcountrycode);
		}

		namelist.clear();

	}
	
	public static void findCanonicalStatus() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT status FROM clinicaltrials_isa_8_100";

		ResultSet res1 = stmt.executeQuery(sql1);
		while (res1.next()) {
			// Retrieve by column pets
			String selstatus = res1.getString("status");
			statuslist.add(selstatus);
		}

		namelist.clear();

	}


	public static void findCanonicalCountry() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT country FROM clinicaltrials_isa_8_100";

		ResultSet res1 = stmt.executeQuery(sql1);
		while (res1.next()) {
			// Retrieve by column pets
			String selcountry = res1.getString("country");
			countrylist.add(selcountry);
		}

		namelist.clear();

	}
	
	public static void findCanonicalStudyType() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT studytype FROM clinicaltrials_isa_8_100";

		ResultSet res1 = stmt.executeQuery(sql1);
		while (res1.next()) {
			// Retrieve by column pets
			String selstudytype = res1.getString("studytype");
			studytypelist.add(selstudytype);
		}

		namelist.clear();

	}
	
	public static void findCanonicalNumGroups() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT numgroups FROM clinicaltrials_isa_8_100";

		ResultSet res1 = stmt.executeQuery(sql1);
		while (res1.next()) {
			// Retrieve by column pets
			String selnumgroups = res1.getString("numgroups");
			numgroupslist.add(selnumgroups);
		}

		namelist.clear();

	}
	
	public static void findCanonicalPhase() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT phase FROM clinicaltrials_isa_8_100";

		ResultSet res1 = stmt.executeQuery(sql1);
		while (res1.next()) {
			// Retrieve by column pets
			String selphase = res1.getString("phase");
			phaselist.add(selphase);
		}

		namelist.clear();

	}
	
	public static void findCanonicalPrevelant() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT prevelant FROM clinicaltrials_isa_8_100";

		ResultSet res1 = stmt.executeQuery(sql1);
		while (res1.next()) {
			// Retrieve by column pets
			String selprevelant = res1.getString("prevelant");
			prevelantlist.add(selprevelant);
		}

		namelist.clear();

	}
}
