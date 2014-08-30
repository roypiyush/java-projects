import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Scanner;

class CountEnemies {

	static void process(String str) {

		String blocks[] = str.split("\\*");

		int count = 0;
		for (int i = 0; i < blocks.length; i++) {

			String x = blocks[i].replace("O", "");
			String o = blocks[i].replace("x", "");
			
			count = count + (x.length() == 0 ? o.length() : 0);
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
				String str = sc.nextLine().trim();
				process(str);
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
