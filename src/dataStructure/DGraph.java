package dataStructure;
import java.util.HashMap;

import algorithms.Graph_Algo;
import utils.Point3D;
import java.util.Collection;

public class DGraph implements graph{

	HashMap<Integer,node_data> hmN = new HashMap<>();
	HashMap<Integer,HashMap<Integer,edge_data>> hmE = new HashMap<>();	
	int nodeSize,edgeSize;
	int MC;

	/**
	 * constructor
	 */
	public DGraph()
	{
		this.hmN=new HashMap<Integer,node_data>();
		this.hmE = new HashMap<Integer,HashMap<Integer,edge_data>>();
		this.MC=0;
		this.edgeSize=0;
		this.nodeSize=0;	
	}
	/**
	 * return the node by its id
	 */
	@Override
	public node_data getNode(int key) {

		if(!this.hmN.containsKey(key))
		{	
			return null;
		}
		else {return this.hmN.get(key);}
	}

	/**
	 * return the edge by the source and destination vertex key
	 */
	@Override
	public edge_data getEdge(int src, int dest) {
		//why try and catch?
		try
		{
			return this.hmE.get(src).get(dest);
		}
		catch(Exception e)
		{
			return null ;
		}
	}

	/**
	 * add a new node to the graph with the given node_data n.
	 */
	@Override
	public void addNode(node_data n) {
		this.hmN.put(n.getKey(),n);
		this.MC++;
		this.nodeSize++;
	}
	/**
	 * Create a new edge that start at node src and end at node dest
	 */

	@Override
	public void connect(int src, int dest, double w) {
		edge_data e= new Edge(src,dest,w);
		if(w<0)
			throw new RuntimeException("the weight is negative");
		if(src==dest)
			throw new RuntimeException("The keys are equal,it's the same vertex");
		if(hmE.get(src)==null) //creat the new edge
		{
			hmE.put(src, new HashMap<Integer,edge_data>());
			edgeSize++;
			hmE.get(src).put(dest,e);//connect the edge
		}
		else {//the edge is exist just connect the nodes
			hmE.get(src).put(dest, e);
			edgeSize++;
		}
		MC++;
	}

	@Override
	public Collection<node_data> getV() {
		return this.hmN.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		try {
			return this.hmE.get(node_id).values();
		}catch(Exception e){
			return null;
		}
		
	}
	/**
	 * remove the node with the key the we'v got and all the edges that connect to this key 
	 */
	@Override
	public node_data removeNode(int key) {
		if(hmN.get(key)==null)
			return null;
		node_data remove_node=hmN.remove(key);
		hmE.remove(key);
		for(int i=0; i<hmN.keySet().size(); i++)
			if(hmE.get(i).containsKey(key))
				hmE.get(i).remove(key);
		if(remove_node!=null)
		{
			MC++;
			nodeSize--;
		}
		return remove_node;
	}
	/**
	 * remove the edge the starts at node src and ends at node dest 
	 */

	@Override
	public edge_data removeEdge(int src, int dest) {
		if(getNode(src)==null)
			return null;
		edge_data remove_edge=hmE.get(src).remove(dest);
		if(remove_edge!=null) {
			MC++;
			edgeSize--;
		}
		return remove_edge;

	}

	@Override
	public int nodeSize() {
		return this.nodeSize;
	}

	@Override
	public int edgeSize() {
		return this.edgeSize;
	}

	@Override
	public int getMC() {
		return this.MC;
	}
	
	public static void main(String args[])
	{
//		Point3D x1 = new Point3D(14,4,0);
//		Point3D y2 = new Point3D(-75,14,0);
//		Point3D q3 = new Point3D(80,5,0);
//		
//		node_data a1 = new Node(4,2,3,x,"asf");
//		node_data b2 = new Node(5,4,6,y,"ads");
//		node_data c3 = new Node(6,50,50,q,"sf");
//		
		
		
		
		
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
		//d.connect(c.getKey(),a.getKey(),10);
//		d.connect(b.getKey(),a.getKey(),13);
		//System.out.println(d.Edgemap.toString());

//		d.addNode(a1);
//		d.addNode(b2);
//		d.addNode(c3);
//		d.connect(a.getKey(),b.getKey(),2);
//		d.connect(a.getKey(),a1.getKey(),4);
//		d.connect(a.getKey(),c.getKey(),1);
//		d.connect(c.getKey() ,b2.getKey(),1);
//		d.connect(b2.getKey(),a1.getKey(),1);
//		d.connect(a1.getKey(),c3.getKey(),1);
//		d.connect(b.getKey(),c3.getKey(),1);

		Graph_Algo p = new Graph_Algo(d);
		System.out.println(p.isConnected());
	}

}