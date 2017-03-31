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
public abstract class SearchMethod 
{
    private State winPath[];
    
    SearchMethod(){};
    
    
    //Command to execute
    abstract public boolean run(StateManager st);
    
    
    //prints out the winning path
    public void Display(){};
}
