package ontologysynonyms_5_20;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PropertyResourceBundle;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import ontologysynonyms.OFDSynonyms;

public class OFDSynonyms_5_20 {

	private static Iterator<Map.Entry<String, List<List<Integer>>>> iteqmap;
	private static Iterator<Map.Entry<String, List<String>>> itcmap;
	private static Iterator<Map.Entry<String, ArrayList<String>>> bitcmap;
	private static Iterator<Entry<String, List<List<Integer>>>> iteqmap1;
	private static Map<String, List<List<Integer>>> eqmap;
	private static Iterator<Map.Entry<String, ArrayList<ArrayList<Integer>>>> biteqmap;
	private static ArrayList<String> list2 = new ArrayList<String>();
	private static ArrayList<String> list3 = new ArrayList<String>();
	private static Queue<List<Integer>> st1 = new LinkedList<List<Integer>>();
	private static Queue<List<Integer>> st2 = new LinkedList<List<Integer>>();
	private static ArrayList<Integer> list1;
	private static Map<String, List<String>> cmap;
	private static List<String> checklist;
	private static List<String> badOFD = new ArrayList<String>();
	private static List<String> goodOFD = new ArrayList<String>();
	private static String LHS = null;
	private static String RHS = null;
	private static String badLHS = null;
	private static String badRHS = null;
	private static ArrayList<ArrayList<Integer>> processlistresult = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<ArrayList<Integer>> processlistresulttemp1 = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<ArrayList<Integer>> processlistresulttemp2 = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<ArrayList<Integer>> processlist1 = null;
	private static ArrayList<ArrayList<Integer>> processlist2 = null;
	private static List<List<String>> al = new ArrayList<List<String>>();
	private static Map<Set<String>, String> resultmap;
	private static boolean isExcessThree;
	private static int columncount = 5;
	
	public static boolean isExcessThree() {
		return isExcessThree;
	}

	public static void setExcessThree(boolean isExcessThree) {
		OFDSynonyms_5_20.isExcessThree = isExcessThree;
	}

