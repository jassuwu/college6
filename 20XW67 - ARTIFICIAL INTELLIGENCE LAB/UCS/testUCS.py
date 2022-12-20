from WeightedDigraph import WeightedDigraph


UCSDemo = WeightedDigraph(5)
UCSDemo.addEdge(0, 1, 1)
UCSDemo.addEdge(1, 2, 2)
UCSDemo.addEdge(2, 3, 3)
UCSDemo.addEdge(3, 4, 4)
UCSDemo.addEdge(4, 0, 5)

print("No. of vertices: ",UCSDemo.V())
print("No. of edges: ",UCSDemo.E())

print("ADJLIST",UCSDemo.adjList)
