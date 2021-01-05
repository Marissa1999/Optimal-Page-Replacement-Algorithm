import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * Programmer: Marissa Gonçalves
 * Due Date: October 11, 2018
 */

public class FIFOPageReplacementAlgorithm 
{
   
    //Initialize the needed variables for the algorithm
    private static int i, j, nof, nor;
    private static int flag = 0;
    private static int pf = 0;
    private static int victim = -1;
    private static final int[] ref = new int[50];
    private static final int[] frm = new int[50];
   
    
    
     /**
     * Runs the FIFO Page Replacement Algorithm
     * @param args The classic name defining a String array in the main class.
     * @throws IOException Throws an exception if the input is not valid.
     */
    public static void main(String[] args) throws IOException
    {
       
       //Create a new scanner object to read the following inputs
       Scanner keyboard = new Scanner(System.in);
         
       
       //Display the title of the FIFO algorithm
       System.out.println("FIFO PAGE REPLACEMENT ALGORITHM");
       
       
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
 
       
       //Initialize all the frame values to -1
       for (i = 1; i <= nof; i++)
       {
           frm[i]= -1;
       }
 
       
       //For every reference character in the string, the following FIFO algorithm takes place
       for (i = 0; i < nor; i++)
       {
           //Initialize the flag value to 0
           flag = 0;
           
           //Print each reference character's pages
           System.out.printf("\n\t Reference NO.%d -> \t", ref[i]);
           
           
           //For every frame, the following steps are shown 
           for (j = 0; j < nof; j++)
           {
               //If the frame array value is equal to the reference character value, proceed with the following code
               if (frm[j] == ref[i])
               {
                     //Set the flag variable to 1
                     flag = 1;
                     
                     //Break out of the for-loop and proceed with the program
                     break;
               }
           }
           
             //If the flag is still equal to 0, proceed with the following procedure
             if (flag == 0)
             {
                 
                //Increment the page fault by 1
                pf++;
                
                //Increment the victim by 1
                victim++;
                
                //The victim is calculated as the remainder of its value and the number of frames
                victim %= nof;
                
                 //For every frame value that is the victim, make it equal to the reference character value
                frm[victim] = ref[i];
         
                
                //For each reference character, display the calculated values for the set of pages
                for (j = 0; j < nof; j++)
                {
                   System.out.printf("%4d",frm[j]);
                }
                
             }
       }
       
       
       //Print the number of page faults that have been found in the FIFO algorithm
       System.out.printf("\n \n No. of pages faults...%d \n", pf);
   
       
       
    }
}

