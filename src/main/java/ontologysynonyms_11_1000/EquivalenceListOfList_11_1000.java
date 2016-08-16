package ontologysynonyms_11_1000;


import java.sql.*;
import java.util.*;


public class EquivalenceListOfList_11_1000 {

	private static List<List<List<Integer>>> equilist = new ArrayList<List<List<Integer>>>() ;
	private static List<String> names = new ArrayList<String>();
	private static List<List<Integer>> listOfCountryCode = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfCountry = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfDisease = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfMedicine = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfStatus = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfStudyDesign = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfStudyType = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfPhase = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfMinAge = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfMaxAge = new ArrayList<List<Integer>>();
	private static List<List<Integer>> listOfNoGroups = new ArrayList<List<Integer>>();

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
			
			String sql = "SELECT countrycode FROM clinicaltrials_11_1000    ";
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
			String sql1 = "SELECT country FROM clinicaltrials_11_1000    ";

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
		    
			String sql2 = "SELECT disease FROM clinicaltrials_11_1000    ";

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
			
			String sql3 = "SELECT medicine FROM clinicaltrials_11_1000    ";
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
					
		    // End of select medicine
		    
//		 // Select status from database
			
		 			String sql4 = "SELECT status FROM clinicaltrials_11_1000    ";
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
//		 		    
////
////					// Select study design from database
////					
					String sql5 = "SELECT study_design FROM clinicaltrials_11_1000    ";
					//System.out.println();

					ResultSet res5 = stmt.executeQuery(sql5);
					while(res5.next()){
				         //Retrieve by column countrycode
				         String study_design = res5.getString("study_design");
				         names.add(study_design);
				    }
					
					for(int i=0; i<names.size(); i++){
						ArrayList<Integer> study_design = new ArrayList<Integer>();
						if(temp.contains(i+1))
							continue;
						study_design.add((i+1));
						temp.add((i+1));
						for(int j=i+1; j<names.size(); j++){
							if(names.get(i).equals(names.get(j))){
								study_design.add((j+1));
								temp.add((j+1));
							}
						}
						listOfStudyDesign.add(study_design);
					}
					
					//System.out.println();
				    equilist.add(listOfStudyDesign);
				    names.clear();
				    temp.clear();
//////							
//////				    // End of select study design
////////				    
////////
////////					// Select studytype from database
//					
					String sql6 = "SELECT study_type FROM clinicaltrials_11_1000    ";
					//System.out.println();

					ResultSet res6 = stmt.executeQuery(sql6);
					while(res6.next()){
				         //Retrieve by column countrycode
				         String study_type = res6.getString("study_type");
				         names.add(study_type);
				    }
					
					for(int i=0; i<names.size(); i++){
						ArrayList<Integer> study_type = new ArrayList<Integer>();
						if(temp.contains(i+1))
							continue;
						study_type.add((i+1));
						temp.add((i+1));
						for(int j=i+1; j<names.size(); j++){
							if(names.get(i).equals(names.get(j))){
								study_type.add((j+1));
								temp.add((j+1));
							}
						}
						listOfStudyType.add(study_type);
					}
					
					//System.out.println();
				    equilist.add(listOfStudyType);
				    names.clear();
				    temp.clear();
//////							
////////				    // End of select studytype
////////				    
////////
////////					// Select safety_issue from database
//					
					String sql7 = "SELECT phase FROM clinicaltrials_11_1000    ";
					//System.out.println();

					ResultSet res7 = stmt.executeQuery(sql7);
					while(res7.next()){
				         //Retrieve by column countrycode
				         String safety_issue = res7.getString("phase");
				         names.add(safety_issue);
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
								temp.add((j+1));
							}
						}
						listOfPhase.add(phase);
					}
					
					//System.out.println();
				    equilist.add(listOfPhase);
				    names.clear();
				    temp.clear();
////							
////				    // End of select safetyissue
////				    
////				    // Select minage from database
//					
					String sql8 = "SELECT minage FROM clinicaltrials_11_1000    ";
					//System.out.println();

					ResultSet res8 = stmt.executeQuery(sql8);
					while(res8.next()){
				         //Retrieve by column countrycode
				         String minage = res8.getString("minage");
				         names.add(minage);
				    }
					
					for(int i=0; i<names.size(); i++){
						ArrayList<Integer> minage = new ArrayList<Integer>();
						if(temp.contains(i+1))
							continue;
						minage.add((i+1));
						temp.add((i+1));
						for(int j=i+1; j<names.size(); j++){
							if(names.get(i).equals(names.get(j))){
								minage.add((j+1));
								temp.add((j+1));
							}
						}
						listOfMinAge.add(minage);
					}
					
					//System.out.println();
				    equilist.add(listOfMinAge);
				    names.clear();
				    temp.clear();
//////							
////				    // End of select minage
////				    
////				    // Select maxage from database
////					
						String sql9 = "SELECT maxage FROM clinicaltrials_11_1000    ";
						//System.out.println();

						ResultSet res9 = stmt.executeQuery(sql9);
						while(res9.next()){
					         //Retrieve by column countrycode
					         String maxage = res9.getString("maxage");
					         names.add(maxage);
					    }
						
						for(int i=0; i<names.size(); i++){
							ArrayList<Integer> maxage = new ArrayList<Integer>();
							if(temp.contains(i+1))
								continue;
							maxage.add((i+1));
							temp.add((i+1));
							for(int j=i+1; j<names.size(); j++){
								if(names.get(i).equals(names.get(j))){
									maxage.add((j+1));
									temp.add((j+1));
								}
							}
							listOfMaxAge.add(maxage);
						}
						
						//System.out.println();
					    equilist.add(listOfMaxAge);
					    names.clear();
					    temp.clear();
								
////					    // End of select maxage
////					    
////					    // Select noofgroups from database
////////						
						String sql10 = "SELECT no_of_groups FROM clinicaltrials_11_1000";
						//System.out.println();

						ResultSet res10 = stmt.executeQuery(sql10);
						while(res10.next()){
					         //Retrieve by column countrycode
					         String no_of_groups = res10.getString("no_of_groups");
					         names.add(no_of_groups);
					    }
						
						for(int i=0; i<names.size(); i++){
							ArrayList<Integer> no_of_groups = new ArrayList<Integer>();
							if(temp.contains(i+1))
								continue;
							no_of_groups.add((i+1));
							temp.add((i+1));
							for(int j=i+1; j<names.size(); j++){
								if(names.get(i).equals(names.get(j))){
									no_of_groups.add((j+1));
									temp.add((j+1));
								}
							}
							listOfNoGroups.add(no_of_groups);
						}
						
						//System.out.println();
					    equilist.add(listOfNoGroups);
					    names.clear();
					    temp.clear();
//								
//					    // End of select noofgroups
//		    
			res.close();
			stmt.close();
			conn.close();
//			
			map.put("countrycode", listOfCountryCode);
			map.put("country", listOfCountry);
			map.put("disease", listOfDisease);
			map.put("medicine", listOfMedicine);
			map.put("status", listOfStatus);
			map.put("study_design", listOfStudyDesign);
			map.put("study_type", listOfStudyType);
			map.put("phase", listOfPhase);
			map.put("minage", listOfMinAge);
			map.put("maxage", listOfMaxAge);
			map.put("numgroups", listOfNoGroups);

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
