/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlesolver;

/**
 *
 * @author kordusj
 */
public class StateManager 
{
    private State goal;
    private State passedStates[];
    private int across;
    private int tall;
    private int stepsIn;
    
    public void goalState(int width, int length)
    {
        tall = length;
        across = width;
        int setGoal[][] = new int [width] [length];
        for(int i = 0; i < length; i++ )
            for(int j = 1; j < width; j++)
            {
                setGoal[i][i + j] =i + j;
            }
        goal = new State(setGoal);
    }
    
    public boolean FoundGoal(State curState)
    {  
        return curState.Compare(goal);
    }
    
    //returns all availble states in an array
    //pos [0] = move empty space down
    //pos [1] = move empty up
    //pos [2] = move empty left
    //pos [3] = move empty right
    //places null in respective slot if state is not valid
    public State[] GetAvailStates(State curState)
    {
        int state[][] = curState.getState();
        int posX = 0, posY = 0;
        for(int i = 0; i < tall; i++ )
            for(int j = 1; j < across; j++)
                if(state[i][i + j] == 0)
                {
                    posX = i + j;
                    posY = i;
                }  
        State availStates[] = new State[4];
        if(tall < posY + 1)
        {
            int temp[][] = goal.getState();
            int holder = temp[posY][posX];
            temp[posY][posX] = temp[posY + 1][posX];
            temp[posY + 1][posX] = holder;
            if(!visited(temp))
                availStates[0] = new State(temp);
            else
                availStates[0] = null;
        } 
        else
            availStates[0] = null;
        if(0 < posY - 1)
        {
            int temp[][] = goal.getState();
            int holder = temp[posY][posX];
            temp[posY][posX] = temp[posY - 1][posX];
            temp[posY - 1][posX] = holder;
            if(!visited(temp))
                availStates[1] = new State(temp);
            else
                availStates[1] = null;
        }
        else
            availStates[1] = null;  
        if(0 < posX - 1)
        {
            int temp[][] = goal.getState();
            int holder = temp[posY][posX];
            temp[posY][posX] = temp[posY][posX - 1];
            temp[posY][posX - 1] = holder;
            if(!visited(temp))
               availStates[2] = new State(temp);
            else
                availStates[2] = null;
        }
        else
            availStates[2] = null;
        if(0 < posX + 1)
        {
            int temp[][] = goal.getState();
            int holder = temp[posY][posX];
            temp[posY][posX] = temp[posY][posX + 1];
            temp[posY][posX + 1] = holder;
            if(!visited(temp))
                availStates[3] = new State(temp);
            else
                availStates[3] = null;
        }
        else
            availStates[3] = null;
        return availStates;
    }
    
    //Checks to see if a state has been visited previously
    private boolean visited(int toCheck[][])
    {
        if(stepsIn == 0)
        {
            State tempStates[];
            tempStates = passedStates;
            passedStates = new State[stepsIn + 1];
            State temp = new State(toCheck);
            for(int i = 0; i < stepsIn; i ++)
            {
                if(temp.Compare(passedStates[i]));
                return true;
            }
        }
        return false;
    }
    
    //progresses the state forward one and stores the previous state on a
    //stack
    public boolean stepInto(State next, State curState)
    {
        if(!(visited(next.getState())))
        { 
            passedStates[stepsIn++] = curState;
            curState = next;
            return true;
        }
        return false;
    }
   
    
 //Needs: 
    //Heuristic Getter
    
    
    
    
}
