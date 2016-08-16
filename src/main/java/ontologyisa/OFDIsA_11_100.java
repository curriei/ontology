package ontologyisa;

import static graph.Traversal.BFS;
import static ontologyisa.IO.DISEASE;
import static ontologyisa.IO.MEDICINE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import graph.Vertex;

public class OFDIsA_11_100 {

	private static final int columncount = 11;
	private static Map<String, List<List<Integer>>> eqmap;
	private static Iterator<Map.Entry<String, List<List<Integer>>>> iteqmap;
	private static Map<String, List<String>> cmap;
	private static Iterator<Map.Entry<String, List<String>>> itcmap;
	private static ArrayList<String> list2 = new ArrayList<String>();
	private static ArrayList<String> list3 = new ArrayList<String>();
	private static Queue<List<Integer>> st1 = new LinkedList<List<Integer>>();
	private static Queue<List<Integer>> st2 = new LinkedList<List<Integer>>();
	private static Map<Vertex, Vertex> bfsDisease = BFS(IO.createDiseaseGraph(), DISEASE);
	private static Map<Vertex, Vertex> bfsMedicine = BFS(IO.createMedicinesGraph(), MEDICINE);
	private static Iterator<Map.Entry<String, List<List<Integer>>>> iteqmap1;
	private static Iterator<Map.Entry<String, List<String>>> bitcmap;
	private static List<List<Integer>> processlistresult = new ArrayList<List<Integer>>();
	private static Map<Set<String>, String> resultmap;
	private static PrintWriter writer = null;
	private static boolean isExcessThree;
	
	public static boolean isExcessThree() {
		return isExcessThree;
	}

	public static void setExcessThree(boolean isExcessThree) {
		OFDIsA_11_100.isExcessThree = isExcessThree;
	}

	public static void main(String[] args) throws SQLException, IOException {
		EquivalenceList eq1 = new EquivalenceList();
		eqmap = eq1.getEquiList();

		CanonicalISA c1 = new CanonicalISA();
		cmap = c1.calculateFinlist();

		iteqmap = eqmap.entrySet().iterator();
		itcmap = cmap.entrySet().iterator();
		resultmap = new HashMap<Set<String>, String>();
		
		writer = new PrintWriter("isa_11_5000", "UTF-8");

		processList(iteqmap, itcmap);
		
		writer.close();
		
		stripDuplicatesFromFile("isa_11_5000");
		//System.out.println();
		
		System.out.println("contents of file are: ");
		System.out.println();
		
		BufferedReader br = new BufferedReader(new FileReader("isa_11_5000"));
		 String line = null;
		 while ((line = br.readLine()) != null) {
		   System.out.println(line);
		 }
	}

	private static void stripDuplicatesFromFile(String filename) throws IOException {
		 BufferedReader reader = new BufferedReader(new FileReader(filename));
		    Set<String> lines = new HashSet<String>(10000); // maybe should be bigger
		    String line;
		    while ((line = reader.readLine()) != null) {
		        lines.add(line);
		    }
		    reader.close();
		    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		    for (String unique : lines) {
		        writer.write(unique);
		        writer.newLine();
		    }
		    writer.close();
	}

	private static void processList(Iterator<Entry<String, List<List<Integer>>>> iteqmap,
			Iterator<Entry<String, List<String>>> itcmap) {

		Map.Entry<String, List<List<Integer>>> mapEntry = null;
		Map.Entry<String, List<String>> mapEntry1 = null;
		
		long startTime = System.currentTimeMillis();

		while (iteqmap.hasNext()) {
			boolean flag;
			mapEntry = iteqmap.next();
			String eqkey = mapEntry.getKey();
			while (itcmap.hasNext()) {
				mapEntry1 = itcmap.next();
				String ckey = mapEntry1.getKey();
				if (eqkey.equals(ckey)) {
					continue;
				} else {
					List<List<Integer>> value1 = mapEntry.getValue();
					List<String> value2 = mapEntry1.getValue();
					flag = compareTwoLists(value1, value2, ckey);

				}
				if (flag) {
					String LHS = eqkey;
					String RHS = ckey;
					//System.out.println();
					writer.println(eqkey + "->" + ckey);
					//System.out.println(eqkey + "->" + ckey);
					//pruneSupersetsGood(eqmap, LHS, RHS);
				}
				else {
					//System.out.println("------- BAD OFD PROCESSING -------");
					String LHS1 = eqkey;
					String RHS1 = ckey;
					List<List<Integer>> value1 = mapEntry.getValue();
					List<String> value2 = mapEntry1.getValue();
					//System.out.println();
					processBadOFD(value1, value2, LHS1, RHS1, iteqmap, itcmap);
					//System.out.println("------- END OF BAD OFD PROCESSING -------");
					// badOFD.add(eqkey + "->" + ckey);
				}
			}
			itcmap = cmap.entrySet().iterator();
		}
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Execution time(in mins): " + elapsedTime);

	}

