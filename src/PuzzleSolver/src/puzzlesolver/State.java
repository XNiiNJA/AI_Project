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
    private int pHeight;
    private int pWidth;
    
    State (int iHeight, int iWidth)
    {
        pHeight = iHeight;
        pWidth = iWidth;
        curState = new int[pHeight][pWidth];
        Shuffler();
    }
    State(int newState[][])
    {
        curState = newState;
        pHeight = newState.length;
        pWidth = newState[0].length;
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
    
    public void Shuffler()
    {
        int NORTH = 0;
        int EAST = 1;
        int WEST = 2;
        int SOUTH = 3;
        int randir;
        int shufflenum = 1000;
        for (int i = 0; i < pHeight; i++)
            for (int j = 0; j < pWidth; j++)
                curState[i][j] = (i * pWidth) + j + 1;
        curState[pHeight-1][pWidth-1]=0;
        int posx = pWidth - 1;
        int posy = pHeight - 1;
        for (int i = 0; i < shufflenum; i++)
        {
            randir = (int)(Math.random() * 4);
            if(randir == NORTH && posy > 0)
            {
                curState[posy][posx] = curState[posy-1][posx];
                curState[posy-1][posx]=0;
                posy--;
            }
            else if(randir == EAST && posx < (pWidth-1))
            {
                curState[posy][posx] = curState[posy][posx+1];
                curState[posy][posx+1]=0;
                posx++;
            }
            else if(randir == WEST && posx > 0)
            {
                curState[posy][posx] = curState[posy][posx-1];
                curState[posy][posx-1] = 0;
                posx--;
            }
            else if(randir == SOUTH && posy < (pHeight-1))
            {
                curState[posy][posx] = curState[posy+1][posx];
                curState[posy+1][posx]=0;
                posy++;
            }
        }
    }
}
