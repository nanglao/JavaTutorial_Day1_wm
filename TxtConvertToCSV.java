import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class TxtConvertToCSV {
	
	public static void main(String[] args) {
		
		//file_layout.txtからデータを取得
		FileRead fileReadIndex = new FileRead();
		fileReadIndex.fileRead();
	    
	    String filePath = "/Users/Shared/convertCSV/input/労働者名簿_固定長.txt";
	    if (Files.exists(Paths.get(filePath))) {
	      try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
	        stream.forEach(line -> {
	            
	        	// regex patternを使って文字列を空白区切りで配列に変換する
	            String pattern = "^(.{0,5})(.{0,30})(.{0,1})(.{0,8})(.{0,8})(.{0,10})(.{0,11})(.{0,15})(.{0,15})(.{0,12})$";

	            // Pattern objectを作成
	            Pattern r = Pattern.compile(pattern);

	            // matcher objectを作成
	            Matcher m = r.matcher(line);
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
		            String csvRow = String.join(",", filterd);
		            System.out.println(csvRow);
		            
		            // CSV ファアリ出力ため
		            File filePathOutput = new File("/Users/Shared/convertCSV/output/労働者名簿_固定長NAN.csv");	            
		            PrintWriter pw = null;
					try {
						pw = new PrintWriter(filePathOutput);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					int data ;
					StringBuilder sb = new StringBuilder();
					sb.append(csvRow);
					pw.write(sb.toString());
					pw.close();  
		           		            
	            } else {
	                System.out.println("NO MATCH");
	            }

	        });
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    } else {
	      System.out.print("ファイルは存在しません");
	    }
	  }

}
