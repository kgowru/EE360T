package pset5;

/**
 * Created by kgowru on 4/22/15.
 */
    import static org.junit.Assert.*;
    import java.util.TreeSet;
    import java.util.Set;
    import org.junit.Test;

public class GraphTester {
    // tests for method "addEdge" in class "Graph"
    @Test
    public void tae0() {
        Graph g = new Graph();
        g.addEdge(0, 1);
        System.out.println(g.toString());
        assertEquals(g.toString(), "nodes: [0, 1]\nedges: {0=[1]}");
    }

    // your tests for method "addEdge" in class "Graph" go here
    // you must provide at least 4 test methods;
    // each test method must have at least 1 invocation of addEdge;
    // each test method must have at least 1 test assertion;
    // your test methods must provide full statement coverage of your
    //   implementation of addEdge and any helper methods
    // no test method directly invokes any method that is not
    //   declared in the Graph class as given in this homework
    // tests for method "reachable" in class "Graph"

    @Test
    public void tae1(){
        Graph g = new Graph();
        g.addNode(0);
        g.addNode(1);
        g.addEdge(0,1);
        System.out.println(g.toString());
        assertEquals(g.toString(), "nodes: [0, 1]\nedges: {0=[1]}");
    }

    @Test
    public void tae2(){
        Graph g = new Graph();
        g.addNode(0);
        g.addEdge(0,1);
        System.out.println(g.toString());
        assertEquals(g.toString(), "nodes: [0, 1]\nedges: {0=[1]}");
    }

    @Test
    public void tae3(){
        Graph g = new Graph();
        g.addNode(1);
        g.addEdge(0,1);
        System.out.println(g.toString());
        assertEquals(g.toString(), "nodes: [0, 1]\nedges: {0=[1]}");
    }

    @Test
    public void tae4(){
        Graph g = new Graph();
        g.addEdge(0,0);
        System.out.println(g.toString());
        assertEquals(g.toString(), "nodes: [0]\nedges: {0=[0]}");
    }

    @Test
    public void tr0() {
        Graph g = new Graph();
        g.addNode(0);
        Set<Integer> nodes = new TreeSet<Integer>();
        nodes.add(0);
        assertTrue(g.reachable(nodes, nodes));
    }
    // your tests for method "reachable" in class "Graph" go here
    // you must provide at least 6 test methods;
    // each test method must have at least 1 invocation of reachable;
    // each test method must have at least 1 test assertion;
    // at least 2 test methods must have at least 1 invocation of addEdge;
    // your test methods must provide full statement coverage of your//   implementation of reachable and any helper methods
    // no test method directly invokes any method that is not
    //   declared in the Graph class as given in this homework

    @Test
    public void tr1() {
        Graph g = new Graph();
        g.addNode(0);
        Set<Integer> nodes = new TreeSet<Integer>();
        nodes.add(0);
        assertTrue(g.reachable(nodes, nodes));
    }
    @Test
    public void tr2() {
        Graph g = new Graph();
        g.addNode(0);
        g.addNode(1);
        g.addEdge(0,1);
        Set<Integer> sourceNodes = new TreeSet<Integer>();
        sourceNodes.add(0);
        Set<Integer> targetNodes= new TreeSet<Integer>();
        targetNodes.add(1);
        assertTrue(g.reachable(sourceNodes, targetNodes));
    }
    @Test
    public void tr3() {
        Graph g = new Graph();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        Set<Integer> sourceNodes = new TreeSet<Integer>();
        sourceNodes.add(0);
        sourceNodes.add(1);
        sourceNodes.add(2);
        Set<Integer> targetNodes= new TreeSet<Integer>();
        targetNodes.add(0);
        targetNodes.add(1);
        targetNodes.add(2);

        assertTrue(g.reachable(sourceNodes, targetNodes));
    }
    @Test
    public void tr4() {
        Graph g = new Graph();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        Set<Integer> sourceNodes = new TreeSet<Integer>();
        sourceNodes.add(0);
        sourceNodes.add(1);
        Set<Integer> targetNodes= new TreeSet<Integer>();
        targetNodes.add(0);
        targetNodes.add(1);
        targetNodes.add(2);

        assertFalse(g.reachable(sourceNodes, targetNodes));
    }
    @Test
    public void tr5() {
        Graph g = new Graph();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addEdge(0,1);
        g.addEdge(1,2);
        Set<Integer> sourceNodes = new TreeSet<Integer>();
        sourceNodes.add(0);
        sourceNodes.add(1);
        sourceNodes.add(2);
        Set<Integer> targetNodes= new TreeSet<Integer>();
        targetNodes.add(3);
        assertFalse(g.reachable(sourceNodes, targetNodes));
    }
    @Test
    public void tr6() {
        Graph g = new Graph();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(2,3);
        Set<Integer> sourceNodes = new TreeSet<Integer>();
        sourceNodes.add(0);
        Set<Integer> targetNodes= new TreeSet<Integer>();
        targetNodes.add(3);
        assertTrue(g.reachable(sourceNodes, targetNodes));
    }

    @Test
    public void tr7() {
        Graph g = new Graph();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(0,3);
        Set<Integer> sourceNodes = new TreeSet<Integer>();
        sourceNodes.add(0);
        Set<Integer> targetNodes= new TreeSet<Integer>();
        targetNodes.add(3);
        targetNodes.add(1);
        targetNodes.add(2);
        targetNodes.add(0);
        assertTrue(g.reachable(sourceNodes, targetNodes));
    }
}
