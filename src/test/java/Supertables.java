import java.io.BufferedInputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;




class TestClass {
	
	public static int combination(int n, int k)
	{
	    return permutation(n) / (permutation(k) * permutation(n - k));
	}

	public static int permutation(int i)
	{
	    if (i == 1)
	    {
	        return 1;
	    }
	    return i * permutation(i - 1);
	}
	
	public static void main(String[] args) {

		Scanner sc = null;
		
		try {
			sc = new Scanner(new BufferedInputStream(System.in));

			long numberOfTestCases = Integer.parseInt(sc.nextLine());
			
			LinkedList<Integer> array = new LinkedList<Integer>();
			while (numberOfTestCases-- > 0) {
				array.add(Integer.parseInt(sc.nextLine().trim()));
			}
			
			Iterator<Integer> iterator = array.iterator();
			Integer prev = iterator.next();
			
			int count = 1;
			int totalCount = 0;
			while (iterator.hasNext()) {
				Integer curr = iterator.next();
				
				if(prev < curr)
					count = count + 1;
				else
					count = 1;
				totalCount += count;
				prev = curr;
			}
			System.out.print(totalCount);
			
		} catch (Exception e) {
			System.out
					.println(String.format("Error due to %s", e.getMessage()));
		} finally {
			if (sc != null) {
				sc.close();
			}
		}

	}

	

}
