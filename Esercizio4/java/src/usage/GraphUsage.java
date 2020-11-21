package usage;

import java.io.BufferedReader;
import java.lang.String;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.lang.*;
import algorithm.*;
import graph.*;

public class GraphUsage {

  public static UnDirectedGraph<String, Double> loadFile(String inputFile) {
    String line = "";
    String[] words;
    UnDirectedGraph<String, Double> graph = new UnDirectedGraph<>();
    ArrayList<String> array = new ArrayList<>();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(inputFile));
      System.out.println("Loading file... \"italian_dist_graph\"");
      while ((line = reader.readLine()) != null) {
        words = line.split(",");
        for (int i = 0; i < words.length; i++)
          array.add(words[i]);
      }
      reader.close();
      System.out.println("File loaded... \"italian_dist_graph\"\n");
    } catch (IOException ex) {
      System.out.println("File not found");
    }

    for (int i = 0; i < array.size(); i = i + 3) {
      if (!graph.containsEdge(array.get(i), array.get(i + 1))) {
        graph.addEdge(array.get(i), array.get(i + 1), Double.parseDouble(array.get(i + 2)));
      }
    }

    return graph;
  }

  public static void main(String[] args) throws Exception {
    if (args.length < 1)
      throw new Exception("\nUsage: GraphUsage <file_path>");

    UnDirectedGraph<String, Double> result = new UnDirectedGraph<>();
    Kruskal k = new Kruskal();
    result = loadFile(args[0]);
    System.out.println(k.mst(result));

    int numOfVertices = result.numOfVertices();
    System.out.println("\n\nnumber of Edges after Kruskal: " + k.numOfEdgesAfterKruskal() + "\nnumber of Vertices: "
        + numOfVertices + "\ntotal distance: " + k.getTotalDistance() + " km");
  }
}