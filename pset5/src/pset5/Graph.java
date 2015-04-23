/**
 * Created by kgowru on 4/20/15.
 */

package pset5;

import java.util.*;

public class Graph {
    private Set<Integer> nodes; // set of nodes in the graph
    private Map<Integer, TreeSet<Integer>> edges;
    // map between a node and a set of nodes that are connected to it by outgoing edges
    // class invariant: fields "nodes" and "edges" are non-null;
    //                  "this" graph has no node that is not in "nodes"
    public Graph() {
        nodes = new TreeSet<Integer>();
        edges = new TreeMap<Integer, TreeSet<Integer>>();
    }
    public String toString() {
        return "nodes: " + nodes + "\n" + "edges: " + edges;
    }
    public void addNode(int n) {
        // postcondition: adds the node "n" to this graph
        nodes.add(n);
    }
    public void addEdge(int from, int to) {
        // postcondition: adds a directed edge "from" -> "to" to this graph
        // your code goes here
        //...
        if(!nodes.contains(from)) {
            this.addNode(from);
            edges.put(from, new TreeSet<Integer>());
        }
        if(!nodes.contains(to)) {
            this.addNode(to);
            //edges.put(to, new TreeSet<Integer>());
        }
        if(!edges.containsKey(from))
            edges.put(from, new TreeSet<Integer>());

        TreeSet<Integer> children = edges.get(from);
        if(!children.contains(to))
            children.add(to);
    }
    public boolean reachable(Set<Integer> sources, Set<Integer> targets) {
        if (sources == null || targets == null) throw new IllegalArgumentException();
        // postcondition: returns true if (1) "sources" is a subset of "nodes", (2) "targets" is
        //                           a subset of "nodes", and (3) for each node "m" in set "targets",
        //                           there is some node "n" in set "sources" such that there is a
        //                           directed path that starts at "n" and ends at "m" in "this"; and
        //                           false otherwise
        // your code goes here
        //...
        if(nodes.containsAll(sources) && nodes.containsAll(targets)) {
            Set<Integer> reachableNodes = new TreeSet<Integer>();
            Stack<Integer> searchNodes = new Stack<Integer>();
            searchNodes.addAll(sources);
            while(!searchNodes.isEmpty()) {
                Integer currentNode = searchNodes.pop();
                reachableNodes.add(currentNode);
                if(edges.containsKey(currentNode)) {
                    TreeSet<Integer> childNodes = edges.get(currentNode);
                    for (Integer node : childNodes) {
                        reachableNodes.add(node);
                        searchNodes.push(node);
                    }
                }
            }
            if (reachableNodes.containsAll(targets))
                return true;
        }
        return false;
    }
}
