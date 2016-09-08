
## Requirements for running the code:

Java 8 64-bit, Maven 3.0.3 (m2eclipse plugin if you plan to use Eclipse)

## Maven clean installation:

Run the command "mvn clean install" before running the project. This command will clean the code and make it ready for execution.

## Datasets for our experiments:

Download link : https://drive.google.com/open?id=0B6Ti4BUq0pv5TnJaaE1XcUlzV1k

Unzip the files and upload it to mysql database.

## Setting up MySql database:

Create two tables in MySql database - Clinicaltrials and Census.

Import the two excel files in the respective tables.

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
    
## How to run the code:

    1. Download the datasets from the folder and create two tables in MySQL database. Then establish the connection to the database from the .java file by providing the connection parameters such as url, username and password.

    2. Ontology Synonyms algorithm:
        
        -- EquivalenceListofList.java and Canonical.java are the supporting files for OFDSynonyms algorithm.
        
        -- EquivalenceListofList.java: 
                
            -- findEquivalence() is the method in this class that computes the equivalence classes.
            -- In findEquivalence(), there is a separate query for each attribute in the relation.
            -- For example, "String sql = "SELECT disease FROM clinicaltrials"". Like this query, there is a separate query for each attribute in the relation. This query will extract the data for the specific column from the clinicaltrials table.
            -- Then the result gets transformed to compute the equivalence classes for each attribute in the relation.
            -- This file has to be edited based on the varied attributes in the relation.
    
        -- Canonical.java:
        
            -- In Canonical.java, we have a separate method for each attribute in the relation for computing the canonical classes.
            -- For example, for the disease attribute in clinicaltrials table, we have a method findCanonicalDisease() that computes the canonical class for the disease attribute.
            
        -- Run the file OFDSynonyms.java 
        
    3. Ontology Inheritance Algorithm:
    
        -- EquivalenceListofList.java and Canonical.java are the supporting files for OFDISA algorithm for computing equivalence and canonical classes.
        
        -- IO.Java: This is the file where the ontology graph is built. This is where the value of threshold is set in the variabel "THRESHOLD".
        
        -- OntologyISA.java - This is the file which computes the list of ancestors for each node and then computes the Least Common Ancestor for each node in the graph.
        
        -- In the Graph folder, there is a class called Traversal.java that traverses the tree in BFS fashion.
        
        -- Create these files and then run the main program OFDISA.java
    
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
        
     


    
