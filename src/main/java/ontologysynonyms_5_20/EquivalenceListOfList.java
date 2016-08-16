package ontologysynonyms_5_20;

import java.sql.*;
import java.util.*;


public class EquivalenceListOfList {
	private static List<List<List<Integer>>> equilist = new ArrayList<List<List<Integer>>>() ;
	private static List<String> names = new ArrayList<String>();
	private static List<List<Integer>> listOfCountryCode = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfCountry = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfDisease = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfMedicine = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfStatus = new ArrayList<List<Integer>>();
	static ArrayList<Integer> temp = new ArrayList<Integer>();
	private static Connection conn = null;
	private static Statement stmt = null;
	private static String url = "jdbc:mysql://localhost:3306/test";
	private static String user = "sridevi";
	private static String password = "LakshmI25";
	
	private static void setConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
//			if (conn != null)
//				System.out.println("Connected with the database");
//			else
//				System.out.println("Failed to make connection!");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
	}

	private static Map<String,List<List<Integer>>> findEquivalence() throws SQLException {
		
			Map<String,List<List<Integer>>> map =new LinkedHashMap<String,List<List<Integer>>>();
			
			stmt = conn.createStatement();
			
			// Select countrycode from database
			
			String sql = "SELECT countrycode FROM clinicaltrials_5_20";
			//System.out.println();

			ResultSet res = stmt.executeQuery(sql);
			while(res.next()){
		         //Retrieve by column countrycode
		         String countrycode = res.getString("countrycode");
		         names.add(countrycode);
		    }
			
			for(int i=0; i<names.size(); i++){
				ArrayList<Integer> countrycode = new ArrayList<Integer>();
				if(temp.contains(i+1))
					continue;
				countrycode.add((i+1));
				temp.add((i+1));
				for(int j=i+1; j<names.size(); j++){
					if(names.get(i).equals(names.get(j))){
						countrycode.add((j+1));
						temp.add((j+1));
					}
				}
				listOfCountryCode.add(countrycode);
			}
			
			//System.out.println();
		    equilist.add(listOfCountryCode);
		    names.clear();
		    temp.clear();
					
		    // End of select countrycode
		    
		    // Start of select country
//		    
			String sql1 = "SELECT country FROM clinicaltrials_5_20";

			ResultSet res1 = stmt.executeQuery(sql1);
			while(res1.next()){
		         //Retrieve by column countrycode
		         String country = res1.getString("country");
		         names.add(country);
		    }
			
			for(int i=0; i<names.size(); i++){
				ArrayList<Integer> country = new ArrayList<Integer>();
				if(temp.contains(i+1))
					continue;
				country.add((i+1));
				temp.add((i+1));
				for(int j=i+1; j<names.size(); j++){
					if(names.get(i).equals(names.get(j))){
						country.add((j+1));
						temp.add(j+1);
					}
				}
				listOfCountry.add(country);
			}

			//System.out.println();
			equilist.add(listOfCountry);
		    names.clear();
		    temp.clear();

		    
		    // End of select country
		    
		    // Start of select disease
		    
			String sql2 = "SELECT disease FROM clinicaltrials_5_20";

			ResultSet res2 = stmt.executeQuery(sql2);
			while(res2.next()){
		         //Retrieve by column disease
		         String disease = res2.getString("disease");
		         names.add(disease);
		    }
			
			for(int i=0; i<names.size(); i++){
				ArrayList<Integer> disease = new ArrayList<Integer>();
				if(temp.contains(i+1))
					continue;
				disease.add((i+1));
				temp.add((i+1));
				for(int j=i+1; j<names.size(); j++){
					if(names.get(i).equals(names.get(j))){
						disease.add((j+1));
						temp.add(j+1);
					}
				}
				listOfDisease.add(disease);
			}
		    
			//System.out.println();
			equilist.add(listOfDisease);
		    names.clear();
		    temp.clear();
			
		    // End of select disease
		    

			// Select medicine from database
			
			String sql3 = "SELECT medicine FROM clinicaltrials_5_20";
			//System.out.println();

			ResultSet res3 = stmt.executeQuery(sql3);
			while(res3.next()){
		         //Retrieve by column countrycode
		         String medicine = res3.getString("medicine");
		         names.add(medicine);
		    }
			
			for(int i=0; i<names.size(); i++){
				ArrayList<Integer> medicine = new ArrayList<Integer>();
				if(temp.contains(i+1))
					continue;
				medicine.add((i+1));
				temp.add((i+1));
				for(int j=i+1; j<names.size(); j++){
					if(names.get(i).equals(names.get(j))){
						medicine.add((j+1));
						temp.add((j+1));
					}
				}
				listOfMedicine.add(medicine);
			}
			
			//System.out.println();
		    equilist.add(listOfMedicine);
		    names.clear();
		    temp.clear();
					
		    // End of select countrycode
		    
//		 // Select status from database
			
		 			String sql4 = "SELECT status FROM clinicaltrials_5_20";
//		 			//System.out.println();
//
		 			ResultSet res4 = stmt.executeQuery(sql4);
		 			while(res4.next()){
		 		         //Retrieve by column status
		 		         String status = res4.getString("status");
		 		         names.add(status);
		 		    }
		 			
		 			for(int i=0; i<names.size(); i++){
		 				ArrayList<Integer> status = new ArrayList<Integer>();
		 				if(temp.contains(i+1))
		 					continue;
		 				status.add((i+1));
		 				temp.add((i+1));
		 				for(int j=i+1; j<names.size(); j++){
		 					if(names.get(i).equals(names.get(j))){
		 						status.add((j+1));
		 						temp.add((j+1));
		 					}
		 				}
		 				listOfStatus.add(status);
		 			}
		 			
		 			//System.out.println();
		 		    equilist.add(listOfStatus);
		 		    names.clear();
		 		    temp.clear();
//		 					
//		    // End of select status
		    
			res.close();
			stmt.close();
			conn.close();
			
			map.put("countrycode", listOfCountryCode);
			map.put("country", listOfCountry);
			map.put("disease", listOfDisease);
			map.put("medicine", listOfMedicine);
			map.put("status", listOfStatus);

			return map;
			
			//return equilist;

	}

	public static Map<String,List<List<Integer>>> getEquiList() throws SQLException {
		setConnection();
		return findEquivalence();
	}
	
	public static void main(String[] args) throws SQLException {
		
		Map<String,List<List<Integer>>> equilist = getEquiList();
		
	}

}
