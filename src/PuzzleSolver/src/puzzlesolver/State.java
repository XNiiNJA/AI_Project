/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlesolver;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author kordusj
 */
public class State 
{
    private static final int SHUFFLE_TIMES = 5000;
    private State nextStates[];
    
    private State previous;
    
    private int curState [][];
    
    private float gScore = Float.MAX_VALUE;
    private float fScore = Float.MAX_VALUE;
    private float hScore;
    private int pHeight;
    private int pWidth;
    private String path;
    
    State (int iWidth, int iHeight)
    {
        pHeight = iHeight;
        pWidth = iWidth;
        curState = new int[pWidth][pHeight];
        Shuffler();
    }
    State(int newState[][])
    {
        curState = newState;
        pWidth = newState.length;
        pHeight = newState[0].length;
    }
    
    public int[][] getState()
    {
        return curState;
    }
    
    public void initPath(String init)
    {
        path = init;
    }
    
    public String getPath()
    {
        return path;
    }
    
    public void appendPath(String nextIden)
    {
        path += '|' + nextIden;
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
    
   public void setGScore(float g) {
      gScore = g;
   }

   public void setFScore(float f) {
      fScore = f;
   }

   public void setHScore(float h) {
      hScore = h;
   }

   public float getGScore() {
      return gScore;
   }

   public float getFScore() {
      return fScore;
   }

   public float getHScore() {
      return hScore;
   }
   
   public int getWidth()
   {
      return pWidth;
   }
   
   public int getHeight()
   {
      return pHeight;
   }

   @Override
   public int hashCode() {
      int hash = 7;
      hash = 61 * hash + Arrays.deepHashCode(this.curState);
      return hash;
   }

   @Override
   public boolean equals(Object o) {

      if (o instanceof State) {
         State s = (State) o;

         int[][] raw = s.getState();

         for (int i = 0; i < raw.length; i++) {
            for (int j = 0; j < raw[i].length; j++) {
               if (raw[i][j] != curState[i][j]) {
                  return false;
               }
            }
         }

         return true;
      }

      return false;

   }

   // Tod - made a simple print state method. Haven't tested it, but it should come in handy when we need to debug, can
   // make more pretty later
   public void printCurrentState() {
      for (int i = 0; i < pHeight; i++) {
         for (int j = 0; j < pWidth; j++) {            
            System.out.print(curState[j][i]);
            System.out.print("\t");
         }
         System.out.print("\n");
      }
   }

   public void Shuffler() {

      /* int NORTH = 0;
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
        }*/

      State current = StateManager.generateGoalState(pWidth, pHeight);

      boolean doRand = false;
      int curCount = 0;
      int switchCount = 50;

      StateManager st = new StateManager(current);

      st.goalState(pWidth, pHeight);

      Random rand = new Random();

      rand.setSeed(System.currentTimeMillis());

      int numShuffle = rand.nextInt(SHUFFLE_TIMES);

      for (int i = 0; i < numShuffle; i++) {

         State[] next = st.GetNextStates(current);

         float heu = 0;
         int ourIndex = 0;

         for (int j = 0; j < next.length; j++) {
            
            if (doRand) {
               ourIndex = (int) (rand.nextFloat() * (next.length));//rand.nextInt(next.length - 1);

               ourIndex = Math.min(ourIndex, next.length - 1);

               break;

            }
            else {

               float thisHeu = st.getHeuristic(next[j]);
               if (thisHeu > heu) {
                  heu = thisHeu;
                  ourIndex = j;
               }

            }
            
           

         }
         
         curCount++;
         
         if(curCount >= switchCount)
         {
            doRand = !doRand;
            curCount = 0;
         }
         
         current = next[ourIndex];
         
         //current.printCurrentState();
         //System.out.print("\n\n\n");
         //System.out.println(ourIndex);

      }

      System.out.print("Shuffled with heuristic: ");
      System.out.println(st.getHeuristic(current));
      
      current.printCurrentState();
      
      curState = current.getState();

   }
}
