/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlesolver;

import java.util.*;

/**
 *
 * @author kordusj
 */
public class BreadthFirst extends SearchMethod {

    private List<State> states;
    private List<State> passedStates;
    private List<State> removedStates;
    private List<State> win;
    private int stepsIn;
    private int first = 0;

    BreadthFirst() {
        stepsIn = 0;
        states = new ArrayList<>();
        passedStates = new ArrayList<>();
        removedStates = new ArrayList<>();
    }
    
    public List<State> getWin()
    {
        return win;
    }


    @Override
    public boolean run(StateManager init) {
        init.getStart().initPath("A");
        states.add(init.getStart());
        boolean goal = false;
        while (!goal) {
            if(states.isEmpty())
                return false;
            State temp[] = init.GetNextStates(states.get(first));
            String path = states.get(first).getPath();
            removedStates.add(states.get(first));
            init.addToClosedSet(states.remove(first));
            for (int i = 0; i < temp.length; i++) {
                if (!init.isInClosedSet(temp[i])) {
                    states.add(temp[i]);
                    temp[i].initPath(path);
                    temp[i].appendPath(Integer.toString(i));
                    init.addToClosedSet(temp[i]);
                }
            }
            if (states.contains(init.getGoal())) {
                goal = true;
            }
            stepsIn++;

        }
        int index = states.indexOf(init.getGoal());
        display(states.get(index));
        calculatePath(index);
        return true;
    }

    private void display(State win) {
        System.out.println("Success after: " + stepsIn);
        win.printCurrentState();
    }

    public void calculatePath(int index) 
    {
        win = new ArrayList<>();
        String winningPath = states.get(index).getPath();
        int endIndex = 3;
        System.out.println("Calculate win");
        System.out.println("Win path: " + winningPath);
        System.out.println("======================================");
        while (endIndex <= winningPath.length()) 
        {
            for(int i = 0; i < removedStates.size(); i++)
            {
                if(removedStates.get(i).getPath().equals(
                        winningPath.substring(0, endIndex)))
                {
                    win.add(removedStates.get(i));
                    break;
                }
            }
            endIndex += 2;
        }
        win.add(states.get(index));
        System.out.println("Path found:");
        for(int i = 0; i < win.size(); i++)
        {
            System.out.println(win.get(i).getPath());
        }
        System.out.println("End Algorithm");

    }
}