	private static void processBadOFD(List<List<Integer>> value1, List<String> value2, String LHS1, String RHS1,
			Iterator<Entry<String, List<List<Integer>>>> iteqmap, Iterator<Entry<String, List<String>>> itcmap) {

		Map.Entry<String, List<List<Integer>>> mapEntry = null;
		Map.Entry<String, List<List<Integer>>> mapEntry1 = null;

		iteqmap = eqmap.entrySet().iterator();

		while (iteqmap.hasNext()) {
			// processlist1 = value1.get(LHS1);
			List<List<Integer>> processlist1 = eqmap.get(LHS1);
			mapEntry = iteqmap.next();
			String eqkey = mapEntry.getKey();
			resultmap.clear();
			if (eqkey.equals(LHS1) || eqkey.equals(RHS1)) {
				continue;
			} else {
				// processlist2 = value1.get(eqkey);
				List<String> list = new ArrayList<String>();
				List<List<Integer>> processlist2 = eqmap.get(eqkey);
				List<List<Integer>> result = findIntersection(processlist1, processlist2);
				boolean value = compareLists(result, bitcmap, LHS1, RHS1, eqkey);
				boolean flag = false;
				if (value) {
					String[] array1 = new String[2];
					array1[0] = LHS1;
					array1[1] = eqkey;
					//System.out.println();
					flag = true;
					Arrays.sort(array1);
					writer.println("[" + array1[0] + "," + array1[1] + "]" + "->" + RHS1);
					//System.out.println("[" + LHS1 + "," + eqkey + "]" + "->" + RHS1);
					//pruneSupersetsBad(eqmap, LHS1, eqkey, RHS1);
				} else {
					findSupersetsBad(eqmap, LHS1, RHS1, eqkey);
					if(flag == true)
						printresultmap();
					processlistresult.clear();
					continue;
				}
			}
			printresultmap();
		}

	}

	private static void printresultmap() {
		Iterator it = resultmap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			writer.println(pair.getKey() + " -> " + pair.getValue());
			System.out.println(pair.getKey() + " -> " + pair.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}
	}

	private static void findSupersetsBad(Map<String, List<List<Integer>>> eqmap, String LHS1, String RHS1,
			String eqkey) {
		String str = "intersection";
		setExcessThree(true);
		for (int i = 3; i < columncount; i++) {
			if(isExcessThree())
				deriveCombinationsBad(eqmap, LHS1, eqkey, RHS1, i, str);
			break;
		}
	}

	private static void deriveCombinationsBad(Map<String, List<List<Integer>>> eqmap, String LHS, String eqkey1,
			String RHS, int i, String str) {
		Map.Entry<String, List<List<Integer>>> mapEntry1;
		String[] arr = new String[columncount - 1];
		int length = -1;
		iteqmap1 = eqmap.entrySet().iterator();
		while (iteqmap1.hasNext()) {
			mapEntry1 = iteqmap1.next();
			String eqkey = mapEntry1.getKey();
			if (!eqkey.equals(RHS)) {
				arr[++length] = eqkey;
			}
		}
		combineBad(arr, i, LHS, RHS, str, eqkey1);
	}

	private static void combineBad(String[] arr, int r, String LHS, String RHS, String str, String eqkey1) {
		String[] res = new String[r];
		doCombineBad(arr, res, 0, 0, r, LHS, RHS, str, eqkey1);
	}

