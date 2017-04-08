/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlesolver;

/**
 *  AI project
 * @author Jonesto
 * State node does much of the work of beam search
 * it keeps track of connecting nodes and checks the 
 * heuristic to send back the best two possible matches
 */
public class StateNode {
    private State curState;
    private int entropy;
    
    private StateNode root;
    private int visited;
    private Point origin;
    static int north = 0;
    static int east = 1;
    static int west = 2;
    static int south = 3;
    private int rootDir;
    private StateNode nextMove [];
    private int rows;
    private int columns;
    private State goal;
     
    
    public StateNode(State puzzlePos)                  
    {
       curState = puzzlePos;
       visited = 0;
       root = null;
       rootDir = -1;
       nextMove = new StateNode [4];
       rows =  puzzlePos.getState().length;
       columns = puzzlePos.getState()[0].length;
       SetGoalState();
       nextMove[north] = nextMove[east] = 
               nextMove[west] = nextMove[south] = null;
       entropy = getPermHeuristic(puzzlePos);
    }
   
   public void DescendTree(StateNode userNode)
   {
       
       StateNode ancestor;
       if(userNode != null)
           ancestor = userNode;
       else
           ancestor = this;
       int size = columns * rows;
       origin = findPoint(size, curState); 
       int [][] puzTable = CopyArray(curState.getState());
       int tempInt = 0;
       State tempState;
       StateNode visitState;
       if (origin.x > 0) //up
       {  
           puzTable = CopyArray(curState.getState());
           tempInt = puzTable[origin.x-1][origin.y];
           puzTable[origin.x][origin.y] = tempInt;
           puzTable[origin.x-1][origin.y] = size;
           tempState = new State(puzTable);
           if(tempState.Compare(ancestor.GetCurState()))
           {
               nextMove[north] = ancestor;
               root = ancestor;
               rootDir = north;
           }
           else
           {
              visitState = ancestor.GetRoot(tempState);
              if (visitState == null)
                  nextMove[north] = new StateNode(tempState);
              else 
              {
                  nextMove[north] = visitState;
              }
            
                        
           }
       }
       if ( origin.x+1 < rows) //down
       {  
           puzTable = CopyArray(curState.getState());
           tempInt = puzTable[origin.x+1][origin.y];
           puzTable[origin.x][origin.y] = tempInt;
           puzTable[origin.x+1][origin.y] = size;
           tempState = new State(puzTable);
           if(tempState.Compare(ancestor.GetCurState()))
           {
               nextMove[south] = ancestor;
               root = ancestor;
               rootDir = south;
           }
           else
           {
              visitState = ancestor.GetRoot(tempState);
              if (visitState == null)
                  nextMove[south] = new StateNode(tempState);
              else 
                  nextMove[south] = visitState;
              
           }
           
       }
       if (origin.y > 0) //left
       {  
           puzTable = CopyArray(curState.getState());
           tempInt = puzTable[origin.x][origin.y-1];
           puzTable[origin.x][origin.y] = tempInt;
           puzTable[origin.x][origin.y-1] = size;
           tempState = new State(puzTable);
           if(tempState.Compare(ancestor.GetCurState()))
           {
                
               nextMove[west] = ancestor;
               root = ancestor;
               rootDir = west;
           }
           else
           {
              visitState = ancestor.GetRoot(tempState);
              if (visitState == null)
                  nextMove[west] = new StateNode(tempState);
              else 
                  nextMove[west] = visitState;
                 
              
           }
       }
       if ( origin.y+1 < columns) //right
       { 
           puzTable = CopyArray(curState.getState());
           tempInt = puzTable[origin.x][origin.y+1];
           puzTable[origin.x][origin.y] = tempInt;
           puzTable[origin.x][origin.y+1] = size;
           tempState = new State(puzTable);
           if(tempState.Compare(ancestor.GetCurState()))
           {
                nextMove[east] = ancestor;
                root = ancestor;
               rootDir = east;
           }
           else
           {
              visitState = ancestor.GetRoot(tempState);
              if (visitState == null)
                  nextMove[east] = new StateNode(tempState);
              else 
                  nextMove[east] = visitState;
              
           }
       }
   }
   public int GetEntropy()
   {
       return entropy;
   }

   public State GetCurState()
   {
       return curState;
   }
   //add number of times node was visited
   public void SetVisited(int vnum)
   {
       //System.out.print("final test, wtf \n");
       visited += vnum;
   }
   
   public int GetVisits()
   {
       return visited;
   }
   
