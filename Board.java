/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimaxxo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Georges
 */
public class Board {
    public static final int NoPlayer=0;
    public static final int PlayerX=1;
    public static final int PlayerO=2;
    int[][] board=new int[3][3];
    PointCoord compMove;
    
    public boolean isGameOver()
    {
        return WinCheck(PlayerX) || WinCheck(PlayerO) || getAvailableElement().isEmpty();
    }

    public boolean WinCheck(int player) {
        if((board[0][0]==board[1][1] && board[0][0]==board[2][2] && board[0][0]==player) || 
                (board[0][2]==board[1][1] && board[0][2]==board[2][0] && board[0][2]==player))
        {
            return true;
        }
        for(int i=0;i<3;i++)
        {
            if((board[i][0] == board[i][1] && board[i][0]==board[i][2] && board[i][0]==player) ||
                    (board[0][i] == board[1][i] && board[0][i]==board[2][i] && board[0][i]==player))
            {
                return true;
            }
        }
        return false;
    }
    
    public List<PointCoord> getAvailableElement()
    {
        List<PointCoord> availableElem=new ArrayList<>();
        
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board[i][j]==NoPlayer)
                    availableElem.add(new PointCoord(i,j));
            }
        }
        return availableElem;
    }
    
    public boolean makeAMove(PointCoord pt, int player)
    {
        if(board[pt.x][pt.y]!=NoPlayer)
            return false;
        board[pt.x][pt.y]=player;
        return true;
    }
    
    public void display()
    {
        System.out.println();
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String value="?";
                if(board[i][j]==PlayerX)
                    value="X";
                else if(board[i][j]==PlayerO)
                    value="O";
                System.out.print(value+" ");
            }
            System.out.println();
        }
    }
    
    public int minimax(int depth, int turn)//depth is where am i in a level
    {//depth should be 0 when the computer want to discuss
        if(WinCheck(PlayerX))
            return 1;
        if(WinCheck(PlayerO))
            return -1;
        
        List<PointCoord> availableElem=getAvailableElement();
        
        if(availableElem.isEmpty())
            return 0;
        
        int min=Integer.MAX_VALUE;//2^31-1 = 2147483647
        int max=Integer.MIN_VALUE;//-2^31 = -2147483648
        
        for(int i=0;i<availableElem.size();i++)
        {
            PointCoord pt=availableElem.get(i);
            if(turn == PlayerX){
                makeAMove(pt,PlayerX);
                int currentScore = minimax(depth+1,PlayerO);
                max=Math.max(currentScore, max);
                /*if(depth==0)
                    System.out.println("Computer score for position "+pt+" = "+currentScore);*/
                if(currentScore>=0 && depth==0)
                    compMove=pt;
                if(currentScore==1)
                {
                    board[pt.x][pt.y]=NoPlayer;
                    break;
                }
                if(i==availableElem.size()-1 && max<0 && depth==0)
                    compMove=pt;
            }else if(turn == PlayerO)
            {
                makeAMove(pt,PlayerO);
                int currentScore=minimax(depth+1,PlayerX);
                min=Math.min(currentScore,min);
                if(min==-1)
                {
                    board[pt.x][pt.y]=NoPlayer;
                    break;
                }
            }
            board[pt.x][pt.y]=NoPlayer;
        }
        return turn==PlayerX?max:min;
    }
    
}






