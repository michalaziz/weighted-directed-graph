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

public class Graph_GUI extends JFrame implements ActionListener, MouseListener,Serializable {
	private graph graph_gui ;
    private Graph_Algo gAlgo;
    //LinkedList<Point3D> points = new LinkedList<Point3D>();
	
	public Graph_GUI(graph g)
	{
		
		this.graph_gui=g;
		this.gAlgo.init(g);
		initGUI();
//		this.graph_gui=g;
//		this.initGUI();

	}

	public Graph_GUI()
	{
		initGUI();
	}

	private void initGUI() 
	{
//		StdDraw.setCanvasSize(400,400);
//		this.drawPoints();
//		this.drawEdges();
		
		
		this.setSize(500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MenuBar menuBar = new MenuBar();
		
		//menu for draw,save,download
		Menu menu = new Menu("File");
		menuBar.add(menu);
		this.setMenuBar(menuBar);
		
		MenuItem save = new MenuItem("Save");
		save.addActionListener(this);
		
		MenuItem paint = new MenuItem("Paint");
		paint.addActionListener(this);
		
		MenuItem download = new MenuItem("Download");
		download.addActionListener(this);
		
		
		menu.add(paint);
		menu.add(save);
		menu.add(download);
		
		//menu for algorithms
		Menu menu1=new Menu("Algorithms");
		menuBar.add(menu1);
		this.setMenuBar(menuBar);
		
		MenuItem isConnect = new MenuItem("isConnected");
		isConnect.addActionListener(this);
		
		MenuItem tsp = new MenuItem("TSP");
		tsp.addActionListener(this);
		
		MenuItem shortPath = new MenuItem("shortestPathDis");
		shortPath.addActionListener(this);
		
//		MenuItem shortPath1 = new MenuItem("shortestPath");
//		shortPath1.addActionListener(this);
		
		menu1.add(isConnect);
		menu1.add(tsp);
		menu1.add(shortPath);
	//	menu1.add(shortPath1);

		this.addMouseListener(this);
		
		
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Collection<node_data> nodes =graph_gui.getV();
		Iterator <node_data> it= nodes.iterator();
		while(it.hasNext())
		{
			node_data node= it.next();
			Point3D point= node.getLocation();
			g.setColor(Color.BLUE);
			g.fillOval((point.x(),point.y(), 10, 10);
    		g.drawString("" + node.getKey(), point.x(), point.y());
			Collection<node_data> edges =graph_gui.getE(node.getkey());
			Iterator <edge_data> it1= edges.iterator();
             
			while(it1.hadNext)
			{
				g.setColor(Color.RED);
				edge_data edge= it1.next();
				node_data node1=this.graph_gui.getNode(edge.getDest());
				Point3D point1= node1.getLocation();
				g.drawLine(point.x(),point.y(),point1.x(),point1.y());
				g.drawString(""+ e.getWeight(),(point.x()+point1.getLocation().x())/2,(point.y()+point1.getLocation().y())/2);
				g.setColor(Color.PINK);
				g.fillOval((point.x(),point.y(), 10, 10);
			}
		
		}
			 
		
		
	
		
		
//		super.paint(g);
//
//		Point3D prev = null;
//
//		for (Point3D p : points) 
//		{
//			g.setColor(Color.BLUE);
//			g.fillOval((int)p.x(), (int)p.y(), 10, 10);
//
//			if(prev != null)
//			{
//				g.setColor(Color.RED);
//				g.drawLine((int)p.x(), (int)p.y(), 
//						(int)prev.x(), (int)prev.y());
//
//				g.drawString("5", (int)((p.x()+prev.x())/2),(int)((p.y()+prev.y())/2));
//			}
//
//			prev = p;
//		}
	

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String str = e.getActionCommand();

		if(str.equals("Paint"))
		{
				
		}
		
		if else (str.equals("Save"))
		{
				
		}
		
		if else (str.equals("shortestPathDis"))
		{
				
		}
		
		if else (str.equals("Download"))
		{
				
		}
		
		if else (str.equals("TSP"))
		{
				
		}
		
		if else (str.equals("isConnected"))
		{
				
		}
		
//		else (str.equals("shortestPath"))
//		{
//				
//		}
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

