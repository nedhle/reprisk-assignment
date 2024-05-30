# Company Finder Application

This application identifies which companies from a given list are mentioned in a set of news articles. It uses a trie data structure to efficiently search for company names within the articles.

## Features

- Efficient company name searching using a trie data structure.
- Handles various forms of company names, including those with legal terms (e.g., Ltd., Inc.) and special characters (e.g., parentheses, quotes).
- Outputs the results to a specified file.

## Prerequisites

- Java 21
- Maven (for building and running tests)

## Getting Started

### 1. Clone the Repository

```sh
git clone git@github.com:nedhle/reprisk-assignment.git
````

### 2. Running
```sh
java -jar .\task-0.0.1-SNAPSHOT.jar
````

### 3. Example result

```
Process Start Time : Wed May 29 17:57:56 CEST 2024 End Time : Wed May 29 17:58:08 CEST 2024 
 1392 Company found 
 More information in result.txt..
````
## Notes
Project is a command line project, you can see the results (like time and
how many company project founds) on command line.
 Also which company in which articles, you find in results.txt
