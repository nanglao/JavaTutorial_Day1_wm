//
public class Problemex1_3 {
	public static int result,a,b;
	public static String c,d;
	
	public static void main(String[] args) {
		 a  = (int)(Math.random() * 10)  + 1;     //  1～10の乱数を発生
		 b = (int)(Math.random() * 10)  + 1;     //  1～10の乱数を発生
		result = mul(a,b);  //  二つの数の積を求める
		System.out.println(a + " * " + b + " = " + result);
		for (int i =1 ; i <= a ;i++ ) {
			for (int j=1; j <= b ;j++ ) {
			 c = "■"+ " ";
			System.out.print(c);
			}
			System.out.println();
		}
			
	}
   //  掛け算
	private static int mul(int a,int b){
        return a * b;
	}

}
