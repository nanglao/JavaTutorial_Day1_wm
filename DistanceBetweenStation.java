import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DistanceBetweenStation {

	private static String READ_FILE_YAMANOTELINE = "/Users/Shared/distanceBetweenStation/input/山手線駅間所要時間_入力.txt";
	private static String READ_FILE_FROM_TOKYOSTATION = "/Users/Shared/distanceBetweenStation/conf/東京駅からの所要時間.txt";
	private static String WRITE_FILE = "/Users/Shared/distanceBetweenStation/output/Time_Required_FromTokyoStation.txt";
	private static HashMap<String,String> hmap = new HashMap<String,String>(); 
	private static String[] stationListName;
	private static String[] stationListNameTime;
	private static StringBuilder sBuilder;
	static ArrayList<TrainStation>  stData= new ArrayList<TrainStation>();
	private static ArrayList<TrainStation> differentMinuteDatalist = new ArrayList<TrainStation>();
	static int totalMinute = 0;
	static boolean checkPointFlag = false;
	static boolean foundFirst = false;
	static boolean foundLast = false;
	static boolean startCount = false;
	static int startStationIndex = -1;
	static int endStationIndex = -1;
	static int lastDifferentMinute = 0;

	public static void main(String[] args){
		fileReadDistanceFromTokyo(READ_FILE_FROM_TOKYOSTATION);
		defineDifferentStationTime();
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
	        	System.out.println(stationListName[0] + " " + stationListName[1] );
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
	        		startStationIndex = returnIndex(stationListNameTime[0]);
	        		endStationIndex = returnIndex(stationListNameTime[1]);
//	        		System.out.println(stationListNameTime[0] + " " + stationListNameTime[1]+ " " + calculation());
	        	   sBuilder.append(calculation()+ System.lineSeparator() );
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

	private static int calculation() {
		startCount = false;
		foundFirst = false;
		foundLast = false;
		int total = 0;
		totalMinute = 0;
		for (int i = 0; i < differentMinuteDatalist.size(); i++) {
			
			total += Integer.parseInt(differentMinuteDatalist.get(i).time);
			if (i == startStationIndex) {
				foundFirst = true;
			} else if (i == endStationIndex) {
				foundLast = true;
			}
			if (foundFirst == true || foundLast == true) {
				if (startCount == true) {
					totalMinute += Integer.parseInt(differentMinuteDatalist.get(i).time);
				}
				startCount = true;
				if (foundFirst == true && foundLast == true) {
					startCount = false;
					foundFirst = false;
					foundLast = false;
				}
			}
		}
		total += lastDifferentMinute;//key point for check point *********
		
		int finalResult = 0;
		int differentResult = 0;
		differentResult = total-totalMinute;
		if(totalMinute>differentResult)
			finalResult = differentResult;
		else
			finalResult = totalMinute;
// 	    System.out.println("calculation logic for given two station = " + totalMinute);
//		System.out.println("shortest time for given two station result = " + finalResult);
		return finalResult;
	}

	private static void defineDifferentStationTime() {
		int dm = 0;
		lastDifferentMinute = 0;
		for (int i = 0; i < stData.size(); i++) {
			if (i == 0) {
				int tmp = Integer.parseInt(stData.get(i).time) - dm;
				differentMinuteDatalist.add(new TrainStation(stData.get(i).stationName, String.valueOf(tmp)));
				dm = Integer.parseInt(stData.get(i).time);
			} else if (stData.size() - 1 == i) {// last
				int tmp = Integer.parseInt(stData.get(i).time) - dm;
				lastDifferentMinute = tmp;
			} else {
				int tmp = Integer.parseInt(stData.get(i).time) - dm;
				differentMinuteDatalist.add(new TrainStation(stData.get(i).stationName, String.valueOf(tmp)));
				dm = Integer.parseInt(stData.get(i).time);
			}
		}
	}

	private static int returnIndex(String startStation) {
		for (int i = 0; i < differentMinuteDatalist.size(); i++) {
			if (differentMinuteDatalist.get(i).stationName.equals(startStation)) {
				return i;
			}
		}
		return -1;
	}
}