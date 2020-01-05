package Tests;

import dataStructure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Point3D;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;


class DGraphTest {

	DGraph d = new DGraph();


	@Test
	void addNode() {
		Point3D y = new Point3D(-10, -20, -3);
		node_data b = new Node(1, 4, 3, y, "2");
		d.addNode(b);
		try {
			Point3D p1=new Point3D(-2, 4, 7);
			node_data b2 = new Node(1,7, p1);
			d.addNode(b2);
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println(" can not add this node cause this key already exist ");
		}
	}

	@Test
	void connect() {

		DGraph d = new DGraph();
		int i = 0;
		while (i < 1000) {
			Point3D point = new Point3D(i, i + 1, i + 2);
			node_data n = new Node(i, i + 1, point);
			d.addNode(n);
			i++;
		}
		int j = 0;
		while (j < 990) {
			edge_data e = new Edge(j, j + 1, j);
			d.connect(j, j + 1, j);
			assertEquals(j, e.getWeight());
			j++;
		}
		try {
			d.connect(990, 991, -7);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("negative weight");
		}
		try {
			d.connect(1005, 900, 10);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("src does not exist");
		}
		try {
			d.connect(2000, 2000, 20);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("src and dest does not exist");
		}
		try {
			d.connect(995, 995, 20);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" same src and dest");
		}
	}


	@Test
	void getV() {
		DGraph d = new DGraph();
		int i = 0;
		while (i < 1000) {
			Point3D point = new Point3D(i, i + 1, i + 2);
			node_data n = new Node(i, i + 1, point);
			d.addNode(n);
			i++;
		}
		Collection<node_data> res = d.getV();
		assertEquals(1000, res.size());
		System.out.println("test (getV) succeeded ! ");
	}

	@Test
	void getE() {
		DGraph d = new DGraph();
		int i = 0;
		while (i < 1000) {
			Point3D point = new Point3D(i, i + 1, i + 2);
			node_data n = new Node(i, i + 1, point);
			d.addNode(n);
			i++;
		}
		int j = 0;
		while (j < 990) {
			edge_data e = new Edge(j, j + 1, j);
			d.connect(j, j + 1, j);
			assertEquals(1, d.getE(e.getSrc()).size());
			j++;
		}
		System.out.println("test (getE) succeeded ! ");
	}

	@Test
	void removeNode() { //not working
		DGraph g1 = new DGraph();
		DGraph g2 = new DGraph();
		Node n1 = new Node(5,0,0,new Point3D(3,4),"");
		Node n2 = new Node(8,0,0,new Point3D(1,4),"");
		g1.addNode(n1);
		g1.addNode(n2);
		g2.addNode(n1);
		g2.addNode(n2);
		g2.removeNode(n1.getKey());
		assertNotEquals(g1.nodeSize(), g2.nodeSize());
	}
	
	@Test
	void removeEdge() { // not working
		DGraph d = new DGraph();
		Point3D x = new Point3D(10, 20, 3);
		Point3D y = new Point3D(-10, -20, -3);
		Point3D z = new Point3D(20, 40, 8);
		Point3D w = new Point3D(-32, 30, 9);
		Point3D s = new Point3D(40, -20, 0);
		node_data a_0 = new Node(0, 3, 2, x, "michal");
		node_data b_1 = new Node(1, 4, 3, y, "yarden");
		node_data c_2 = new Node(2, 5, 4, z, "sf");
		node_data d_3 = new Node(3, 5, 6, s, "s");
		node_data e_4 = new Node(4, 9, 6, w, "tt");
		this.d.addNode(a_0);
		this.d.addNode(b_1);
		this.d.addNode(c_2);
		this.d.addNode(d_3);
		this.d.addNode(e_4);
		this.d.connect(a_0.getKey(), d_3.getKey(), 1);
		this.d.connect(a_0.getKey(), b_1.getKey(), 2);
		this.d.connect(b_1.getKey(), c_2.getKey(), 3);
		this.d.connect(d_3.getKey(), c_2.getKey(), 4);
		this.d.connect(e_4.getKey(), d_3.getKey(), 5);

		edge_data e4 = d.getEdge(e_4.getKey(), d_3.getKey());
		d.removeEdge(e_4.getKey(), d_3.getKey());
		assertTrue(e4 == null);

		//        edge_data e = d.getEdge(d_3.getKey(), c_2.getKey());
		//        d.removeEdge(d_3.getKey(),c_2.getKey());
		//        assertTrue(e==null);
	}

	@Test
	void nodeSize() {
		DGraph d = new DGraph();
		int i = 0;
		while (i < 1000) {
			Point3D point = new Point3D(i, i + 1, i + 2);
			node_data n = new Node(i, i + 1, point);
			d.addNode(n);
			i++;
		}
		assertEquals(1000,d.nodeSize());
		System.out.println("test (nodeSize) succeeded ! ");
	}

	@Test
	void edgeSize() {
		DGraph d = new DGraph();
		int i = 0;
		while (i < 1000) {
			Point3D point = new Point3D(i, i + 1, i + 2);
			node_data n = new Node(i, i + 1, point);
			d.addNode(n);
			i++;
		}
		int j = 0;
		while (j < 990) {
			edge_data e = new Edge(j, j + 1, j);
			d.connect(j, j + 1, j);
			j++;
		}
		d.removeEdge(0,1);
		assertEquals(d.edgeSize(),989);
		System.out.println("test (edgeSize) succeeded ! ");
	}

	@Test
	void getMC() {
		DGraph d = new DGraph();
		node_data node1 = new Node(1,2,new Point3D(1,2,3));
		node_data node2 = new Node(2,4,new Point3D(1,2,3));
		node_data node3 = new Node(3,4,new Point3D(1,2,3));
		d.addNode(node1);
		d.addNode(node2);
		d.addNode(node3);
		d.connect(node1.getKey(),node2.getKey(),10);
		d.removeEdge(node1.getKey(),node2.getKey());
		d.connect(node1.getKey(),node3.getKey(),23);
		assertEquals(6 , d.getMC());
		System.out.println("expected : 6 , actual : MC = " + d.getMC());
	}

}