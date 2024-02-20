# CSE1505_LabsStudent

This repository contains skeleton code for CSE1505 Assignment 1.
This is what you have to do.


## Setup
- Clone this GITHUB repository using your favorite Git client (this can be your IDE of choice, the command line, or the GitHub Desktop software)
- We strongly recommend using an IDE for this task. You need an IDE which supports both Java and Maven. I personally use Netbeans (matter of taste, it's nice
but not perfect), also IntelliJ or Eclipse are of course nice. Open the cloned project with your IDE (as a Maven Java Project), explore a bit.
- In case you have never used Maven, look up what Maven is and does, and check out the POM.XML file

## Task 1.2 (SQL Queries using JDBC)
Please implement the interface found package tudelft.wis.idm_tasks.basicJDBC.interfaces.
- Start with implementing the JDBCManager.java interface. You need to be able to connect to your PostgreSQL database with the IMDB dataset
- Implement JDBCTask2Interface.java as described in the assignment task PDF.
- Write a short class which calls all methods of your Task2Interface implementation, and prints the retrieved
 result sets of each task to the console. Use that console printout in your Brightspace submission to show that you solved this task .
(i.e., copy-pase the console printout of the result sets in your submission file).

## Task 1.3 (Boardgame Tracker with JDBC / JPA)
In this task, you are going to learn how to implement Java object persistence using JDBC and JPA/Hibernate
(and passively also deal a bit with Maven, GIT, and JUNIT - search teh Web / discuss).
with peers or TAs if you need help with those things 
We ask you to implement the the interfaces found in package tudelft.wis.idm_tasks.boardGameTracker.interfaces.

Here are some tips to do that:

General:
- Have a close look the very basic reference implementation the package 
tudelft.wis.idm_solutions.BoardGameTracker.POJO_Implementation. This implementation 
does not use any databases, but instead stores all data in POJOs (plain old java objects).
-  TEST the reference implementation: Find tudelft.wis.idm_solutions.BoardGameTracker.POJO_Implementation.POJO_Test.java. Execute this class as a JUNIT Test!
(in Netbeans, that is simply done with right-click/test-file.) Try to figure out what is happening when you do that!

JDBC:
- Create a new database for this task. Create the tables you need for solving the task.
- For the JDBC implementation of this task, we are happy enough if you only implement all interfaces to related Player and Boardgame 
(you can ignore PlaySession for now). This includes:
-- Player.java
-- BoardGame.java
-- BgtDataManager.java, all methods which relate to Player and Boardgame (but not those on PLaySession unless you feel like it).

JPA
- You have two choices: re-use the databse you created for the JDBC task and tweak your JPA implementation to use it, 
or create a new empty database (this might be easier).



using either pure JDBC or JPA (refer to additional turorial for this task,s earch if you need to)
- Implement tudelft.wis.idm_solutions.BoardGameTracker.AbstractBGTDemo.java to run / test your implementation.
- See tudelft.wis.idm_solutions.BoardGameTracker.POJO_Implementation.BGT_test_Pojo.java for a reference implementation using POJO (Plain Old Java Objects) -- no database functionality here!!
    - You can develop your own JPA solution in tudelft.wis.idm_solutions.BoardGameTracker.JPA_Implementation. 
- Feel free to expand on BGT_Test_JPA.java. Note how this class uses DuckDB (look it up!). Duck DB is super easy to embed, and will store all data in a local database file ./DB. I used it here because it is dead simple and does not require any server setup.
    - Feel free to use DuckDB, or switch to PosgreSQL. Your choice.
    - Remember that you some need to deal with primary keys. The current interfaces and the POJO implementation do not consider that.

