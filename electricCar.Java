/* electricCar.java
 * Program that determines the route from a map based on the most remaining amouunt power
 * @author Christopher
 * @version 1.2 date:4/5/2021
 */

import java.util.Scanner; //import scanner
import java.io.File; //inport file i/o
import java.io.PrintWriter; //import file write

class electricCar {
  /*Class variables so the method can access it*/
  public static char[][] currentCity;
  public static int battery;
  public static int startingBattery;
  
  /*Method that copies arrays so I don't have to make a for loop everytime I make a copy*/
  public static char[][] copyArray(char[][]array) {
    char[][] newArray = new char[array.length][array[0].length];
    for (int r=0;r<array.length;r++) {
      for (int c=0;c<array[0].length;c++) {
        newArray[r][c] = array[r][c];
      }
    }
    return newArray;
  }
  
  /*Method that uses recursion to find the best path*/
  public static char[][] bestPath(int r, int c, char[][] city, int energy) {
    
    /*Check right*/
    if (c<city[0].length) {//Make sure it's not out of range
      if (city[r][c+1] == ' ') { //Check the next column for space
        char[][] cityCopy = copyArray(city);//copy city or else everything will use the same array
        cityCopy[r][c+1] = '*';//change space into star to mark the route
        int energyCopy = energy;//copy energy or else everything will use the same energy value
        energyCopy-=1;//Each move will take away one battery level
        bestPath(r,c+1,cityCopy,energyCopy); //Call the method with the copied variables so it doesn't use the same map
      } else if (city[r][c+1] == 'H') { //check the next column for space
        char[][] cityCopy = copyArray(city);
        cityCopy[r][c+1] = '*';//turn the H into a star to mark it
        int energyCopy = energy;
        energyCopy-=5;//Each H move will take away 5 battery levels
        bestPath(r,c+1,cityCopy,energyCopy);
      } else if (city[r][c+1] == 'D') { //check if the next column is the destination
        if ((battery == startingBattery) || (energy>battery)) { //check if the route is more efficient than the other, if it's the first route, always will change
          battery = energy;
          currentCity = copyArray(city);
        }
      }
    }
    /*Check down*/
    if (r<city.length) {
      if (city[r+1][c] == ' ') {
        char[][] cityCopy = copyArray(city);
        cityCopy[r+1][c] = '*';
        int energyCopy = energy;
        energyCopy-=1;
        bestPath(r+1,c,cityCopy,energyCopy);
      } else if (city[r+1][c] == 'H') {
        char[][] cityCopy = copyArray(city);
        cityCopy[r+1][c] = '*';
        int energyCopy = energy;
        energyCopy-=5;
        bestPath(r+1,c,cityCopy,energyCopy);
      } else if (city[r+1][c] == 'D') {
        if ((battery == startingBattery) || (energy>battery)) {
          battery = energy;
          currentCity = copyArray(city);
        }
      }
    }
    /*Check up*/
    if (r>0) {
      if (city[r-1][c] == ' ') {
        char[][] cityCopy = copyArray(city);
        cityCopy[r-1][c] = '*';
        int energyCopy=energy;
        energyCopy-=1;
        bestPath(r-1,c,cityCopy,energyCopy);
      } else if (city[r-1][c] == 'H') {
        char[][] cityCopy = copyArray(city);
        cityCopy[r-1][c] = '*';
        int energyCopy = energy;
        energyCopy-=5;
        bestPath(r-1,c,cityCopy,energyCopy);
      } else if (city[r-1][c] == 'D') {
        if ((battery == startingBattery) || (energy>battery)) {
          battery = energy;
          currentCity = copyArray(city);
        }
      }
    }
    /*Check left*/
    if (c>0) {
      if (city[r][c-1] == ' ') {
        char[][] cityCopy = copyArray(city);
        cityCopy[r][c-1] = '*';
        int energyCopy = energy;
        energyCopy -=1;
        bestPath(r,c-1,cityCopy,energyCopy);
      } else if (city[r][c-1] == 'H') {
        char[][] cityCopy = copyArray(city);
        cityCopy[r][c-1] = '*';
        int energyCopy = energy;
        energyCopy-=5;
        bestPath(r,c-1,cityCopy,energyCopy);
      } else if (city[r][c-1] == 'D') {
        if ((battery == startingBattery) || (energy>battery)) {
          battery = energy;
          currentCity = copyArray(city);
        }
      }
    }
    return currentCity;//return once everything is done
  }
  
  public static void main(String[] args) throws Exception {
    
    Scanner input = new Scanner(System.in);//get user input
    System.out.println("What is the name of the city data file?");
    String cityName = input.nextLine();
    input.close();//close input
    
    File cityData = new File(cityName);//Finds the citydata and allows us to access it
    File route = new File("Route.txt");//Create new file called "Route.txt"
    Scanner fileInput = new Scanner(cityData);//Create a scanner and associate it with the city data file
    PrintWriter output = new PrintWriter(route);//Associate PrinterWriter to allow us to output data into the file
    
    int rows = fileInput.nextInt();//Get the number of Rows
    int columns = fileInput.nextInt();//Get the number of Columns
    startingBattery = fileInput.nextInt();//Get the starting battery level
    battery = startingBattery;//This variable will be used as a comparison and changed if the program finds a route with the least battery used
    char[][] city = new char[rows][columns];//Create 2d arrac for the city
    currentCity = new char[rows][columns];//This variable will be used as a comparison and changed if the program finds a route with least battery used
    int startingRowPosition =0;//Set up variables for the starting position of the car
    int startingColumnPosition =0;
    
    fileInput.nextLine();//nextblues
    
    /*Get the citydata file and turn it into a 2d array*/
    for (int r=0;r<rows;r++) {
      String line = fileInput.nextLine();
      for (int c=0;c<columns;c++) {
        city[r][c] = line.charAt(c);
        if (line.charAt(c) == 'S') {
          //Saves the position of S
          startingRowPosition = r;
          startingColumnPosition = c;
        }
      }
    }
    fileInput.close();//close fileInput, don't need to read from the file anymore
    city = bestPath(startingRowPosition,startingColumnPosition,city,battery);//When the recursive method is done, it returns the best path
    
    /*Output Data*/
    System.out.println("Power Remaining: "+battery);
    for (int r=0;r<rows;r++) { //for loop that outputs the route onto a file called route.txt
      for (int c=0;c<columns;c++) {
        output.print(city[r][c]);
      }
      output.print("\n");
    }
    output.close();//close output, nothing left to output
  }
}
