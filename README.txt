Doug Foiles
1/23/16
Intuit Coding Challenge

Problem #1
I wrote method Sort that uses bubble sort to arrange the list of numbers in ascending 
order. An arraylist was passed by reference and was modified inside the method. 
I assumed you wanted me to write a sort method instead of using the built in
arraylist sort.

Problem #2
I wrote method FindPrime to determine the prime number at the index passed in. I looped 
through numbers starting at 1 and checked to see if they were prime. I counted the 
amount of prime numbers found and stopped at the index passed to the method.

Problem #3
I wrote method GetJSONPath that read in the test json from a file. The json file has to be in
the same directory as the executable. It parses the file and stores each individual "id" string 
into an arraylist. I then looped through the arraylist and checked if an individual "id" string 
contained the element passed to the method. If the element was a subitem extra parsing was done.
I assumed I was not supposed to use existing json libraries so I parsed the json myself. Optimized 
with knowing there is only one level of sub items. 

Compiling (commandline)
javac CodingChallenge.java

Running executable after compiling (commandline)
java CodingChallenge
