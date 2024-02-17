#include<iostream>
#include<bits/stdc++.h>
using namespace std;

#define INF 1e9

int main()
{
    ifstream file_name("offline.txt");
    int n,m,x,y;
    int w;
    file_name >> n >> m;

    int shortestPath[n][n];

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            if(i==j) shortestPath[i][j]=0;
            else
            shortestPath[i][j] = INF;
        }
    }

    for(int i=0; i<m; i++)
    {
       file_name>>x>>y>>w;
       shortestPath[x][y] = w;
    }

    for(int k=1; k<=n; k++){
        for(int i=1; i<=n;i++){
            for(int j=1;j<=n;j++){
                if(shortestPath[i][j]>(shortestPath[i][k]+shortestPath[k][j]))
                    shortestPath[i][j]=shortestPath[i][k]+shortestPath[k][j];
            }
        }
    }

    cout << "Shortest distance matrix" << endl;

     for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            if(shortestPath[i][j]==INF)
                cout << "INF " ;
            else
            cout << shortestPath[i][j] << " ";
        }
        cout << endl;
    }

}

