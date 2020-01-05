package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.node_data;

import javax.swing.JFrame;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.graph;
import utils.Point3D;
import utils.StdDraw;

public class Graph_GUI extends JFrame implements Serializable {

	private graph graph ;
	private Graph_Algo gAlgo;

	public Graph_Algo get_gAlgo()
	{
		return this.gAlgo;
	}
	public graph get_graph()
	{
		return graph;
	}
	public Graph_GUI(graph g )
	{
		this.graph = g;	
		gAlgo=new Graph_Algo();
		gAlgo.init(this.graph);
		StdDraw.setGui(this);
		initGUI();

	}

	public Graph_GUI()
	{
		gAlgo=new Graph_Algo();
		graph=new DGraph();
		StdDraw.setGui(this);
	}

	public void initGUI() 
	{	
		this.setScale();
		this.drawPoints();
		this.drawEdges();
	}
	public void init(String name)
	{
		this.gAlgo.init(name);
		this.graph= gAlgo.graph_A;
		initGUI();
	}

	public void setScale() {
		int x_min = 0;
		int x_max = 0;
		int y_min = 0;
		int y_max = 0;

		Iterator<node_data> iter = this.graph.getV().iterator();
		while(iter.hasNext()) {
			node_data currentNode = iter.next();
			if(currentNode.getLocation().x() < x_min) {
				x_min = (int) currentNode.getLocation().x();
			}
			if(currentNode.getLocation().x() > x_max) {
				x_max = (int) currentNode.getLocation().x();
			}
			if(currentNode.getLocation().y() < y_min) {
				y_min = (int) currentNode.getLocation().y();
			}
			if(currentNode.getLocation().y() > y_max) {
				y_max = (int) currentNode.getLocation().y();
			}
		}
		StdDraw.setCanvasSize(Math.abs(x_min+x_max) +300 , Math.abs(y_min+y_max)+300);
		StdDraw.setXscale(x_min-10,x_max+10);
		StdDraw.setYscale(y_min-10,y_max+10);
	}

	public void drawPoints()
	{

		Iterator<node_data> iter=  this.graph.getV().iterator();
		while(iter.hasNext())
		{
			node_data temp= iter.next();
			StdDraw.setPenRadius(0.02);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.point(temp.getLocation().x(),temp.getLocation().y());
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(0.001);
			StdDraw.text(temp.getLocation().x()-0.1, temp.getLocation().y()-3,""+temp.getKey());;
		}
	}

	public void drawEdges()
	{
		Iterator<node_data> iterN=  this.graph.getV().iterator();
		while(iterN.hasNext())
		{
			node_data tempV=iterN.next();
			Collection<edge_data> edges = this.graph.getE(tempV.getKey());
			if(edges==null)
				break;
			Iterator<edge_data> iterE=edges.iterator();
			while(iterE.hasNext())
			{
				StdDraw.setPenRadius(0.003);
				StdDraw.setPenColor(StdDraw.BLACK);
				edge_data tempE=iterE.next();
				StdDraw.line(graph.getNode(tempE.getSrc()).getLocation().x(),
						graph.getNode(tempE.getSrc()).getLocation().y(),
						graph.getNode(tempE.getDest()).getLocation().x(),
						graph.getNode(tempE.getDest()).getLocation().y());
				StdDraw.setPenRadius(0.015);
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.point(((graph.getNode(tempE.getSrc()).getLocation().x()+
						graph.getNode(tempE.getDest()).getLocation().x()*7)/8),
						((graph.getNode(tempE.getSrc()).getLocation().y())+
								graph.getNode(tempE.getDest()).getLocation().y()*7)/8);
				StdDraw.setPenColor(StdDraw.BOOK_BLUE);
				StdDraw.text(((graph.getNode(tempE.getSrc()).getLocation().x()+
						graph.getNode(tempE.getDest()).getLocation().x())/2),
						((graph.getNode(tempE.getSrc()).getLocation().y())+
								graph.getNode(tempE.getDest()).getLocation().y())/2,
						""+tempE.getWeight());
			}
		}
	}
	public static void main(String args[]) {

		DGraph dg = new DGraph();

		Point3D p1 = new Point3D(0,0,0);
		Point3D p2 = new Point3D(0,-30,0);
		Point3D p3 = new Point3D(40,10,0);
		Point3D p4 = new Point3D(80,0,0);
		Point3D p5 = new Point3D(-40,10,0);
		Point3D p6 = new Point3D(-80,0,0);

		Node n1 = new Node(1,1,1,p1,"1");
		Node n2 = new Node(2,2,2,p2,"2");
		Node n3 = new Node(3,3,3,p3,"3");
		Node n4 = new Node(4,4,4,p4,"4");
		Node n5 = new Node(5,5,5,p5,"5");
		Node n6 = new Node(6,6,6,p6,"16");
		
		dg.addNode(n1);
		dg.addNode(n2);
		dg.addNode(n3);
		dg.addNode(n4);
		dg.addNode(n5);
		dg.addNode(n6);
		
		dg.connect(1, 5, 1);
		dg.connect(5, 6, 2);
		dg.connect(6, 2, 3);
		dg.connect(2, 4, 4);
		dg.connect(4, 3, 5);
		dg.connect(3, 1, 6);
		
		Graph_GUI gui = new Graph_GUI(dg);
		
		
	}



}