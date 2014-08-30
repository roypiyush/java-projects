import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;


class AshishAndBinaryMatrix {

	static boolean process(String matrix[], int m) {
			
		for (int ignoreCol = 0; ignoreCol < m; ignoreCol++) {
			boolean add = true;
			HashSet<String> uniqueIntegers = new HashSet<String>();
			for (int i = 0; i < matrix.length; i++) {
				
				String str = matrix[i];
				int length = str.length();
				String binaryNum = str.substring(0, ignoreCol) + str.substring(ignoreCol+1, length);
				
				add = add & uniqueIntegers.add(binaryNum);
			}
			if(add)
				return true;
		}
		
		return false;
	}

	
	public static void main(String[] args) {

		Scanner sc = null;
		BufferedInputStream br = null;
		try {
			br = new BufferedInputStream(System.in);
			sc = new Scanner(br);
			
			
			int T = Integer.parseInt(sc.nextLine().trim());
			
			while(T-- > 0) {
				
				String str[] = sc.nextLine().trim().split(" ");
				
				int n = Integer.parseInt(str[0]); // row
				int m = Integer.parseInt(str[1]); // col
				
				String matrix[] = new String[n];
				for (int i = 0; i < matrix.length; i++) {
					matrix[i] = sc.nextLine().trim();
				}
				
				System.out.println(process(matrix, m) ? "Yes" : "No");
				
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
