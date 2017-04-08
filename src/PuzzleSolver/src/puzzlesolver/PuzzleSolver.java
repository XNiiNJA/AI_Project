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
//   1 2 3
//   4 5 6
//   7 9 8
/**
 *
 * @author Grant
 */
public class PuzzleSolver {

    private static BreadthFirst breadth;
    private static AStarSearch astar;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        breadth = new BreadthFirst();
        astar = new AStarSearch();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to this program");
        System.out.println("Pardon the Dust, we are still working on it");
        int start[][] = new int[3][3];
        //=================
        start[0][0] = 4;
        start[0][1] = 1;
        start[0][2] = 7;
        //=================
        start[1][0] = 9;
        start[1][1] = 6;
        start[1][2] = 5;
        //=================
        start[2][0] = 2;
        start[2][1] = 3;
        start[2][2] = 8;
        //=================

        State init = new State(start);
        StateManager manage = new StateManager(init);
        manage.goalState(3, 3);
        String input = "";
        while (!input.equals("Exit")) {

            System.out.println("Initial State is:");
            init.printCurrentState();
            System.out.println("Which method would you like to use?");
            System.out.println("Enter: Breadth | A* | Beam | Exit ");
            try {
                input = in.readLine();

                if (input.equals("Breadth")) {
                    breadth.run(manage);
                } else if (input.equals("A*")) {
                    astar.run(manage);
                } else if (input.equals("Beam")) {
                    continue;
                }


            } catch (IOException ex) {

            }
        }

    }

}
