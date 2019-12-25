package gui;

import javax.swing.JFrame;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.node_data;
import gui.Graph_GUI;
import utils.Point3D;

public class Main
{

	public static void main(String[] args) 
	{
		Graph_GUI gui = new Graph_GUI();
		gui.setVisible(true);
		
		
		Point3D x = new Point3D(1,2,3);
		Point3D y = new Point3D(-1,-2,-3);
		Point3D z = new Point3D(6,7,8);
		
		node_data a = new Node(1,3,2,x,"michal");
		node_data b = new Node(2,4,3,y,"yarden");
		node_data c = new Node(3,5,4,z,"sf");

		DGraph d = new DGraph();
		d.addNode(a);
		d.addNode(b);
		d.addNode(c);
		d.connect(a.getKey(),b.getKey(),10);
		d.connect(b.getKey(),c.getKey(),10);
		d.connect(c.getKey(),a.getKey(),10);
		//Graph_Algo p = new Graph_Algo(d);
		
		
	}

}
