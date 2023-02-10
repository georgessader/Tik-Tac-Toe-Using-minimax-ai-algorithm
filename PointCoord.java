/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimaxxo;

/**
 *
 * @author Georges
 */
public class PointCoord {
    int x,y;
    
    public PointCoord(int x1, int y1)
    {
        x=x1;
        y=y1;
    }
    
    @Override
    public String toString()
    {
        return "["+x+","+y+"]";
    }
}
