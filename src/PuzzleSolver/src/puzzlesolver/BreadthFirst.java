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

    private State toProcess[];
    private StateManager manager;
    private int size;
    private State passedStates[];
    private int stepsIn;

    BreadthFirst() {
        size = 1;
        stepsIn = 0;
        toProcess = new State[size];
        passedStates = new State[stepsIn];
    }

    private void growTree() {
        size++;
        State temp[] = toProcess;
        toProcess = new State[size];
        toProcess = Arrays.copyOf(temp, size);
    }

    public boolean run(StateManager init) {
        boolean goalReached = false;
        manager = init;
        toProcess[0] = manager.getStart();
        int oldSize;
        int itterations = 0;
        while (!goalReached) {
            itterations++;
            oldSize = size;
            for (int i = 0; i < size; i++) {
                if (manager.FoundGoal(toProcess[i])) {
                    goalReached = true;
                    display(itterations, toProcess[i]);
                }
            }
            for (int i = 0; i < oldSize; i++) {
                State tempState[] = manager.GetNextStates(toProcess[i]);
                stepInto(toProcess[0]);
                removeFirst();
                //System.out.println(tempState.length);
                for (int j = 0; j < tempState.length; j++) {
                    if (!Visited(tempState[j])) {
                        addState(tempState[j]);
                    }
                }
            }

            if (goalReached == false) {
                System.out.println(itterations);
            }
        }
        return true;
    }

    private void display(int runs, State win) {
        System.out.println("Success after: " + runs);
        win.printCurrentState();

    }

    private void addState(State toAdd) {
        growTree();
        toProcess[size - 1] = toAdd;
    }

    private void removeFirst() {
        if (size > 1) {
            for (int i = 1; i < size - 1; i++) {
                toProcess[i - 1] = toProcess[i];
            }
        } else {
            toProcess = new State[size];
        }
        size--;
    }

    public boolean Visited(State toCheck) {
        if (stepsIn == 0) {
            State tempStates[];
            tempStates = passedStates;
            passedStates = new State[stepsIn + 1];
            State temp = toCheck;
            for (int i = 0; i < stepsIn; i++) {
                if (temp.Compare(passedStates[i]));
                {
                    System.out.println("Visited");
                    return true;
                }
            }
        }
        System.out.println("not Visited");
        return false;
    }

    //progresses the state forward one and stores the previous state on a
    //stack
    public void stepInto(State expanding) {
        stepsIn++;
        State temp[] = passedStates;
        passedStates = new State[size];
        passedStates = Arrays.copyOf(temp, stepsIn);
        passedStates[stepsIn - 1] = expanding;
    }
}
