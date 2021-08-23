//Marcello Sautto
//Project 3

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Project3 {

    Scanner sc;
    int numTests;
    int currentArraySize;
    int[] currentArray;
    int[] minimumJumps;

    Project3(){
        try {
            sc = new Scanner(new File("input.txt"));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error: Input File Not Found");
            System.exit(1);
        }
        numTests = sc.nextInt();
    }

    public static void main(String[] args){
        Project3 p3 = new Project3();

        //run test cases
        for(int i = 0; i < p3.numTests; i++)
        {
            p3.readCurrentTestData();
            p3.findMinimumJumps();
        }

    }

    //read data from text file
    private void readCurrentTestData(){
        currentArraySize = sc.nextInt();
        currentArray = new int[currentArraySize];
        for(int i = 0; i < currentArraySize; i++)
            currentArray[i] = sc.nextInt();

        minimumJumps = new int[currentArraySize];

        for(int i = 0; i < currentArraySize; i++)
            minimumJumps[i] = 0;

    }

    private void findMinimumJumps() {

        int rangeFromCurrentIndex = 0;

        //base case
        minimumJumps[0] = 0;

        for (int dest = 1; dest < currentArraySize; dest++) {

            for (int start = 0; start < dest; start++) {
                rangeFromCurrentIndex = currentArray[start] + start;

                //if we can reach the destination, add a distance
                //If we haven't jumped to this destination yet, set its minimum jump distance to 1 + the minimumJumps from the current start cell
                // otherwise, set it to the minimum of 1 + minimumJumps from the current start cell and minimumJumps from the destination cell
                if (rangeFromCurrentIndex >= dest) {
                    if (minimumJumps[dest] == 0)
                        minimumJumps[dest] = 1 + minimumJumps[start];
                    else
                        minimumJumps[dest] = Math.min(1 + minimumJumps[start], minimumJumps[dest]);
                }
            }
        }
        System.out.println(minimumJumps[currentArraySize-1]);
    }
}
