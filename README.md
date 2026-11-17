# OOP Lab (SY AI&DS) - Java Experiments 1-10

Works fully offline. Needs only JDK 17 or newer (check: `javac -version`).

## Run an experiment (Windows Command Prompt)
    cd oop-lab
    run.bat 1        (Experiment 1)
    run.bat 2        (Experiment 2)   ... up to run.bat 10

Linux/Mac: `./run.sh 1`

Compiled files go into the `out` folder (safe to delete).

## Experiments
| Exp | Folder | Topic |
|-----|--------|-------|
| 01 | Exp01_Calculator | Calculator with exception handling |
| 02 | Exp02_Employee | Constructor overloading (Employee) |
| 03 | Exp03_Matrix | Matrix operations with 2D arrays |
| 04 | Exp04_Product | Inheritance: Product / Electronics / Clothing |
| 05 | Exp05_Shape | Method overriding: Shape / Circle / Rectangle / Triangle |
| 06 | Exp06_Interfaces | Multiple inheritance via interfaces (Academic, Sports) |
| 07 | Exp07_ATM | ATM with try / catch / finally |
| 08 | Exp08_ChatThreads | Multithreaded chat (priority, isAlive, join, pause/resume/stop) |
| 09 | Exp09_Collections | Employee Directory: ArrayList, HashSet, HashMap, Iterator |
| 10 | Exp10_JDBC | JDBC CRUD with MySQL (PreparedStatement, commit/rollback) |

## Experiment 10 setup (one-time)
1. Install MySQL Server (and MySQL Workbench if you like).
2. Download MySQL Connector/J (platform independent .zip) and copy
   `mysql-connector-j-x.x.x.jar` into the `lib` folder.
3. Create the database: open MySQL command line and run
   `source C:\path\to\oop-lab\Exp10_JDBC\schema.sql`
   (or paste the contents of schema.sql into Workbench).
4. In `Exp10_JDBC\Main.java` set PASSWORD to your MySQL root password
   (the manual uses "root").
5. Make sure the MySQL service is running, then: `run.bat 10`
