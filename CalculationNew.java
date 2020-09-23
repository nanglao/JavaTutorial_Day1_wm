import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculationNew {
	static boolean flag = false;
	static String tmp ="";
	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			findInner(scanner.nextLine());
			//findInner("(2+(4/10))*10");
			//findInner("6/(1-(3/4))");
		}catch (RuntimeException exception) {
            // Output expected RuntimeException.
        }
	}
	
	private static void findInner(String input) {
		String pattern = "^(.*)(\\((.*?)\\))(.*)$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(input);
		while(m.find()) {
			System.out.println("inner "+m.group(3));
			System.out.println("calc "+calc(m.group(3)));
			tmp= m.group(1) +calc(m.group(3)) + m.group(4);
//			System.out.println("inner "+ m.group(1) +calculation(m.group(3)) + m.group(4));
			System.out.println("tmp "+ tmp);
			findInner(tmp);
		}
		if(m.find() == false && flag == false){ //recursive
			flag = true;
			System.out.println("result "+ calc(tmp));
		}
	}
	private static double calc(String string) {
		String pattern = "^(.*)([\\+\\-\\*\\/]+)(.*)$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(string);
		double cal = 0;
		while (m.find()) {
			if(m.group(2).equals("*")) {
				cal = Double.valueOf(m.group(1)) * Double.valueOf(m.group(3));
			}else if(m.group(2).equals("/")) {
				cal = Double.valueOf(m.group(1)) / Double.valueOf(m.group(3));
			}else if(m.group(2).equals("+")) {
				cal = Double.valueOf(m.group(1)) + Double.valueOf(m.group(3));
			}else if(m.group(2).equals("-")) {
				cal = Double.valueOf(m.group(1)) - Double.valueOf(m.group(3));
			}
		}
		return cal;
	}
}
