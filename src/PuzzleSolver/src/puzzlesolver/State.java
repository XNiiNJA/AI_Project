/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlesolver;

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
        puzzleSize = 3;
        curState = new int[pSize][pSize];
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
}

