import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


class PilgrimsAndPortals {
	
	static void process(int adjMatrix[][] , int k) {
		
		Set<Integer> visitedVertices = new HashSet<Integer>();
		
		int[] adjList = adjMatrix[0];
		
		int min = Integer.MAX_VALUE;
		for (int i = 1; i < k; i++) {
			
			visitedVertices.clear();
			visitedVertices.add(0);
			
			if(adjList[i] > 0) {
				int dis = adjList[i] + depthFirstSearchVisit(visitedVertices, adjMatrix, k, i);
				if(dis < min)
					min = dis;
			}
		}
		
		System.out.println(min);
	}

	static int depthFirstSearchVisit(Set<Integer> visitedVertices, int adjMatrix[][], int shrines, int currentVertex) {
		
		visitedVertices.add(currentVertex);
		int[] adjList = adjMatrix[currentVertex];
		
		int min = 0;
		for (int i = 0; i < shrines; i++) {
			
			if(adjList[i] > 0) {
				
				if(!visitedVertices.contains(i)) {
					int distanceTravelled = adjList[i] + depthFirstSearchVisit(visitedVertices, adjMatrix, shrines, i);
					
					if(min == 0)
						min = distanceTravelled;
					if(distanceTravelled < min)
						min = distanceTravelled;
				}
			}
		}
		
		return min;
		
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
				
				int n = Integer.parseInt(str[0]); // number of cities
				int m = Integer.parseInt(str[1]); // number of roads
				int k = Integer.parseInt(str[2]); // number of shrines
				
				int adjMatrix[][] = new int[n][n];
				
				for (int i = 0; i < m; i++) {
					
					String adj[] = sc.nextLine().trim().split(" ");
					
					int a = Integer.parseInt(adj[0]) - 1; // city
					int b = Integer.parseInt(adj[1]) - 1; // city
					int c = Integer.parseInt(adj[2]); // distance
					
					adjMatrix[a][b] = c;
					adjMatrix[b][a] = c;
				}
				
				process(adjMatrix, k);
				
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
