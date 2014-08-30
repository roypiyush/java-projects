import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Scanner;


class NobitaAndString {

	static void process(String words[]) {
		int len = words.length;
		
		int i = 0, j = len - 1;
		
		while(i < j) {
			String t = words[i];
			words[i] = words[j];
			words[j] = t;
			i += 1;
			j -= 1;
		}
		
		for (String string : words) {
			System.out.print(string + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {

		Scanner sc = null;
		BufferedInputStream br = null;
		try {
			br = new BufferedInputStream(System.in);
			sc = new Scanner(br);
			
			
			int T = Integer.parseInt(sc.nextLine().trim());
			
			while(T-- > 0) {
				
				String str = sc.nextLine().trim();
				
				String words[] = str.split(" ");
				
				process(words);
				
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
