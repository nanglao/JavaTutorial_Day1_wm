//1から10の2つ乱数を発生させ、その和を求めるものである。このとき、このプログラムを以下のように改造しなさい。
//1から10までの乱数を発生させてそれを画面に表示し、5以上ならば、”5以上です”と表示するプログラムを作りなさい。

public class RandomNumber {
	
	public static void main(String[] args) {
        int a = (int)(Math.random() * 10)  + 1;     //  1～10の乱数を発生
        int b = (int)(Math.random() * 10)  + 1;     //  1～10の乱数を発生
        int c = (int)(Math.random() * 9) ;     //  0～10の乱数を発生
        int result = add(a,b);  
        System.out.println(a + " + " + b + " = " + result);
        
        if(b > 5) {
        	System.out.print(b + "は 5以上です " );
        }
    }
    //  足し算
    static int add(int a,int b){
        return a + b;
    }

}
