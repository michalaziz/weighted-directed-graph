package dataStructure;
import java.util.HashMap;
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
		if(hmE.get(src).containsKey(dest))//check if the edge is already exist
			throw new RuntimeException("the edge("+src+","+dest+") is already exist");
		hmE.get(src).put(dest, e);
		MC++;
		nodeSize++;
	}


	//		if(this.getNode(src)!=null && this.getNode(dest)!=null)
	//		{
	//			if(src!=dest)//check if the key's src and dest are different.
	//			{
	//				if(this.getEdge(src, dest)==null)//check if the edge exist
	//				{
	//					if(w>=0)// check if the weight negative
	//					{
	//						this.hmE.get(src).put(dest,e); // add e to hmE 
	//						this.MC++;
	//						this.edgeSize++;
	//					}
	//					else {throw new RuntimeException("Edge has no negative weight");}
	//				}
	//				else //???????//need to check this!///////
	//				{
	//					this.hmE.get(src).put(dest,e);
	//					this.MC++;
	//				} 
	//			} 
	//			else{throw new RuntimeException("The keys are equal,it's the same vertex");}
	//		}
	//		else 
	//		{
	//			if(this.getNode(src)==null && w>=0)
	//			{
	//				HashMap<Integer,edge_data>edges=new HashMap <Integer,edge_data>();
	//				this.hmE.put(src,edges);
	//				this.hmE.get(src).put(dest,e);
	//				this.MC++;
	//				this.edgeSize++;
	//				this.nodeSize++;
	//			}
	//		}

	@Override
	public Collection<node_data> getV() {
		return this.hmN.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return this.hmE.get(node_id).values();
	}
	/**
	 * remove the node with the key the we'v got and all the edges that connect to this key 
	 */
	@Override
	public node_data removeNode(int key) {
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

}
