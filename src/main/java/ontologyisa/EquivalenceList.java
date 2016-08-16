package ontologyisa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EquivalenceList {
	private static List<List<List<Integer>>> equilist = new ArrayList<List<List<Integer>>>() ;
	private static List<String> names = new ArrayList<String>();
	private static List<List<Integer>> listOfDisease = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfMedicine = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfMinage = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfMaxage = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfStatus = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfStudydesign = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfCountryCode = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfCountry = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfStudyType = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfNumGroups = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfPhase = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfPrevelant = new ArrayList<List<Integer>>();


	//private static ArrayList<ArrayList<Integer>> listOfMedicine = new ArrayList<ArrayList<Integer>>();
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
			
			// Select personname from database
			
			String sql = "SELECT disease FROM clinicaltrials_isa_8_100";
			//System.out.println();

			ResultSet res = stmt.executeQuery(sql);
			while(res.next()){
		         //Retrieve by column personname
		         String disease = res.getString("disease");
		         names.add(disease);
		    }
			
			for(int i=0; i<names.size(); i++){
				ArrayList<Integer> diseaselist = new ArrayList<Integer>();
				if(temp.contains(i+1))
					continue;
				diseaselist.add((i+1));
				temp.add((i+1));
				for(int j=i+1; j<names.size(); j++){
					if(names.get(i).equals(names.get(j))){
						diseaselist.add((j+1));
						temp.add((j+1));
					}
				}
				listOfDisease.add(diseaselist);
			}
			
			//System.out.println();
		    equilist.add(listOfDisease);
		    names.clear();
		    temp.clear();
					
		    // End of select personname
		    
		    // Start of select profession
//		    
			String sql1 = "SELECT medicine FROM clinicaltrials_isa_8_100";

			ResultSet res1 = stmt.executeQuery(sql1);
			while(res1.next()){
		         //Retrieve by column profession
		         String medicine = res1.getString("medicine");
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
						temp.add(j+1);
					}
				}
				listOfMedicine.add(medicine);
			}

			//System.out.println();
			equilist.add(listOfMedicine);
		    names.clear();
		    temp.clear();

		    
		    // End of select profession
		    
		    // Start of select car
//		    
//			String sql2 = "SELECT minage FROM clinicaltrials_isa_8_100";
//
//			ResultSet res2 = stmt.executeQuery(sql2);
//			while(res2.next()){
//		         //Retrieve by column disease
//		         String minage = res2.getString("minage");
//		         names.add(minage);
//		    }
//			
//			for(int i=0; i<names.size(); i++){
//				ArrayList<Integer> minage = new ArrayList<Integer>();
//				if(temp.contains(i+1))
//					continue;
//				minage.add((i+1));
//				temp.add((i+1));
//				for(int j=i+1; j<names.size(); j++){
//					if(names.get(i).equals(names.get(j))){
//						minage.add((j+1));
//						temp.add(j+1);
//					}
//				}
//				listOfMinage.add(minage);
//			}
//		    
//			//System.out.println();
//			equilist.add(listOfMinage);
//		    names.clear();
//		    temp.clear();
//			
		    // End of select cars
		    
		    // Start of select pets
		    
//			String sql3 = "SELECT maxage FROM clinicaltrials_isa_8_100";
//
//			ResultSet res3 = stmt.executeQuery(sql3);
//			while(res3.next()){
//		         //Retrieve by column pets
//		         String maxage = res3.getString("maxage");
//		         names.add(maxage);
//		    }
//			
//			for(int i=0; i<names.size(); i++){
//				ArrayList<Integer> maxage = new ArrayList<Integer>();
//				if(temp.contains(i+1))
//					continue;
//				maxage.add((i+1));
//				temp.add((i+1));
//				for(int j=i+1; j<names.size(); j++){
//					if(names.get(i).equals(names.get(j))){
//						maxage.add((j+1));
//						temp.add(j+1);
//					}
//				}
//				listOfMaxage.add(maxage);
//			}
//		    
//			//System.out.println();
//			equilist.add(listOfMaxage);
//		    names.clear();
//		    temp.clear();
//			
		    // End of select cars
		    
		 // Start of select studydesign
