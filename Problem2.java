
public class Problem2 {
	
	public static void main(String[] args) {
		
		//increment();
		//decrement();
		pyramid();
		
	}

	private static void pyramid() {
		int n = 5;
		int px = n;
		int py = n;
		for(int i=1 ; i<=n ;i++) {
			for(int j=1 ; j< n*2 ;j++) {
				if(j>=px && j<=py) {
					System.out.print("*");
				}
				else {
					System.out.print("");
				}
			}
			px--;
			py++;
			System.out.println();
		}
	}

	private static void increment() {
		//* increment 
		for(int i=1 ; i<=5 ;i++) {
			for(int j=5 ; j>=1 ;j--) {
				if(i>=j) {
					System.out.print("*");
				}
			}
			
			System.out.println();
		}
	}

	
	private static void decrement() {
		//* decrement 
		for(int i=1 ; i<=5 ;i++) {
			for(int j=5 ; j>=i ;j--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	
	
}
