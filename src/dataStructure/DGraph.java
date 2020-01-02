package dataStructure;
import java.util.HashMap;

import algorithms.Graph_Algo;
import utils.Point3D;

import java.io.Serializable;
import java.util.Collection;

public class DGraph implements graph,Serializable {

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
	 * @param key - the node_id
	 * @return the node by its id
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
	 * @param src is the key source's node
	 * @param dest is the ket dest's node
	 * @return the edge by the source and destination vertex key
	 */
	@Override
	public edge_data getEdge(int src, int dest) {

		try
		{
			return this.hmE.get(src).get(dest);
		}
		catch(Exception e) // if dest or src does not exist
		{
			return null ;
		}
	}


	/**
	 * add a new node to the graph with the given node_data n.
	 * @param n
	 */
	@Override
	public void addNode(node_data n) {
		this.hmN.put(n.getKey(),n);
		this.MC++;
		this.nodeSize++;
	}

	/**
	 * This functuon connect an edge with weight w between node src to node dest.
	 * @param src - the source of the edge.
	 * @param dest - the destination of the edge.
	 * @param w - positive weight representing the cost between src-->dest.
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

	/**
	 * @return all vertex in this graph.
	 */
	@Override
	public Collection<node_data> getV() {
		return this.hmN.values();
	}

	/**
	 * @param node_id is the key's node
	 * @return a collection representing all the edges getting out of a given node by ist id.
	 */
	@Override
	public Collection<edge_data> getE(int node_id) {
		try {
			return this.hmE.get(node_id).values();
		}catch(Exception e){
			return null;
		}
		
	}

	/**
	 * This function delete a node from the graph by ist key and delete all edges which starts
	 * or ends at this node.
	 * @param key is the key we wont to delete.
	 * @return
	 */
	@Override
	public node_data removeNode(int key) {
		if(hmN.get(key)==null)
			return null;
		node_data remove_node=hmN.remove(key);
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
	/**
	 * This function delete the edge from the graph by src and dest keys.
	 * @param src
	 * @param dest
	 * @return the deleted edge.
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

	/**
	 * @return the number of vertices in the graph.
	 */
	@Override
	public int nodeSize() {
		return this.nodeSize;
	}

	/**
	 * @return the number of edges that in the graph.
	 */
	@Override
	public int edgeSize() {
		return this.edgeSize;
	}

	/**
	 * @return the number of changes that has been done in the graph.
	 */
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