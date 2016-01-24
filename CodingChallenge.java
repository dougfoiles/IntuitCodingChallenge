/* 
 * Doug Foiles
 * 1/23/16
 *
 * Intuit Coding Challenge
 *
 */

import java.util.*;
import java.io.*;
import java.lang.*;

 public class CodingChallenge {
    
     public static void main(String[] args) {
         ArrayList<Integer> list = new ArrayList<Integer>(); 
         int primeNumber;
         ArrayList<String> fullPath;
         
         //set list to 1, 10, 5, 63, 29, 71, 10, 12 ...
         InitializeList(list);
         
         
         //Print list before list is sorted
         System.out.println("Before:");
         for(int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");   
         }
         System.out.println();
         
         //sort list - arraylist is passed by reference 
         Sort(list);
         
         //Print list after it is sorted
         System.out.println("After:");
         for(int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");   
         }
         System.out.println("\n");
         
         //Find primes at given index and return the number
         primeNumber = FindPrime(3);
         System.out.println("The prime at index " + 3 + " is " + primeNumber);
         
         primeNumber = FindPrime(58);
         System.out.println("The prime at index " + 58 + " is " + primeNumber);
         
         primeNumber = FindPrime(10001);
         System.out.println("The prime at index " + 10001 + " is " + primeNumber);
         System.out.println();
         
         //Finding the JSON paths
         try{
            fullPath = GetJSONPath("item6");
            for(int i = 0; i < fullPath.size(); i++) {
                System.out.println("Full path for item6 is : " + fullPath.get(i));           
            }
             
            fullPath = GetJSONPath("subItem1Item3");
            for(int i = 0; i < fullPath.size(); i++) {
                System.out.println("Full path for subItem1Item3 is : " + fullPath.get(i));      
            }
             
            fullPath = GetJSONPath("item1");
            for(int i = 0; i < fullPath.size(); i++) {
                System.out.println("Full path for item1 is : " + fullPath.get(i));
                               
            }
             
            fullPath = GetJSONPath("item4");
            for(int i = 0; i < fullPath.size(); i++) {
                System.out.println("Full path for item4 is : " + fullPath.get(i));
                               
            }
            
            //Bad name case. Will not print anything.
            fullPath = GetJSONPath("wrongitem2");
            for(int i = 0; i < fullPath.size(); i++) {
                System.out.println("Full path for wrongitem2 is : " + fullPath.get(i));
                               
            }
             
         } catch(IOException ex) {
             ex.printStackTrace();
         }
     }
     
     // This method takes in an arraylist of integers and 
     // sorts it in ascending order.
     // 
     // Bubble Sort
     public static void Sort(ArrayList<Integer> list) {
         int temp, size;
         boolean finished = false;
         if(list == null) {
            return;
         }
         
         size = list.size();
         
         while(finished == false) {
             finished = true;
             for(int i = 0; i < size - 1; i++) {
                if(list.get(i) > list.get(i + 1)) {
                    finished = false;
                    temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                }
             }
         }
     }
     
     //This method sets up the list.
     public static void InitializeList(ArrayList<Integer> list) {
         list.add(1);
         list.add(10);
         list.add(5);
         list.add(63);
         list.add(29);
         list.add(71);
         list.add(10);
         list.add(12);
         list.add(44);
         list.add(29);
         list.add(10);
         list.add(-1);
     }
     
     
     // This method will find and print the prime number
     // at the passed in index.
     public static int FindPrime(int index) {
         int primesFound, currentPrime, i;
         
         primesFound = 0;
         i = currentPrime = 1;
         
         //Bad index passed to method.
         if(index < 1) {
            return -1;   
         }
         
         while(primesFound < index) {    
            if(IsPrime(i) == true) {
                currentPrime = i;
                primesFound++;
            }
            i++;
         }
         
         return currentPrime;
     }
     
     //This method takes in a number and checks if it is prime.
     //Will return true if the number is prime and false if it is not.
     public static boolean IsPrime(int num) {
         if(num <= 1) {
            return false;
         }
         
         if(num == 2) {
            return true;
         }
         
         for(int i = 2; i < num; i++) {
            if(num % i == 0) {
                return false;  
            }
         }
         
         return true;
     }
     
     //This method takes in a string containing the name of the element being searched.
     //Prints the full path to that element.
     public static ArrayList<String> GetJSONPath(String element) throws IOException {
        String line, delimiters,temp;
        String[] tokens, tokenParse;
        ArrayList<String> fullPath = new ArrayList<String>();
        ArrayList<String> idList = new ArrayList<String>();
        int itemIndex = 0;
        int fullPathIndex = 0;
        int count;
        boolean containsElement = false;
         
        
        try {
            //create a buffered reader to parse the json file
            FileReader fileReader = new FileReader("ItemList.json");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            //skip first line
            bufferedReader.readLine();
            
            //This while loop fills an arraylist with all of the "id" strings
            while((line = bufferedReader.readLine()) != null) {
                if(line.contains(element)) {
                    containsElement = true;
                }
                   
                if(line.contains("{") && line.contains("}")) {
                    idList.add(line);
                } else {
                    //sub item
                    temp = line;
                    while((line = bufferedReader.readLine()) != null) {
                        if(line.contains(element)) {
                            containsElement = true;
                        }
                        
                        temp = temp + line;
                        
                        if(line.contains("}") && !line.contains("{")) {
                            idList.add(temp);
                            break;
                        }
                    }   
                }            
            }
            
            //Returns an empty array list if the element was not found.
            if(containsElement == false) {
                return fullPath;
            }
            
            //Loops through the arraylist and searches for the element passed in
            for(int i = 0; i < idList.size(); i++) {
                    temp = idList.get(i);
                    
                    if(temp.contains(element)){
                        count = 0;
                        for(int j = 0; j < temp.length() - 1; j++) {
                            if(temp.substring(j, j + 2).compareTo("id") == 0) {
                                count++;
                            }
                        }
                        
                        if(count > 1) {
                            tokens = temp.split("[}]+");
                            for(int j = 0; j < tokens.length; j++) {
                                if(tokens[j].contains(element)) {
                                    fullPath.add("itemList/items[" + i +
                                     "]/subItems[" + j + "]/id");
                                }
                            }
                        } else {
                            fullPath.add("itemList/items[" + i + "]/id");
                        }
                    }
                }   
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
         
        return fullPath;
     }
 }
