/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlesolver;

import java.util.Arrays;

/**
 *
 * @author kordusj
 */
public class State 
{
    private State nextStates[];
    
    private State previous;
    
    private int curState [][];
    
    private float gScore = Float.MAX_VALUE;
    private float fScore = Float.MAX_VALUE;
    private float hScore;
    
    State(int newState[][])
    {
        curState = newState;
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
    
    public boolean setPrevious(State state)
    {
       if(state != null)
          previous = state;
         
       return state != null;
    }
    
    public State getPrevious()
    {
       return previous;
    }
    
    public int[][] getState()
    {
        return curState;
    }

    public void setGScore(float g)
    {
       gScore = g;
    }
    
    public void setFScore(float f)
    {
       fScore = f;
    }
    
    public void setHScore(float h)
    {
       hScore = h;
    }
    
    public float getGScore()
    {
       return gScore;
    }
    
    public float getFScore()
    {
       return fScore;
    }
    
    public float getHScore()
    {
       return hScore;
    }
    
   @Override
   public int hashCode() {
      int hash = 7;
      hash = 61 * hash + Arrays.deepHashCode(this.curState);
      return hash;
   }
    
    @Override
    public boolean equals(Object o)
    {
       
       if(o instanceof State)
       {
          State s = (State)o;
          
          int[][] raw = s.getState();
          
          for(int i = 0; i < raw.length; i++)
          {
             for(int j = 0; j < raw[i].length; j++)
                if(raw[i][j] != curState[i][j])
                  return false;
          }
          
          return true;
       }
       
       return false;
       
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

