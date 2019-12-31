package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms,Serializable{
	private graph graph_A;

	public Graph_Algo()
	{
		this.graph_A=null;
	}

	public Graph_Algo(DGraph g){
		this.graph_A=g;
	}
	@Override
	public void init(graph g) {
		this.graph_A=g;	
	}

	@Override
	public void init(String file_name) {
		graph g= this.graph_A;
		File f= new File (file_name); 
		try {
			FileInputStream fis= new FileInputStream(f);
			ObjectInputStream ois= new ObjectInputStream(fis);
			g= (graph) ois.readObject();

			ois.close();
			fis.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void save(String filename) {
		graph graph= this.graph_A;
		try
		{    
			FileOutputStream file = new FileOutputStream(filename); 
			ObjectOutputStream out = new ObjectOutputStream(file); 

			out.writeObject(graph); 

			out.close(); 
			file.close(); 

			System.out.println("Object has been serialized"); 
		}   
		catch(IOException ex) 
		{ 
			System.out.println("IOException is caught"); 
		} 

	}


	@Override
	public boolean isConnected() {
		
		setTag_0(this.graph_A);
		Collection<node_data> vertex = this.graph_A.getV();
		Iterator<node_data> it=vertex.iterator();
        node_data v1= (node_data)it.next();
        dfsWithoutRecursion(v1);
	    for(node_data i : vertex)
	    {
	      if(i.getTag()==0) return false;	
	    }
	    return true;
	}


	@Override
	public double shortestPathDist(int src, int dest) {
		Collection<node_data> vertex = this.graph_A.getV();
		Iterator<node_data> iterV=vertex.iterator();
		node_data desT= this.graph_A.getNode(dest);
	    node_data srcCurrent= this.graph_A.getNode(src);

		while(iterV.hasNext())
		{
			node_data n  = iterV.next();
			n.setTag(0);
			n.setWeight(Double.MAX_VALUE);
		}
		srcCurrent.setWeight(0);
		Dijkstra(srcCurrent,desT);
		return this.graph_A.getNode(dest).getWeight();
	}



	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		Iterator<Integer> iter= targets.iterator();
		List<node_data> path = new ArrayList<node_data>();
		boolean flag= false;

		while(iter.hasNext()&&flag==false)
		{
			path=isVisit(iter.next(),targets);
			if(path!=null)
				flag= true;
		}

		return path;
	}



	@Override
	public graph copy() {
		Graph_Algo gC= new Graph_Algo();
		this.save("temp.txt");
		gC.init("temp.txt");
		return gC.graph_A;
	}


/////////////////////helper functions to implement the algorithms//////////////////////
	
	
	private List<node_data> isVisit (Integer current , List<Integer>tNodes)
	{
		List<node_data> path =new ArrayList<node_data>();
		path.add(graph_A.getNode(current));
		tNodes.remove(new Integer(current));
		Iterator<edge_data> iter=graph_A.getE(graph_A.getNode(current).getKey()).iterator();
		while(iter.hasNext())
		{
			edge_data tempEdge =iter.next();
			if(tNodes.contains(tempEdge.getDest()))
			{
				tNodes.remove(new Integer(tempEdge.getDest()));

			}
			path.add(graph_A.getNode(tempEdge.getDest()));
		}
		if(!tNodes.isEmpty())
			return null;
		return path;
	}

	//set the all vetrex's tags to 0
	private void setTag_0(graph graph) {
		Collection <node_data> v = graph.getV();
		Iterator<node_data> it=v.iterator();
		for(node_data n : v)
		{
			node_data node= (node_data) it.next();
			node.setTag(0);
		}
	}

	public void dfsWithoutRecursion(node_data start) {
		
		Stack<node_data> stack = new Stack<node_data>();
		stack.push(start);
		Collection<edge_data> eColl ;

		while(!stack.isEmpty())
		{
			node_data current = stack.pop();
			current.setTag(1);
			eColl= this.graph_A.getE(current.getKey());
			for(edge_data edge :eColl)
			{
				if( this.graph_A.getNode(edge.getDest()).getTag() == 0 && this.graph_A.getE(edge.getDest()) != null) 
				{
					stack.push(this.graph_A.getNode(edge.getDest()));
			    }
			}	
			
		}
		
	}

	public double Dijkstra(node_data src, node_data dest)
	{
		Queue<node_data> Queue = new LinkedList<node_data>();
		node_data src_node = this.graph_A.getNode(src.getKey());
		src_node.setWeight(0);
		Queue.add(src_node);

		while(!Queue.isEmpty())
		{
			src_node.setTag(1);
			Collection<edge_data> eSrc = this.graph_A.getE(src_node.getKey());
			for(edge_data e : eSrc)
			{
				node_data neibor = this.graph_A.getNode(e.getDest());
				double neibor_Plus = src_node.getWeight()+ e.getWeight();
				if(neibor.getWeight() > neibor_Plus)
				{
					neibor.setWeight( src_node.getWeight()+ e.getWeight());
//					neibor.setInfo(""+src_node.getKey());
					Queue.add(neibor);
				}
			}
			src_node = Queue.poll();
		}
		if (this.graph_A.getNode(dest.getKey()).getWeight() == Double.MAX_VALUE)
			throw new RuntimeException("there is no path ");
		else
			return this.graph_A.getNode(dest.getKey()).getWeight();

	}

	public static void main(String args[])
	{
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
		List<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(4);
		list.add(1);
		Graph_Algo g = new Graph_Algo();
		g.init(d);
		g.save("test.txt");
		g.init("test.txt");
		//g.TSP(list);


	}
}