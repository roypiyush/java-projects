import java.util.Scanner;


public class ManasaAndStones {

	public static void main(String[] args) {

		
		Scanner scanner = null;
		
		try {
			
			scanner = new Scanner(System.in);
			
			int T = scanner.nextInt();
			
			while(T-- > 0) {
				
				int n = scanner.nextInt();
				int a = scanner.nextInt();
				int b = scanner.nextInt();
				
				process(n - 1, a, b);
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			if(scanner != null)
				scanner.close();
		}

	}

	private static void process(int n, int a, int b) {
		
		long pow = (long) Math.pow(2, n);
		for (int i = 0; i < pow; i++) {
			String format = "%1$" + n + "s";
			String binary = String.format(format, Integer.toBinaryString(i)).replace(' ', '0');
			
			int count = 0;
			for(int j = 0; j < n; j++) {
				count += (binary.charAt(j) == '0' ? a : b);
			}
			
			System.out.print(count + (i + 1 == n ? "" : " "));
		}
		
	}

}
