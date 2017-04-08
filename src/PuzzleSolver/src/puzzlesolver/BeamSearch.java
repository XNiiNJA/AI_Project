/*
 * Tod Jones 
 * Ai
 */
package puzzlesolver;

//import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//import java.util.Arrays;
/**
 *
 * @author jonesto
 */
public class BeamSearch extends SearchMethod
{
/**
   @Override
   */
    
    List vStates;
    private State initial;
    private StateNode beamA;
    private StateNode beamB;
    private StateNode [] nextArr;
    private StateNode prevNodeA;
    private StateNode prevNodeB;
    private int iter;
    private State lState;
    private StateManager manage;
    private StateNode tempState;        
    BeamSearch()
    {
        iter = 10000;
        vStates = new LinkedList <State> ();
    }
    @Override
   public boolean run(StateManager st) {  
       
       manage = st;
       initial = st.getStart();
       lState = initial;
       beamA = new StateNode(lState);
       beamA.DescendTree(null); //root of tree
       beamB = beamA;
       while(beamA.GetEntropy() != 0 && beamB.GetEntropy() != 0 && iter > 0)
       {
           iter--;
           vStates.add(beamA);
           vStates.add(beamB);
           StateNode childArrA[] = new StateNode [4];
           StateNode childArrB[] = new StateNode [4];
           //int childVisitsA[] = {0,0,0,0};
           //int childVisitsB[] = {0,0,0,0};
           for(int i = 0; i < 4; i++)
           {
               childArrA[i] = beamA.GetChild(i);
               childArrB[i] = beamB.GetChild(i);
           }
           for (Object obj : vStates)
           {
               for (int i = 0; i < 4; i++)
               {
                   tempState = (StateNode)obj;
                   if(tempState.GetCurState().equals(childArrA[i].GetCurState()))
                   {   
                       beamA.SetChild(1, childArrA[i].GetCurState());
                   }
                   if(tempState.GetCurState().equals(childArrB[i].GetCurState()))          
                   {
                       beamB.SetChild(1, childArrB[i].GetCurState());
                   }
               }
              
           }
           
           prevNodeA = beamA;
           prevNodeB = beamB;
           if (beamB == beamA)
           {
               nextArr = beamA.GetNextNodes();
               beamA =  nextArr[0];
               beamB =  nextArr[1];
           }
           else
           {
               nextArr = beamA.GetNextNodes();
               beamA = nextArr[0];
               nextArr = beamB.GetNextNodes();
               beamB = nextArr[0];
           }
           beamA.DescendTree(prevNodeA);
           beamB.DescendTree(prevNodeB);
       
       }
       
       if (iter <= 0)
           System.out.print("Couldn't find solution \n");
       else
           System.out.print("\n You won: " + iter);
       System.out.print("\n BeamA: \n");
       beamA.GetCurState().printCurrentState();
       System.out.print("\n BeamA entropy: " + beamA.GetEntropy()  + "\n");
       System.out.print("\n BeamB: \n");
       beamB.GetCurState().printCurrentState();
       System.out.print("\n BeamB entropy: " + beamB.GetEntropy()  + "\n");
       System.out.print("\n BeamA Visits: " + beamA.GetVisits()  + "\n");
       System.out.print("\n BeamB Visits: " + beamB.GetVisits()  + "\n");
       return true;
   }
}
    
