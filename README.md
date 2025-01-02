# Car Shop Project

Welcome to the Car Shop project repository! This is a collaborative project developed by a group of friends interested in creating a database for a car shop and designing graphical interfaces using JavaFX and Maven.

## Objectives

The goal of this project is to create an efficient database system for a car shop, allowing us to store information about vehicles, customers, sales, and more. Additionally, we implemented user-friendly graphical interfaces using JavaFX to interact with the database.

## Technologies Used

- Java
- JavaFX
- MySQL/MariaDB Database
- Maven

## Repository Structure

The repository is organized as follows:

- `src/main/java/com/example/javafxproject/Controller`: Contains Java source files for implementing JavaFX screens and system logic.
- `src/main/java/com/example/javafxproject/EstruturaBanco`: Contains SQL scripts for database creation and maintenance.
- `src/main/resources/com/example/javafxproject`: Contains all FXML scripts for the screens.

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/lojadecarro/javafx-project.git


<!-- Remove the `.git` folder: 
```bash rm -rf .git 
``` 

Initialize a new Git repository, make commits, and push to your remote repository. -->


2. After cloning, go to src/main/java/com/example/javafxproject/Conexao/Conexao.java: You need to comment/uncomment the unnecessary/necessary imports for your machine (depending on whether you are using MariaDB or MySQL). Also, modify the URL according to your server.

3. Go to src/main/java/com/example/javafxproject/Main.java: This method tests the connection with the database server. Repeat the steps from point 2. At the end of the main method, you will find commands to create tables, insert values, and remove them. It is necessary to create the tables and perform the inserts for the system to function properly.

In the computer labs at IFSP, it is necessary to configure the proxy for Maven to work:

```bash
export JAVA_TOOL_OPTIONS="-Djava.net.useSystemProxies=true"
```

In the same terminal where the proxy was configured, run the following command:
```bash
./mvnw javafx:run
```



## Contacts

If you have any questions or need assistance, feel free to contact the members of this project:

- [Tro Armen](https://github.com/troarmen)
- [Rodrigo Mariano](https://github.com/Rodriomariano)
- [Letícia Brandão](https://github.com/lleleal)
- [Paulo Henrique](https://github.com/paulohernrique)

This is the car shop system we developed for the LP1 course at IFSP!
