# CSVparser
    Java Application for parsing a CSV file and adding the processed data into a SQLite Databse.
    Uses MAVEN repos. It is done by me WITHOUT the help of other open source libraries
    like ApacheCSV or OpenCSV.

CSVparser

    CSVparser program intends to be a Java parser for the CSV file format. The valid processed data  is then written into a SQLite database. The invalid data is written to another CSV file, which has the timestamp in the title of the file. It is done using MAVEN tools.

What is a CSV file ?

    A comma-separated values (CSV) file is a delimited text file that uses a comma to separate values. A CSV file stores tabular data (numbers and text) in plain text. Each line of the file is a data record. Each record consists of one or more fields, separated by commas. The use of the comma as a field separator is the source of the name for this file format. CSV uses a file format that is very often supported by consumer, business, and scientific applications. Among its most common uses is moving tabular data between programs that natively operate on incompatible (often proprietary and/or undocumented) formats. This works because so many programs support some variation of CSV at least as an alternative import/export format.

Compilation

    The main method is located in: /src/main/java/ClientReader.java which has detailed explanation for every step of the program. 
The initial CSV (rawData.csv) file IS LOCATED in this folder of the maven project: src/main/resources/rawData.csv

Execution 

    After compilation and execution of the program, a new bad-data-<timestamp>.csv file is generated, which contains all the bad data from the initial CSV file. Also a LOG file is generated with the results of the execution. A new SQLite database is generated with the default name of  "myDB.sqlite". 

Future releases
    In future releases, the main objective will be to increase performance. Open source libraries like Apache CSV or OpenCSV might also be used.

Thank you for support.
