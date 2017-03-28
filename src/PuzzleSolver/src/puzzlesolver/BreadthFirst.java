/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlesolver;

/**
 *
 * @author kordusj
 */
public class BreadthFirst extends SearchMethod 
{
    private State toProcess[];
    private StateManager manager;
    private int size;
    
    BreadthFirst()
    {
        size = 1;
        toProcess = new State[size];
    }
    
    private void growTree()
    {
        State temp[] = toProcess;
        toProcess = new State[++size];
        toProcess = temp;
    }
    
    public boolean run(StateManager init)
    {
        boolean goalReached = false;
        manager = init;
        int oldSize;
        growTree();
        while(!goalReached)
        {
            oldSize = size;
            for(int i = 0; i < oldSize; i++)
            {
                State tempState[] = manager.GetAvailStates(toProcess[i]);
                removeFirst();
                for(int j = 0; j < 4; j++)
                {
                    if(tempState[j] != null)
                    {
                        addState(tempState[j]);
                    }
                }   
            }
            for(int i = 0; i < size; i++)
            {
                if(manager.FoundGoal(toProcess[i]))
                    goalReached = true;
            }
        }   
        return true;
    }
    
    
    private void addState(State toAdd)
    {
        toProcess[size] = toAdd;
        growTree();
    }
    
    private void removeFirst()
    {
        for(int i =1; 1 < size; i++)
        {
            toProcess[i - 1] = toProcess[i];
        }
    }
}
