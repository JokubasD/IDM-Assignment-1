# CSE1505_LabsStudent

This repository contains the skeleton code for CSE1505 Assignment 1.
This is what you have to do.


## Setup

- Clone this GitHub repository using your favorite Git client (this can be your IDE of choice, the command line, or the GitHub Desktop software).
- We strongly recommend using an IDE for the following tasks. You need an IDE which supports both Java and Maven. I personally use IntelliJ (matter of taste, it's nice
but not perfect), also Netbeans or Eclipse of course do the job (eventhough Thomas told me that he doesn't like the latter two...). 
Open the cloned project with your IDE (as a Maven Java Project), then explore the codebase a bit.
- In case you have never used Maven, look up what Maven is, and check out the `pom.xml` file in the project.

## Task 2.1 (SQL Queries using JDBC)

Please implement the interfaces found in package `tudelft.wis.idm_tasks.basicJDBC.interfaces`.
- Start with implementing the `JDBCManager.java` interface. You need to be able to connect to your PostgreSQL database with the IMDB dataset.
- Implement `JDBCTask2Interface.java` as described in the assignment task PDF.
- Write a short class which calls all methods of your `Task2Interface.java` implementation, and prints the firest 20 retrieved results of each query to the console. Use that console printout in your Brightspace submission to show that you solved this task.
(i.e., copy-paste the console printout of the result sets in your submission file).

## Task 2.2 (Boardgame Tracker with JDBC/JPA)

In this task, you are going to learn how to implement Java object persistence using JDBC and JPA/Hibernate (and passively also deal a bit with Maven, Git, and JUnit - search the Web/discuss).
Discuss with peers or TAs if you need help with those things (or ask on EWI Answers using the tag CSE1505) - this is a rather explorative self-study task.
Then, you are to implement the interfaces found in package `tudelft.wis.idm_tasks.boardGameTracker.interfaces`.

Here are some tips to do that:

General:
- Inspect and understand all the interfaces in the package `tudelft.wis.idm_tasks.boardGameTracker.interfaces`.
- Have a close look at the very basic reference implementation in the package `tudelft.wis.idm_solutions.BoardGameTracker.POJO_Implementation`. This implementation does not use any database persistence, but instead stores all data in POJOs (Plain Old Java Objects). It still can guide you towards your own JDBC/JPA implementation.
- TEST the reference implementation: Find `tudelft.wis.idm_solutions.BoardGameTracker.POJO_Implementation.POJO_Test.java`. Execute this class as a JUnit test (typically, something like right-click/test-file)! Try to figure out what is happening when you do that!

JDBC:
- Create a new database for this task. Create the tables you will later need.
- For the JDBC implementation of this task, we are happy enough if you only implement all interfaces to related Player and Boardgame 
(you can ignore PlaySession for now). This includes:
-- `Player.java`
-- `BoardGame.java`
-- `BgtDataManager.java`, all methods which relate to Player and Boardgame (those related to PlaySession can be skipped if you feel like it).
- Make a copy of the `POJO_Test.java` Junit test class we provided, and adapt it to test your JDBC implementation. Remove the tests for PlaySession if
you didn't implement PlaySession functionality.
- Run the test class as a JUnit test. Copy-paste the console output into your Brightspace submission file. Also add the source code of your class which implements (!) `Player.java` and `BgtDataManager.java`.

JPA:
- You have two choices: re-use the database you created for the JDBC task and tweak your JPA implementation to use it, or create a new empty database and have JPA take care of the rest (this might be easier).
- You can use this quickstart tutorial (or any other you find) to guide you along: https://docs.jboss.org/hibernate/orm/6.4/quickstart/html_single/#tutorial_jpa
- Set up an appropriate `persistence.xml` file.
- Implement all interfaces!
- Make a copy of the `POJO_Test.java` Junit test class, and adapt it to test your JPA implementation. The tests for PlaySession need to remain this time!
- Run the test class as a JUnit test. Copy-paste the console output into your Brightspace submission file. Also add the source code of your class which implements (!) `Player.java`, `Boardgame.java`, `PlaySession.java` and `BgtDataManager.java`.


