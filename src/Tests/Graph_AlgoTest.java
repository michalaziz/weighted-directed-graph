package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.Node;
import dataStructure.node_data;
import utils.Point3D;

class Graph_AlgoTest {

	@Test
	void testInitGraph() {
		fail("Not yet implemented");
	}

	@Test
	void testInitString() {
		fail("Not yet implemented");
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Test
	void testIsConnected() {
		fail("Not yet implemented");
	}

	@Test
	void testShortestPathDist() {
		fail("Not yet implemented");
	}

	@Test
	void testShortestPath() {
		fail("Not yet implemented");
	}

	@Test
	void TSPTest() {
		Graph_Algo ga = new Graph_Algo();
		ga.graph_A.addNode(new Node(0,0,0,new Point3D(0, 0),"ra"));
		ga.graph_A.addNode(new Node(1,0,0,new Point3D(-10,-10), "li"));
		ga.graph_A.addNode(new Node(2,0,0,new Point3D(10,10), "ra"));
		ga.graph_A.addNode(new Node(3,0,0,new Point3D(-10,10), "li"));
		ga.graph_A.addNode(new Node(4,0,0,new Point3D(0,-20), "ra"));
		ga.graph_A.addNode(new Node(5,0,0,new Point3D(10, -10),"li"));

		ga.graph_A.connect(0,1, 1);
		ga.graph_A.connect(1,0, 1.5);
		ga.graph_A.connect(0,2, 0);
		ga.graph_A.connect(2,0, 2);
		ga.graph_A.connect(0,3, 5);
		ga.graph_A.connect(3,0, 4);
		ga.graph_A.connect(0,4, 2);
		ga.graph_A.connect(4,0, 3);
		ga.graph_A.connect(0,5, 1.2);
		ga.graph_A.connect(5,0, 2.5);

		List<Integer> targets=new ArrayList<>();
		targets.add(1);
		targets.add(0);
		targets.add(2);
		targets.add(3);
		targets.add(4);
		targets.add(5);

		List<node_data> ans=ga.TSP(targets);
		List<node_data> myAns=new ArrayList<>();
		myAns.add(ga.graph_A.getNode(1));
		myAns.add(ga.graph_A.getNode(0));
		myAns.add(ga.graph_A.getNode(2));
		myAns.add(ga.graph_A.getNode(0));
		myAns.add(ga.graph_A.getNode(3));
		myAns.add(ga.graph_A.getNode(0));
		myAns.add(ga.graph_A.getNode(4));
		myAns.add(ga.graph_A.getNode(0));
		myAns.add(ga.graph_A.getNode(5));

		assertEquals(myAns,ans);
		
		ga.graph_A.removeNode(0);
		ans=ga.TSP(targets);
		assertEquals(ans,null);//Error:
	}

	@Test
	void testCopy() {
		fail("Not yet implemented");
	}

}
