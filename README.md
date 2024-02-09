# CSE1505_LabsStudent

This repository contains skeleoton code for the CSE1505 Assignments.
You can check it out and open it as a Java Maven project in your favorite IDE (I use
Netbeans for that, but of course also Eclipse, IntelliJ or VisualStudio are nice choices).

Refer to the manual of your IDE on how to clone a GitHub Repository.

## Task 1.2 (SQL Queries using JDBC)
Please implement the interface found package tudelft.wis.idm_tasks.basicJDBC.interfaces

## Task 1.3 (Boardgame Tracker with JDBC / JPA)
-- Implement the interfaces found in tudelft.wis.idm_tasks.boardGameTracker.interfaces using either pure JDBC or JPA (refer to additional turorial for this task,s earch if you need to)
-- Implement tudelft.wis.idm_solutions.BoardGameTracker.AbstractBGTDemo.java to run / test your implementation.
--- See tudelft.wis.idm_solutions.BoardGameTracker.POJO_Implementation.BGT_test_Pojo.java for a reference implementation using POJO (Plain Old Java Objects) -- no database functionality here!!
-- You can develop your own JPA solution in tudelft.wis.idm_solutions.BoardGameTracker.JPA_Implementation. 
--- Feel free to expand on BGT_Test_JPA.java. Note how this class uses DuckDB (look it up!). Duck DB is super easy to embed, and will store all data in a local database file ./DB. I used it here because it is dead simple and does not require any server setup.
Feel free to use DuckDB, or switch to PosgreSQL. Your choice.