	private static Set<String> doCombineBad(String[] arr, String[] res, int currIndex, int level, int r, String LHS,
			String RHS, String str, String eqkey1) {

		if (level == r) {
			return (printArrayBad(res, LHS, RHS, str, eqkey1));
			// return;
		}
		for (int i = currIndex; i < arr.length; i++) {
			res[level] = arr[i];
			doCombineBad(arr, res, i + 1, level + 1, r, LHS, RHS, str, eqkey1);
			// // way to avoid printing duplicates
			// if (i < arr.length - 1 && arr[i] == arr[i + 1]) {
			// i++;
			// }
		}
		return convertArrayToSet(res);
	}

	private static Set<String> printArrayBad(String[] res, String LHS, String RHS, String str, String eqkey1) {

		Set<String> printlist = convertArrayToSet(res);

		if (str == "derive" && printlist.contains(LHS) && printlist.contains(eqkey1)) {
			printBad(printlist, RHS);
			//System.out.println(printlist + "->" + RHS);
		} else if (str == "intersection" && printlist.contains(LHS) && printlist.contains(eqkey1)) {
			List<List<List<Integer>>> result = new ArrayList<List<List<Integer>>>();
			for (String value : res) {
				result.add(eqmap.get(value));
			}
			List<List<Integer>> value = findIntersectionBad(result, RHS);
			boolean value1 = compareBadLists(value, RHS);
			if (value1 == true) {
				printBad(printlist, RHS);
				setExcessThree(true);
				//System.out.println(printlist + "->" + RHS);
			}else{
				setExcessThree(false);
			}
		}
		return printlist;
	}

	private static Set<String> convertArrayToSet(String[] res) {
		Set<String> result = new HashSet<String>();
		for (String value : res) {
			result.add(value);
		}
		return result;
	}

	private static void printBad(Set<String> printlist, String RHS) {
		resultmap.put(printlist, RHS);
	}

	private static void pruneSupersetsBad(Map<String, List<List<Integer>>> eqmap, String LHS, String eqkey,
			String RHS) {
		Map.Entry<String, ArrayList<ArrayList>> mapEntry1 = null;
		String[] array1 = new String[columncount - 1];
		int j = -1;
		String str = "derive";
		// Changed to i = 3 for testing . originally it was 2
		for (int i = 3; i < columncount; i++) {
			deriveCombinationsBad(eqmap, LHS, eqkey, RHS, i, str);
		}
	}

	private static boolean compareLists(List<List<Integer>> result, Iterator<Entry<String, List<String>>> bitcmap,
			String LHS1, String RHS1, String eqkey) {
		Stack<List<Integer>> stack = new Stack<List<Integer>>();
		boolean flag1 = true;
		List<String> list = cmap.get(RHS1);
		for (List<Integer> value : result) {
			if (value.size() > 1) {
				stack.push(value);
			}
		}
		while (!stack.isEmpty()) {
			List<Integer> popvalue = stack.pop();
			Object[] array = popvalue.toArray();
			boolean value = compareLength(array, list);
			if (value) {
				continue;
			} else {
				flag1 = false;
				break;
			}
		}

		return flag1;
	}

	private static boolean compareLength(Object[] array, ArrayList<String> list) {
		ArrayList<String> compare = new ArrayList<String>();
		Stack<String> compstack = new Stack<String>();
		for (int i = 0; i < array.length; i++) {
			compare.add(list.get(((int) array[i]) - 1));
		}
		if (compare.size() == 0)
			return true;
		else {
			String first = compare.get(0);
			for (String element : compare) {
				if (element != first)
					return false;
			}
			return true;
		}
	}

	private static List<List<Integer>> findIntersection(List<List<Integer>> l1, List<List<Integer>> l2) {

		List<List<Integer>> intersect = l1.stream().flatMap(sl1 -> l2.stream().map(sl2 -> {
			List<Integer> lout = new ArrayList<>();
			lout.addAll(sl1);
			lout.retainAll(sl2);
			return lout;
		})).filter(l -> l.size() > 0).distinct().collect(Collectors.toList());

		return intersect;
	}