   public StateNode GetRoot(State compState)
   {
       if (compState.equals(curState))
       {
           return this;
       }
       if (root != null)
           root.GetRoot(compState);
       return null;
   }
   
   
   public StateNode [] GetNextNodes ()
   {
       int minEntropy, minVisited, dirBest, dirNext;
       if(nextMove[north] != null && rootDir != north)
       {    
           minVisited =  nextMove[north].GetVisits();
           minEntropy = nextMove[north].GetEntropy();
           dirBest = dirNext = north;
       }
       else if (nextMove[east] != null && rootDir != east)
       {
           minVisited = nextMove[east].GetVisits();
           minEntropy = nextMove[east].GetEntropy();
           dirBest = dirNext = east;
       }
       
       else if (nextMove[west] != null && rootDir != west)
       {
           minVisited = nextMove[west].GetVisits();
           minEntropy = nextMove[west].GetEntropy();
           dirBest = dirNext = west;
       }
       
       else 
       {
           minVisited = nextMove[south].GetVisits();
           minEntropy = nextMove[south].GetEntropy();
           dirBest = dirNext = south;
       }
       
       for (int i = dirNext; i < (south+1); i++)
       {
           if (nextMove[i] != null &&
                   nextMove[i].GetVisits() < minVisited && i != rootDir)
           {
              if(dirBest != dirNext)
                  dirNext = dirBest;
              minVisited = nextMove[i].GetVisits();
              dirBest = i;                          
           }   
       }
       for (int i = dirNext; i < (south+1); i++)
       {
           if (nextMove[i] != null && nextMove[i].GetVisits() <= minVisited && 
                   nextMove[i].GetEntropy() < minEntropy)
           {
              if(dirBest != dirNext)
                  dirNext = dirBest;
              minVisited = nextMove[i].GetEntropy();
              dirBest = i;                          
           }   
       }
       
       if (dirNext == dirBest && rootDir != -1)
           dirNext = rootDir;
 
       
       minVisited = nextMove[dirNext].GetVisits();
       minEntropy = nextMove[dirNext].GetEntropy();
       
       
       for (int i = dirNext; i < (south+1); i++)
       {
           if (nextMove[i] != null && 
                   nextMove[i].GetVisits() < minVisited && i != rootDir)
           {
              
              minVisited = nextMove[i].GetVisits();
              dirNext = i;                          
           }   
       }
       
       StateNode returnStates [] = {nextMove[dirBest], nextMove[dirNext]};
       return returnStates;
   }
   public int getPermHeuristic(State state) 
   {

        int[][] rawState = state.getState();

        int posX, negX, posY, negY, distance;
        posX = negX = posY = negY = distance = 0;
        for (int i = 0; i < rawState.length; i++) {
            for (int j = 0; j < rawState[i].length; j++) {

                Point goalPoint = findPoint(rawState[i][j], goal);
                Point thisPoint = new Point(i, j);

                if ((thisPoint.x - goalPoint.x) >= 0)
                    posX += (thisPoint.x - goalPoint.x);
                else 
                    negX += (thisPoint.x - goalPoint.x);
                if ((thisPoint.y - goalPoint.y) >= 0)
                    posY += (thisPoint.y - goalPoint.y);
                else 
                    negY += (thisPoint.y - goalPoint.y);

            }
            
        }
        
        distance = posX + posY;
        
            if (distance != (-1 * (negX + negY)))
                return -1;
            return distance;
    }
   
    public Point findPoint(int x, State state) {

        for (int i = 0; i < state.getState().length; i++) {
            for (int j = 0; j < state.getState()[i].length; j++) {
                if (state.getState()[i][j] == x) {
                    return new Point(i, j);
                }
            }

        }
        return null;
    } 
    public void SetGoalState()
    {
        int setGoal[][] = new int[rows][columns];
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < columns; j++) 
            {
                setGoal[i][j] = (columns * i) + j + 1;
            }
        goal = new State(setGoal);
    } 
    
    public int[][] CopyArray(int[][] userArr)
    {
        int [][] copyArr =  new int[userArr.length][userArr[0].length];
        for (int i = 0; i < userArr.length; i++)
            for (int j = 0; j < userArr[0].length; j++)
                copyArr[i][j] = userArr[i][j];
        return copyArr;
    }
    
    StateNode GetChild(int dir)
    {
        
        if(nextMove[dir] != null)
            return nextMove[dir];
        else if (root != null)
            return root;
        else 
            return this;
        
    }
    
    void SetChild(int visits, State lState)
    {
        
        if (nextMove[north] != null && lState.equals(nextMove[north].GetCurState()))
            nextMove[north].SetVisited(visits);
        if (nextMove[east] != null && lState.equals(nextMove[east].GetCurState()))
            nextMove[east].SetVisited(visits);
        if (nextMove[south] != null && lState.equals(nextMove[south].GetCurState()))
            nextMove[south].SetVisited(visits);
        if (nextMove[west] != null && lState.equals(nextMove[west].GetCurState()))
            nextMove[west].SetVisited(visits);
    }
    
    @Override
   public boolean equals(Object o) 
   {

      if (o instanceof StateNode) 
      {
         StateNode s = (StateNode) o;
         return this.GetCurState().equals(s);
      }
      return false;
   }
}