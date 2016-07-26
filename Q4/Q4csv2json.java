package leetcodestart;

import java.io.*;
import java.util.*;

public class Q4csv2json {

    public static void main( String args[] ){
		String source ="/Users/luozhongyi/Desktop/2016Spring/CSE6242/hw2/HW2/Q4/unhcr_persons_of_concern.csv";
		String destination ="/Users/luozhongyi/Desktop/2016Spring/CSE6242/hw2/HW2/Q4/Q4.json";
		csv2json(source, destination);
	}

	public static void csv2json(String s, String d) {
		// TODO Auto-generated method stub
		//ArrayList<String> NodeList =new ArrayList<String>();
		ArrayList<String> OriginList =new ArrayList<String>();
		ArrayList<String> DestinationList =new ArrayList<String>();
		// for node
		ArrayList<String> OriginNO =new ArrayList<String>();
		ArrayList<String> DestinationNO =new ArrayList<String>();
		
		ArrayList<Integer> Year =new ArrayList<Integer>();
		ArrayList<Integer> Total =new ArrayList<Integer>();
		
		ArrayList<String> Origin =new ArrayList<String>();
		ArrayList<String> Destination =new ArrayList<String>();
		// for link
		
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
				
				if((! list[3].equals("*")) && (! list[3].equals(""))){
				Year.add(Integer.parseInt(list[0]));
				Origin.add(list[2]);
				Destination.add(list[1]);
				Total.add(Integer.parseInt(list[3]));
				
				if( ! OriginList.contains(list[2]))
					OriginList.add(list[2]); // add origin
				
				if( ! DestinationList.contains(list[1]))
					DestinationList.add(list[1]); // add destination
				}
			
			}
				
			con.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		// reorder the node NO
		Collections.sort(DestinationList);
		Collections.sort(OriginList);
		DestinationList.addAll(OriginList);
		ArrayList<String> NodeList =new ArrayList<String>(DestinationList);
		//for(int i=0; i<NodeList.size(); i++)
		    //System.out.println(NodeList.get(i));
		
		for(int j=0; j<Origin.size(); j++){
			String temp = Origin.get(j);
			int index = NodeList.indexOf(temp);
			OriginNO.add(Integer.toString(index));
		}
		
		for(int k=0;k<Destination.size();k++){
			String temp = Destination.get(k);
			int index = NodeList.indexOf(temp);
			DestinationNO.add(Integer.toString(index));
		}
		
		for(int m=0;m<Destination.size();m++){
			System.out.println(Year.get(m) + "  "+ DestinationNO.get(m) +"  "+ OriginNO.get(m) + "  " + Total.get(m)
			+ "  " + Destination.get(m) +"  "+ Origin.get(m));
		}
		
		try {
				
		    BufferedWriter bw = new BufferedWriter(new FileWriter(f2, true));
		    bw.write("{");
		    bw.newLine();
		    bw.write("\"nodes\":[");
		    bw.newLine();
		    
		    for(int i=0;i<NodeList.size()-1;i++){
		    bw.write( "{\"node\":" + i + ",\"name\":\""+NodeList.get(i)+"\"},"); 
	        bw.newLine(); 
		    }
		    
		    bw.write( "{\"node\":" + (NodeList.size()-1) + ",\"name\":\""+NodeList.get(NodeList.size()-1)+"\"}"); 
		    bw.newLine();
		    
		    bw.write("],");
		    bw.newLine();
		    bw.write("\"links\":[");
		    bw.newLine();
		    
		    for(int j=0; j<OriginNO.size()-1; j++){
		    bw.write("{\"source\":" +OriginNO.get(j)+",\"target\":" + DestinationNO.get(j)+
		    		",\"value\":" +Total.get(j) +",\"year\":" + Year.get(j) +"},"); // presently used are direct integers
		    bw.newLine();
		    }
		    
		    bw.write("{\"source\":" +OriginNO.get(OriginNO.size()-1)+",\"target\":" + DestinationNO.get(OriginNO.size()-1)+
		    		",\"value\":" +Total.get(OriginNO.size()-1) +",\"year\":"+ Year.get(OriginNO.size()-1) +"}"); // presently used are direct integers
		    bw.newLine();
		    
		    bw.write("]}");
	        bw.close(); 	
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		    System.out.println("Trasmission completed!");
		   

	}
}