	private static void pruneSupersetsGood(Map<String, List<List<Integer>>> eqmap, String LHS, String RHS) {
		Map.Entry<String, List<List>> mapEntry1 = null;
		String[] array1 = new String[columncount - 1];
		int j = -1;
		String str = "derive";
		// Changed to i = 3 for testing . originally it was 2
		for (int i = 2; i < columncount; i++) {
			deriveCombinationsGood(eqmap, LHS, RHS, i, str);
		}
	}

	private static void deriveCombinationsGood(Map<String, List<List<Integer>>> eqmap, String LHS, String RHS, int i,
			String str) {
		Map.Entry<String, List<List<Integer>>> mapEntry1;
		String[] arr = new String[columncount - 1];
		int length = -1;
		iteqmap1 = eqmap.entrySet().iterator();
		while (iteqmap1.hasNext()) {
			mapEntry1 = iteqmap1.next();
			String eqkey = mapEntry1.getKey();
			if (!eqkey.equals(RHS)) {
				arr[++length] = eqkey;
			}
		}
		combine(arr, i, LHS, RHS, str);
	}

	private static void combine(String[] arr, int r, String LHS, String RHS, String str) {
		String[] res = new String[r];
		doCombine(arr, res, 0, 0, r, LHS, RHS, str);
	}

	private static Object doCombine(String[] arr, String[] res, int currIndex, int level, int r, String LHS, String RHS,
			String str) {
		if (level == r) {
			return (printArrayGood(res, LHS, RHS, str));
			// return;
		}
		for (int i = currIndex; i < arr.length; i++) {
			res[level] = arr[i];
			doCombine(arr, res, i + 1, level + 1, r, LHS, RHS, str);
			// way to avoid printing duplicates
			if (i < arr.length - 1 && arr[i] == arr[i + 1]) {
				i++;
			}
		}
		return res;
	}

	private static Object printArrayGood(String[] res, String LHS, String RHS, String str) {
		if (str == "derive") {
			List<String> printlist = Arrays.asList(res);
			if (printlist.contains(LHS)) {
				writer.println(printlist + "->" + RHS);
				System.out.println(printlist + "->" + RHS);

			}
		} else if (str == "intersection") {
			List<List<List<Integer>>> result = new ArrayList<List<List<Integer>>>();
			for (String value : res) {
				result.add(eqmap.get(value));
			}
			List<List<Integer>> value = findIntersectionBad(result, RHS);
			boolean value1 = compareBadLists(value, RHS);
		}
		return res;
	}

	private static List<List<Integer>> findIntersectionBad(List<List<List<Integer>>> processlist, String RHS) {
		Stack<List<List<Integer>>> superStack = new Stack<List<List<Integer>>>();
		for (int i = 0; i < processlist.size(); i++) {
			superStack.push(processlist.get(i));
		}

		return callIntersection(superStack);
	}

	private static List<List<Integer>> callIntersection(Stack<List<List<Integer>>> superStack) {
		List<List<Integer>> intersect = superStack.pop();

		while (!superStack.empty()) {
			List<List<Integer>> l2 = superStack.pop();
			intersect = intersect.stream().flatMap(sl1 -> l2.stream().map(sl2 -> {
				List<Integer> lout = new ArrayList<>();
				lout.addAll(sl1);
				lout.retainAll(sl2);
				return lout;
			})).filter(l -> l.size() > 0).distinct().collect(Collectors.toList());
		}

		return intersect;
	}

	private static boolean compareBadLists(List<List<Integer>> intersection, String RHS) {
		Stack<List<Integer>> stack = new Stack<List<Integer>>();
		boolean flag1 = true;
		List<String> list = cmap.get(RHS);
		for (List<Integer> value : intersection) {
			if (value.size() > 1) {
				stack.push(value);
			}
		}
		while (!stack.isEmpty()) {
			List<Integer> popvalue = stack.pop();
			Object[] array = popvalue.toArray();
			boolean value = compareLength(array, list);
			if (value) {
				continue;
			} else {
				flag1 = false;
				break;
			}
		}
		return flag1;
	}

