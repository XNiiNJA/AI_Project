/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlesolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Initial State
//   2 8 6
//   3 5 x
//   1 4 7
/**
 *
 * @author Grant
 */
public class PuzzleSolver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to this program");
        System.out.println("Pardon the Dust, we are still working on it");
        int start[][] = new int[3][3];
        //=================
        start[0][0] = 2;
        start[0][1] = 8;
        start[0][2] = 6;
        //=================
        start[1][0] = 3;
        start[1][1] = 5;
        start[1][2] = 0;
        //=================
        start[2][0] = 1;
        start[2][1] = 4;
        start[2][2] = 7;
        //=================
        State init = new State(start);
        String input = "";
        while (!input.equals("Exit")) {

            System.out.println("Initial State is:");

            System.out.println("|2 8 6|");
            System.out.println("|3 5 0|");
            System.out.println("|1 4 7|");
            System.out.println("Which method would you like to use?");
            System.out.println("Enter: Breadth | A* | Beam | Exit ");
            try {
                input = in.readLine();
                switch (input) {
                    case "Breadth":
                        //your frunction call here
                        break;
                    case "A*":
                        //your function call here
                        break;
                    case "Beam":
                        //your function call
                        break;
                    default:
                        System.out.println("Not an option.");
                }
            } catch (IOException ex) {

            }
        }

    }

}
