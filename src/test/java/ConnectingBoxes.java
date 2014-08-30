import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;


class Range {
	int i;
	int j;
	public Range(int i, int j) {
		this.i = i;
		this.j = j;
	}
}

class ConnectingBoxes {

	static boolean process(LinkedList<Range> ranges, Range range, int min , int max) {
		if(ranges.size() == 0) {
			return ranges.add(range);
		}
		
		for(Range r : ranges) {
			if(r.i == range.i || r.j == range.j) {
				return false;
			}
			else {
				if((range.i > r.i && range.i < r.j) && (range.j > r.i && range.i < r.j))
					return false;
				else if(range.j > r.i && range.j < r.j)
					return false;
				else 
					return ranges.add(range);
			}
		}
		
		return false;
	}

	public static void main(String[] args) {

		Scanner sc = null;
		BufferedInputStream br = null;
		try {
			br = new BufferedInputStream(System.in);
			sc = new Scanner(br);
			
			
			String string = sc.nextLine().trim();
			
			int N = Integer.parseInt(string.split(" ")[0]);
			int testCase = Integer.parseInt(string.split(" ")[1]);
			
			LinkedList<Range> ranges = new LinkedList<Range>();
			while (testCase-- > 0) {
				String str[] = string.split(" ");
				int i = Integer.parseInt(str[0]);
				int j = Integer.parseInt(str[1]);
				
				Range range;
				if(i < j)
					range = new Range(i, j);
				else if(j < i)
					range = new Range(j, i);
				else {
					System.out.println("NO");
					continue;
				}
				
				System.out.println(process(ranges, range , 1, N) ? "YES" : "NO");
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