from UndirectedGraph import UndirectedGraph
# Test client for UG_API
# Making a new DFSDemo
DFSDemo = UndirectedGraph(13)

# Addition of edges to the DFSDemo
DFSDemo.addEdge(0, 5)
DFSDemo.addEdge(4, 3)
DFSDemo.addEdge(0, 1)
DFSDemo.addEdge(9, 12)
DFSDemo.addEdge(6, 4)
DFSDemo.addEdge(5, 4)
DFSDemo.addEdge(0, 2)
DFSDemo.addEdge(11, 12)
DFSDemo.addEdge(9, 10)
DFSDemo.addEdge(0, 6)
DFSDemo.addEdge(7, 8)
DFSDemo.addEdge(9, 11)
DFSDemo.addEdge(5, 3)

# Making a new BFSDemo
BFSDemo = UndirectedGraph(6)

# Addition of edges to the BFSDemo
BFSDemo.addEdge(0, 5)
BFSDemo.addEdge(2, 4)
BFSDemo.addEdge(2, 3)
BFSDemo.addEdge(1, 2)
BFSDemo.addEdge(0, 1)
BFSDemo.addEdge(3, 4)
BFSDemo.addEdge(3, 5)
BFSDemo.addEdge(0, 2)

###################################################################################
print('\n######################################################################\n')


# Printing the no. of vertices, and edges in the DFSDemo
print("The no. of vertices: ", DFSDemo.V())
print("The no. of edges: ", DFSDemo.E())

# The Depth-First Traversal of the DFSDemo
print("A Depth-First Search of the graph DFSDemo gives: ", DFSDemo.DFS(0))

# Using DFS to print the pathTo
print("A path from 0 to 6: ", DFSDemo.pathTo(0, 6, DFSDemo.DFS))
print("A path from 0 to 7: ", DFSDemo.pathTo(0, 7, DFSDemo.DFS))


###################################################################################
print('\n######################################################################\n')


# Printing the no. of vertices, and edges in the BFSDemo
print("The no. of vertices: ", BFSDemo.V())
print("The no. of edges: ", BFSDemo.E())

# The Breadth-First Traversal of the BFSDemo
print("A Breadth-First Search of the graph BFSDemo gives: ", BFSDemo.BFS(0))

# Using BFS in the pathTo fn gives us the shortest path
print("A shortest path from 0 to 4 using BFS: ",
      BFSDemo.pathTo(0, 4, BFSDemo.BFS))
print("A shortest path from 0 to 5 using BFS: ",
      BFSDemo.pathTo(0, 5, BFSDemo.BFS))


###################################################################################
print('\n######################################################################\n')

# Using the DFSDemo for the DLS search
print("A (2) Depth-Limited Search of the graph DFSDemo gives: ", DFSDemo.DLS(0))

# Using DLS for path-finding ? idk about that...
print("A path from 0 to 6 using DLS: ",
      DFSDemo.pathTo(0, 6, DFSDemo.DLS))
print("A path from 0 to 7 using DLS: ",
      DFSDemo.pathTo(0, 7, DFSDemo.DLS))

###################################################################################
print('\n######################################################################\n')

# Using the DFSDemo for the IDDFS search
print("A (StartingDepth-1) Iterative Deepening Depth-First Search of the graph DFSDemo gives: ",
      DFSDemo.IDDFS(0))

# Using IDDFS for path-finding
print("A path from 0 to 6 using IDDFS: ",
      DFSDemo.pathTo(0, 6, DFSDemo.IDDFS))
print("A path from 0 to 7 using IDDFS: ",
      DFSDemo.pathTo(0, 7, DFSDemo.IDDFS))

###################################################################################
print('\n######################################################################\n')
