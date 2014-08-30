import java.util.HashSet;


public class Sort {
	static String missingChar(String inputStr) {
			HashSet<Character> characters = new HashSet<Character>();
			
			String lowerString = inputStr.toLowerCase();
			
			int min = 1000, max = 0;
			for (int i = 0; i < lowerString.length(); i++) {
				char c = lowerString.charAt(i);
				int c1 = (int) c;
				int c2 = (int) c;
				
				characters.add(new Character(c));
				
				if(c1 > max)
					max = c1;
				if(c2 < min)
					min = c2;
			}
			
			for (int i = min; i <= max; i++) {
				Character character = (char) i;
				if(!characters.contains(character)) {
					return character.toString();
				}
			}
			
			return "NONE";

    }
	
	static void matrixMirror(int[] array, int N) {
        int matrix[][] = new int[N][N];
        
        int k = 0;
        for(int i = 0 ; i < N; i++) {
        	for (int j = 0; j < N; j++) {
				matrix[i][j] = array[k++];
			}
        }

        
    	for (int j = 0; j < N; j++) {
    		for(int i = 0 ; i < N; i++) {
    			System.out.println(matrix[i][j]);
    		}
        }
        
    }
	
	
	
	public static void main(String[] args) {
//		String str = "baADfc";
//		System.out.println(missingChar(str));
		
		int[] array = {1,2,3,4,5,6,7,8,9};
		int N = 3;
		matrixMirror(array, N);
	}
}
