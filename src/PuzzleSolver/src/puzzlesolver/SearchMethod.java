package puzzlesolver;

import java.util.List;

/**
 *
 * @author kordusj
 */
public abstract class SearchMethod 
{
    private State winPath[];
    boolean halted = false;
    
    SearchMethod(){};
    
   abstract public List<State> getWin();
   
   abstract public int getSteps();
    
   abstract public int getDepth();
   
   public void kill() {
      halted = true;
   }
   
   public boolean isHalted(){
      return halted;
   }
   
   public void resetHalt()
   {
      halted = false;
   }
   
   //Command to execute
   abstract public boolean run(StateManager st);
    
    
   //prints out the winning path
   public void Display(){};
}
