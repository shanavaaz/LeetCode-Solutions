import java.util.*;

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        
        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double val = values[i];
            
            graph.putIfAbsent(u, new HashMap<>());
            graph.putIfAbsent(v, new HashMap<>());
            
            graph.get(u).put(v, val);
            graph.get(v).put(u, 1.0 / val);
        }
        
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);
            
            if (!graph.containsKey(start) || !graph.containsKey(end)) {
                results[i] = -1.0; 
            } else if (start.equals(end)) {
                results[i] = 1.0; 
            } else {
                results[i] = dfs(graph, start, end, 1.0, new HashSet<>());
            }
        }
        
        return results;
    }
    
    private double dfs(Map<String, Map<String, Double>> graph, String current, String target, double currentProduct, Set<String> visited) {
        visited.add(current);
        Map<String, Double> neighbors = graph.get(current);
        
        if (neighbors.containsKey(target)) {
            return currentProduct * neighbors.get(target);
        }
        
        for (Map.Entry<String, Double> neighbor : neighbors.entrySet()) {
            String nextNode = neighbor.getKey();
            if (!visited.contains(nextNode)) {
                double result = dfs(graph, nextNode, target, currentProduct * neighbor.getValue(), visited);
                if (result != -1.0) {
                    return result;
                }
            }
        }
        
        return -1.0;
    }
}