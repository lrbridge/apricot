package part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetBoard {
	
	static int[][] Pboard=new int[6][6];

	
	
	public int[][] Getboard(String filename) throws IOException
	{
		
	
	BufferedReader br = new BufferedReader(new FileReader(filename));
	String s1="";
	String str=br.readLine();
	while((str!=null))
	{
		s1+=str;
		str=br.readLine();
		}
	br.close();
	String [] data=s1.split("	");
	int [][] datas=new int [6][6];
	int k=0;
	for(int i=0;i<6;i++)
	{
		for (int j=0;j<6;j++){
			datas[i][j]=Integer.parseInt(data[k]);
			k++;
		}	
	}
	/*for(int i=0;i<datas.length;i++)
	{
		for (int j=0;j<datas[i].length;j++){
			System.out.print(datas[i][j]+" ");
	}
		System.out.println("");
	}	*/
	return datas;
	}
	
	//When either player place a move, it will affect other points
	public void move(Point point,int turn){
		Pboard[point.x][point.y]=turn;
		Pboard[point.x+1][point.y]=turn;
		Pboard[point.x-1][point.y]=turn;
		Pboard[point.x][point.y+1]=turn;
		Pboard[point.x][point.y-1]=turn;
	}
	
	
	
	public static void main(String args[]) throws IOException {
		//GetBoard b= new GetBoard();	
			//b.Getboard("Keren.txt");
		for (int i=0;i<6;i++){
			for (int j=0;j<6;j++){
				System.out.println(Pboard[i][j]);
			}
		}
		
		
	}
}