	public static void main(String[] args) throws SQLException {

		EquivalenceListOfList eq1 = new EquivalenceListOfList();
		eqmap = eq1.getEquiList();

		Canonical c1 = new Canonical();
		cmap = c1.calculateFinlist();

		iteqmap = eqmap.entrySet().iterator();
		itcmap = cmap.entrySet().iterator();
		resultmap = new HashMap<Set<String>, String>();
		checklist = new ArrayList<String>();

		System.out.println("OFD's are");
		
		processList(iteqmap, itcmap);
		
		//System.out.println("Map values");
		
		 Iterator it = resultmap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        System.out.println(pair.getKey() + " -> " + pair.getValue());
		        it.remove(); // avoids a ConcurrentModificationException
		    }

	} // end of main

	private static void processList(Iterator<Entry<String, List<List<Integer>>>> iteqmap,
			Iterator<Entry<String, List<String>>> itcmap) {

		Map.Entry<String, List<List<Integer>>> mapEntry = null;
		Map.Entry<String, List<String>> mapEntry1 = null;

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
					flag = compareTwoLists(value1, value2);
				}
				if (flag) {
					String LHS = eqkey;
					String RHS = ckey;
					System.out.println(eqkey + "->" + ckey);
					//pruneSupersetsGood(eqmap, LHS, RHS);
					checklist.add(RHS);
					// System.out.println();
				} else {
					// System.out.println("------- BAD OFD PROCESSING -------");
					String LHS1 = eqkey;
					String RHS1 = ckey;
					List<List<Integer>> value1 = mapEntry.getValue();
					List<String> value2 = mapEntry1.getValue();
					processBadOFD(value1, value2, LHS1, RHS1, iteqmap, itcmap);
					// System.out.println("------- END OF BAD OFD PROCESSING
					// -------");
					// badOFD.add(eqkey + "->" + ckey);
				}
			}
			itcmap = cmap.entrySet().iterator();
		}
	}

	private static void pruneSupersetsGood(Map<String, List<List<Integer>>> eqmap, String LHS, String RHS) {
		Map.Entry<String, ArrayList<ArrayList>> mapEntry1 = null;
		String[] array1 = new String[10];
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
		String[] arr = new String[4];
		int length = -1;
		iteqmap1 = eqmap.entrySet().iterator();
		while (iteqmap1.hasNext()) {
			mapEntry1 = iteqmap1.next();
			String eqkey = mapEntry1.getKey();
			if (!eqkey.equals(RHS)) {
				arr[++length] = eqkey;
			}
		}
		combineGood(arr, i, LHS, RHS, str);
	}

	private static void combineGood(String[] arr, int r, String LHS, String RHS, String str) {
		String[] res = new String[r];
		doCombineGood(arr, res, 0, 0, r, LHS, RHS, str);
	}

	private static Object doCombineGood(String[] arr, String[] res, int currIndex, int level, int r, String LHS,
			String RHS, String str) {
		if (level == r) {
			return (printArrayGood(res, LHS, RHS, str));
			// return;
		}
		for (int i = currIndex; i < arr.length; i++) {
			res[level] = arr[i];
			doCombineGood(arr, res, i + 1, level + 1, r, LHS, RHS, str);
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

	private static void processBadOFD(List<List<Integer>> value1, List<String> value2, String LHS1, String RHS1,
			Iterator<Entry<String, List<List<Integer>>>> iteqmap, Iterator<Entry<String, List<String>>> itcmap) {

		Entry<String, List<List<Integer>>> mapEntry = null;
		Map.Entry<String, ArrayList<ArrayList<Integer>>> mapEntry1 = null;

		ArrayList<List<List<Integer>>> processlist = new ArrayList<List<List<Integer>>>();

		iteqmap = eqmap.entrySet().iterator();

		while (iteqmap.hasNext()) {
			List<List<Integer>> processlist1 = eqmap.get(LHS1);
			mapEntry = iteqmap.next();
			String eqkey = mapEntry.getKey();
			if (eqkey.equals(LHS1) || eqkey.equals(RHS1)) {
				continue;
			} else {
				List<List<Integer>> processlist2 = eqmap.get(eqkey);
				List<List<Integer>> result = findIntersection(processlist1, processlist2);
				boolean value = compareLists(result, RHS1);
				if (value && !checklist.contains(RHS1)) {
					System.out.println("[" + LHS1 + "," + eqkey + "]" + "->" + RHS1);
					//pruneSupersetsBad(eqmap, LHS1, eqkey, RHS1);
				} else {
					// processlistresult.clear();
					findSupersetsBad(eqmap, LHS1, RHS1, eqkey);
					continue;
				}
			}

		}
	}

	private static void pruneSupersetsBad(Map<String, List<List<Integer>>> eqmap, String LHS, String eqkey,
			String RHS) {
		Map.Entry<String, ArrayList<ArrayList>> mapEntry1 = null;
		String[] array1 = new String[10];
		int j = -1;
		String str = "derive";
		// Changed to i = 3 for testing . originally it was 2
		for (int i = 3; i < columncount; i++) {
			deriveCombinationsBad(eqmap, LHS, eqkey, RHS, i, str);
		}
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

	private static void findSupersetsBad(Map<String, List<List<Integer>>> eqmap, String LHS1, String RHS1,
			String eqkey) {
		String str = "intersection";
		setExcessThree(true);
		for (int i = 3; i < columncount; i++) {
			if (isExcessThree())
				deriveCombinationsBad(eqmap, LHS1, eqkey, RHS1, i, str);
			break;
		}
	}

	private static boolean compareLists(List<List<Integer>> result, String RHS1) {
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
				if (element != first)
					return false;
			}
			return true;
		}
	}

	private static List<List<Integer>> findIntersection(List<List<Integer>> processlist12,
			List<List<Integer>> processlist22) {

		List<List<Integer>> intersect = processlist12.stream().flatMap(sl1 -> processlist22.stream().map(sl2 -> {
			List<Integer> lout = new ArrayList<>();
			lout.addAll(sl1);
			lout.retainAll(sl2);
			return lout;
		})).filter(l -> l.size() > 0).distinct().collect(Collectors.toList());

		return intersect;
	}

	private static void deriveCombinationsBad(Map<String, List<List<Integer>>> eqmap, String LHS, String eqkey1,
			String RHS, int i, String str) {
		Map.Entry<String, List<List<Integer>>> mapEntry1;
		String[] arr = new String[4];
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

		if (str == "derive" && printlist.contains(LHS) && printlist.contains(eqkey1) && !(checklist.contains(RHS))) {
			printBad(printlist, RHS);
			//System.out.println(printlist + "->" + RHS);
		} else if (str == "intersection" && printlist.contains(LHS) && printlist.contains(eqkey1)) {
			List<List<List<Integer>>> result = new ArrayList<List<List<Integer>>>();
			for (String value : res) {
				result.add(eqmap.get(value));
			}
			List<List<Integer>> value = findIntersectionBad(result, RHS);
			boolean value1 = compareBadLists(value, RHS);
			if (value1 == true && !(checklist.contains(RHS))) {
				printBad(printlist, RHS);
				setExcessThree(true);
			}
			else {
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

	private static boolean compareTwoLists(List<List<Integer>> eqvalue, List<String> cvalue) {
		list2.clear();
		st1.clear();
		for (int i = 0; i < eqvalue.size(); i++) {
			st1.add(eqvalue.get(i));
		}
		for (int j = 0; j < cvalue.size(); j++) {
			String cstring = cvalue.get(j);
			list2.add(cstring);
		}
		boolean flag = compareValues(st1);
		return flag;
	}

	private static boolean compareValues(Queue<List<Integer>> st1) {
		boolean flag = true;
		while (!st1.isEmpty()) {
			List<Integer> poll = st1.poll();
			if (poll.size() == 1) {
				continue;
			} else {
				Object[] array = poll.toArray();
				boolean value = compareGreaterLength(array);
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

	private static boolean compareGreaterLength(Object[] array) {
		ArrayList<String> compare = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			compare.add(list2.get(((int) array[i]) - 1));
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


}
