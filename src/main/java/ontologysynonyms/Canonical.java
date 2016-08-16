package ontologysynonyms;

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
	static List<String> studydesignlist = new ArrayList<String>();
	static List<String> studytypelist = new ArrayList<String>();
	static List<String> phaselist = new ArrayList<String>();
	static List<String> minagelist = new ArrayList<String>();
	static List<String> maxagelist = new ArrayList<String>();
	static List<String> numgroupslist = new ArrayList<String>();
	static List<String> genderlist = new ArrayList<String>();
	static List<String> sagencylist = new ArrayList<String>();
	static List<String> hvolunteerslist = new ArrayList<String>();
	static List<String> datelist = new ArrayList<String>();
	static List<String> numarmslist = new ArrayList<String>();

	private static Connection conn = null;
	private static Statement stmt = null;
//	private static String url = "jdbc:mysql://localhost:3306/baskas_db";
//	private static String user = "baskas_user";
//	private static String password = "Lakshmi25";
	private static String url = "jdbc:mysql://localhost:3306/test";
	private static String user = "yogesh";
	private static String password = "hydesing123";
	private static ArrayList<String> namelist = new ArrayList<String>();
	private static Map<String, List<String>> finmap = new LinkedHashMap<String, List<String>>();
	public static int numOfCols;
	private static String OP_SIZE = null; 


	public static Map<String, List<String>> calculateFinlist(String size) throws SQLException {
		setOP_SIZE(size);
		findCanonicalCountryCode();
		findCanonicalCountry();
		findCanonicalDisease();
		findCanonicalMedicine();
		findCanonicalStatus();
		findCanonicalStudyDesign();
		findCanonicalStudyType();
		findCanonicalPhase();
		findCanonicalMinAge();
		findCanonicalMaxAge();
		findCanonicalNumGroups();
//		findCanonicalGender();
//		findCanonicalSAgency();
//		findCanonicalHVolunteers();
//		findCanonicalDate();
//		findCanonicalNumArms();

		finlist.add(countrycodelist);
		finlist.add(countrylist);
		finlist.add(diseaselist);
		finlist.add(medicinelist);
		finlist.add(statuslist);
		finlist.add(studydesignlist);
		finlist.add(studytypelist);
		finlist.add(phaselist);
		finlist.add(minagelist);
		finlist.add(maxagelist);
		finlist.add(numgroupslist);
//		finlist.add(genderlist);
//		finlist.add(sagencylist);
//		finlist.add(hvolunteerslist);
//		finlist.add(datelist);
//		finlist.add(numarmslist);


		// Add to map
		finmap.put("countrycode", countrycodelist);
		finmap.put("country", countrylist);
		finmap.put("disease", diseaselist);
		finmap.put("medicine", medicinelist);
		finmap.put("status", statuslist);
		finmap.put("study_design", studydesignlist);
		finmap.put("study_type", studytypelist);
		finmap.put("phase", phaselist);
		finmap.put("minage", minagelist);
		finmap.put("maxage", maxagelist);
		finmap.put("numgroups", numgroupslist);
//		finmap.put("gender", genderlist);
//		finmap.put("sagency", sagencylist);
//		finmap.put("hvolunteers", hvolunteerslist);
//		finmap.put("date", datelist);
//		finmap.put("numarms", numarmslist);

		return finmap;

	}

	private static void findCanonicalNumArms() throws SQLException {
		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT numberofarms FROM clinicaltrials_11_100000 limit  " + OP_SIZE;

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column numberofarms
			String selarms = res1.getString("numberofarms");
			namelist.add(selarms);
		}

		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).equals("randomized"))
				datelist.add("r");
			else if (namelist.get(i).equals("multicenter"))
				datelist.add("m");
			else if (namelist.get(i).equals("openlabel"))
				datelist.add("o");
		}

		namelist.clear();
	}

	private static void findCanonicalDate() throws SQLException {
		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT downloaddate FROM clinicaltrials_11_100000 limit  ";

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column date
			String seldate = res1.getString("downloaddate");
			namelist.add(seldate);
		}

		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).equals("2009"))
				datelist.add("9");
			else if (namelist.get(i).equals("2010"))
				datelist.add("10");
			else if (namelist.get(i).equals("2011"))
				datelist.add("11");
			else if (namelist.get(i).equals("2012"))
				datelist.add("12");
		}

		namelist.clear();
	}

	private static void findCanonicalHVolunteers() throws SQLException {
		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT healthyvolunteers FROM clinicaltrials_11_100000 limit  ";

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column sponsoragency
			String selhvolunteers = res1.getString("healthyvolunteers");
			namelist.add(selhvolunteers);
		}

		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).equals("yes"))
				hvolunteerslist.add("y");
			else if (namelist.get(i).equals("no"))
				hvolunteerslist.add("n");
		}

		namelist.clear();
	}

	private static void findCanonicalSAgency() throws SQLException {
		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT sponsoragency FROM clinicaltrials_11_100000 limit  ";

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column sponsoragency
			String selsagency = res1.getString("sponsoragency");
			namelist.add(selsagency);
		}

		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).equals("NIA"))
				sagencylist.add("N");
			else if (namelist.get(i).equals("GSP"))
				sagencylist.add("G");
			else if (namelist.get(i).equals("OCD"))
				sagencylist.add("O");
			else if (namelist.get(i).equals("ITP"))
				sagencylist.add("I");
		}

		namelist.clear();
	}

	private static void findCanonicalGender() throws SQLException {
		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT gender FROM clinicaltrials_11_100000 limit  ";

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column disease
			String selgender = res1.getString("gender");
			namelist.add(selgender);
		}

		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).equals("male"))
				genderlist.add("m");
			else if (namelist.get(i).equals("female"))
				genderlist.add("f");
			else if (namelist.get(i).equals("both"))
				genderlist.add("b");
		}

		namelist.clear();
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

		String sql1 = "SELECT status FROM clinicaltrials_11_100000 limit  " + OP_SIZE;

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column status
			String selstatus = res1.getString("status");
			namelist.add(selstatus);
		}

		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).equals("active"))
				statuslist.add("a");
			else if (namelist.get(i).equals("complete"))
				statuslist.add("c");
		}

		namelist.clear();
	}

	private static void findCanonicalDisease() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT disease FROM clinicaltrials_11_100000 limit " + OP_SIZE ;

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column disease
			String seldisease = res1.getString("disease");
			namelist.add(seldisease);
		}

		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).equals("Anemia"))
				diseaselist.add("A");
			else if (namelist.get(i).equals("Common cold"))
				diseaselist.add("C");
			else if (namelist.get(i).equals("Fever"))
				diseaselist.add("F");
		}

		namelist.clear();
	}

	public static void findCanonicalCountry() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT country FROM clinicaltrials_11_100000 limit  " + OP_SIZE;
		ResultSet res1 = stmt.executeQuery(sql1);
		while (res1.next()) {
			// Retrieve by column country
			String selcountry = res1.getString("country");
			namelist.add(selcountry);
		}

		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).equals("Canada"))
				countrylist.add("c");
			else if (namelist.get(i).equals("Australia"))
				countrylist.add("a");
			else if (namelist.get(i).equals("India"))
				countrylist.add("i");
			else if (namelist.get(i).equals("United States"))
				countrylist.add("U");
		}

		namelist.clear();

	}

	public static void findCanonicalCountryCode() throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT countrycode FROM clinicaltrials_11_100000 limit " + OP_SIZE;

		ResultSet res1 = stmt.executeQuery(sql1);
		while (res1.next()) {
			// Retrieve by column countrycode
			String selcountrycode = res1.getString("countrycode");
			namelist.add(selcountrycode);
		}

		List<String> str1 = new ArrayList<String>();
		str1.add("CA");
		str1.add("CAN");
		map1.put("c", str1);

		List<String> str2 = new ArrayList<String>();
		str2.add("IN");
		str2.add("IND");
		map1.put("i", str2);

		List<String> str3 = new ArrayList<String>();
		str3.add("US");
		str3.add("USA");
		map1.put("U", str3);

		List<String> str4 = new ArrayList<String>();
		str4.add("AU");
		str4.add("AUS");
		map1.put("A", str4);

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

	private static void findCanonicalMedicine( ) throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT medicine FROM clinicaltrials_11_100000 limit  " + OP_SIZE;

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
		map1.put("a", str1);

		List<String> str2 = new ArrayList<String>();
		str2.add("Iron Dextran");
		str2.add("Dexferrum");
		map1.put("D", str2);

		List<String> str3 = new ArrayList<String>();
		str3.add("Benzonatate");
		str3.add("Benz");
		map1.put("B", str3);

		List<String> str4 = new ArrayList<String>();
		str4.add("Codeine");
		str4.add("Codifen");
		map1.put("C", str4);

		List<String> str5 = new ArrayList<String>();
		str5.add("Advil");
		str5.add("Ibuprofen");
		map1.put("Ad", str5);

		for (int i = 0; i < namelist.size(); i++) {
			for (Map.Entry<String, List<String>> entry : map1.entrySet()) {
				List<String> saAccused = entry.getValue();
				if (saAccused.contains(namelist.get(i))) {
					medicinelist.add(entry.getKey());
				}
			}
		}
	}

	private static void findCanonicalStudyDesign( ) throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT study_design FROM clinicaltrials_11_100000 limit  " + OP_SIZE ;

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column disease
			String selstudydesign = res1.getString("study_design");
			namelist.add(selstudydesign);
		}

		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).equals("interventional"))
				studydesignlist.add("i");
			else if (namelist.get(i).equals("clinical"))
				studydesignlist.add("c");
			else if (namelist.get(i).equals("research"))
				studydesignlist.add("r");
		}

		namelist.clear();
	}

	private static void findCanonicalStudyType( ) throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT study_type FROM clinicaltrials_11_100000 limit " + OP_SIZE;

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column disease
			String selstudytype = res1.getString("study_type");
			namelist.add(selstudytype);
		}

		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).equals("diagnostic"))
				studytypelist.add("d");
			else if (namelist.get(i).equals("basic science"))
				studytypelist.add("b");
			else if (namelist.get(i).equals("science"))
				studytypelist.add("s");
			else if (namelist.get(i).equals("research"))
				studytypelist.add("r");
		}

		namelist.clear();
	}

	private static void findCanonicalPhase( ) throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT phase FROM clinicaltrials_11_100000 limit  " + OP_SIZE;

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column disease
			String selphase = res1.getString("phase");
			namelist.add(selphase);
		}

		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).equals("2"))
				phaselist.add("2");
			else if (namelist.get(i).equals("3"))
				phaselist.add("3");
			else if (namelist.get(i).equals("4"))
				phaselist.add("4");
			else if (namelist.get(i).equals("5"))
				phaselist.add("5");
		}

		namelist.clear();
	}
	private static void findCanonicalMinAge( ) throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT minage FROM clinicaltrials_11_100000 limit " + OP_SIZE;

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column disease
			String selminage = res1.getString("minage");
			namelist.add(selminage);
		}

		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).equals("13"))
				minagelist.add("13");
			if (namelist.get(i).equals("14"))
				minagelist.add("14");
			if (namelist.get(i).equals("15"))
					minagelist.add("15");
			if (namelist.get(i).equals("18"))
				minagelist.add("18");
		}

		namelist.clear();
	}
	
	private static void findCanonicalMaxAge( ) throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT maxage FROM clinicaltrials_11_100000 limit  " + OP_SIZE;

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column disease
			String selmaxage = res1.getString("maxage");
			namelist.add(selmaxage);
		}

		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).equals("45"))
				maxagelist.add("45");
			if (namelist.get(i).equals("46"))
				maxagelist.add("46");
			if (namelist.get(i).equals("47"))
					maxagelist.add("47");
			if (namelist.get(i).equals("48"))
				maxagelist.add("48");
			if (namelist.get(i).equals("49"))
				maxagelist.add("49");
			if (namelist.get(i).equals("50"))
				maxagelist.add("50");
		}

		namelist.clear();
	}
	
	private static void findCanonicalNumGroups( ) throws SQLException {

		setConnection();

		stmt = conn.createStatement();

		String sql1 = "SELECT no_of_groups FROM clinicaltrials_11_100000 limit  " + OP_SIZE;

		ResultSet res1 = stmt.executeQuery(sql1);

		while (res1.next()) {
			// Retrieve by column disease
			String selno_of_groups = res1.getString("no_of_groups");
			namelist.add(selno_of_groups);
		}

		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).equals("2"))
				numgroupslist.add("2");
			if (namelist.get(i).equals("3"))
				numgroupslist.add("3");
			if (namelist.get(i).equals("4"))
					numgroupslist.add("4");
			if (namelist.get(i).equals("5"))
				numgroupslist.add("5");
		}

		namelist.clear();
	}

	public static String getOP_SIZE() {
		return OP_SIZE;
	}

	public static void setOP_SIZE(String oP_SIZE) {
		OP_SIZE = oP_SIZE;
	}


}
