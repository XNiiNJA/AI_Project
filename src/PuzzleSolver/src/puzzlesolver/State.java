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
    private int pHeight;
    private int pWidth;
    private int curState [][];
    
    //default constructor, uses default size of 3x3 - Tod
    State()
    {
        pHeight = 3;
        pWidth = 3;
        curState = new int[pHeight][pWidth];
        randomizer();
    }
    //constructor that allows user to input square table dimensions-Tod
    State (int inputDim)
    {
        pHeight = inputDim;
        pWidth = inputDim;
        curState = new int[pHeight][pWidth];
        randomizer();
    }
    
    //puzzle with inputs for different height and width
    State (int iHeight, int iWidth)
    {
        pHeight = iHeight;
        pWidth = iWidth;
        curState = new int[pHeight][pWidth];
        randomizer();
    }
    //returns height
    int GetHeight()
    {
        return pHeight;
    }
    //returns width
    int GetWidth()
    {
        return pWidth;
    }
    
    State(int newState[][])
    {
        curState = newState;
        pWidth = newState[0].length;
        pHeight = newState.length;
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
    /**
   overriden equals method for State class
   @param obj being compared, in this case State objects
   @return true if all items are equal - Tod
   */

   public boolean equals(Object obj)
   {
      if (obj instanceof State)
      {
         State comp = (State) obj;
         for(int i = 0; i < pHeight; i++)
             for(int j = 0; j < pWidth; j++)  
                 if (comp.curState[i][j] != curState[i][j])
                     return false;
          return true;             
      }
      return false;
   }
  

    
    
    
    //Tod - Randomize sets each number sequentially and swaps out 
    // in array
    public void randomizer()
    {
        for (int i = 0; i < pHeight; i++)
            for (int j = 0; j < pWidth; j++)
                curState[i][j] = (i * pWidth) + j;
        
        int temp, rand1, rand2 = 0;
        for (int i = 0; i < pHeight; i++)
            for (int j = 0; j < pWidth; j++)
            {
                rand1 = (int)(Math.random() * pHeight);
                rand2 = (int)(Math.random() * pWidth);
                temp = curState[i][j];
                curState[i][j] = curState[rand1][rand2];
                curState[rand1][rand2] = temp;
            }
    }

}

