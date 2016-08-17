
## Requirements for running the code:

Java 8 64-bit, Maven 3.0.3 (m2eclipse plugin if you plan to use Eclipse)

## Maven clean installation:

Run the command "mvn clean install" before running the project. This command will clean the code and make it ready for execution.

## Files in src/main/java:

-- For Ontology Synonyms algorithm

    1. EquivalenceListOfList.java: This is the class that computes the equivalence class for each attribute in the relation.
    
    2. Canonical.java: This is the class that computes the canonical class for each attribute in the relation.
    
    3. OFDSynonyms.java: This is the class that performs the actual computation of OFDSynonyms algorithm.
    
-- For Ontology Inheritance algorithm

    1. EquivalenceList.java: This is the class that computes the equivalence class for each attribute in the relation.
    
    2. Canonical.java: This is the class that computes the canonical class for each attribute in the relation.
    
    3. OntologyISA.java: This is the class that performs the actual computation of OFDSynonyms algorithm.
    
    4. Graph.java: This is the class in which the nodes and edges of the graph are built. 
    
    5. Traversal.java: This is the class in which Breadth First Search traversal happens.
    
    6. IGraph.java: This is the interface which implements the Graph class.
    
## Datasets used:

    1. Clinical trials dataset:
    
     http://www.cs.toronto.edu/~oktie/linkedct/
     
     Ontology references:
     
        a) https://countrycode.org/ (Countrycode ontology)
        
        b) https://bioportal.bioontology.org/ontologies (Medical ontology)

        c) https://data.medicare.gov (Medical ontology)

        d) https://www.medicare.gov/hospitalcompare/Data/Data-Download.html (Medical ontology)

    2. Census dataset:
    
    https://archive.ics.uci.edu/ml/datasets/Census+Income
    
     Ontology references:
     
        a) https://countrycode.org/ (Countrycode ontology)
        
        b) http://www.bbc.co.uk/ontologies/coreconcepts (Occupation ontology)
        
     


    
