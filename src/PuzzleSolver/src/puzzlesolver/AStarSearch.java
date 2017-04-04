/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlesolver;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Grant
 */
public class AStarSearch extends SearchMethod{
   
   StateManager st;
   
   AStarSearch()
   {
      
      
   }
   
   public boolean run(StateManager st)
   {
       
      /*int[][] raw;
      //raw = new int[][]{{2, 3, 0},
      //                  {1, 6, 8},
      //                  {7, 5, 4}};
      
      raw = new int[][]{{6, 0, 1},
                        {3, 4, 8},
                        {7, 2, 5}};
      
      float lowestH = Float.MAX_VALUE;
      
      
      State start = new State(raw);
      
      st = new StateManager(start);
      
      //First, generate a goal state to run on.
      st.goalState(3, 3);*/
      
      State start = st.getStart();
      
      //Set the G score of the start to 0.
      start.setGScore(0);
      
      //The F score to get to the end is pure heuristic.
      start.setFScore(st.getHeuristic(start));
              
      st.addToOpenSet(start);
      
      State current = null;
      
      while(!st.openSetEmpty()) //Do this forever and ever.
      {
         current = st.findLowestF();
         
         if(st.FoundGoal(current))
         {
            System.out.println("Found!");
            display(current);
            return true;
         }
         
         st.removeFromOpenSet(current);
         st.addToClosedSet(current);
         
         State[] nextStates = st.GetNextStates(current);
         
         for(int i = 0; i < nextStates.length; i++)
         {
            State neighbor = nextStates[i];
            if(st.isInClosedSet(neighbor))
               continue;
            
            float tentative_gScore = 1 + current.getGScore();

            if (!st.isInOpenSet(neighbor)) {
               st.addToOpenSet(neighbor);
            }
            else if(tentative_gScore >= neighbor.getGScore())
            {
               continue;
            }

            neighbor.setPrevious(current);
            neighbor.setGScore(tentative_gScore);

            float neighbor_h = st.getHeuristic(neighbor);

            neighbor.setHScore(neighbor_h);

            neighbor.setFScore(neighbor.getHScore() + neighbor.getGScore());

            st.updateOpenStateInstance(neighbor);
            
/*            if(neighbor.getHScore() < lowestH)
            {
               
               neighbor.printCurrentState();
               
               //lowestH = neighbor.getHScore();
               
               //System.out.println("\n" + lowestH);
               
            }*/
            
            
         }
          
          
      }
      
      System.out.println("Failure!");
      display(current);
      
      return false;
   }
   
/*   private State getNextBest(State state)
   {
      State[] states = st.GetAvailStates(state);
      
      int lowestIndex = -1;
      
      for(int i = 0; i < states.length; i++)
      {
         if(states[i] != null && st.getHeuristic(states[i]) <= st.getHeuristic(states[lowestIndex]))
            lowestIndex = i;
      }
      
      return states[lowestIndex];
      
   }
*/   
   public void display(State path)
   {
      
      while(path != null)
      {
         
         path.printCurrentState();
         
         path = path.getPrevious();
         
         System.out.print("\n");
         
      }
      
      
      
   }        
   
   
   public static void main(String args[])
   {
      
      State start = new State(2, 3);
      
      StateManager st = new StateManager(start);
              
      st.goalState(2, 3);
      
      new AStarSearch().run(st);
      
      
      
   }
   
}
