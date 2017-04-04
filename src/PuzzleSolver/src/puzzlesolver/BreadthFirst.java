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
    private StateManager manager;
    private int size;
    private int stepsIn;
    private int first = 0;

    BreadthFirst() {
        size = 1;
        stepsIn = 0;
        states = new ArrayList<State>();
        passedStates = new ArrayList<State>();
    }

    @Override
    public boolean run(StateManager init) 
    {
        states.add(init.getStart());
        float x = 0;
        boolean goal = false;
        while(!goal)
        {
            State temp[] = init.GetNextStates(states.get(first));
            passedStates.add(states.remove(first));
            for(int i = 0; i < temp.length; i++)
            {
                if(!passedStates.contains(temp[i]))
                {
                    states.add(temp[i]);
                    passedStates.add(temp[i]);
                }
                else
                {
                    System.out.println((float)(passedStates.size()/ (float) 181000));
                }
            }
            if(states.contains(init.getGoal()))
            {
                goal = true;
            }
            stepsIn++;
                
        }
        int index = states.indexOf(init.getGoal());
        display(states.get(index));
        return true;
    }

    private void display( State win) 
    {
        System.out.println("Success after: " + stepsIn);
        win.printCurrentState();
    }
}
