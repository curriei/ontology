package ontologyisa;

import static graph.Color.WHITE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

import graph.Graph;
import graph.Vertex;

@SuppressWarnings("unused")
public final class IO {
	public static final int THRESHOLD = 3;
	public static final Vertex PAPILLARY_RCC = new Vertex("PAPILLARY RCC");
	public static final Vertex NODULAR_BCC = new Vertex("NODULAR BCC");
	public static final Vertex SUPERFICIAL_BCC = new Vertex("SUPERFICIAL BCC");
	public static final Vertex ORAL_CANCER = new Vertex("ORAL CANCER");
	public static final Vertex HEAD_CANCER = new Vertex("HEAD CANCER");
	public static final Vertex CARCINOID = new Vertex("CARCINOID");
	public static final Vertex LIPO_SARCOMA = new Vertex("LIPO SARCOMA");
	public static final Vertex DISEASE = new Vertex("DISEASE");

	
	public static final Vertex AMOXICILLIN = new Vertex("AMOXICILLIN");
	public static final Vertex CODEINE = new Vertex("CODEINE");
	public static final Vertex MORPHINE = new Vertex("MORPHINE");
	public static final Vertex ASPIRIN = new Vertex("ASPIRIN");
	public static final Vertex TYLENOL = new Vertex("TYLENOL");
	public static final Vertex MEDICINE = new Vertex("MEDICINE");


//	public static final Vertex ANIMAL = new Vertex("ANIMAL");
//	public static final Vertex JAGUAR = new Vertex("JAGUAR");
//	public static final Vertex MUSTANG = new Vertex("MUSTANG");
//	public static final Vertex IMPALA = new Vertex("IMPALA");
//	public static final Vertex CAT = new Vertex("CAT");
//	public static final Vertex TIGER = new Vertex("TIGER");
//	public static final Vertex BUTTERFLY = new Vertex("BUTTERFLY");
//	public static final Vertex BEETLE = new Vertex("BEETLE");
//	public static final Vertex FISH = new Vertex("FISH");

	private final static Logger LOGGER = Logger.getLogger(IO.class.getName());

