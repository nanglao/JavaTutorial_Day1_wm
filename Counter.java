
public class Counter {
	
	 //  カウント
    private int count = 0;
    //  コンストラクタ
    public void reset(){
        count = 0;
    }
    //  カウント
    public void count(){
        count++;
    }
    //  値の取得
    public int getCount(){
        return count;
    }

}
