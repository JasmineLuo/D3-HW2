package leetcodestart;

import java.io.*;
import java.util.*;

public class Q2 {

    public static void main( String args[] ){
		String source ="/Users/luozhongyi/Desktop/2016Spring/CSE6242/hw2/HW2/Q2/nepal.csv";
		String destination ="/Users/luozhongyi/Desktop/2016Spring/CSE6242/hw2/HW2/Q2/nepal_new.csv";
		rearrangecsv(source, destination);
	}
    
    public static void rearrangecsv(String s, String d){
    	
    	ArrayList<String> District =new ArrayList<String>();
		ArrayList<String> year2007 =new ArrayList<String>();
		ArrayList<String> year2008 =new ArrayList<String>();
		ArrayList<String> year2009 =new ArrayList<String>();
		ArrayList<String> year2010 =new ArrayList<String>();
		ArrayList<String> year2011 =new ArrayList<String>();
		
		File f1 = new File(s);
		File f2 = new File(d);
		
		Scanner con;
		String currentline;
		String[] list;
		int count=0;
		
		try {
			con = new Scanner(f1);
			con.nextLine(); //jump the title
			
			while(con.hasNextLine()){
				
				count++;
				currentline= con.nextLine();
				
				list=currentline.split(",");
				District.add(list[0]);
				year2008.add(list[2]);
				
				currentline= con.nextLine();
				list=currentline.split(",");
				year2009.add(list[2]);
				
				currentline= con.nextLine();
				list=currentline.split(",");
				year2010.add(list[2]);
				
				currentline= con.nextLine();
				list=currentline.split(",");
				year2011.add(list[2]);
				
				currentline= con.nextLine();
				list=currentline.split(",");
				year2007.add(list[2]);
				
			}
				
			con.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    try {
				
		    BufferedWriter bw = new BufferedWriter(new FileWriter(f2, true));
		    bw.write("District,2007,2008,2009,2010,2011");
		    bw.newLine();
		    
		    for(int i=0;i<count;i++){
		    bw.write( District.get(i)+ "," + year2007.get(i) + "," +  year2008.get(i) +","
		    		+  year2009.get(i) + "," + year2010.get(i) + "," + year2011.get(i)); 
	        bw.newLine(); 
		    }
	        bw.close(); 	
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		    System.out.println("Trasmission completed!");
		   
		
    }
}
