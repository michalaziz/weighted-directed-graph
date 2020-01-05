package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.node_data;
import utils.Point3D;

class Graph_AlgoTest {


	@Test
	void testInitGraph() {
		DGraph dg = new DGraph();
		Node n1 = new Node(5,0,0,new Point3D(3,4),"");
		Node n2 = new Node(8,0,0,new Point3D(1,4),"");
		dg.addNode(n1);
		dg.addNode(n2);
		Graph_Algo ga = new Graph_Algo();
		ga.init(dg);
		assertEquals(dg.nodeSize(), ga.graph_A.nodeSize());
	}

	@Test
	void testInitString() {
		DGraph dg = new DGraph();
		Node n1 = new Node(5,0,0,new Point3D(3,4),"");
		Node n2 = new Node(8,0,0,new Point3D(1,4),"");
		dg.addNode(n1);
		dg.addNode(n2);
		Graph_Algo ga1 = new Graph_Algo();
		ga1.init(dg);
		String file = "Graph_file.txt";
		ga1.save(file);
		Graph_Algo ga2 = new Graph_Algo();
		ga2.init(file);
		assertEquals(ga1.graph_A.nodeSize(),ga2.graph_A.nodeSize());
	}

	@Test
	void testSave() {
		DGraph dg = new DGraph();
		Node n1 = new Node(5,0,0,new Point3D(3,4),"");
		Node n2 = new Node(8,0,0,new Point3D(1,4),"");
		dg.addNode(n1);
		dg.addNode(n2);
		Graph_Algo ga1 = new Graph_Algo();
		ga1.init(dg);
		String file = "Graph_file.txt";
		ga1.save(file);
		Graph_Algo ga2 = new Graph_Algo();
		ga2.init(dg);
		assertTrue(ga2.graph_A.equals(ga2.graph_A));
	}

	@Test
	void testIsConnected() {
		Point3D p0 = new Point3D(1,2,0);
		Point3D p1 = new Point3D(6,0,0);
		Point3D p2 = new Point3D(0,-10,0);
		Point3D p3 = new Point3D(6,-10,0);
		Point3D p4 = new Point3D(10,-5,0);
		Point3D p5 = new Point3D(-10,-12,0);

		
		Node n0= new Node(0,0,0,p0,"0");
		Node n1= new Node(1,0,0,p1,"1");
		Node n2= new Node(2,0,0,p2,"2");
		Node n3= new Node(3,0,0,p3,"3");
		Node n4= new Node(4,0,0,p4,"4");
		Node n5= new Node(5,0,0,p5,"5");

		
		DGraph dg= new DGraph();
     	dg.addNode(n0);
		dg.addNode(n1);
		dg.addNode(n2);
		dg.addNode(n3);
		dg.addNode(n4);
		dg.addNode(n5);

		
		dg.connect(0, 4, 5);
		dg.connect(1, 2, 1);
		dg.connect(1, 4, 2);
		dg.connect(2, 3, 4);
		dg.connect(2, 5, 20);
		dg.connect(3, 2, 6);
		dg.connect(3, 0, 7);
		dg.connect(4, 1, 3);
		dg.connect(4, 2, 9);
		dg.connect(4, 3, 2);
		dg.connect(5, 2, 14);
		
		Graph_Algo ga = new Graph_Algo();
		ga.init(dg);
		assertTrue(ga.isConnected());
		
		Point3D p6 = new Point3D(-10,5,0);
		Node n6= new Node(6,0,0,p6,"6");
		dg.addNode(n6);
		
		Graph_Algo ga1 = new Graph_Algo();
		ga1.init(dg);
		
		assertFalse(ga1.isConnected());
		
	}

	@Test
	void testShortestPathDist() {
		Point3D p0 = new Point3D(1,2,0);
		Point3D p1 = new Point3D(6,0,0);
		Point3D p2 = new Point3D(0,-10,0);
		Point3D p3 = new Point3D(6,-10,0);
		Point3D p4 = new Point3D(10,-5,0);
		Point3D p5 = new Point3D(-10,-12,0);

		
		Node n0= new Node(0,0,0,p0,"0");
		Node n1= new Node(1,0,0,p1,"1");
		Node n2= new Node(2,0,0,p2,"2");
		Node n3= new Node(3,0,0,p3,"3");
		Node n4= new Node(4,0,0,p4,"4");
		Node n5= new Node(5,0,0,p5,"5");

		
		DGraph dg= new DGraph();
     	dg.addNode(n0);
		dg.addNode(n1);
		dg.addNode(n2);
		dg.addNode(n3);
		dg.addNode(n4);
		dg.addNode(n5);

		
		dg.connect(0, 4, 5);
		dg.connect(1, 2, 1);
		dg.connect(1, 4, 2);
		dg.connect(2, 3, 4);
		dg.connect(2, 5, 20);
		dg.connect(3, 2, 6);
		dg.connect(3, 0, 7);
		dg.connect(4, 1, 3);
		dg.connect(4, 2, 9);
		dg.connect(4, 3, 2);
		dg.connect(5, 2, 14);
		
		Graph_Algo ga = new Graph_Algo();
		ga.init(dg);
		
		
		double expected = 26;
		double res = ga.shortestPathDist(3, 5);
		boolean flag = false;
		if(expected == res) {
			flag = true;
		}
		else {
			flag = false;
		}
		assertTrue(flag);
		
		
		expected = 4;
		res = ga.shortestPathDist(4, 2);
		flag=false;
		if(expected == res) {
			flag = true;
		}
		else {
			flag = false;
		}
		assertTrue(flag);
	}

