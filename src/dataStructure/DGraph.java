package dataStructure;
import java.util.HashMap;
import java.util.Iterator;

import algorithms.Graph_Algo;
import utils.Point3D;

import java.io.Serializable;
import java.util.Collection;

public class DGraph implements graph,Serializable {

	HashMap<Integer,node_data> nodes = new HashMap<>();
	HashMap<Integer,HashMap<Integer,edge_data>> edges = new HashMap<>();	
	int nodeSize=0;
	int edgeSize=0;
	int MC=0;

	/**
	 * constructor
	 */
	public DGraph()
	{
		this.nodes=new HashMap<Integer,node_data>();
		this.edges = new HashMap<Integer,HashMap<Integer,edge_data>>();
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

		if(!this.nodes.containsKey(key))
		{	
			return null;
		}
		else {return this.nodes.get(key);}
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
			return this.edges.get(src).get(dest);
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
		if (nodes.keySet().contains(n.getKey()))
			throw new RuntimeException(" this key is already exist ! ");
		else {
			this.nodes.put(n.getKey(),n);
			this.MC++;
			this.nodeSize++;
		}
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
		if(edges.get(src)==null) //creat the new edge
		{
			edges.put(src, new HashMap<Integer,edge_data>());
			edgeSize++;
			edges.get(src).put(dest,e);//connect the edge
		}
		else {//the edge is exist just connect the nodes
			edges.get(src).put(dest, e);
			edgeSize++;
		}
		MC++;
	}

	/**
	 * @return all vertex in this graph.
	 */
	@Override
	public Collection<node_data> getV() {
		return this.nodes.values();
	}

	/**
	 * @param node_id is the key's node
	 * @return a collection representing all the edges getting out of a given node by ist id.
	 */
	@Override
	public Collection<edge_data> getE(int node_id) {
		try {
			return this.edges.get(node_id).values();
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

		node_data removed= (Node)nodes.remove(key);
		if(removed!=null)
		{
			nodeSize--;
		}

		for (Iterator<Integer> iter = nodes.keySet().iterator(); iter.hasNext();) 
			removeEdge(iter.next(), key);

		for (Iterator<Integer> iter2 = nodes.keySet().iterator(); iter2.hasNext();) 
			removeEdge(key,iter2.next());
		return removed;
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
		edge_data temp =this.getEdge(src,dest);
		if(temp!=null) {
			this.edges.get(src).remove(dest);
			this.edgeSize--;
			this.MC++;
			return temp ;
		}
		return temp;
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

}