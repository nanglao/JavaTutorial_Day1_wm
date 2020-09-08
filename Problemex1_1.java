//1から10の2つ乱数を発生させ、その和を求めるものである。このとき、このプログラムを以下のように改造しなさい。

public class Problemex1_1 {
	
	public static void main(String[] args) {
        int a = (int)(Math.random() * 10)  + 1;     //  1～10の乱数を発生
        int b = (int)(Math.random() * 10)  + 1;     //  1～10の乱数を発生
        int result = add(a,b);  
        System.out.println(a + " + " + b + " = " + result);
    }
    //  足し算
    static int add(int a,int b){
        return a + b;
    }

}
