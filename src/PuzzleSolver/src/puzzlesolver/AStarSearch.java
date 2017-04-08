package puzzlesolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Grant
 */
public class AStarSearch extends SearchMethod{
   
   StateManager st;
   int depth = 0;
   int stepsIn = 0;
   private List<State> win;
   
   AStarSearch()
   {
      
      
   }
   
   public boolean run(StateManager st)
   {
       
      stepsIn = 0;
      depth = 0;
      
      State start = st.getStart();
      
      //Set the G score of the start to 0.
      start.setGScore(0);
      
      //The F score to get to the end is pure heuristic.
      start.setFScore(st.getHeuristic(start));
              
      st.addToOpenSet(start);
      
      State current = null;
      
      while(!st.openSetEmpty() && !halted) //Do this forever and ever.
      {
         current = st.findLowestF();
         
         if(st.getGoal().equals(current))
         {
            System.out.println("Found!");
            display(current);
            calculatePath(current);
            return true;
         }
         
         st.removeFromOpenSet(current);
         st.addToClosedSet(current);
         
         System.out.println();
         
         State[] nextStates = st.GetNextStates(current);
         
         stepsIn++;
         
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
             
         }
          
      }
      
      System.out.println("Failure!");
      
      return false;
   }
   
   private void calculatePath(State state)
   {
      
      List<State> backwardsWin = new ArrayList();
      win = new ArrayList();
      
      State endState = state;
      
      while(state != null)
      {
         depth++;
         
         backwardsWin.add(state);
         
         state = state.getPrevious();
         
      }
      

      for(int i = depth; i > 0; i--)
      {
         win.add(backwardsWin.get(i - 1));
      }
      
      
   }
   
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
              
      st.goalState(start.getWidth(), start.getHeight());
      
      new AStarSearch().run(st);
      
      
      
   }

   @Override
   public List<State> getWin() {
      return win;
   }

   @Override
   public int getSteps() {
      return stepsIn;
   }

   @Override
   public int getDepth() {
      return depth;
   }
 
}
