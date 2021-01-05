import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Programmer: Marissa Gonçalves
 * Due Date: October 11, 2018
 */

public class OptimalPageReplacementAlgorithm
{
    
    //Initialize the needed variables for the algorithm
    private static int i, j, nof, nor;
    private static int flag = 0;
    private static int pf = 0;
    private static int victim = -1;
    private static int count = 0;
    private static final int[] ref = new int[50];
    private static final int[] recent = new int[10];
    private static final int[] optcal = new int[50];
    private static final int[] frm = new int[50];
  
    
     /**
     * Runs the Optimal Page Replacement Algorithm
     * @param args The classic name defining a String array in the main class.
     * @throws IOException Throws an exception if the input is not valid.
     */
    public static void main(String[] args) throws IOException
    {
       
       //Create a new scanner object to read the following inputs
       Scanner keyboard = new Scanner(System.in);
         
       
        //Display the title of the Optimal Page algorithm
       System.out.println("OPTIMAL PAGE REPLACEMENT ALGORITHM");
       
       
       //Print statement telling user to input the number of frames (thus nof means "number of frames")
       System.out.printf("\n Enter no. of frames.... " );
       
       //Scanner object reads "number of frames" input
       nof = keyboard.nextInt();
       
       
       
       //Print statement telling user to input the number of characters in the reference string (thus nor means "number of reference characters")
       System.out.printf("\n Enter no. of reference string.... ");
       
       //Scanner object reads "number of reference characters" input
       nor = keyboard.nextInt();

       
       //Print statement telling user to input the reference string based on the number of reference characters from previous input
       System.out.printf("\n Enter the reference string.... ");
       
       
       //Create a buffered reader to read the reference string input
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       String reference = br.readLine();
       
       
       //The reference string is placed into an array of characters and being able to read each value separated by spaces
       String[] referenceArray = reference.trim().split("\\s+");
       
       
       //Based on the number of chracters in the reference string input, the input is converted into a series of integers
       for (i = 0; i < referenceArray.length; i++)
       {
           ref[i] = Integer.parseInt(referenceArray[i]);
       }
       
       
       //Print statement to mention the inputted reference string
       System.out.printf("\n The given reference string:");
       System.out.printf("\n............................");
       
       
       //For every character in the reference string, it is reprinted before the algorithm begins
       for (i = 0; i < nor; i++)
       {
           System.out.printf("%4d", ref[i]);
       }
 
       
       
       //Initialize all the frame values to -1 and the optimal array variables to 0
       for(i = 0; i < nof; i++)
       {
           frm[i] = -1;
           optcal[i] = 0;
       }
       
       
       //Initialize all 10 recent array values to 0
       for(i = 0; i < 10; i++)
       {    
           recent[i] = 0;  
       }
           
       
           //For every reference character in the string, the following Optimal Page algorithm takes place
           for(i = 0; i < nor; i++)
           {
               
                //Initialize the flag value to 0
                flag = 0;
                
                //Print each reference character's pages
                System.out.printf("\n\t Reference NO.%d -> \t", ref[i]);
                
                
                //For every frame, the following steps are shown 
                for(j = 0; j < nof; j++)
                {
                    //If the frame array value is equal to the reference character value, set the flag to 1 and break out of loop
                    if(frm[j] == ref[i])
                    {
                        flag = 1;
                        break;
                    }
                }
                
                //If the flag is still equal to 0, proceed with the following procedure
                if(flag == 0)
                {
                    
                    //Increment the count value by 1
                    count++;
                    
                    //If the count value is less than or equal to the number of frames, increment the victim value by 1
                    if(count <= nof)
                    {
                        victim++;
                    }
                    
                    //Else the victim value is equal to the returned value of the optVictim(int index) method, based on the implemented parameter
                    else
                    {
                        victim = optVictim(i);
                    }
                    
                    //Increment the page fault by 1
                    pf++;
                    
                    //For every frame value that is the victim, make it equal to the reference character value
                    frm[victim] = ref[i];
                    
                    //For each reference character, display the calculated values based on the inputted number of pages
                    for(j = 0; j < nof; j++)
                    {
                        System.out.printf("%4d", frm[j]);
                    }
                }   
           }
           
            //Print the number of page faults that have been found in the Optimal Page algorithm
            System.out.printf("\n\n No. of pages faults...%d \n", pf);
            
       }

    

    //Method to determine the value that is not used for the longest time
    private static int optVictim(int index) 
    {
      
       //Initialize the variables needed in the method
       int i, j, temp, notfound;
       
       
       //For every value counting up to the number of pages, the following procedure is demonstrated
       for (i = 0; i < nof; i++)
       {
           
           //Initialize the notfound variable to 1
           notfound = 1;
           
           
           //For every reference character where the position starts out with the implemented index (which the for-loop incremented variable)
           for(j = index; j < nor; j++)
           {
               //If the frame array value is equal to the reference character value, the following steps are followed
               if(frm[i] == ref[j])
               {
                   //Initialize the notfound variable to 0
                   notfound = 0;
                   
                   //Make the optimal page array value equal to the incremented element in the for-loop
                   optcal[i] = j;
                   
                   //Break out of the for-loop and proceed with the program
                   break;
                   
               }
               
           }
           
           
               //If the notfound variable is equal to 1, return the element index from the previous for-loop variable
               if (notfound == 1)
               {
                   return i;
               }
           
           
        }
           
       
       
           //Set the stored value (temp) to the first element in the optimal page algorithm
           temp = optcal[0];
           
           
           //For every value (starting from 1) counting up to the number of frames, the following code is demonstrated
           for(i = 1; i < nof; i++)
           {
               //If the stored value (temp) is less than the optimal value from the array, make both values equal to one another
               if(temp < optcal[i])
               {
                   temp = optcal[i];
               }
           }
           
           
           //For every page frame, continue with the following steps
           for(i = 0; i < nof; i++)
           {
                //If the frame array containing the stored value (temp) is equal to the page frame indexed value, return the incrementing variable
                if(frm[temp] == frm[i])
                {
                    return i;
                }
                
           }
           
           //If no value matches the previous if statements, return the integer 0 
           return 0;
       
    }
     
}
    
   