package part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {


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
}
