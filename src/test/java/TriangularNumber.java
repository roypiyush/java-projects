import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Scanner;

class TriangularNumber {

	static boolean checkTN(long n) {
		long i = (long)Math.sqrt(n * 2);
		long l = (i * (i + 1)) / 2;
		return n == l;
	}
	
	static long TN(long i) {
		return (i * (i + 1)) / 2;
	}
	
	static void process(long L, long R) {
	
		int count = 0;
		
		for (long k = L; k <= R; k++) {
			for (int j = 1; ; j++) {
				long a = TN(j);
				if(a > k >> 1)
					break;
				if(checkTN(k - a))
					count++;
			}
			
		}
		
		System.out.println(count);
	}

	public static void main(String[] args) {

		Scanner sc = null;
		BufferedInputStream br = null;
		try {
			br = new BufferedInputStream(System.in);
			sc = new Scanner(br);
			
			
			int testCase = Integer.parseInt(sc.nextLine().trim());
			
			while (testCase-- > 0) {
				String str[] = sc.nextLine().trim().split(" ");
				process(Long.parseLong(str[0]), Long.parseLong(str[1]));
			}

		} catch (Exception e) {
			System.out
					.println(String.format("Error due to %s", e.getMessage()));
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (sc != null) {
				sc.close();
			}
		}
	}

}