	private static boolean compareLength(Object[] array, List<String> list) {
		ArrayList<String> compare = new ArrayList<String>();
		Stack<String> compstack = new Stack<String>();
		for (int i = 0; i < array.length; i++) {
			compare.add(list.get(((int) array[i]) - 1));
		}
		if (compare.size() == 0)
			return true;
		else {
			String first = compare.get(0);
			for (String element : compare) {
				if (!(element.equals(first)))
					return false;
			}
			return true;
		}
	}

	private static boolean compareTwoLists(List<List<Integer>> eqvalue, List<String> cvalue, String ckey) {
		list2.clear();
		st1.clear();
		for (int i = 0; i < eqvalue.size(); i++) {
			st1.add(eqvalue.get(i));
		}
		for (int j = 0; j < cvalue.size(); j++) {
			String cstring = cvalue.get(j);
			list2.add(cstring);
		}
		boolean flag = compareValues(st1, ckey);
		return flag;
	}

	private static boolean compareValues(Queue<List<Integer>> st1, String ckey) {
		boolean flag = true;
		while (!st1.isEmpty()) {
			List<Integer> poll = st1.poll();
			if (poll.size() == 1) {
				continue;
			} else {
				Object[] array = poll.toArray();
				boolean value = compareGreaterLength(array, ckey);
				if (value) {
					continue;
				} else {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	private static boolean compareGreaterLength(Object[] array, String ckey) {
		OntologyISA isa = new OntologyISA();
		List<String> compare = new ArrayList<String>();
		Stack<String> compstack = new Stack<String>();
		List<Vertex> vertexlist = new ArrayList<Vertex>();
		vertexlist.clear();
		for (int i = 0; i < array.length; i++) {
			compare.add(list2.get(((int) array[i]) - 1));
		}
		for (String string : compare) {
			vertexlist.add(new Vertex(string));
		}
		if (compare.size() == 0)
			return true;
		else if (ckey == "disease") {
			List<Vertex> diseaseList = new ArrayList<Vertex>();
			for (Vertex vertex : vertexlist) {
				diseaseList.add(bfsDisease.get(vertex));
			}
			Vertex lca = isa.findLCA(diseaseList);
			boolean value = isa.isWithinThreshold(lca, diseaseList);
			return value;
		} else if (ckey == "medicine") {
			List<Vertex> medicineList = new ArrayList<Vertex>();
			for (Vertex vertex : vertexlist) {
				medicineList.add(bfsMedicine.get(vertex));
			}
			Vertex lca = isa.findLCA(medicineList);
			boolean value = isa.isWithinThreshold(isa.findLCA(medicineList), medicineList);
			return value;
		} else if (ckey == "minage") {
			String first = compare.get(0);
			for (String element : compare) {
				if (!(element.equals(first))) {
					return false;
				}
			}
			return true;
		} else if (ckey == "maxage") {
			String first = compare.get(0);
			for (String element : compare) {
				if (!(element.equals(first))) {
					return false;
				}
			}
			return true;
		} else if (ckey == "studydesign") {
			String first = compare.get(0);
			for (String element : compare) {
				if (!(element.equals(first))) {
					return false;
				}
			}
			return true;
		}
		else if (ckey == "countrycode") {
			String first = compare.get(0);
			for (String element : compare) {
				if (!(element.equals(first))) {
					return false;
				}
			}
			return true;
		}else if (ckey == "country") {
			String first = compare.get(0);
			for (String element : compare) {
				if (!(element.equals(first))) {
					return false;
				}
			}
			return true;
		}else if (ckey == "studytype") {
			String first = compare.get(0);
			for (String element : compare) {
				if (!(element.equals(first))) {
					return false;
				}
			}
			return true;
		}
		else if (ckey == "numgroups") {
			String first = compare.get(0);
			for (String element : compare) {
				if (!(element.equals(first))) {
					return false;
				}
			}
			return true;
		}else if (ckey == "safetyissue") {
			String first = compare.get(0);
			for (String element : compare) {
				if (!(element.equals(first))) {
					return false;
				}
			}
			return true;
		}else if (ckey == "prevelant") {
			String first = compare.get(0);
			for (String element : compare) {
				if (!(element.equals(first))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
