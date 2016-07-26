package leetcodestart;

import java.io.*;
import java.util.*;

public class Q5dataconvert {

    public static void main( String args[] ) throws IOException{
		String source ="/Users/luozhongyi/Desktop/2016Spring/CSE6242/hw2/HW2/Q5/temp_4.csv";
		String destination ="/Users/luozhongyi/Desktop/2016Spring/CSE6242/hw2/HW2/Q5/Q5a_4.csv";
		convert2report(source, destination);
	}

	public static void convert2report(String s, String d) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> OPEID =new ArrayList<String>(); //list 1
		ArrayList<String> INSTName =new ArrayList<String>(); //list 3
		// for node
		ArrayList<String> State =new ArrayList<String>(); // list 5
		ArrayList<Integer> StateNO =new ArrayList<Integer>(); // correspond to 5
		
		ArrayList<Integer> SATave =new ArrayList<Integer>(); // list 44
		ArrayList<Integer> UGDs =new ArrayList<Integer>(); // list 84
		
		ArrayList<Double> PCTPELL =new ArrayList<Double>(); //list 108
		//ArrayList<Double> CompRate =new ArrayList<Double>(); //list 119
		
		ArrayList<Integer> Salary =new ArrayList<Integer>(); //list 120
		ArrayList<Double> SalaryRate =new ArrayList<Double>(); //list 121
		
		String[] Statename = {"AL", "AK", "AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID",
				"IL", "IN","IA","KS","LY","LA","ME","MD","MA","MI", "MN","MS","MO","MT","NE",
				"NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX",
				"UT","VT","VA","WA","WV","WI","WY","AE","AA","AP"};
		
		File f1 = new File(s);
		File f2 = new File(d);
		
		Scanner con;
		String currentline;
		String[] list;
		int count=0;
		int f;
	
		
		try {
			con = new Scanner(f1);
			//con.nextLine(); //jump the title
			
			while(con.hasNextLine()){
				
				count++;
				currentline= con.nextLine();
				
				list=currentline.split(",");
				f=1;
				
				if((! list[1].equals("0")) 
						&& (!list[44].contains("N")) && (!list[44].contains("P"))
						&& (!list[84].contains("N")) && (!list[84].contains("P"))
						&& (!list[108].contains("N")) && (!list[108].contains("P"))
					    //&& (!list[119].contains("N")) && (!list[119].contains("P")) 
						&& (!list[120].contains("N")) && (!list[120].contains("P"))
						&& (!list[121].contains("N")) && (!list[121].contains("P")) ){
					
				OPEID.add((list[1]));
				INSTName.add(list[3]);
				State.add(list[5]);
				//state no
				SATave.add(Integer.parseInt(list[44]));
				UGDs.add(Integer.parseInt(list[84]));
				PCTPELL.add(Double.parseDouble(list[108]));
				//CompRate.add(Double.parseDouble(list[119]));
				Salary.add(Integer.parseInt(list[120]));
				SalaryRate.add(Double.parseDouble(list[121]));
				
				for(int i=0; i<Statename.length;i++){
					if(list[5].equals(Statename[i])){
						f=0;
						StateNO.add(i);
					}
				}
				
				if(f==1){
					OPEID.remove(OPEID.size() - 1);
					INSTName.remove(INSTName.size() - 1);
					State.remove(State.size() - 1);
					SATave.remove(SATave.size() - 1);
					UGDs.remove(UGDs.size() - 1);
					PCTPELL.remove(PCTPELL.size() - 1);
					Salary.remove(Salary.size() - 1);
					SalaryRate.remove(SalaryRate.size() - 1);
				}//pop out the ones that can't match to state
				
				}
				else
					System.out.println(currentline);
				
			
			}
				
			con.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(OPEID.size()+","+ INSTName.size()+","+ State.size()+","
				+StateNO.size()+","+SATave.size()+","+UGDs.size()+","+PCTPELL.size()+","+SalaryRate.size());
				//+CompRate.size()+","+Salary.size()+",";
		/*System.out.println(OPEID.get(1)+","+ INSTName.get(1)+","+ State.get(1)+","
				+StateNO.get(1)+","+SATave.get(1)+","+UGDs.get(1)+","+PCTPELL.get(1)+","
				+CompRate.get(1)+","+Salary.get(1)+","+SalaryRate.get(1));*/
		
		//export
			
		    BufferedWriter bw = new BufferedWriter(new FileWriter(f2, true));

		    for(int j=0;j<StateNO.size();j++){
		    	
		    bw.write(OPEID.get(j)+","+ INSTName.get(j)+","+ State.get(j)+",");
			bw.write(StateNO.get(j)+","+UGDs.get(j)+","+SATave.get(j)+","+PCTPELL.get(j)+","+Salary.get(j)+","+SalaryRate.get(j));
		    //bw.write(/*","+CompRate.get(j)+*/);
		    bw.newLine();
		    }

				
		    bw.close(); 
		
		    System.out.println("Trasmission completed!");
	}
    
}
