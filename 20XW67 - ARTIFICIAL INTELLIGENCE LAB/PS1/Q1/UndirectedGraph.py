# UG API
class UndirectedGraph:
    def __init__(self, vertices):
        self.vertices = vertices
        self.edges = 0
        self.adjList = [[] for _ in range(self.vertices)]
        pass

    def addEdge(self, v, w):
        # Two connections because undirected graph
        self.adjList[v].append(w)
        self.adjList[w].append(v)
        self.edges += 1

    def adj(self, v):
        return self.adjList[v]

    def V(self):
        return self.vertices

    def E(self):
        return self.edges

    # Typical Graph-processing functionality
    def degree(self, v):
        return len(self.adj(v))

    def maxDegree(self):
        return max([self.degree(v) for v in range(self.V())])

    def averageDegree(self):
        return 2 * self.E() / self.V()

    def numberOfSelfLoops(self):
        count = 0
        for i in range(self.V()):
            if i in self.adj(i):
                count += 1
        return count
    # End of Typical Graph-processing functionality

    # (a)
    def DFS(self, v):
        marked = [False for i in range(self.V())]
        edgeTo = [None for i in range(self.V())]
        self._DFS(v, marked, edgeTo)
        return {"marked": marked, "edgeTo": edgeTo}

    # (a)'s helper fn.
    def _DFS(self, v, marked, edgeTo):
        marked[v] = True
        for vertex in self.adj(v):
            if not marked[vertex]:
                self._DFS(vertex, marked, edgeTo)
                edgeTo[vertex] = v

    # (b)

    def BFS(self, v):
        marked = [False for i in range(self.V())]
        edgeTo = [None for i in range(self.V())]
        q = []
        q.append(v)
        marked[v] = True
        while len(q) != 0:
            curr = q[0]
            del q[0]
            for vertex in self.adj(curr):
                if not marked[vertex]:
                    q.append(vertex)
                    marked[vertex] = True
                    edgeTo[vertex] = curr
        return {"marked": marked, "edgeTo": edgeTo}

    # (c)
    def DLS(self, v):
        # This can be set dynamically but it is set to 2 for demo purposes.
        limit = 2
        marked = [False for i in range(self.V())]
        edgeTo = [None for i in range(self.V())]
        self._DLS(v, marked, edgeTo, limit)
        return {"marked": marked, "edgeTo": edgeTo}

    # (c)'s helper fn.
    def _DLS(self, v, marked, edgeTo, limit):
        limitHere = limit
        marked[v] = True
        for vertex in self.adj(v):
            if not marked[vertex] and limitHere:
                self._DLS(vertex, marked, edgeTo, limit)
                edgeTo[vertex] = v
                limitHere -= 1

    # (d)
    def IDDFS(self, v):
        startingLimit = 1
        marked = [False for i in range(self.V())]
        edgeTo = [None for i in range(self.V())]
        self._IDDFS(v, marked, edgeTo, startingLimit)
        return {"marked": marked, "edgeTo": edgeTo}

    # (d)'s helper fn.
    def _IDDFS(self, v, marked, edgeTo, limit):
        limitHere = limit
        marked[v] = True
        for vertex in self.adj(v):
            if not marked[vertex] and limitHere:
                self._IDDFS(vertex, marked, edgeTo, limit + 1)
                edgeTo[vertex] = v
                limitHere -= 1

    def hasPathTo(self, v, w, searchTech):
        return searchTech(v)["marked"][w]

    def pathTo(self, v, w, searchTech):
        if not self.hasPathTo(v, w, searchTech):
            return None
        path = []
        theEdgeTo = searchTech(w)["edgeTo"]
        x = v
        while x != w:
            path.append(x)
            x = theEdgeTo[x]
        path.append(w)
        return "->".join([str(num) for num in path])
