import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TxtConvertToCSV {
	
	public static String filePath_koteicho = "/Users/Shared/convertCSV/input/労働者名簿_固定長.txt";
	public static String filePath__fileLayout = "/Users/Shared/convertCSV/input/file_layout.txt";
	public static String filePath__fileLayout1 = "/Users/Shared/convertCSV/input/file_layout.txt";
	public static String filePath__CSVfile = "/Users/Shared/convertCSV/output/労働者名簿_固定長NAN.csv";
	public static ArrayList<String> arrayString;
	private static String outputFile;
	public static StringBuilder sBuilder;
	static String[] filterd;
	 
	
	public static void main(String[] args) throws IOException {
		//file_layout.txtからデータを取得
		fileRead (filePath__fileLayout);
		fileRead (filePath_koteicho);
		outputFile = fileRead(filePath_koteicho);
		fileWrite();
	  }
	
  private static void fileWrite() throws IOException {
        // CSV ファアリ出力ため
	try {
        File filePathOutput = new File("/Users/Shared/convertCSV/output/労働者名簿_固定長NAN.csv");
	        if(!filePathOutput.exists()) {
	        	filePathOutput.createNewFile();
	        }
	            if (filePathOutput.exists() && filePathOutput.canWrite()){
	              FileWriter fw = new FileWriter(filePathOutput);
				  System.out.print("sdsdsdsds"+ outputFile);
	              fw.write(outputFile);
	              fw.close();
	            } else {
	            	System.out.print("ファイルは書きこめません\n");
	            }
        }catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}           
		
}	
	//FileReaderクラス
	private static String fileRead(String readFileName) throws IOException {
		
			 try {
				  File file = new File(readFileName);
				      //ファイルが存在するか確認する
						System.out.print("\n");
						//FileReaderクラスのオブジェクトを生成する
						FileReader filereader = new FileReader(file);
						BufferedReader br = new BufferedReader(filereader); 
						sBuilder = new StringBuilder();     
						String data = null;
		                while((data = br.readLine()) != null) {
			               	  
			               String str = data;
			               String[] arrayList = str.toString().split( "," , 0 );
				               for(int i =0;i < arrayList.length;i++) {
			            	   System.out.println(arrayList[i]);
				               }
			            	   	String pattern = "^(.{0,5})(.{0,30})(.{0,1})(.{0,8})(.{0,8})(.{0,10})(.{0,11})(.{0,15})(.{0,15})(.{0,12})$";
		            	   		Pattern r = Pattern.compile(pattern);
		            	   		Matcher m = r.matcher(data);
		            	   		if (m.find()) {
		        	                String[] filterd = {
		        		            		m.group(1),
		        		            		m.group(2),
		        		            		m.group(3),
		        		            		m.group(4),
		        		            		m.group(5),
		        		            		m.group(6),
		        		            		m.group(7),
		        		            		m.group(8),
		        		            		m.group(9),
		        		            		m.group(10),
		        		            		};
		        		            
		        		            // 文字列配列をカンマ区切りの文字列に変換する
		        	                data = String.join(",", filterd);  
			            	  
				             }     
		            	   		//String csvRow = String.join(",", filterd);
		        		           sBuilder.append(data);
		        		           sBuilder.append("\n");
		        		           //Builder.toString();
		        		           //sBuilder.append("\n");
		        		           
		               } 
		             filereader.close();  
				} catch (FileNotFoundException e) {
					System.out.println(e);
				} catch (IOException e) {
					System.out.println(e);
				}
			 
			 return sBuilder.toString();
		}	
	
}
