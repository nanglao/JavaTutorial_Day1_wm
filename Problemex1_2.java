//1から10の2つ乱数を発生させ、その積を求めるものである。
public class Problemex1_2 {
	
	public static void main(String[] args) {
		int a  = (int)(Math.random() * 10)  + 1;     //  1～10の乱数を発生
		int b = (int)(Math.random() * 10)  + 1;     //  1～10の乱数を発生
		int c = (int)(Math.random() * 10)  + 1;     //  1～10の乱数を発生
		int result = mul(a,b,c);  //  二つの数の積を求める
		System.out.println(a + " * " + b +  " * " + c +" = " + result);
	}
   //  掛け算
	private static int mul(int a,int b,int c){
        return a * b * c;
	}
	
}
