package part2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board {

    static int boardWidth = 6;
    static int boardHeight = 6;
    /* the board to keep track of the player's occupation in the board */
	static int[][] Pboard=new int[boardWidth][boardHeight];
    /* the board to calculate the possible movement of the player's occupation in the board */
	static int[][] TPboard=new int[boardWidth][boardHeight];
    /* the board to calculate the score of the player in the board */
	static int[][] Sboard=Getboard("part2-files/Sevastopol.txt");
	
	public static int[][] Getboard(String filename) {
        String s1="";
        String str;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filename));

            while((str=br.readLine())!=null) {
                s1+=str;
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        } catch (IOException e) {
            System.out.println("File not Found");
        }

        String [] data = s1.split("	");
        int [][] dataBoard = new int [boardWidth][boardHeight];
        int k=0;
        for(int i=0;i<boardWidth;i++) {
            for (int j=0;j<boardHeight;j++){
                dataBoard[i][j]=Integer.parseInt(data[k]);
                k++;
            }
        }
        return dataBoard;
	}
	
	
	//When either player place a move, it will affect other points
	public void Pmove(Point point,int turn){
		Pboard[point.x][point.y]=turn;
		if(oldpointP(point,turn)){
		if (point.x+1 < boardWidth){
			if (Pboard[point.x+1][point.y]!=0)Pboard[point.x+1][point.y]=turn;
		}
		if (point.x-1>=0){
			if (Pboard[point.x-1][point.y]!=0)Pboard[point.x-1][point.y]=turn;
		}
		if (point.y+1 < boardHeight){
			if (Pboard[point.x][point.y+1]!=0)Pboard[point.x][point.y+1]=turn;
		}
		if (point.y-1>=0){
			if (Pboard[point.x][point.y-1]!=0)Pboard[point.x][point.y-1]=turn;
		}
		}
	}
	
	public void TPmove(Point point,int turn){
		TPboard[point.x][point.y]=turn;
		if(oldpointTP(point,turn)){
		if (point.x+1 < boardWidth){
			if (TPboard[point.x+1][point.y]!=0)TPboard[point.x+1][point.y]=turn;
		}
		if (point.x-1>=0){
			if (TPboard[point.x-1][point.y]!=0)TPboard[point.x-1][point.y]=turn;
		}
		if (point.y+1 < boardHeight){
			if (TPboard[point.x][point.y+1]!=0)TPboard[point.x][point.y+1]=turn;
		}
		if (point.y-1>=0){
			if (TPboard[point.x][point.y-1]!=0)TPboard[point.x][point.y-1]=turn;
		}
		}
	}
	
	private boolean oldpointTP(Point point,int turn) {
		int k=0;
		if (point.x+1 < boardWidth){
			if (TPboard[point.x+1][point.y]==turn)k++;
		}
		if (point.x-1>=0){
			if (TPboard[point.x-1][point.y]==turn)k++;
		}
		if (point.y+1 < boardHeight){
			if (TPboard[point.x][point.y+1]==turn)k++;
		}
		if (point.y-1>=0){
			if (TPboard[point.x][point.y-1]==turn)k++;
		}
		if (k>0){
			return true;
		} else return false;
	}

	private boolean oldpointP(Point point,int turn) {
		int k=0;
		if (point.x+1 < boardWidth){
			if (Pboard[point.x+1][point.y]==turn)k++;
		}
		if (point.x-1>=0){
			if (Pboard[point.x-1][point.y]==turn)k++;
		}
		if (point.y+1 < boardHeight){
			if (Pboard[point.x][point.y+1]==turn)k++;
		}
		if (point.y-1>=0){
			if (Pboard[point.x][point.y-1]==turn)k++;
		}
		if (k>0){
			return true;
		}else return false;		
	}

	//Find the total value player1 owned
	public int checkTPvalue(){
		int sum=0;
		for (int i=0; i < boardWidth;i++){
			for (int j=0; j < boardHeight;j++){
				if (TPboard[i][j]==1)sum+=Sboard[i][j];					
				}
			}
		return sum;
		}
	
	public int checkPvalue(){
		int sum=0;
		for (int i=0;i<boardWidth;i++){
			for (int j=0;j<boardHeight;j++){
				if (Pboard[i][j]==1)sum+=Sboard[i][j];					
				}
			}
		return sum;
		}
	
	  public static List<Point> getAvailableStatesTP() {
		  List<Point> availablePoints = new ArrayList<>();
	        for (int i = 0; i < boardWidth; ++i) {
	            for (int j = 0; j < boardHeight; ++j) {
	                if (TPboard[i][j] == 0) {
	                    availablePoints.add(new Point(i, j));
	                }
	            }
	        }
	        return availablePoints;
	    }
	  
	  public static List<Point> getAvailableStatesP() {
		  List<Point> availablePoints = new ArrayList<>();
	        for (int i = 0; i < 6; ++i) {
	            for (int j = 0; j < 6; ++j) {
	                if (Pboard[i][j] == 0) {
	                    availablePoints.add(new Point(i, j));
	                }
	            }
	        }
	        return availablePoints;
	    }
	
	 Point nextmove=new Point(0, 0);
	  
	public static boolean ifEnd(){
		List<Point> point=getAvailableStatesP();	
		return point.isEmpty();		
	}
	
	public int minimax_max(int depth,int turn){
		
		int score=checkTPvalue();
		
		List<Point> pointsAvailable = getAvailableStatesTP();
		if (pointsAvailable.isEmpty()) return score;
		
		if (depth==3) return score;
		
		int max = -1;

		int[][]restore =new int[boardWidth][boardHeight];
        for (int a=0;a<boardWidth;a++){
            for (int b=0;b<boardHeight;b++){
                restore[a][b]=TPboard[a][b];
            }
        }
		if (turn==1){

				for (int i=0;i<pointsAvailable.size();i++){
                    Point point=pointsAvailable.get(i);

                    for (int a=0;a<boardWidth;a++){
                        for (int b=0;b<boardHeight;b++){
                            TPboard[a][b]=restore[a][b];
                        }
                    }

                    TPmove(point,1);

                    System.out.println("Player1 turn");
                    for (int o=0;o<boardWidth;o++){
                        for (int j=0;j<boardHeight;j++){
                            System.out.print(TPboard[o][j]+" ");
                        }
                        System.out.println("");
                    }
                    System.out.println("");

                    int currentScore=minimax_min(depth+1,2);
                    if (currentScore>max){
                        max=currentScore;
                        nextmove=point;
                    }
                }
                if (depth==0){
                    Pmove(nextmove,1);
                    System.out.println("PPPPPPP");
                    for (int o=0;o<boardWidth;o++){
                        for (int j=0;j<boardHeight;j++){
                            System.out.print(Pboard[o][j]+" ");
                        }
                        System.out.println("");
                    }
                    System.out.println("");
				}
			}
		
			if (turn==2){
				for (int i=0;i<pointsAvailable.size();i++){
				Point point=pointsAvailable.get(i);
				for (int a=0;a<boardWidth;a++){
					for (int b=0;b<boardHeight;b++){
						TPboard[a][b]=restore[a][b];
					}
				}
				TPmove(point,2);
				System.out.println("Player2 turn");
				for (int o=0;o<boardWidth;o++){
					for (int j=0;j<boardHeight;j++){
						System.out.print(TPboard[o][j]+" ");
					}
					System.out.println("");
				}
				System.out.println("");
				
				int currentScore=minimax_min(depth+1,1);
				if (currentScore>max){
					max=currentScore;
					nextmove=point;
				}
				}
				if (depth==0){
					Pmove(nextmove,2);
					System.out.println("PPPPPPP");
					for (int o=0;o<boardWidth;o++){
						for (int j=0;j<boardHeight;j++){
							System.out.print(Pboard[o][j]+" ");
						}
						System.out.println("");
					}
					System.out.println("");
					
				}
			}
		
		return max;		
	}
	
	public int minimax_min(int depth,int turn){
		int score=checkTPvalue();
		List<Point> pointsAvailable = getAvailableStatesTP();
		if (pointsAvailable.isEmpty()) return score;
		if (depth==3) return score;
		int min = Integer.MAX_VALUE;
		
		int [][]restore=new int [boardWidth][boardHeight];
		for (int a=0;a<boardWidth;a++){
			for (int b=0;b<boardHeight;b++){
				restore[a][b]=TPboard[a][b];
			}
		}
		if (turn==1){	
				for (int i=0;i<pointsAvailable.size();i++){
				Point point=pointsAvailable.get(i);	
				for (int a=0;a<boardWidth;a++){
					for (int b=0;b<boardHeight;b++){
						TPboard[a][b]=restore[a][b];
					}
				}
				TPmove(point,1);
				System.out.println("Player1 turn");
				for (int o=0;o<boardWidth;o++){
					for (int j=0;j<boardHeight;j++){
						System.out.print(TPboard[o][j]+" ");
					}
					System.out.println("");
				}
				System.out.println("");
				
				int currentScore=minimax_max(depth+1,2);
				if (currentScore<min){
					min=currentScore;
				}
			}
		}
			if (turn==2){
				for (int i=0;i<pointsAvailable.size();i++){
				Point point=pointsAvailable.get(i);
				TPboard=restore;
				TPmove(point,2);
				System.out.println("Player2 turn");
				for (int o=0;o<boardWidth;o++){
					for (int j=0;j<boardHeight;j++){
						System.out.print(TPboard[o][j]+" ");
					}
					System.out.println("");
				}
				System.out.println("");
				
				int currentScore=minimax_max(depth+1,1);
				if (currentScore<min){
					min=currentScore;
				}
			}
		}
		return min;	
		
	}
	
	public static void main(String args[]) {
		Board b=new Board();
		while (!ifEnd()){		
			b.minimax_max(0, 1);
			TPboard=Pboard;
			b.minimax_max(0, 2);
			TPboard=Pboard;
		/*	for (int o=0;o<6;o++){
				for (int j=0;j<6;j++){
					System.out.print(Pboard[o][j]+" ");
				}
				System.out.println("");
			}
			System.out.println("");*/
		}
		
		
		
		
		
	}
	

	
}
