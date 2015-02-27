package pset1;

import static org.junit.Assert.*;
import org.junit.Test;

import pset1.SLList.Node;

public class SLListRepOkTester {

    @Test public void t0() {
        SLList l = new SLList();
        assertTrue(l.repOk());
    }

    @Test public void t1() {
        SLList l = new SLList();

        // h --> n2 --> null
        Node header = new Node();
        header.elem = true;

        Node nodeTwo = new Node();
        nodeTwo.elem = false;
        nodeTwo.next = null;

        header.next = nodeTwo;
        l.header = header;

        assertTrue(l.repOk());
    }

    @Test public void t2(){
        SLList l = new SLList();

        // h --> n2 --> h
        Node header = new Node();
        header.elem = true;

        Node nodeTwo = new Node();
        nodeTwo.elem = false;
        nodeTwo.next = header;

        header.next = nodeTwo;
        l.header = header;

        assertFalse(l.repOk());
    }

    @Test public void t3(){
        SLList l = new SLList();

        // h --> null
        Node header = new Node();
        header.elem = true;

        header.next = null;
        l.header = header;

        assertTrue(l.repOk());

    }

    @Test public void t4(){
        SLList l = new SLList();

        // h --> h
        Node header = new Node();
        header.elem = true;

        header.next = header;
        l.header = header;


        assertFalse(l.repOk());
    }

    @Test public void t5(){
        SLList l = new SLList();

        // h --> n2 --> n2
        Node header = new Node();
        header.elem = true;

        Node nodeTwo = new Node();
        nodeTwo.elem = false;
        nodeTwo.next = nodeTwo;

        header.next = nodeTwo;
        l.header = header;

        assertFalse(l.repOk());
    }
}