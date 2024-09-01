
import java.util.*;
import java.io.*;

public class Main
{


	// class name of users and choices.
	public static class User {
		char choice;
		String username;
		User(char choice,String username) {
			this.choice=choice;
			this.username=username;
		}
	}

	//inital implementation.

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);

		//initial tictactoe grid.
		System.out.println();
		char[][] a=new char[3][3];
		for(char[] i:a) {
			Arrays.fill(i,'-');
		}
		for(char[] i:a) {
			for(char j:i) {
				System.out.print(j+" ");
			}
			System.out.println();
		}

		// 2 Characters playing in game and thier respective choices of (X || O).

		User x=new User('X',"shyamsunder");
		User y=new User('O',"venumadhav");

		System.out.println();
		System.out.println(x.choice+" "+x.username);
		System.out.println(y.choice+" "+y.username);
		System.out.println();
		int count=0;
		//taking inputs and checking for the winner and gameover.
		User user=x;
		while(count<10) {
			if(gameOer(a)==true) {
				System.out.println("GameOver there is no winner it is a draw");
			}
			int p=sc.nextInt();
			int q=sc.nextInt();
			if(p>3 || q>3) {
				System.out.println("invalid move,enter position again");
				continue;
			}
			if(gameOver(a,p-1,q-1)==false) {

				//checking if there is anymore valid positions and the game is Over.
				boolean posLeft=positionLeft(a,user,p-1,q-1);
				if(posLeft==false) {
					System.out.println("enter the position again");
					continue;
				}

				//checking if the position is valid and filling the position with users choice and toggling user.
				boolean valid=validPosition(a,user,p-1,q-1,posLeft);
				user=userToggle(user,x,y);
				for(char[] i:a) {
					for(char j:i) {
						System.out.print(j+" ");
					}
					System.out.println();
				}
				count++;

				//checking for a winner

				boolean winner=winnerUser(a,user,x,y);
                User us=user;
		        us=userToggle(us,x,y);
				if(winner==false) {
					System.out.println();
					System.out.println("winner is not decided yet");
					System.out.println(us.choice);
					continue;
				}

				//if there is a winner,printing winner and exiting the game.
				User usw=user;
		        usw=userToggle(usw,x,y);
				if(winner==true) {
					System.out.println(usw.username+" is the Game winner..");
					break;
				}
			}
		}
	}

	//functions
	//posLeft function
	public static boolean positionLeft(char[][] a,User user,int p,int q) {
		if(a[p][q]=='-') {
			System.out.println();
			return true;
		}
		else {
			return false;
		}
	}

	//validPosition
	public static boolean validPosition(char[][] a,User user,int p,int q,boolean posLeft) {
		a[p][q]=user.choice;
		return true;
	}


	//userToggle
	public static User userToggle(User user,User x,User y) {
		if(user.choice=='X') {
			user=y;
		} else {
			user=x;
		}
		return user;
	}

	public static boolean gameOer(char[][] a) {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(a[i][j]=='-') {
					return false;
				} else if(a[i][j]!='-' && (i==3 && j==3)) {
					return true;
				}
				else if(a[i][j]=='X' || a[i][j]=='O') {
					return false;
				}
				else {
					return true;
				}
			}
		}
		return true;
	}
	//gameOver function
	public static boolean gameOver(char[][] a,int p,int q) {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(a[i][j]=='-') {
					return false;
				}
				else if(a[i][j]=='X' || a[i][j]=='O') {
					continue;
				} else {
					return true;
				}
			}
		}
		return false;
	}

	//winnerUser function
	public static boolean winnerUser(char[][] a,User user,User x,User y) {
		boolean row=rowCheck(a,user,x,y);
		boolean col=colCheck(a,user,x,y);
		boolean diagonal=diaCheck(a,user,x,y);
		return row || col || diagonal;
	}
	//rowCheck function
	public static boolean rowCheck(char[][] a,User user,User x,User y) {
		User usr=user;
		usr=userToggle(usr,x,y);
		char c=usr.choice;
		for(int i=0; i<3; i++) {
			if(a[i][0]==c && (a[i][1]==c && a[i][2]==c)) {
				return true;
			}
		}
		return false;
	}
	//column check function
	public static boolean colCheck(char[][] a,User user,User x,User y) {
		User usr=user;
		usr=userToggle(usr,x,y);
		char c=usr.choice;
		for(int i=0; i<3; i++) {
			if(a[0][i]==c && (a[1][i]==c && a[2][i]==c)) {
				return true;
			}
		}
		return false;
	}
	//diagonalcheck function
	public static boolean diaCheck(char[][] a,User user,User x,User y) {
		User usr=user;
		usr=userToggle(usr,x,y);
		if(a[0][0]==usr.choice && (a[1][1]==usr.choice && a[2][2]==usr.choice)) {
			return true;
		}
		else if(a[0][2]==usr.choice && (a[1][1]==usr.choice && a[2][1]==usr.choice)) {
			return true;
		}
		return false;
	}
}
