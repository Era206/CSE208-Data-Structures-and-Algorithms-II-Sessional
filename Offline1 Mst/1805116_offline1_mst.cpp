#include <bits/stdc++.h>
using namespace std;

# define INF 1e9

typedef pair<double , int> edgePair;

class disjointSet
{
    int *parent;
    int *rank;
public:
    int *get_parent(){
        return parent;
    }
	int *get_rank()
	{
	    return rank;
	}
	disjointSet(int n)
	{
		parent = new int[n];
		rank = new int[n];

		for (int i = 0; i < n; i++)
		{
			parent[i] = -1;
			rank[i] = 0;
		}
	}

};

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

    void addEdgeVal(int x, int y, double w)
	{
		edgelist.push_back({w, x, y});
		adj[x].push_back(make_pair(w, y));
        adj[y].push_back(make_pair(w, x));
	}

	int findSet(int i, disjointSet ds)
	{
		if (ds.get_parent()[i] == -1)
			return i;

		return ds.get_parent()[i] = findSet(ds.get_parent()[i],ds);
	}

	void Union(int x, int y, disjointSet ds)
	{
		int s1 = findSet(x, ds);
		int s2 = findSet(y, ds);

		if (s1 != s2)
		{
			if (ds.get_rank()[s1] < ds.get_rank()[s2])
			{
				ds.get_parent()[s1] = s2;
			}
			else if(ds.get_rank()[s2]<ds.get_rank()[s1])
			{
				ds.get_parent()[s2] = s1;
			}
			else{
                ds.get_parent()[s1] = s2;
                ds.get_rank()[s2] += 1;
			}
		}
	}

	double kruskals_mst()
	{
		sort(edgelist.begin(), edgelist.end());

		disjointSet s(V);
		double ans = 0;
		cout << "List of edges selected by Kruskal's:{" ;
		for (auto edge : edgelist)
		{
			double w = edge[0];
			int x = edge[1];
			int y = edge[2];

			if (findSet(x,s) != findSet(y,s))
			{
				Union(x, y,s);
				cout << "(" << x << ","<< y << ")";
				ans += w;
			}
		}
		cout << "}" << endl;
		return ans;
	}

	void prims_MST()
    {

        priority_queue< edgePair, vector <edgePair> , greater<edgePair> > priorQ;

        int src = 0;

        vector<double> value(V, INF);

        vector<int> parent(V, -1);

        vector<bool> visited(V, false);

        priorQ.push(make_pair(0, src));
        value[src] = 0;
        double total=0;

        while (!priorQ.empty())
        {

            int u = priorQ.top().second;

            if(visited[u] == true){
                    priorQ.pop();
                continue;
            }

            visited[u] = true;
            total += priorQ.top().first;
            priorQ.pop();



            list< pair< double, int> >::iterator i;
            for (i = adj[u].begin(); i != adj[u].end(); ++i)
            {
                int v = (*i).second;
                double weight = (*i).first;


                if (visited[v] == false && value[v] > weight)
                {

                    value[v] = weight;
                    priorQ.push(make_pair(value[v], v));
                    parent[v] = u;
                }
            }
        }

        cout << "Cost of the minimum spanning tree :" << total << endl;
         cout << "List of edges selected by Prim's:{";

        for (int i = 1; i < V; ++i)
            cout << "(" << parent[i] << ","<< i << ")";
        cout << "}" << endl;

        //return total;
    }


};
int main()
{
	ifstream file_name("mst.txt");
    int n,m,x,y;
    double w;
    file_name>>n;
    Graph g(n);
    file_name>>m;
    for(int i=0; i<m; i++)
    {
        file_name>>x>>y>>w;
        g.addEdgeVal(x, y, w);

    }
    //Graph g(6);
	/*g.addEdgeVal(0, 1, 1);
	g.addEdgeVal(1, 3, 5);
	g.addEdgeVal(3, 0, 3);
	g.addEdgeVal(3, 4, 1);
	g.addEdgeVal(1, 4, 1);
	g.addEdgeVal(1, 2, 6);
	g.addEdgeVal(5, 2, 2);
	g.addEdgeVal(2, 4, 4);
	g.addEdgeVal(5, 4, 4);*/

	g.prims_MST();
	g.kruskals_mst();

	return 0;
}