//		    
		 			String sql4 = "SELECT studydesign FROM clinicaltrials_isa_8_100";

		 			ResultSet res4 = stmt.executeQuery(sql4);
		 			while(res4.next()){
		 		         //Retrieve by column pets
		 		         String studydesign = res4.getString("studydesign");
		 		         names.add(studydesign);
		 		    }
//		 			
		 			for(int i=0; i<names.size(); i++){
		 				ArrayList<Integer> studydesign = new ArrayList<Integer>();
		 				if(temp.contains(i+1))
		 					continue;
		 				studydesign.add((i+1));
		 				temp.add((i+1));
		 				for(int j=i+1; j<names.size(); j++){
		 					if(names.get(i).equals(names.get(j))){
		 						studydesign.add((j+1));
		 						temp.add(j+1);
		 					}
		 				}
		 				listOfStudydesign.add(studydesign);
		 			}
		 		    
		 			//System.out.println();
		 			equilist.add(listOfStudydesign);
		 		    names.clear();
		 		    temp.clear();
//		 			
//		 		    // End of select studydesign
//		 		    
//		 			 // Start of select country
				    
		 			String sql5 = "SELECT countrycode FROM clinicaltrials_isa_8_100";

		 			ResultSet res5 = stmt.executeQuery(sql5);
		 			while(res5.next()){
		 		         //Retrieve by column pets
		 		         String countrycode = res5.getString("countrycode");
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
		 						temp.add(j+1);
		 					}
		 				}
		 				listOfCountryCode.add(countrycode);
		 			}
		 		    
		 			//System.out.println();
		 			equilist.add(listOfCountryCode);
		 		    names.clear();
		 		    temp.clear();
//		 			
//		 		    // End of select country
//		 		    
//		 			 // Start of select countrycode
////////				    
		 			String sql6 = "SELECT country FROM clinicaltrials_isa_8_100";

		 			ResultSet res6 = stmt.executeQuery(sql6);
		 			while(res6.next()){
		 		         //Retrieve by column pets
		 		         String country = res6.getString("country");
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
//		 			
//		 		    // End of select countrycode
//		 		    
		 			 // Start of select status
////////		    
 			String sql11 = "SELECT status FROM clinicaltrials_isa_8_100";

 			ResultSet res11 = stmt.executeQuery(sql11);
 			while(res11.next()){
 		         //Retrieve by column pets
 		         String status = res11.getString("status");
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
 						temp.add(j+1);
 					}
 				}
 				listOfStatus.add(status);
 			}
 		    
 			//System.out.println();
 			equilist.add(listOfStatus);
 		    names.clear();
 		    temp.clear();
// 			
// 		    // End of select countrycode
		 		    
//		 			 // Start of select studytype
				    
		 			String sql7 = "SELECT studytype FROM clinicaltrials_isa_8_100";

		 			ResultSet res7 = stmt.executeQuery(sql7);
		 			while(res7.next()){
		 		         //Retrieve studytype column pets
		 		         String studytype = res7.getString("studytype");
		 		         names.add(studytype);
		 		    }
		 			
		 			for(int i=0; i<names.size(); i++){
		 				ArrayList<Integer> studytype = new ArrayList<Integer>();
		 				if(temp.contains(i+1))
		 					continue;
		 				studytype.add((i+1));
		 				temp.add((i+1));
		 				for(int j=i+1; j<names.size(); j++){
		 					if(names.get(i).equals(names.get(j))){
		 						studytype.add((j+1));
		 						temp.add(j+1);
		 					}
		 				}
		 				listOfStudyType.add(studytype);
		 			}
		 		    
		 			//System.out.println();
		 			equilist.add(listOfStudyType);
		 		    names.clear();
		 		    temp.clear();
