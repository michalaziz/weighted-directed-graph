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
    //LinkedList<Point3D> points = new LinkedList<Point3D>();

    public Graph_Algo get_gAlgo()
    {
        return gAlgo;
    }
    public graph get_graph_gui()
    {
        return graph_gui;
    }
    public Graph_GUI(graph g)
    {
        this.graph_gui=g;
        this.initGUI();

    }

    public Graph_GUI()
    {
        initGUI();
    }

    private void initGUI()
    {
/***********/
        //draw the graph//
        this.drawPoints();
        this.drawEdges();
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
            Iterator<edge_data> iterE = edges.iterator();
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


        Point3D x = new Point3D(0,30,0);
        Point3D y = new Point3D(-30,-15,0);
        Point3D z = new Point3D(30,-15,0);
        Point3D w = new Point3D(0,-20,0);
        Point3D q = new Point3D(0,15,0);

        node_data a = new Node(1,3,2,x,"michal");
        node_data b = new Node(2,4,3,y,"yarden");
        node_data c = new Node(3,5,4,z,"sf");
        node_data e = new Node(4,6,5,w,"sd");
        //node_data f = new Node(5,7,6,q,"ssss");

        DGraph d = new DGraph();
        d.addNode(a);
        d.addNode(b);
        d.addNode(c);
        d.addNode(e);
        //		d.addNode(f);
        d.connect(a.getKey(),b.getKey(),1);
        d.connect(b.getKey(),c.getKey(),2);
        d.connect(c.getKey(),a.getKey(),3);
        d.connect(c.getKey(),e.getKey(),4);
        d.connect(e.getKey(),b.getKey(),4);
        d.connect(e.getKey(),a.getKey(),4);

        Graph_GUI gui = new Graph_GUI(d);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

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