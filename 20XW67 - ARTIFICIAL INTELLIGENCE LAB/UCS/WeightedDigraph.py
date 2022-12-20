class WeightedDigraph:
    def __init__(self, vertices):
        self.vertices = vertices
        self.edges = 0
        self.adjList = [[] for _ in range(self.V())]

    def V(self):
        return self.vertices

    def E(self):
        return self.edges

    def addEdge(self, fromNode, toNode, weight):
        self.adjList[fromNode].append((toNode, weight))
        self.edges+=1

    def UCS(self, v):
        pass

    