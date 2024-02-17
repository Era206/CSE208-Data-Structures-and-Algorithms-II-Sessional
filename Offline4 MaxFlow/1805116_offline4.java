
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
import java.util.*;
import java.util.LinkedList;

class MaxFlow {
    int bfs(int resGraph[][], int s, int t, int parent[])
    {
        int V = t+1;
        boolean visitedNode[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visitedNode[i] = false;

        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(s);
        visitedNode[s] = true;
        parent[s] = -1;

        while (list.size() != 0) {
            int u = list.poll();

            for (int v = 0; v < V; v++) {
                if (visitedNode[v] == false && resGraph[u][v] > 0) {
                    if (v == t) {
                        parent[v] = u;
                        return 1;
                    }
                    list.add(v);
                    parent[v] = u;
                    visitedNode[v] = true;
                }
            }
        }

        return 0;
    }

    int EdmondsKarp(int graph[][], int s, int t)
    {
        int u, v;
        int V = t+1;

        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        int parent[] = new int[V];

        int maxFlow = 0;

        while (bfs(rGraph, s, t, parent)==1) {

            int totalPath = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                totalPath = Math.min(totalPath, rGraph[u][v]);
            }

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[v][u] += totalPath;
                rGraph[u][v] -= totalPath;
            }

            maxFlow += totalPath;
        }

        return maxFlow;
    }

    public static void main(String[] args)
            throws java.lang.Exception
    {
       /* File file= new File("E:\Study\2-2\CSE208\Offline 4\src\in.txt");
        Scanner s = null;
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        Scanner s = new Scanner(System.in);
        int totTeams= s.nextInt();
        String[] teamNames = new String[totTeams];
        int[] teamWins = new int[totTeams];
        int[] teamLosses = new int[totTeams];
        int[] gameRemain = new int[totTeams];
        int gamePlan[][] = new int[totTeams][totTeams];
       for(int iTeam=0; iTeam<totTeams;iTeam++){
           teamNames[iTeam] = s.next();
           teamWins[iTeam] = s.nextInt();
           teamLosses[iTeam] = s.nextInt();
           gameRemain[iTeam] = s.nextInt();
           for (int iAgainst = 0; iAgainst < totTeams; iAgainst++) {
               gamePlan[iTeam][iAgainst] = s.nextInt();
           }
       }

       for(int iTeam=0; iTeam<totTeams;iTeam++){
           int[][] teamRemain = new int[totTeams-1][totTeams-1];
           for(int i=0; i < (totTeams-1);i++){

               for(int j=0; j< (totTeams-1); j++){
                  if(i<iTeam){
                     if(j<iTeam){
                         teamRemain[i][j]=gamePlan[i][j];
                     }
                     else{
                         teamRemain[i][j]=gamePlan[i][j+1];
                     }
                  }
                  else{
                      if(j<iTeam){
                          teamRemain[i][j]=gamePlan[i+1][j];
                      }
                      else{
                          teamRemain[i][j]=gamePlan[i+1][j+1];
                      }
                  }
               }
           }
           for(int i=0; i<(totTeams-1);i++){
               for (int j=0; j<i; j++){
                   teamRemain[i][j]=0;
               }
           }

           int firstLen=1;
           for(int i=1; i<=(totTeams-2); i++){
               firstLen*=i;
           }

           int n=firstLen+(totTeams-1)+2;
           int result[][] = new int[n][n];
           for(int i=0; i<n;i++){
               for(int j=0; j<n; j++){
                   result[i][j]=0;
               }
           }

           int k=1;
           int total =0;
               for(int i=0; i<(totTeams-1);i++){
                   for (int j=i+1; j<(totTeams-1); j++){
                       result[0][k]=teamRemain[i][j];
                       total+=teamRemain[i][j];
                       result[k][firstLen+i+1]= teamRemain[i][j];
                       result[k][firstLen+j+1]=teamRemain[i][j];
                       k++;
                   }
               }
           //System.out.println("total : " + total);

           int value = teamWins[iTeam]+gameRemain[iTeam];
           for(int i=0;i<totTeams;i++){
               if(i<iTeam){
                   result[firstLen+1+i][n-1]=value-teamWins[i];
               }
               else if(i>iTeam){
                   result[firstLen+i][n-1]=value-teamWins[i];
               }
               else {
                   i++;
               }
           }
           /*for(int i=0; i< n; i++){
               for(int j=0; j<n; j++){
                   System.out.print(result[i][j] + " ");
               }
               System.out.println();
           }*/
           MaxFlow m = new MaxFlow();
           int maxFlow = m.EdmondsKarp(result, 0, n-1);
           if(maxFlow<total) System.out.println(teamNames[iTeam] + " is eliminated.");

       }

    }
}