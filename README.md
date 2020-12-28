[![Work in Repl.it](https://classroom.github.com/assets/work-in-replit-14baed9a392b3a25080506f3b7b6d57f295ec2978f6f33ec97e36a161684cbe9.svg)](https://classroom.github.com/online_ide?assignment_repo_id=3854860&assignment_repo_type=AssignmentRepo)
# ParkingLot Lab
1.	Create a class called ```ParkingLot``` with instance variable ```int numCars``` to represent the number of car's in the parking lot.
2.	Add a public integer constant ```MAX_CARS``` and set it to 2500.
3.	Add a default constructor that sets the starting number of cars to 0.
4.	Add another constructor that sets the starting number of cars through a parameter.
5.	Add accessor ```getNumCars()``` and mutator ```setNumCars (int theNumCars)```.  
6.	Add a ```toString()``` method that returns "Number of cars is 10" (for example, if the lot had 10 cars in it).
7.	Add method ```boolean equals(Object other)``` to return true if this parking lot has the same number of cars as the other parking lot. So that you can access the other object’s ```numCars```, the first line should look like this:  
        
                ParkingLot p = (ParkingLot)other;
8.	Write a test class called ```LFHS``` with a method ```ParkingLot fillLot()``` that creates a ```ParkingLot``` object and sets its number of cars to 400 and returns the ```ParkingLot``` object.

