#include<iostream>
#include <bits/stdc++.h>
#include<list>
#include<vector>
using namespace std;

# define INF 1e9

typedef pair<double , int> edgePair;

class Graph
{
	vector<vector<double>> edgelist;
	int V;
	list< pair<double, int> > *adj;



public:
	Graph(int V)
	{
		this->V = V;
		adj = new list<edgePair> [V];
	}

    void addEdge(int x, int y, double w)
	{
		edgelist.push_back({w, x, y});
		adj[x].push_back(make_pair(w, y));
	}

	void dijkstra(int src,int dest)
    {

        priority_queue< edgePair, vector <edgePair> , greater<edgePair> > priorQ;


        list<int> queue;

        vector<double> dist(V, INF);

        vector<int> parent(V, -1);

        for(int i=0; i<V; i++){
            parent[i]=i;
        }

        priorQ.push(make_pair(0, src));
        dist[src] = 0;
        double total=0;

        while (!priorQ.empty())
        {

            int u = priorQ.top().second;

            total += priorQ.top().first;
            priorQ.pop();



            list< pair< double, int> >::iterator i;
            for (i = adj[u].begin(); i != adj[u].end(); ++i)
            {
                int v = (*i).second;
                double weight = (*i).first;


                if ( dist[u] + weight  < dist[v])
                {

                    dist[v] =dist[u] + weight;
                    priorQ.push(make_pair(dist[v], v));
                    parent[v] = u;
                }
            }
        }

        int j=dest, i=1, tot=0;
        int *sequence = new int[V];
        sequence[0]= dest;
        while(parent[j]!=j){
            sequence[i]=parent[j];
            j=parent[j];
            i++;
            tot++;
        }
        sequence[i]=src;


        cout << "Shortest path cost: " << dist[dest] << endl;
        cout << sequence[tot] ;

         for(i=tot-1; i>=0; i--){
            cout << " -> "<< sequence[i] ;
        }
        cout << endl;
}

void bellmanFord(int src, int dest){
        vector<double> dist(V, INF);

        vector<int> parent(V, -1);

        for(int i=0; i<V; i++){
            parent[i]=i;
        }
        dist[src] = 0;

        for (int i = 0; i < V ; i++)
        {
            for (auto edge : edgelist)
		{
			double weight = edge[0];
			int u = edge[1];
			int v = edge[2];
			if ( dist[u] + weight < dist[v]){
                    if(i==(V-1)){
                        cout << "The graph contains a negative cycle" << endl;
                        return;
                    }
                dist[v] = dist[u] + weight;
                parent[v] = u;
		}

        }
        }
        int j=dest, i=1, tot=0;
        int *sequence = new int[V];
        sequence[0]= dest;
        while(parent[j]!=j){
            sequence[i]=parent[j];
            j=parent[j];
            i++;
            tot++;
        }
        sequence[i]=src;

        cout << "The graph does not contain a negative cycle" << endl;
        cout << "Shortest path cost: " << dist[dest] << endl;
        cout << sequence[tot] ;

         for(i=tot-1; i>=0; i--){
            cout << " -> "<< sequence[i] ;
        }
 }
};

int main()
{
    ifstream file_name("offline.txt");
    int n,m,x,y;
    double w;
    file_name>>n;
    Graph g(n);
    file_name>>m;
    for(int i=0; i<m; i++)
    {
        file_name>>x>>y>>w;
        g.addEdge(x, y, w);

    }
    int destination;
    int source;
    file_name>>source>>destination;

    g.dijkstra(source,destination);
    g.bellmanFord(source, destination);



    return 0;
}


