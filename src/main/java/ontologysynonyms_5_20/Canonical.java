package ontologysynonyms_5_20;

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

public class Canonical {
	static List<List<String>> finlist = new ArrayList<List<String>>();
	static HashMap<String, List<String>> map1 = new HashMap<String, List<String>>();
	static List<String> countrycodelist = new ArrayList<String>();
	static List<String> countrylist = new ArrayList<String>();
	static List<String> diseaselist = new ArrayList<String>();
	static List<String> medicinelist = new ArrayList<String>();
	static List<String> statuslist = new ArrayList<String>();
	private static Connection conn = null;
	private static Statement stmt = null;
	private static String url = "jdbc:mysql://localhost:3306/test";
	private static String user = "yogesh";
	private static String password = "hydesing123";
	private static ArrayList<String> namelist = new ArrayList<String>();
	private static Map<String, List<String>> finmap = new LinkedHashMap<String,List<String>>();
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
		finlist.add(countrycodelist);
		finlist.add(countrylist);
		finlist.add(diseaselist);
		finlist.add(medicinelist);
		finlist.add(statuslist);

		// Add to map
		finmap.put("countrycode", countrycodelist);
		finmap.put("country", countrylist);
		finmap.put("disease", diseaselist);
		finmap.put("medicine", medicinelist);
		finmap.put("status", statuslist);

		return finmap;

	}

	private static void setConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
	}
	
	private static void findCanonicalStatus() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT status FROM clinicaltrials_5_20";

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column status
			String selstatus = res1.getString("status");
			namelist.add(selstatus);
		}

		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).equals("active"))
				statuslist.add("active");
			else if (namelist.get(i).equals("complete"))
				statuslist.add("complete");
		}

		namelist.clear();
	}


	private static void findCanonicalDisease() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT disease FROM clinicaltrials_4_20";

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column disease
			String seldisease = res1.getString("disease");
			namelist.add(seldisease);
		}

		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).equals("Anemia"))
				diseaselist.add("Anemia");
			else if (namelist.get(i).equals("Common cold"))
				diseaselist.add("Common cold");
			else if (namelist.get(i).equals("Fever"))
				diseaselist.add("Fever");
		}

		namelist.clear();
	}

	public static void findCanonicalCountry() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT country FROM clinicaltrials_4_20";

		ResultSet res1 = stmt.executeQuery(sql1);
		while (res1.next()) {
			// Retrieve by column country
			String selcountry = res1.getString("country");
			namelist.add(selcountry);
		}

		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).equals("Canada"))
				countrylist.add("canada");
			else if (namelist.get(i).equals("Australia"))
				countrylist.add("australia");
			else if (namelist.get(i).equals("India"))
				countrylist.add("india");
			else if (namelist.get(i).equals("United States"))
				countrylist.add("US");
		}

		namelist.clear();

	}

	public static void findCanonicalCountryCode() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT countrycode FROM clinicaltrials_4_20";

		ResultSet res1 = stmt.executeQuery(sql1);
		while (res1.next()) {
			// Retrieve by column countrycode
			String selcountrycode = res1.getString("countrycode");
			namelist.add(selcountrycode);
		}

		List<String> str1 = new ArrayList<String>();
		str1.add("CA");
		str1.add("CAN");
		map1.put("canada", str1);

		List<String> str2 = new ArrayList<String>();
		str2.add("IN");
		str2.add("IND");
		map1.put("india", str2);

		List<String> str3 = new ArrayList<String>();
		str3.add("US");
		str3.add("USA");
		map1.put("United States", str3);

		List<String> str4 = new ArrayList<String>();
		str4.add("AU");
		str4.add("AUS");
		map1.put("Australia", str4);
		
		for (int i = 0; i < namelist.size(); i++) {
			for (Map.Entry<String, List<String>> entry : map1.entrySet()) {
				List<String> saAccused = entry.getValue();
				if (saAccused.contains(namelist.get(i))) {
					countrycodelist.add(entry.getKey());
				}
			}
		}
		namelist.clear();
		map1.clear();
	}

	private static void findCanonicalMedicine() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT medicine FROM clinicaltrials_4_20";

		ResultSet res1 = stmt.executeQuery(sql1);
		while (res1.next()) {
			// Retrieve by column countrycode
			String selmedicine = res1.getString("medicine");
			namelist.add(selmedicine);
		}

		List<String> str1 = new ArrayList<String>();
		str1.add("Darbepoetin alfa");
		str1.add("Epoetin");
		str1.add("Erythropoietin precursor");
		map1.put("alfa", str1);

		List<String> str2 = new ArrayList<String>();
		str2.add("Iron Dextran");
		str2.add("Dexferrum");
		map1.put("Dextran", str2);

		List<String> str3 = new ArrayList<String>();
		str3.add("Benzonatate");
		str3.add("Benz");
		map1.put("Benz", str3);

		List<String> str4 = new ArrayList<String>();
		str4.add("Codeine");
		str4.add("Codifen");
		map1.put("Codeine", str4);

		List<String> str5 = new ArrayList<String>();
		str5.add("Advil");
		str5.add("Ibuprofen");
		map1.put("Advil", str5);

		for (int i = 0; i < namelist.size(); i++) {
			for (Map.Entry<String, List<String>> entry : map1.entrySet()) {
				List<String> saAccused = entry.getValue();
				if (saAccused.contains(namelist.get(i))) {
					medicinelist.add(entry.getKey());
				}
			}
		}
	}
}
