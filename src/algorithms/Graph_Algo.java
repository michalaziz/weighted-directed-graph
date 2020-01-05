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
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

import dataStructure.*;
import utils.Point3D;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author Cohen yarden && Aziz michal
 *
 */

public class Graph_Algo implements graph_algorithms,Serializable{
	public graph graph_A;
	public static double infinity = Double.POSITIVE_INFINITY;

	// constructors //

	public Graph_Algo()
	{
		this.graph_A=new DGraph();
	}
	public Graph_Algo(DGraph g) {
		this.graph_A=g;
	}
	
	public Graph_Algo(graph g) {
		this.graph_A=g;
	}
	

	/**
	 * Initialization this set of algorithms on parameter g.
	 * @param g
	 */
	@Override
	public void init(graph g) {
		this.graph_A=g;	
	}

	/**
	 * This function initialization a graph from file.
	 * @param file_name
	 */
	@Override
	public void init(String file_name) {
		try {    
			FileInputStream file = new FileInputStream(file_name); 
			ObjectInputStream in = new ObjectInputStream(file);  
			graph_A = (graph) in.readObject(); 
			in.close(); 
			file.close();   
			System.out.println("Object has been deserialized"); 

		} 

		catch(IOException ex) 
		{ 
			System.out.println("IOException is caught"); 
		} 

		catch(ClassNotFoundException ex) 
		{ 
			System.out.println("ClassNotFoundException is caught"); 
		} 		
	}

	/**
	 * This function saves the graph to a file.
	 * @param file_name
	 */
	@Override
	public void save(String file_name) {
        try
        {    
            FileOutputStream file = new FileOutputStream(file_name); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(this.graph_A); 
              
            out.close(); 
            file.close(); 
              
            System.out.println("Object has been serialized"); 
        }   
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
	}

	/**
	 * This function checks whether the graph is a strong bond graph.
	 * @return true <---> (if and only if) there is a valid path from every node to each other node.
	 */
	@Override
	public boolean isConnected() {
		Collection<node_data> vertex = this.graph_A.getV();
		if(vertex.size()==0)
			return true;
		if(vertex.size()==1)
			return true;
		setTag_0(this.graph_A);
		Iterator<node_data> it=vertex.iterator();
		node_data v1= (node_data)it.next();
		dfsWithoutRecursion(v1);
		for(node_data i : vertex)
		{
			if(i.getTag()==0) return false;	
		}
		return true;
	}

	/**
	 * The function calculates the shortest path between src and dest and return it.
	 * The famous Dijkstra algorithm is used in this function as an helped function
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return
	 */
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

	/**
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return the shortest path between src to dest - as an ordered List of nodes, if there is no path
	 * between theme the function throws exception
	 */
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		this.shortestPathDist(src,dest);
		if( this.graph_A.getNode(dest).getWeight() == Integer.MAX_VALUE)
		{
			throw new RuntimeException("there is no path between src and dest ! ");
		}
		ArrayList<node_data> res = new ArrayList<>();
		node_data dest_node = this.graph_A.getNode(dest);
		res.add(dest_node);
		while(src!=dest_node.getKey())
		{
			dest_node=this.graph_A.getNode(Integer.parseInt(dest_node.getInfo()));
			res.add(dest_node);
		}
		ArrayList<node_data> ans = new ArrayList<node_data>();
		for(int j= res.size()-1; j>=0 ;j--)
		{
			ans.add(res.get(j));
		}
		return ans;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		List<node_data> Nodes = new ArrayList<node_data>();
		List<node_data> temp = new ArrayList<node_data>();
		List<node_data> res = new ArrayList<node_data>();
		
		for(Integer key :targets)
		{
			if(graph_A.getE(key)==null)//the targets list is not connect
				throw new RuntimeException("the targets list is disconnect");
		}

		for (Integer key : targets) {
			if(graph_A.getNode(key)==null) {
				return null;
			}
			Nodes.add(graph_A.getNode(key));
		}

		for(int i=0; i<Nodes.size()-1; i++) {
			List<node_data> list = new ArrayList<node_data>();

			list = (ArrayList<node_data>) shortestPath(Nodes.get(i).getKey(), Nodes.get(i+1).getKey());

			if(list==null) {
				return null;
			}
			for(int j=0; j<list.size(); j++) {
				temp.add(list.get(j));
			}
		}
		if(temp.size() % 2 == 0) {
			for(int i=0; i<temp.size(); i++) {
				if(i == temp.size()-1) {
					if(temp.get(i-1) != temp.get(i)) {
						res.add(temp.get(i));
					}
				}
				else if(temp.get(i) == temp.get(i+1)) {
					res.add(temp.get(i));
					i++;
				}
				else {
					res.add(temp.get(i));
				}
			}
		}
		else {
			for(int i=0; i<temp.size()-1; i++) {
				if(i == temp.size()-2) {
					if(temp.get(i) == temp.get(i+1)) {
						res.add(temp.get(i));
						i++;
					}
					else {
						res.add(temp.get(i));
						res.add(temp.get(i+1));
					}
				}

				if(temp.get(i) == temp.get(i+1)) {
					res.add(temp.get(i));
					i++;
				}
				else {
					res.add(temp.get(i));
				}
			}
		}
		return res;	
	}

	/**
	 * This function compute a deep copy of this graph.
	 * @return the copied graph.
	 */
	@Override
	public graph copy() {
		Graph_Algo gC= new Graph_Algo();
		this.save("temp.txt");
		gC.init("temp.txt");
		return gC.graph_A;
	}


	///////////////////////////helper functions to implement the algorithms///////////////////////


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

	// helper function for isConnected , used DFS algorithm.
	public void dfsWithoutRecursion(node_data start) {

		Stack<node_data> stack = new Stack<node_data>();
		stack.push(start);
		Collection<edge_data> eColl ;

		while(!stack.isEmpty())
		{
			node_data current = stack.pop();
			current.setTag(1);
			eColl= this.graph_A.getE(current.getKey());
			if(eColl==null)
				return;
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
					neibor.setWeight(src_node.getWeight() + e.getWeight());
					neibor.setInfo("" + src_node.getKey());
					Queue.add(neibor);
				}
			}

			Queue.poll();
			src_node = Queue.peek();
		}

		if (this.graph_A.getNode(dest.getKey()).getWeight()==Double.MAX_VALUE)
			throw new RuntimeException(" there is no path between src and dest ");
		else{return this.graph_A.getNode(dest.getKey()).getWeight();}
	}
	
	public static void main(String args[])
	{
		
	}

}