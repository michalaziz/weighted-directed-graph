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

public class Graph_GUI extends JFrame implements ActionListener, MouseListener,Serializable {
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
	public static void main (String ags[])
	{
		//Graph_GUI gui = new Graph_GUI();
		//gui.setVisible(true);


		//		Point3D x = new Point3D(0,30,0);
		//		Point3D y = new Point3D(-30,-15,0);
		//		Point3D z = new Point3D(30,-15,0);
		//		Point3D w = new Point3D(0,-20,0);
		//		Point3D q = new Point3D(0,15,0);
		//
		//		node_data a = new Node(1,3,2,x,"michal");
		//		node_data b = new Node(2,4,3,y,"yarden");
		//		node_data c = new Node(3,5,4,z,"sf");
		//		node_data e = new Node(4,6,5,w,"sd");
		//		//node_data f = new Node(5,7,6,q,"ssss");
		//
		//		DGraph d = new DGraph();
		//		d.addNode(a);
		//		d.addNode(b);
		//		d.addNode(c);
		//		d.addNode(e);
		//		//		d.addNode(f);
		//		d.connect(a.getKey(),b.getKey(),1);
		//		d.connect(b.getKey(),c.getKey(),2);
		//		d.connect(c.getKey(),a.getKey(),3);
		//		d.connect(c.getKey(),e.getKey(),4);
		//		d.connect(e.getKey(),b.getKey(),4);
		//		d.connect(e.getKey(),a.getKey(),4);

		//		Graph_GUI gui = new Graph_GUI(d);

		DGraph d_g = new DGraph();
		Graph_Algo g_a = new Graph_Algo();
		Point3D x = new Point3D(0, 40, 3);
		Point3D y = new Point3D(-20, 0, 3);
		Point3D z = new Point3D(20, 0, 3);



		node_data a = new Node(1, 4, 0, x, "michal");
		node_data b = new Node(2, 4, 0, y, "michal");
		node_data c = new Node(3, 4, 0, z, "michal");


		d_g.addNode(a);
		d_g.addNode(b);
		d_g.addNode(c);

		d_g.connect(1, 2, 4);
		d_g.connect(2, 3, 4);
		d_g.connect(3, 1, 4);


		//d_g.removeNode(3);
		g_a.init(d_g); 
		Graph_GUI gui = new Graph_GUI(d_g);
		gui.initGUI();

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}