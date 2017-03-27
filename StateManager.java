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
    private State curState;
    private int across;
    private int tall;
    
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
    
    public boolean isGoal()
    {  
        return curState.Compare(goal);
    }
    
    public State[] GetAvailStates()
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
            availStates[0] = new State(temp);
        } 
        else
            availStates[0] = null;
        if(0 < posY - 1)
        {
            int temp[][] = goal.getState();
            int holder = temp[posY][posX];
            temp[posY][posX] = temp[posY - 1][posX];
            temp[posY - 1][posX] = holder;
            availStates[1] = new State(temp);
        }
        else
            availStates[1] = null;  
        if(0 < posX - 1)
        {
            int temp[][] = goal.getState();
            int holder = temp[posY][posX];
            temp[posY][posX] = temp[posY][posX - 1];
            temp[posY][posX - 1] = holder;
            availStates[1] = new State(temp);
        }
        else
            availStates[2] = null;
        if(0 < posX + 1)
        {
            int temp[][] = goal.getState();
            int holder = temp[posY][posX];
            temp[posY][posX] = temp[posY][posX + 1];
            temp[posY][posX + 1] = holder;
            availStates[1] = new State(temp);
        }
        else
            availStates[3] = null;
        return availStates;
    }
    
   
    
 //Needs: 
    //Heuristic Getter
    
    
    
    
}