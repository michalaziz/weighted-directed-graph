package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Graph_GUI extends JFrame implements ActionListener {
	graph graph_gui =new DGraph();
	LinkedList<Point3D> points = new LinkedList<Point3D>();

	Graph_GUI(graph g)
	{
		this.graph_gui=g;
		this.initGUI();

	}

	Graph_GUI()
	{
		initGUI();
	}

	private void initGUI() 
	{
		StdDraw.setCanvasSize(400,400);
		this.drawPoints();
		this.drawEdges();
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		Point3D prev = null;

		for (Point3D p : points) 
		{
			g.setColor(Color.BLUE);
			g.fillOval((int)p.x(), (int)p.y(), 10, 10);

			if(prev != null)
			{
				g.setColor(Color.RED);
				g.drawLine((int)p.x(), (int)p.y(), 
						(int)prev.x(), (int)prev.y());

				g.drawString("5", (int)((p.x()+prev.x())/2),(int)((p.y()+prev.y())/2));
			}

			prev = p;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String str = e.getActionCommand();

		if(str.equals("Item 1"))
		{
			Point3D p1 = new Point3D(100,100);
			Point3D p2 = new Point3D(50,300);
			Point3D p3 = new Point3D(400,150);

			points.add(p1);
			points.add(p2);
			points.add(p3);

			repaint();
		}

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
			StdDraw.setPenRadius(0.003);
			StdDraw.setPenColor(StdDraw.BLACK);
			node_data tempV=iterN.next();
			Iterator<edge_data> iterE=  this.graph_gui.getE(tempV.getKey()).iterator();
			while(iterE.hasNext())
			{
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


		Point3D x = new Point3D(0,30,0);
		Point3D y = new Point3D(-30,-15,0);
		Point3D z = new Point3D(30,-15,0);

		node_data a = new Node(1,3,2,x,"michal");
		node_data b = new Node(2,4,3,y,"yarden");
		node_data c = new Node(3,5,4,z,"sf");

		DGraph d = new DGraph();
		d.addNode(a);
		d.addNode(b);
		d.addNode(c);
		d.connect(a.getKey(),b.getKey(),1);
		d.connect(b.getKey(),c.getKey(),2);
		d.connect(c.getKey(),a.getKey(),3);

		Graph_GUI gui = new Graph_GUI(d);

	}

}
