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
	private graph graph_gui ;
	private Graph_Algo gAlgo;

	public Graph_Algo get_gAlgo()
	{
		return this.gAlgo;
	}
	public graph get_graph_gui()
	{
		return graph_gui;
	}
	public Graph_GUI(graph g )
	{
		this.graph_gui = g;	
		gAlgo=new Graph_Algo();
		gAlgo.init(this.graph_gui);
		StdDraw.setGui(this);

	}

	public Graph_GUI()
	{
		gAlgo=new Graph_Algo();
		graph_gui=new DGraph();
		StdDraw.setGui(this);
	}

	public void initGUI() 
	{	
		this.drawPoints();
		this.drawEdges();
	}
public void init(String name)
{
	this.gAlgo.init(name);
	this.graph_gui= gAlgo.graph_A;
	initGUI();
}


	public void drawPoints()
	{

		StdDraw.setXscale(50, -50);
		StdDraw.setYscale(50,-50);

		Iterator<node_data> iter=  this.graph_gui.getV().iterator();
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
		Iterator<node_data> iterN=  this.graph_gui.getV().iterator();
		while(iterN.hasNext())
		{
			node_data tempV=iterN.next();
			Collection<edge_data> edges = this.graph_gui.getE(tempV.getKey());
			if(edges==null)
				break;
			Iterator<edge_data> iterE=edges.iterator();
			while(iterE.hasNext())
			{
				StdDraw.setPenRadius(0.003);
				StdDraw.setPenColor(StdDraw.BLACK);
				edge_data tempE=iterE.next();
				StdDraw.line(graph_gui.getNode(tempE.getSrc()).getLocation().x(),
						graph_gui.getNode(tempE.getSrc()).getLocation().y(),
						graph_gui.getNode(tempE.getDest()).getLocation().x(),
						graph_gui.getNode(tempE.getDest()).getLocation().y());
				StdDraw.setPenRadius(0.015);
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.point(((graph_gui.getNode(tempE.getSrc()).getLocation().x()+
						graph_gui.getNode(tempE.getDest()).getLocation().x()*7)/8),
						((graph_gui.getNode(tempE.getSrc()).getLocation().y())+
								graph_gui.getNode(tempE.getDest()).getLocation().y()*7)/8);
				StdDraw.setPenColor(StdDraw.BOOK_BLUE);
				StdDraw.text(((graph_gui.getNode(tempE.getSrc()).getLocation().x()+
						graph_gui.getNode(tempE.getDest()).getLocation().x())/2),
						((graph_gui.getNode(tempE.getSrc()).getLocation().y())+
								graph_gui.getNode(tempE.getDest()).getLocation().y())/2,
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
		Point3D x = new Point3D(10, 20, 3);
		Point3D y = new Point3D(-10, -20, -3);
		Point3D z = new Point3D(20, 40, 8);
		Point3D w = new Point3D(-32, 30, 9);
		Point3D s = new Point3D(40, -20, 0);
		Point3D j = new Point3D(90, 10, -8);


		node_data a_0 = new Node(0, 3, 2, x, "michal");
		node_data b_1 = new Node(1, 4, 3, y, "yarden");
		node_data c_2 = new Node(2, 5, 4, z, "sf");
		node_data d_3 = new Node(3, 5, 6, s, "s");
		node_data e_4 = new Node(4, 9, 6, w, "tt");
		node_data f_5 = new Node(5, 9, 6, w, "tt");

		d_g.addNode(a_0);
		d_g.addNode(b_1);
		d_g.addNode(c_2);
		d_g.addNode(d_3);
		d_g.addNode(e_4);
		d_g.addNode(f_5);

		d_g.connect(a_0.getKey(), b_1.getKey(), 10);
		d_g.connect(a_0.getKey(), e_4.getKey(), 5);
		d_g.connect(b_1.getKey(), e_4.getKey(), 2);
		d_g.connect(b_1.getKey(), c_2.getKey(), 1);
		d_g.connect(c_2.getKey(), d_3.getKey(), 4);
		d_g.connect(d_3.getKey(), a_0.getKey(), 7);
		d_g.connect(d_3.getKey(), c_2.getKey(), 6);
		d_g.connect(e_4.getKey(), d_3.getKey(), 2);
		d_g.connect(e_4.getKey(), c_2.getKey(), 9);
		d_g.connect(f_5.getKey(), b_1.getKey(), 3);
		d_g.connect(f_5.getKey(), c_2.getKey(), 14);
		d_g.connect(c_2.getKey(), f_5.getKey(), 20);
		//d_g.removeNode(1);
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