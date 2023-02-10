/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimaxxo;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Georges
 */
public class Minimaxxo {

    /**
     * @param args the command line arguments
     */
    public static final Random RANDOM=new Random();
    public static void main(String[] args) {
        Board b=new Board();
        Scanner in=new Scanner(System.in);
        b.display();
        System.out.println("Select turn\n1: Computer (X)\n2: User(O)");
        int choice=in.nextInt();
        if(choice==Board.PlayerX)
        {
            PointCoord p=new PointCoord(RANDOM.nextInt(3),RANDOM.nextInt(3));
            b.makeAMove(p, Board.PlayerX);
            b.display();
        }
        
        while(!b.isGameOver())
        {
            boolean done=true;
            
            do{
                if(!done)
                    System.out.println("Cell is not empty");
                System.out.println("Your move: ");
                int x=in.nextInt();
                int y=in.nextInt();
                PointCoord usrmove=new PointCoord(x,y);
                done=b.makeAMove(usrmove, Board.PlayerO);
            }while(!done);
            
            b.display();
            
            if(b.isGameOver())
                break;
            b.minimax(0, Board.PlayerX);
            System.out.println("Computer finally Choose: "+ b.compMove);
            b.makeAMove(b.compMove, Board.PlayerX);
            b.display();
        }
        if(b.WinCheck(Board.PlayerX))
            System.out.println("You Lost!");
        else if(b.WinCheck(Board.PlayerX))
            System.out.println("You Win!");
        else
            System.out.println("Draw!");

    }
}
