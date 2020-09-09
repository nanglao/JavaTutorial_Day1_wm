import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {

		public static void fileRead() {
			 try {
		            //Fileクラスに読み込むファイルを指定する
		            File file = new File("/Users/Shared/convertCSV/input/file_layout.txt");

		            //ファイルが存在するか確認する
		            if(file.exists()) {

		                //FileReaderクラスのオブジェクトを生成する
		                FileReader filereader = new FileReader(file);

		                //filereaderクラスのreadメソッドでファイルを1文字ずつ読み込む
		                int data;
		                while((data = filereader.read()) != -1) {
		                    System.out.print((char) data);
		                }
		                //ファイルクローズ
		                filereader.close();

		            } else {
		                System.out.print("ファイルは存在しません");
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			
		}

}
