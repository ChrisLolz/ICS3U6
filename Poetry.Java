/** Poetry.Java
 * Program that determines the syllalbes of a text and predicts the type of poem
 * @author Christopher
 * @version 1.4
 */

/* import statements */
import java.util.Scanner; //Get input

class Poetry {
  public static void main(String[] args) {
    
    Scanner input = new Scanner(System.in); // create a scanner called input
    int numOfLines =0; //count the number of lines in poem
    int totalSyllables =0; //total number of syllables
    String line=""; //used to get each line of the poem
    String pattern="";//Used for pattern of poem
    String prediction="";//type of poem
    
    System.out.println("Enter the poem. Type 'quit' to end");
    line = input.nextLine(); //get first line of poem
    
    /*count syllalbes in each line*/
    while (!line.equals("quit")) {
      
      int numOfSyllables = 0;//reset the number of syllables for each line
      int numOfWords=1;//counts number of words each line
      line = line.toLowerCase(); //convert to lower case to make if statements easier
      numOfLines += 1; //add one line every loop
      
      /*count number of words*/
      for (int i=0;i<line.length();i++) {
        if (line.substring(i,i+1).equals(" ")) {
          numOfWords +=1; //Number of words is the number of spaces plus one
        }
      }
      
      /*count syllables of each word*/
      for (int i=0;i<numOfWords;i++) {
        int endOfWordIndex = line.indexOf(" ")-1;//need to know where the word ends in the line by getting the index of space
        if (endOfWordIndex <0) {
          endOfWordIndex = line.length()-1;//if there's only one word, the end will be the length of line
        }

        for (int j=0;j<endOfWordIndex+1;j++) { //for loop for syllalbes of each word
          
          if (endOfWordIndex <= 2) {
            numOfSyllables++;//if 3 or under letters, it will always be 1 syllable
            break;
          }
          
          if ( (line.substring(j,j+1).equals("a")) 
            ||(line.substring(j,j+1).equals("e")) 
            ||(line.substring(j,j+1).equals("i"))
            ||(line.substring(j,j+1).equals("o"))
            ||(line.substring(j,j+1).equals("u"))
            ||(line.substring(j,j+1).equals("y")) ) {
            numOfSyllables +=1; //add for each vowel and y
          }
          
          if ((line.substring(j,j+1).equals("e")) && (j == endOfWordIndex)) {
            numOfSyllables -=1; //substract at end
          }
          
          if (j+1<=endOfWordIndex) { //check for 2 letter long patterns like "ee", make sure it's not out of range
            if ( (line.substring(j,j+2).equals("au")) 
              ||(line.substring(j,j+2).equals("ai"))
              ||(line.substring(j,j+2).equals("aa"))
              ||(line.substring(j,j+2).equals("ae"))
              ||(line.substring(j,j+2).equals("ay"))
              ||(line.substring(j,j+2).equals("ea"))
              ||( (line.substring(j,j+2).equals("ee")) && (endOfWordIndex-1 != j) ) //not end with ee
              ||(line.substring(j,j+2).equals("ei"))
              ||(line.substring(j,j+2).equals("ey"))
              ||( (line.substring(j,j+2).equals("es")) && (endOfWordIndex-1 == j) ) //end with "es"
              ||(line.substring(j,j+2).equals("ii"))
              ||(line.substring(j,j+2).equals("ie"))
              ||(line.substring(j,j+2).equals("oo"))
              ||(line.substring(j,j+2).equals("oy"))
              ||(line.substring(j,j+2).equals("oa"))
              ||(line.substring(j,j+2).equals("oi"))
              ||(line.substring(j,j+2).equals("oe"))
              ||(line.substring(j,j+2).equals("ou"))
              ||(line.substring(j,j+2).equals("uu"))
              ||(line.substring(j,j+2).equals("ue"))
              ||(line.substring(j,j+2).equals("ua"))
              ||(line.substring(j,j+2).equals("ui"))
              ||(line.substring(j,j+2).equals("uy"))
              ||(line.substring(j,j+2).equals("yy"))
              ||(line.substring(j,j+2).equals("e."))
              ||(line.substring(j,j+2).equals("e!"))
              ||(line.substring(j,j+2).equals("e?"))
              ||(line.substring(j,j+2).equals("e,")) ){
              numOfSyllables -=1; //remove one syllable if one of these show up in the word
            } else if (line.substring(j,j+2).equals("ed")) {
              if ( (!( (line.substring(j-1,j+2).equals("ted")) || (line.substring(j-1,j+2).equals("ded")) ) ) && (j+1==endOfWordIndex)) {
                numOfSyllables -=1; //remove one syllable if not ted or ded
              } else if ( (!( (line.substring(j-1,j+2).equals("ted")) || (line.substring(j-1,j+2).equals("ded")) ) ) && (j+2==endOfWordIndex)) {
                if ((line.substring(j+2,j+3).equals(".")) || (line.substring(j+2,j+3).equals(",")) || (line.substring(j+2,j+3).equals("!")) || (line.substring(j+2,j+3).equals("?")) ) {
                  numOfSyllables--; //remove one syllable if it's just puncuation at the end
                }
              }
            } else if ( (line.substring(j,j+2).equals("le")) && (j+1 == endOfWordIndex) ) {
              numOfSyllables +=1; //add if "le" at the end
            }
          }
          
          if (j+2<=endOfWordIndex) { //check for 3 letter long patterns like "iou", make sure it's not out of range
            if ( ( (line.substring(j,j+3).equals("ale")) && (j+2 == endOfWordIndex) ) //only at the end
              ||( (line.substring(j,j+3).equals("ele")) && (j+2 == endOfWordIndex) )
              ||( (line.substring(j,j+3).equals("ile")) && (j+2 == endOfWordIndex) )
              ||( (line.substring(j,j+3).equals("ole")) && (j+2 == endOfWordIndex) )
              ||( (line.substring(j,j+3).equals("ule")) && (j+2 == endOfWordIndex) )
              ||( (line.substring(j,j+3).equals("yle")) && (j+2 == endOfWordIndex) ) 
              ||( (line.substring(j,j+3).equals("es.")) && (j+2 == endOfWordIndex) )
              ||( (line.substring(j,j+3).equals("es!")) && (j+2 == endOfWordIndex) )
              ||( (line.substring(j,j+3).equals("es,")) && (j+2 == endOfWordIndex) )
              ||( (line.substring(j,j+3).equals("es?")) && (j+2 == endOfWordIndex) )
              ||( (line.substring(j,j+3).equals("you")) && (j==0) ) //only applies to words that start with "you"
              ||( (line.substring(j,j+3).equals("eve")) && (j==0) ) ) { //starts with "eve"
              numOfSyllables -=1; //remove one syllable if it's one of these patterns
            } else if ( ( (line.substring(j,j+3).equals("ees")) && (j+2 == endOfWordIndex) ) 
                     ||( (line.substring(j,j+3).equals("les")) && (j+2 == endOfWordIndex) )
                     ||( (line.substring(j,j+3).equals("le?")) && (j+2 == endOfWordIndex) )
                     ||( (line.substring(j,j+3).equals("le.")) && (j+2 == endOfWordIndex) )
                     ||( (line.substring(j,j+3).equals("le,")) && (j+2 == endOfWordIndex) )
                     ||( (line.substring(j,j+3).equals("le!")) && (j+2 == endOfWordIndex) )
                     ||( (line.substring(j,j+3).equals("ee.")) && (j+2 == endOfWordIndex) )
                     ||( (line.substring(j,j+3).equals("ee,")) && (j+2 == endOfWordIndex) )
                     ||( (line.substring(j,j+3).equals("ee!")) && (j+2 == endOfWordIndex) )
                     ||( (line.substring(j,j+3).equals("ee?")) && (j+2 == endOfWordIndex) )
                     ||(line.substring(j,j+3).equals("tua")) ) {
              numOfSyllables +=1; //remove one syllable if you see these patterns
            }
          }
          
          if (j+3<=endOfWordIndex) { //check for 4 letter long patterns like "tion"
            if ( (line.substring(j,j+4).equals("tion") )
             ||(line.substring(j,j+4).equals("iage") )
             ||(line.substring(j,j+4).equals("ciou") )
             ||(line.substring(j,j+4).equals("tian")) ){
              numOfSyllables -=1;
            } else if ( (line.substring(j,j+4).equals("tuit"))
                     ||(line.substring(j,j+4).equals("tled"))
                     ||( (line.substring(j,j+4).equals("ees!")) && (j+3 == endOfWordIndex) )
                     ||( (line.substring(j,j+4).equals("ees?")) && (j+3 == endOfWordIndex) )
                     ||( (line.substring(j,j+4).equals("ees.")) && (j+3 == endOfWordIndex) )
                     ||( (line.substring(j,j+4).equals("ees,")) && (j+3 == endOfWordIndex) )
                     ||( (line.substring(j,j+4).equals("les,")) && (j+3 == endOfWordIndex) )
                     ||( (line.substring(j,j+4).equals("les.")) && (j+3 == endOfWordIndex) )
                     ||( (line.substring(j,j+4).equals("les!")) && (j+3 == endOfWordIndex) ) 
                     ||( (line.substring(j,j+4).equals("les?")) && (j+3 == endOfWordIndex) ) ) {
              numOfSyllables +=1; //add syllable if you see these patterns
            }
          }
        }
        if (i+1 != numOfWords) {
          line = line.substring(endOfWordIndex+2);//cuts out the checked word except the last one in line
        }
      }
      pattern += numOfSyllables+" ";//pattern for each line
      totalSyllables += numOfSyllables;//add the number of syllables in the line to the total
      line = input.nextLine(); //get each line of poem, I get the input here because it would count 'quit' as part of the poem
    }
    input.close();//close scanner, don't need input anymore
    

    /*determine the type of poem*/
    if ((numOfLines == 3) && (pattern.equals("5 7 5 "))) {
      prediction = "Haiku";
    } else if ((numOfLines == 5) && (pattern.equals("5 7 5 7 7 "))) {
      prediction = "Tanka";
    } else if ((numOfLines == 5) && (pattern.equals("2 4 6 8 2 "))) {
      prediction = "Cinquain";
    } else if ((numOfLines == 9) && (pattern.equals("9 8 7 6 5 4 3 2 1 "))) {
      prediction = "Nonet";
    } else if ((numOfLines == 3) && (pattern.length()==9)) {
      //pattern must be 9 characters long since its 3 two digits with 3 spaces
      if (((Integer.parseInt(pattern.substring(0,2))>=14) && ((Integer.parseInt(pattern.substring(0,2))<=16))) 
        &&((Integer.parseInt(pattern.substring(3,5))>=14) && (Integer.parseInt(pattern.substring(3,5))<=16))
        &&((Integer.parseInt(pattern.substring(6,8))>=14) && (Integer.parseInt(pattern.substring(6,8))<=16))) {
        prediction = "Sijo";
      }    
    } else {
      prediction = "Unknown"; //if none of the poems above, it's unknown
    }
    
    /*Output*/
    System.out.println("There are/is "+numOfLines+" line(s) in this poem");
    System.out.println("There are/is "+totalSyllables+" syllable(s)");
    System.out.println("The pattern is "+pattern);
    System.out.println("Prediction: "+prediction);
  }
}
