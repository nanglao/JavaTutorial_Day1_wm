import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class testing {
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
	
	public static class Graph 
	{ 
		int V; // No. of vertices 
		Vector<Integer>[] adj; // No. of vertices 
		static int level; 
		
		// Constructor 
		@SuppressWarnings("unchecked") 
		Graph(int V) 
		{ 
			this.V = V; 
			this.adj = new Vector[2 * V]; 

			for (int i = 0; i < 2 * V; i++) 
				this.adj[i] = new Vector<>(); 
		} 

		// adds an edge 
		public void addEdge(int v, int w, int weight) 
		{ 

			// that is V. 
			if (weight == 2) 
			{ 
				adj[v].add(v + this.V); 
				adj[v + this.V].add(w); 
			} else // Weight is 1 
				adj[v].add(w); // Add w to v's list. 
		} 

		// print shortest path from a source vertex 's' to 
		// destination vertex 'd'. 
		public int printShortestPath(int[] parent, int s, int d) 
		{ 
			level = 0; 

			// If we reached root of shortest path tree 
			if (parent[s] == -1) 
			{ 
				System.out.printf("Shortest Path between"+ 
								"%d and %d is %s ", s, d, s); 
				return level; 
			} 

			printShortestPath(parent, parent[s], d); 

			level++; 
			if (s < this.V) 
				System.out.printf("%d ", s); 

			return level; 
		} 

		// finds shortest path from source vertex 's' to 
		// destination vertex 'd'. 

		// This function mainly does BFS and prints the 
		// shortest path from src to dest. It is assumed 
		// that weight of every edge is 1 
		public int findShortestPath(int src, int dest) 
		{ 
			boolean[] visited = new boolean[2 * this.V]; 
			int[] parent = new int[2 * this.V]; 

			// Initialize parent[] and visited[] 
			for (int i = 0; i < 2 * this.V; i++) 
			{ 
				visited[i] = false; 
				parent[i] = -1; 
			} 

			// Create a queue for BFS 
			Queue<Integer> queue = new LinkedList<>(); 

			// Mark the current node as visited and enqueue it 
			visited[src] = true; 
			queue.add(src); 

			while (!queue.isEmpty()) 
			{ 

				// Dequeue a vertex from queue and print it 
				int s = queue.peek(); 

				if (s == dest) 
					return printShortestPath(parent, s, dest); 
				queue.poll(); 

				for (int i : this.adj[s]) 
				{ 
					if (!visited[i]) 
					{ 
						visited[i] = true; 
						queue.add(i); 
						parent[i] = s; 
					} 
				} 
			} 
			return 0; 
		} 
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
	        	   sBuilder.append(aa + System.lineSeparator() );
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
		System.out.println("ENtere.....");
		String stationName1 = startStationName;
		String stationName2 = endStationName;
		int st1duration = 0;
		int st2duration = 0;
		int index1 = 0;
		int index2 = 0;
		int result = 0;
		int originalTime = 0;
		int temp = 0;
		boolean flagAntiCLockWise = false;
		boolean flagCheckPoint = false;
		
		int V = stData.size()-1; 
		Graph g = new Graph(V);
		originalTime = Integer.parseInt(stData.get(0).getTime());
		
		for (int i=1;i<stData.size();i++) {
			temp = Integer.parseInt(stData.get(i).getTime()) - originalTime;
			originalTime = Integer.parseInt(stData.get(i).getTime()) ;
			int ss = i;
			int ss2 =i-1;
			if(stData.size()-1 == i) {
				g.addEdge(ss2, 0, temp); 
			} else {
				ss = i;
				g.addEdge(ss2, ss, temp);
				 
			}
//			System.out.println("g.addEdge("+ss2+","+ss+","+temp+")");
			
				if(stData.get(i).getStationName().equals(stationName1)) { 
					index1 = i;
					flagAntiCLockWise = false;
					st1duration = Integer.parseInt(stData.get(i).getTime());
//					System.out.println("stationName1;;;;;" + st1duration);
				} 
				else if(stData.get(i).getStationName().equals(stationName2)) {
					flagAntiCLockWise = true;
					index2 = i;
					st2duration = Integer.parseInt(stData.get(i).getTime());
//					System.out.println("stationName2;;;;;" + st2duration);
					if (st1duration == 0) {
						flagCheckPoint = true;
					}
				} 
			}
		if (flagCheckPoint) {
			result = st2duration - st1duration;
			
			System.out.println("Result;;;;;" + result);
		}else {
			if(index1 < index2) {
				int src = index1, dest = index2; 
				result = g.findShortestPath(src, dest);
			}  if (index2 < index1){
				int src = index2, dest = index1; 
				result = g.findShortestPath(src, dest);
			}	
		}
//		System.out.println("cccc"+flagAntiCLockWise);
//		System.out.println("stationName1;;;;;" + stationName1 +"    stationName2;;;;;" + stationName2 );
//		System.out.println("index1;;;;;" + index1 +"  index2;;;;;" + index2);
//		//int src = index1, dest = index2; 
//		int src = 28, dest = 3; 
//		System.out.printf("\nShortest Distance between" + 
//							" %d and %d is %d\n", src, 
//							dest, g.findShortestPath(src, dest)); 
//		result = st2duration - st1duration;
//		System.out.println("Result;;;;;" + result);
		
			if(result < 0){
				//plus to 
				result *= -1;
			}	
		return result;
	}

}
