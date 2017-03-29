/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlesolver;
import java.util.Random;
/**
 *
 * @author kordusj, jonesto
 */     
public class State 
{
    private State nextStates[];
    private int pSize;
    private int curState [][];
    
    //default constructor - Tod
    State()
    {
        pSize = 3;
        curState = new int[pSize][pSize];
        randomizer();
    }
    
    State(int newState[][])
    {
        curState = newState;
        pSize = newState[0].length;
    }
    
    public boolean Compare(State toCompare)
    {
        return curState.equals(toCompare);
    }
    
    public boolean setNext(State next[])
    {
        if(next == null)
            return false;
        nextStates = next;
        return true;
    }
    
    public int[][] getState()
    {
        return curState;
    }
    // Tod - made a simple print state method. Haven't tested it, but it should come in handy when we need to debug, can
    // make more pretty later
    public void printCurrentState()
    {
        for (int i = 0; i < curState.length; i++)
        {
            for(int j = 0; j < curState[i].length; j++)
            {
                System.out.print(curState[i][j] + " ");
            }
        System.out.print("\n");
        }
    }
    
    //Tod - Randomize sets each number sequentially and swaps out 
    // in array
    public void randomizer()
    {
        for (int i = 0; i < pSize; i++)
            for (int j = 0; j < pSize; j++)
                curState[i][j] = (i * pSize) + j;
        
        int temp, rand1, rand2 = 0;
        for (int i = 0; i < pSize; i++)
            for (int j = 0; j < pSize; j++)
            {
                rand1 = (int)(Math.random() * pSize);
                rand2 = (int)(Math.random() * pSize);
                temp = curState[i][j];
                curState[i][j] = curState[rand1][rand2];
                curState[rand1][rand2] = temp;
            }
    }

}

