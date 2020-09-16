import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DistanceBetweenStation {
	
	private static String READ_FILE_YAMANOTELINE = "/Users/Shared/distanceBetweenStation/input/山手線駅間所要時間_入力.txt";
	private static String READ_FILE_FROM_TOKYOSTATION = "/Users/Shared/distanceBetweenStation/conf/東京駅からの所要時間.txt";
	private static String WRITE_FILE = "/Users/Shared/distanceBetweenStation/output/Time_Required_FromTokyoStation.txt";
	private static HashMap<String,String> hmap = new HashMap<String,String>(); 
	private static String[] stationListName;
	private static String[] stationListNameTime;
	private static StringBuilder sBuilder;
	static ArrayList<TrainStation>  stData= new ArrayList<TrainStation>();
	
	public static void main(String [] args) {
		fileReadDistanceFromTokyo(READ_FILE_FROM_TOKYOSTATION);
		fileReadYomanoteLine(READ_FILE_YAMANOTELINE);
		
	}

	private static void fileReadDistanceFromTokyo(String readFileName) {
		try {
			File file = new File(readFileName);
			if(file.exists()) {
				BufferedReader br=new BufferedReader(new FileReader(file));     
				String data = null;
	           while((data = br.readLine()) != null) {
	        	   stationListName = data.split( "," , -1 );
	        	   hmap.put(stationListName[0], stationListName[1]);
                   stData.add(new TrainStation(stationListName[0],stationListName[1]));
	           }
	           br.close();
			}
		 }catch (IOException e) {
				e.printStackTrace();
			}  
	}

	private static void fileReadYomanoteLine(String readFile) {
		try {
			File file = new File(readFile);
			if(file.exists()) {
				System.out.print("ファイルは存在しています\n");
				//FileReaderクラスのオブジェクトを生成する
				FileWriter fw = new FileWriter(new File(WRITE_FILE));
				FileReader filereader = new FileReader(file);
				BufferedReader br=new BufferedReader(filereader); 
				sBuilder=new StringBuilder();  
				String data;
	           while((data = br.readLine()) != null) {
	        	   sBuilder.append(data);
	        	   sBuilder.append( " " );
	        	   stationListNameTime = data.split(" ");
	        	   int aa = calculateRouteTime(stationListNameTime[0],stationListNameTime[1]);
	        	  // System.out.println(stationListNameTime[0] + " " + stationListNameTime[1] );
	        	   sBuilder.append(aa + System.lineSeparator() );
	        	   //System.out.println(hmap.get(stationListNameTime[0]));
	           }
	           fw.write(sBuilder.toString());
	          System.out.print(sBuilder.toString());
	           br.close();
	           fw.close();
	           
			}
		 }catch (IOException e) {
				e.printStackTrace();
			}  
	}

	private static int calculateRouteTime(String startStationName, String endStationName) {
		String stationName1 = startStationName;
		String stationName2 = endStationName;
		int st1duration = 0;
		int st2duration = 0;
		int result = 0;
		
		for (int i=0;i<stData.size();i++) {
				if(stData.get(i).getStationName().contains(stationName1)) {
					st1duration = Integer.parseInt(stData.get(i).getTime());
					System.out.println("stationName1;;;;;" + st1duration);
				} 
				else if(stData.get(i).getStationName().contains(stationName2)) {
					st2duration = Integer.parseInt(stData.get(i).getTime());
					System.out.println("stationName2;;;;;" + st2duration);
				} 
			}
		result = st2duration - st1duration;
		System.out.println("Result;;;;;" + result);
		
			if(result < 0){
				//plus to 
				result *= -1;
			}	
		return result;
	}
}
