package pset3;

/**
 * Created by kgowru on 3/26/15.
 */
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

import java.util.*;


public class CFG {
    Set<Node> nodes = new HashSet<Node>();
    Map<Node, Set<Node>> edges = new HashMap<Node, Set<Node>>();
    public static class Node {
        int position;
        Method method;
        JavaClass clazz;
        boolean visited;
        Node(int p, Method m, JavaClass c) {
            position = p;
            method = m;
            clazz = c;
            visited = false;
        }
        public Method getMethod() {
            return method;
        }
        public JavaClass getClazz() {
            return clazz;
        }
        public int getPosition(){
            return position;
        }
        public boolean isVisited(){
            return visited;
        }
        public void setVisited(boolean setVisited){
            visited = setVisited;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Node)) return false;
            Node n = (Node)o;
            return (position == n.position) && method.equals(n.method) && clazz.equals(n.clazz);
        }
        public int hashCode() {
            return position + method.hashCode() + clazz.hashCode();
        }
        public String toString() {
            return clazz.getClassName() + "." + method.getName() + method.getSignature() + ": " + position;
        } }
    public void addNode(int p, Method m, JavaClass c) {
        addNode(new Node(p, m, c));
    }
    private void addNode(Node n) {
        nodes.add(n);
        Set<Node> nbrs = edges.get(n);
        if (nbrs == null) {
            nbrs = new HashSet<Node>();
            edges.put(n, nbrs);
        }
    }
    public void addEdge(int p1, Method m1, JavaClass c1, int p2, Method m2, JavaClass c2) {
        Node n1 = new Node(p1, m1, c1);
        Node n2 = new Node(p2, m2, c2);
        addNode(n1);
        addNode(n2);
        Set<Node> nbrs = edges.get(n1);
        nbrs.add(n2);
        edges.put(n1, nbrs);
    }
    public void addEdge(int p1, int p2, Method m, JavaClass c) {
        addEdge(p1, m, c, p2, m, c);
    }
    public String toString() {
        return nodes.size() + " nodes\n" + "nodes: " + nodes + "\n" + "edges: " + edges;
    }
    public void clearNodes(){
        for (Node n : nodes) {
            n.setVisited(false);
        }
    }
    public boolean isReachable(String methodFrom, String clazzFrom,
                               String methodTo, String clazzTo) {

        /** DISREGARD THIS: this was an attempt at creating a map of all the valid exit methods that are possible. The
         * method is available at the bottom for reference
         *
        Map<String,Set<String>> exitMethodsMap = this.exitMethods(clazzFrom);

        for (String key: exitMethodsMap.keySet()){
            System.out.print("key: " + key.toString() + "/set: ");
            Set<String> c = exitMethodsMap.get(key);
            for(String s : c){
                System.out.print(s);
            }
            System.out.print("\n");
        } **/

        // Gets the start node with position (0) and method (methodFrom)
        Node startNode = new Node(0, null, null);
        for (Node n: nodes){
            if (n.getPosition() == 0 && n.getMethod().getName().equals(methodFrom) && n.getClazz().getClassName().equals(clazzFrom)) {
                startNode = n;
                break;
            }
        }

        // Base cases including if the method is equal to itself and if the methodFrom equals "<init>" (and methodTo does not equal to "<init>")
        if (methodFrom.equals(methodTo) && clazzFrom.equals(clazzTo))
            return true;
        if (methodFrom.equals("<init>"))
            return false;

        // Set holding all visited Nodes
        Set<Node> visited = new LinkedHashSet<Node>();

        // Queue for breadth first search
        Queue<Node> queue = new LinkedList<Node>();

        // Mark the current node as visited and enqueue it
        visited.add(startNode);
        queue.add(startNode);

        // Stack cataloguing all exits, stores fromNodeMethod and toNodeMethod in a HashMap
        Stack<HashMap<String,String>> exitStack = new Stack<HashMap<String,String>>();

        while (!queue.isEmpty()) {
            // Get the first node from the Queue
            startNode = queue.poll();

            // Get fromNodeMethod to toNodeMethod names if the position is a -1 and the stack is not empty
            String fromNodeMethod = null;
            String toNodeMethod = null;
            boolean avoid = false;

            if (startNode.getPosition() == -1 && !exitStack.isEmpty()){
                HashMap<String, String> skipNode = exitStack.pop();
                fromNodeMethod = (String) skipNode.keySet().toArray()[0];
                toNodeMethod = skipNode.get(fromNodeMethod);
                avoid = true;
            }

            // Get all neighbors of current Node being looked at
            Set<Node> neighbors = edges.get(startNode);
            for (Iterator<Node> i = neighbors.iterator(); i.hasNext();) {
                // Else, continue to do BFS
                Node n = i.next();

                // If startNode is -1 and the exitStack is empty return false automatically because there can be no traversal options
                if(startNode.getPosition() == -1 && exitStack.isEmpty()){
                    return false;
                }
                // If avoid is true meaning stack is not empty and startNode.getPosition == -1 then only look at nodes that go back to the original methodInvocation
                if (avoid){
                    if(!visited.contains(n) && n.getMethod().getName() == fromNodeMethod && startNode.getMethod().getName() == toNodeMethod)
                    {
                        // If this adjacent node is the destination node, then return true
                        if (n.getMethod().getName().equals(methodTo) && n.getClazz().getClassName().equals(clazzTo))
                            return true;

                        // If the next position is 0 then push to the exitStack the fromNodeMethod to toNodeMethod names
                        if (n.getPosition() == 0){
                            HashMap<String,String> newMap = new HashMap<String, String>();
                            newMap.put(startNode.getMethod().getName(), n.getMethod().getName());
                            exitStack.push(newMap);
                        }
                        visited.add(n);
                        queue.add(n);
                    }
                } else {
                    if (!visited.contains(n)) {
                        // If this adjacent node is the destination node, then return true
                        if (n.getMethod().getName().equals(methodTo) && n.getClazz().getClassName().equals(clazzTo))
                            return true;

                        // If the next position is 0 then push to the exitStack the fromNodeMethod to toNodeMethod names
                        if (n.getPosition() == 0) {
                            HashMap<String, String> newMap = new HashMap<String, String>();
                            newMap.put(startNode.getMethod().getName(), n.getMethod().getName());
                            exitStack.push(newMap);
                        }
                        visited.add(n);
                        queue.add(n);
                    }
                }
            }
        }
        return false;
    }
    /**
    public Map<String,Set<String>> exitMethods(String clazzFrom){

        Map<String, Set<String>> exitMethodsMap = new HashMap<String, Set<String>>();

        Node startNode = new Node(0, null, null);
        for (Node n: nodes){
            if (n.getPosition() == 0 && n.getMethod().getName().equals("main") && n.getClazz().getClassName().equals(clazzFrom)) {
                startNode = n;
                break;
            }
        }

        // Mark all the vertices as not visited
        Set<Node> visited = new LinkedHashSet<Node>();

        // Create a queue for BFS
        Queue<Node> queue = new LinkedList<Node>();


        // Mark the current node as visited and enqueue it
        visited.add(startNode);
        queue.add(startNode);


        while (!queue.isEmpty()) {
            // Dequeue a vertex from queue and print it
            startNode = queue.poll();

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it visited
            // and enqueue it
            Collection<Node> neighbors = edges.get(startNode);
            for (Node n : neighbors) {

                if(n.getPosition() == 0){
                    if(exitMethodsMap.containsKey(startNode.getMethod().getName())){
                        exitMethodsMap.get(startNode.getMethod().getName()).add(n.getMethod().getName());
                    } else {
                        Set<String> newSet = new HashSet<String>();
                        newSet.add(n.getMethod().getName());
                        exitMethodsMap.put(startNode.getMethod().getName(), newSet);
                    }
                }

                // Else, continue to do BFS
                if(!visited.contains(n))
                {
                    visited.add(n);
                    queue.add(n);
                }
            }
        }

        return exitMethodsMap;
    }**/
}