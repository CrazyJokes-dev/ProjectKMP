package cosc311.kmp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KMP {
		private static boolean repeat = false;
	    void KMPSearch(String pat, String txt)
	    {
	        int M = pat.length();
	        int N = txt.length();
	        int LPS[] = new int[M];

	        computeLPSArray(pat, M, LPS);

	        int i = 0; // index for txt[]
	        int j = 0; // index for pat[]
	        while (i < N) {
	            if (pat.charAt(j) == txt.charAt(i)) {
	                j++;
	                i++;
	            }
	            if (j == M) {
	                System.out.println("Found the pattern "
	                        + "at index " + (i - j));
	                j = LPS[j - 1];
	            }

	            // mismatch after j matches
	            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
	                // Do not match LPS[0..LPS[j-1]] characters,
	                // they will match anyway
	                if (j != 0)
	                    j = LPS[j - 1];
	                else
	                    i = i + 1;
	            }
	        }
	    }

	    void computeLPSArray(String pat, int M, int LPS[])
	    {
	        // length of the previous longest prefix suffix
	        int len = 0;
	        int i = 1;
	        LPS[0] = 0; // LPS[0] is always 0

	        // the loop calculates LPS[i] for i = 1 to M-1
	        while (i < M) {
	            if (pat.charAt(i) == pat.charAt(len)) {
	                len++;
	                LPS[i] = len;
	                i++;
	            }
	            else // (pat[i] != pat[len])
	            {
	                // This is tricky. Consider the example.
	                // AAACAAAA and i = 7. The idea is similar
	                // to search step.
	                if (len != 0) {
	                    len = LPS[len - 1];

	                    // Also, note that we do not increment
	                    // i here
	                }
	                else // if (len == 0)
	                {
	                    LPS[i] = len;
	                    i++;
	                }
	            }
	        }
	    }
	    
	    
	    
	    // Driver program to test above function
	    public static void main(String args[]) {
	        Scanner input = new Scanner(System.in);
	        
	       
	        String fileName;
	        
	        
	        String pat;
	        
	        do {  fdsfds
	        try {
	        	System.out.print("Please enter the file name: ");
	        	fileName = input.nextLine();
	        	
	        	System.out.print("What pattern are you looking for: ");
	        	pat = input.nextLine();
	        	
	            File myObj = new File(fileName);
	            Scanner myReader = new Scanner(myObj);
	            
	            String txt = "";
	            while (myReader.hasNextLine()) {
	              txt += myReader.nextLine();
	            }
	            
	            
	            System.out.println("\nText and pattern 1: " + txt + " : " + pat);
		        new KMP().KMPSearch(pat, txt);
	            
	            myReader.close();
	         } catch (FileNotFoundException e) {
	            System.out.println("File not found. Please enter the correct file");
	            e.printStackTrace();
	         }  
	        
	        System.out.print("Would you like to continue: ");
	        if(input.next().equalsIgnoreCase("yes"))
	        	repeat = true;
	        else
	        	repeat = false;
	        
	        }while(repeat == true);
	    	
	    	
//	    	String txt = "XXYXXXY";
//	        String pat = "XXX";
//	        String txt2 = "ACDOQVWRQVWSTUV";
//	        String pat2 = "QVWS";
//
//	        System.out.println("Text and pattern 1: " + txt + " : " + pat);
//	        new KMP().KMPSearch(pat, txt);
//
//	        System.out.println("Text and pattern 2: " + txt2 + " : " + pat2);
//	        new KMP().KMPSearch(pat2,txt2);
	    }
	}