	@Test
	void testShortestPath() {
		Point3D p0 = new Point3D(1,2,0);
		Point3D p1 = new Point3D(6,0,0);
		Point3D p2 = new Point3D(0,-10,0);
		Point3D p3 = new Point3D(6,-10,0);
		Point3D p4 = new Point3D(10,-5,0);
		Point3D p5 = new Point3D(-10,-12,0);

		
		Node n0= new Node(0,0,0,p0,"0");
		Node n1= new Node(1,0,0,p1,"1");
		Node n2= new Node(2,0,0,p2,"2");
		Node n3= new Node(3,0,0,p3,"3");
		Node n4= new Node(4,0,0,p4,"4");
		Node n5= new Node(5,0,0,p5,"5");

		
		DGraph dg= new DGraph();
     	dg.addNode(n0);
		dg.addNode(n1);
		dg.addNode(n2);
		dg.addNode(n3);
		dg.addNode(n4);
		dg.addNode(n5);

		
		dg.connect(0, 4, 5);
		dg.connect(1, 2, 1);
		dg.connect(1, 4, 2);
		dg.connect(2, 3, 4);
		dg.connect(2, 5, 20);
		dg.connect(3, 2, 6);
		dg.connect(3, 0, 7);
		dg.connect(4, 1, 3);
		dg.connect(4, 2, 9);
		dg.connect(4, 3, 2);
		dg.connect(5, 2, 14);
		
		Graph_Algo ga = new Graph_Algo();
		ga.init(dg);
		
		List<node_data> excepted = new ArrayList<node_data>();
		excepted.add(n4);
		excepted.add(n1);
		excepted.add(n2);
		List<node_data> res = new ArrayList<node_data>();
		res=ga.shortestPath(4, 2);
		
		assertEquals(excepted, res);
	}

	@Test
	void testTSP() {
		Point3D p0 = new Point3D(1,2,0);
		Point3D p1 = new Point3D(6,0,0);
		Point3D p2 = new Point3D(0,-10,0);
		Point3D p3 = new Point3D(6,-10,0);
		Point3D p4 = new Point3D(10,-5,0);
		Point3D p5 = new Point3D(-10,-12,0);
		Point3D p6 = new Point3D(-10,5,0);

		
		Node n0= new Node(0,0,0,p0,"0");
		Node n1= new Node(1,0,0,p1,"1");
		Node n2= new Node(2,0,0,p2,"2");
		Node n3= new Node(3,0,0,p3,"3");
		Node n4= new Node(4,0,0,p4,"4");
		Node n5= new Node(5,0,0,p5,"5");
		Node n6= new Node(6,0,0,p6,"6");

		
		DGraph dg= new DGraph();
     	dg.addNode(n0);
		dg.addNode(n1);
		dg.addNode(n2);
		dg.addNode(n3);
		dg.addNode(n4);
		dg.addNode(n5);
		dg.addNode(n6);

		
		dg.connect(0, 4, 5);
		dg.connect(1, 2, 1);
		dg.connect(1, 4, 2);
		dg.connect(2, 3, 4);
		dg.connect(2, 5, 20);
		dg.connect(3, 2, 6);
		dg.connect(3, 0, 7);
		dg.connect(4, 1, 3);
		dg.connect(4, 2, 9);
		dg.connect(4, 3, 2);
		dg.connect(5, 2, 14);
		
		Graph_Algo ga = new Graph_Algo();
		ga.init(dg);
		
		List<Integer> targetTSP = new ArrayList<Integer>();
		targetTSP.add(0);
		targetTSP.add(3);
		targetTSP.add(5);
		

		
		List<node_data> resTSP = new ArrayList<node_data>();
		resTSP=ga.TSP(targetTSP);
		
		List<node_data> excepted = new ArrayList<node_data>();
		excepted.add(n0);
		excepted.add(n4);
		excepted.add(n3);
		excepted.add(n2);
		excepted.add(n5);
		
		assertEquals(excepted, resTSP);
		
	}
}