//		 			
//		 		    // End of select studytype
//		 		    
//		 		    // Start of select numgroups
//				    
//		 			String sql8 = "SELECT numgroups FROM clinicaltrials_isa_8_100";
//
//		 			ResultSet res8 = stmt.executeQuery(sql8);
//		 			while(res8.next()){
//		 		         //Retrieve studytype column pets
//		 		         String numgroups = res8.getString("numgroups");
//		 		         names.add(numgroups);
//		 		    }
//		 			
//		 			for(int i=0; i<names.size(); i++){
//		 				ArrayList<Integer> numgroups = new ArrayList<Integer>();
//		 				if(temp.contains(i+1))
//		 					continue;
//		 				numgroups.add((i+1));
//		 				temp.add((i+1));
//		 				for(int j=i+1; j<names.size(); j++){
//		 					if(names.get(i).equals(names.get(j))){
//		 						numgroups.add((j+1));
//		 						temp.add(j+1);
//		 					}
//		 				}
//		 				listOfNumGroups.add(numgroups);
//		 			}
//		 		    
//		 			//System.out.println();
//		 			equilist.add(listOfNumGroups);
//		 		    names.clear();
//		 		    temp.clear();
		 			
//		 		    // End of select numgroups
//		 		    
//		 		    // Start of select safetyissue
				    
		 			String sql9 = "SELECT phase FROM clinicaltrials_isa_8_100";

		 			ResultSet res9 = stmt.executeQuery(sql9);
		 			while(res9.next()){
		 		         //Retrieve studytype column pets
		 		         String phase = res9.getString("phase");
		 		         names.add(phase);
		 		    }
		 			
		 			for(int i=0; i<names.size(); i++){
		 				ArrayList<Integer> phase = new ArrayList<Integer>();
		 				if(temp.contains(i+1))
		 					continue;
		 				phase.add((i+1));
		 				temp.add((i+1));
		 				for(int j=i+1; j<names.size(); j++){
		 					if(names.get(i).equals(names.get(j))){
		 						phase.add((j+1));
		 						temp.add(j+1);
		 					}
		 				}
		 				listOfPhase.add(phase);
		 			}
		 		    
		 			//System.out.println();
		 			equilist.add(listOfPhase);
		 		    names.clear();
		 		    temp.clear();
		 			
//		 		    // End of select safetyissue
//		 		    
//		 		    // Start of select prevelant
//				    
//		 			String sql10 = "SELECT prevelant FROM clinicaltrials_isa_8_100";
//
//		 			ResultSet res10 = stmt.executeQuery(sql10);
//		 			while(res10.next()){
//		 		         //Retrieve studytype column pets
//		 		         String prevelant = res10.getString("prevelant");
//		 		         names.add(prevelant);
//		 		    }
//		 			
//		 			for(int i=0; i<names.size(); i++){
//		 				ArrayList<Integer> prevelant = new ArrayList<Integer>();
//		 				if(temp.contains(i+1))
//		 					continue;
//		 				prevelant.add((i+1));
//		 				temp.add((i+1));
//		 				for(int j=i+1; j<names.size(); j++){
//		 					if(names.get(i).equals(names.get(j))){
//		 						prevelant.add((j+1));
//		 						temp.add(j+1);
//		 					}
//		 				}
//		 				listOfPrevelant.add(prevelant);
//		 			}
//		 		    
//		 			//System.out.println();
//		 			equilist.add(listOfPrevelant);
//		 		    names.clear();
//		 		    temp.clear();
//		 			
		 		    // End of select prevelant
		    
			res.close();
			stmt.close();
			conn.close();
			
			map.put("countrycode", listOfCountryCode);
			map.put("country", listOfCountry);
			map.put("disease", listOfDisease);
			map.put("medicine", listOfMedicine);
			map.put("status", listOfStatus);
			map.put("studydesign", listOfStudydesign);
			map.put("studytype", listOfStudyType);
			map.put("phase", listOfPhase);
//			map.put("minage", listOfMinage);
//			map.put("maxage", listOfMaxage);
//			map.put("numgroups", listOfNumGroups);



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