	public static Graph<Vertex> createDiseaseGraph() {

		// LOGGER.info("---------BUILDING GRAPH----------");

		Graph<Vertex> graph = new Graph<Vertex>(8);

		graph.getAdj().put(new Vertex("TYPE1 RCC", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("TYPE2 RCC", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("CHROMOPHOBE RCC", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("NODULAR BCC", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("SUPERFICIAL BCC", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("LARYNGEAL", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("HYPOPHARYNGEAL", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("ORAL CANCER", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("SALIVARY GLAND CANCER", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("DCIS", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("IDC", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("ILC", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("CARCINOID", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("LIPO SARCOMA", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("SYNAVIAL SARCOMA", WHITE), new ArrayList<Vertex>());

		
		graph.addEgde(new Vertex("DISEASE", WHITE), new Vertex("CARCINOMA", WHITE));
		graph.addEgde(new Vertex("DISEASE", WHITE), new Vertex("METASTATIC", WHITE));
		graph.addEgde(new Vertex("CARCINOMA", WHITE), new Vertex("RENAL CELL", WHITE));
		graph.addEgde(new Vertex("CARCINOMA", WHITE), new Vertex("BASAL CELL", WHITE));
		graph.addEgde(new Vertex("CARCINOMA", WHITE), new Vertex("SQUAMOUS CELL", WHITE));
		graph.addEgde(new Vertex("RENAL CELL", WHITE), new Vertex("PAPILLARY RCC", WHITE));
		graph.addEgde(new Vertex("RENAL CELL", WHITE), new Vertex("CHROMOPHOBE RCC", WHITE));
		graph.addEgde(new Vertex("PAPILLARY RCC", WHITE), new Vertex("TYPE1 RCC", WHITE));
		graph.addEgde(new Vertex("PAPILLARY RCC", WHITE), new Vertex("TYPE2 RCC", WHITE));
		graph.addEgde(new Vertex("BASAL CELL", WHITE), new Vertex("NODULAR BCC", WHITE));
		graph.addEgde(new Vertex("BASAL CELL", WHITE), new Vertex("SUPERFICIAL BCC", WHITE));
		graph.addEgde(new Vertex("SQUAMOUS CELL", WHITE), new Vertex("HEAD CANCER", WHITE));
		graph.addEgde(new Vertex("SQUAMOUS CELL", WHITE), new Vertex("NECK CANCER", WHITE));
		graph.addEgde(new Vertex("HEAD CANCER", WHITE), new Vertex("LARYNGEAL", WHITE));
		graph.addEgde(new Vertex("HEAD CANCER", WHITE), new Vertex("HYPOPHARYNGEAL", WHITE));
		graph.addEgde(new Vertex("NECK CANCER", WHITE), new Vertex("ORAL CANCER", WHITE));
		graph.addEgde(new Vertex("NECK CANCER", WHITE), new Vertex("SALIVARY GLAND CANCER", WHITE));
		graph.addEgde(new Vertex("METASTATIC", WHITE), new Vertex("BREAST", WHITE));
		graph.addEgde(new Vertex("METASTATIC", WHITE), new Vertex("PROSTRATE", WHITE));
		graph.addEgde(new Vertex("BREAST", WHITE), new Vertex("DCIS", WHITE));
		graph.addEgde(new Vertex("BREAST", WHITE), new Vertex("IDC", WHITE));
		graph.addEgde(new Vertex("BREAST", WHITE), new Vertex("ILC", WHITE));
		graph.addEgde(new Vertex("PROSTRATE", WHITE), new Vertex("CARCINOID", WHITE));
		graph.addEgde(new Vertex("PROSTRATE", WHITE), new Vertex("SARCOMA", WHITE));
		graph.addEgde(new Vertex("SARCOMA", WHITE), new Vertex("LIPO SARCOMA", WHITE));
		graph.addEgde(new Vertex("SARCOMA", WHITE), new Vertex("SYNAVIAL SARCOMA", WHITE));



		// print(graph);
		// LOGGER.info("--------- BUILDING GRAPH COMPLETE----------");
		return graph;
	}

	public static Graph<Vertex> createMedicinesGraph() {

		// LOGGER.info("---------BUILDING GRAPH----------");

		Graph<Vertex> graph = new Graph<Vertex>(8);

		graph.getAdj().put(new Vertex("AMOXICILLIN", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("SUMCYCIN", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("PANMYCIN", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("CODEINE", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("MORPHINE", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("METHADONE", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("ASPIRIN", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("TYLENOL", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("CROCIN", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("PARACETAMOL", WHITE), new ArrayList<Vertex>());


		graph.addEgde(new Vertex("MEDICINE", WHITE), new Vertex("ANTIBIOTIC", WHITE));
		graph.addEgde(new Vertex("MEDICINE", WHITE), new Vertex("ANALGESIC", WHITE));
		graph.addEgde(new Vertex("ANTIBIOTIC", WHITE), new Vertex("PENICILLIN", WHITE));
		graph.addEgde(new Vertex("ANTIBIOTIC", WHITE), new Vertex("TETRACYCLIN", WHITE));
		graph.addEgde(new Vertex("PENICILLIN", WHITE), new Vertex("AMOXICILLIN", WHITE));
		graph.addEgde(new Vertex("TETRACYCLIN", WHITE), new Vertex("SUMCYCIN", WHITE));
		graph.addEgde(new Vertex("TETRACYCLIN", WHITE), new Vertex("PANMYCIN", WHITE));
		graph.addEgde(new Vertex("ANALGESIC", WHITE), new Vertex("NARCOTIC", WHITE));
		graph.addEgde(new Vertex("NARCOTIC", WHITE), new Vertex("CODEINE", WHITE));
		graph.addEgde(new Vertex("NARCOTIC", WHITE), new Vertex("MORPHINE", WHITE));
		graph.addEgde(new Vertex("NARCOTIC", WHITE), new Vertex("METHADONE", WHITE));
		graph.addEgde(new Vertex("ANALGESIC", WHITE), new Vertex("NON STEROIDAL", WHITE));
		graph.addEgde(new Vertex("NON STEROIDAL", WHITE), new Vertex("ASPIRIN", WHITE));
		graph.addEgde(new Vertex("NON STEROIDAL", WHITE), new Vertex("TYLENOL", WHITE));
		graph.addEgde(new Vertex("ANALGESIC", WHITE), new Vertex("ACETAMINOPHEN", WHITE));
		graph.addEgde(new Vertex("ACETAMINOPHEN", WHITE), new Vertex("CROCIN", WHITE));
		graph.addEgde(new Vertex("ACETAMINOPHEN", WHITE), new Vertex("PARACETAMOL", WHITE));



		// print(graph);
		// LOGGER.info("--------- BUILDING GRAPH COMPLETE----------");
		return graph;
	}

	public static Graph<Vertex> createPetsGraph() {

		// LOGGER.info("---------BUILDING GRAPH----------");

		Graph<Vertex> graph = new Graph<Vertex>(14);

		graph.getAdj().put(new Vertex("MUSTANG", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("RABBIT", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("IMPALA", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("JAGUAR", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("TIGER", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("CAT", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("FISH", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("BEETLE", WHITE), new ArrayList<Vertex>());
		graph.getAdj().put(new Vertex("BUTTERFLY", WHITE), new ArrayList<Vertex>());

		graph.addEgde(new Vertex("ANIMAL", WHITE), new Vertex("VERTEBRATES", WHITE));
		graph.addEgde(new Vertex("ANIMAL", WHITE), new Vertex("INVERTEBRATES", WHITE));
		graph.addEgde(new Vertex("INVERTEBRATES", WHITE), new Vertex("FISH", WHITE));
		graph.addEgde(new Vertex("INVERTEBRATES", WHITE), new Vertex("BEETLE", WHITE));
		graph.addEgde(new Vertex("INVERTEBRATES", WHITE), new Vertex("BUTTERFLY", WHITE));
		graph.addEgde(new Vertex("VERTEBRATES", WHITE), new Vertex("HERBIVORES", WHITE));
		graph.addEgde(new Vertex("VERTEBRATES", WHITE), new Vertex("CARNIVORES", WHITE));
		graph.addEgde(new Vertex("HERBIVORES", WHITE), new Vertex("MUSTANG", WHITE));
		graph.addEgde(new Vertex("HERBIVORES", WHITE), new Vertex("RABBIT", WHITE));
		graph.addEgde(new Vertex("CARNIVORES", WHITE), new Vertex("IMPALA", WHITE));
		graph.addEgde(new Vertex("CARNIVORES", WHITE), new Vertex("JAGUAR", WHITE));
		graph.addEgde(new Vertex("CARNIVORES", WHITE), new Vertex("TIGER", WHITE));
		graph.addEgde(new Vertex("CARNIVORES", WHITE), new Vertex("CAT", WHITE));

		// print(graph);
		// LOGGER.info("--------- BUILDING GRAPH COMPLETE----------");
		return graph;
	}

	private static void print(Graph<Vertex> wordGraph) {
		System.out.println("------ ADJACENCY LIST ------");
		for (Entry<Vertex, List<Vertex>> entry : wordGraph.getAdj().entrySet()) {
			Vertex key = entry.getKey();
			List<Vertex> values = entry.getValue();
			System.out.print(key.getValue() + ": ");
			if (values.isEmpty())
				System.out.print("LEAF NODE");
			for (Vertex vertex : values) {
				System.out.print(" " + vertex.getValue() + " ");
			}
			System.out.println();
		}
		System.out.println("------ END OF ADJACENCY LIST ------");
	}

}
