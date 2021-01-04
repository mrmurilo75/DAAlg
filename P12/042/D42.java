import java.util.*;

// Classe para no da fila do BFS: pares (no, capacidade)
class NodeQ {
	public int node;
	public int flow;

	NodeQ(int n, int f) {
		node = n;
		flow = f;
	}
}

// Classe que representa um grafo
class Graph {
	int n;					   // Numero de nos do grafo
	Vector<Vector<Integer>> adj; // Lista de adjacencias
	boolean cap[][];				 // Matriz de capacidades

	Graph(int n) {
		this.n = n;
		adj = new Vector<>();
		for (int i=0; i<=n; i++) adj.add(new Vector<Integer>());
		cap = new boolean[n+1][n+1]; // +1 se os nos comecam em 1 ao inves de 0
	}

	void addLink(int a, int b, int c) {
		// adjacencias do grafo nao dirigido, porque podemos ter de andar no sentido
		// contrario ao procurarmos caminhos de aumento
		adj.get(a).add(b);
		adj.get(b).add(a);
		cap[a][b] = cap[b][a] = true;
	}

	// BFS para encontrar caminho de aumento
	// devolve valor do fluxo nesse caminho
	int bfs(int s, int t, int[] parent) {
		for (int i=1; i<=n; i++) parent[i] = -1;

		parent[s] = -2;
		Queue<NodeQ> q = new LinkedList<>(); // fila do BFS
		// inicializar com no origem e capacidade infinita
		q.add(new NodeQ(s, Integer.MAX_VALUE));

		while (!q.isEmpty()) {
			// returar primeiro no da fila
			int cur = q.peek().node;
			int flow = q.peek().flow;
			q.poll();

			// percorrer nos adjacentes ao no atual (cur)
			for (int next : adj.get(cur)) {
			// se o vizinho ainda nao foi visitado (parent==-1)
			// e a aresta respetiva ainda tem capacidade para passar fluxo
			if (parent[next] == -1 && cap[cur][next]) {
				parent[next] = cur;						// atualizar pai
				int new_flow = 1; // atualizar fluxo
				if (next == t) return new_flow;			// chegamos ao final?
				q.add(new NodeQ(next, new_flow));		  // adicionar a fila
			}
			}
		}

		return 0;
	}

	// Algoritmo de Edmonds-Karp para fluxo maximo entre s e t
	// devolve valor do fluxo maximo (cap[][] fica com grafo residual)
	int maxFlow(int s, int t) {
		int flow = 0;				// fluxo a calcular
		int[] parent = new int[n+1]; // array de pais (permite reconstruir caminho)

		while (true) {
			int new_flow = bfs(s, t, parent); // fluxo de um caminho de aumento
			if (new_flow == 0) break;		 // se nao existir, terminar

			// imprimir fluxo e caminho de aumento
//			System.out.print("Caminho de aumento: fluxo " + new_flow + " | " + t);

			flow += new_flow;  // aumentar fluxo total com fluxo deste caminho
			int cur = t;
			while (cur != s) { // percorrer caminho de aumento e alterar arestas
			int prev = parent[cur];
			cap[prev][cur] = cap[cur][prev] = false;
			cur = prev;
//			System.out.print(" <- " + cur); // imprimir proximo no do caminho
			}
//			System.out.println();
		}

		return flow;
	}
}


class D42 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		for(int C=in.nextInt(), c=0; c<C; c++){
			int n = in.nextInt();
			Graph g = new Graph(2*n+1);
			int e = in.nextInt();
			for (int i=0; i<e; i++) {
				int a = 1+in.nextInt();
				g.addLink(0, a, 1);
				int b = 1+in.nextInt()+n;
				g.addLink(b, 2*n+1, 1);
				g.addLink(a, b, 1);
			}

			// Execucao exemplo usando 1 como no origem a 4 como o destino
			int flow = g.maxFlow(0, 2*n+1);
			if(flow>=n)
				System.out.println("YES");
			else
				System.out.println("NO");

		}
	}
}
