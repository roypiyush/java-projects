import java.util.Scanner;


public class MrMarsh {

	static int getMaxLength(char[][] land, int[][] land1, int[][] land2, int m, int n) {
		int max = 0;
		for (int si = 0; si < m - 1; si++) {
			for (int sj = 0; sj < n - 1; sj++) {
				for (int di = si + 1; di < m; di++) {
					for (int dj = sj + 1; dj < n; dj++) {
						int len = fenceLength(land, land1, land2, si, sj, di, dj);
						max = len > max ? len : max;
						if(land[di][dj] == 'x')
                            break;
					}
				}
			}
		}
		return max;
	}
	
	static int fenceLength(char[][] land, int[][] land1, int[][] land2, int si, int sj,
			int di, int dj) {
		
		int len = 0;
		
		if(land[si][sj] == '.' && land1[si][dj] == (land1[si][sj] + (dj - sj) * 46)) {
			len += (dj - sj);
		}
		else
			return 0;
		
		if(land[di][sj] == '.' && land1[di][dj] == (land1[di][sj] + (dj - sj) * 46)) {
			len += (dj - sj);
		}
		else
			return 0;

        if(land[si][sj] == '.' && land2[di][sj] == (land2[si][sj] + (di - si) * 46)) {
			len += (di - si);
		}
		else
			return 0;
	   
		if(land[si][dj] == '.' && land2[di][dj] == (land2[si][dj] + (di - si) * 46)) {
			len += (di - si);
		}
		else
			return 0;

		return len;
	}
	public static void main(String[] args) {
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			int m = scanner.nextInt();
			int n = scanner.nextInt();
			scanner.nextLine();
            char[][] land = new char[m][n];
			int[][] land1 = new int[m][n];
			int[][] land2 = new int[m][n];
			for (int i = 0; i < land1.length; i++) {
				String row = scanner.nextLine().trim();
				for (int j = 0; j < row.length(); j++) {
                    
                    land[i][j] = row.charAt(j);
                    
					land1[i][j] = (j > 0 ? land1[i][j - 1] : 0)  + (int)row.charAt(j);
					if(i == 0) {
						land2[i][j] = (int)row.charAt(j);
					}
					else {
						land2[i][j] = land2[i - 1][j] + (int)row.charAt(j);
						
					}
				}
			}
			int result = getMaxLength(land, land1, land2, m , n);
			System.out.println(result == 0 ? "impossible" : result);
			
		} catch (Exception e) {
		} finally {
			if(scanner != null)
				scanner.close();
		}
	
	}
}