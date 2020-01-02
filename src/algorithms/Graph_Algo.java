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
import dataStructure.*;
import utils.Point3D;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author Cohen yarden && Aziz michal
 *
 */

public class Graph_Algo implements graph_algorithms{
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
		System.out.println("got in init");
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


		Iterator<Integer> iter= targets.iterator();
		
		List<node_data> path = new ArrayList<node_data>();

		while(iter.hasNext()&&path.isEmpty())
		{
			path=isVisit(iter.next(),targets);
		}

		return path;
	}
	/*temp*/
	private List<node_data> isVisit (Integer current , List<Integer>tNodes)
	{
		List<node_data> path =new ArrayList<node_data>();
		Iterator<edge_data> iter=graph_A.getE(graph_A.getNode(current).getKey()).iterator();
		Iterator<Integer> iter2= tNodes.iterator();
		while(iter.hasNext())
		{
			edge_data tempEdge =iter.next();
			path.add(graph_A.getNode(current));
			path.add(graph_A.getNode(tempEdge.getDest()));
		}
		for(int i=0; i<tNodes.size(); i++)
			if(!path.contains(graph_A.getNode(iter2.next())))
			{
				path.clear();
				return path;
			}
		return path;
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
		DGraph d_g = new DGraph();
		Graph_Algo g_a = new Graph_Algo();
		Point3D x = new Point3D(10, 20, 3);
		Point3D y = new Point3D(-10, -20, -3);
		Point3D z = new Point3D(20, 40, 8);
		Point3D w = new Point3D(-32, 30, 9);
		Point3D s = new Point3D(40, -20, 0);
		Point3D j = new Point3D(90, 10, -8);


//		node_data a_0 = new Node(0, 3, 2, x, "michal");
//		node_data b_1 = new Node(1, 4, 3, y, "yarden");
//		node_data c_2 = new Node(2, 5, 4, z, "sf");
//		node_data d_3 = new Node(3, 5, 6, s, "s");
//		node_data e_4 = new Node(4, 9, 6, w, "tt");
//		node_data f_5 = new Node(5, 9, 6, w, "tt");

		node_data a_0 = new Node(1, 3, 2, x, "michal");
		node_data a_1 = new Node(2, 3, 2, y, "michal");
		node_data a_2 = new Node(3, 3, 2, z, "michal");
		node_data a_3 = new Node(7, 3, 2, w, "michal");
		d_g.addNode(a_0);
		d_g.addNode(a_1);
		d_g.addNode(a_2);
		d_g.addNode(a_3);
		d_g.connect(a_0.getKey(), a_1.getKey(), 10);
		d_g.connect(a_1.getKey(), a_0.getKey(), 10);
		d_g.connect(a_0.getKey(), a_2.getKey(), 10);
		d_g.connect(a_2.getKey(), a_3.getKey(), 10);
//		d_g.addNode(b_1);
//		d_g.addNode(c_2);
//		d_g.addNode(d_3);
//		d_g.addNode(e_4);
//		d_g.addNode(f_5);

//		d_g.connect(a_0.getKey(), b_1.getKey(), 10);
//		d_g.connect(a_0.getKey(), e_4.getKey(), 5);
//		d_g.connect(b_1.getKey(), e_4.getKey(), 2);
//		d_g.connect(b_1.getKey(), c_2.getKey(), 1);
//		d_g.connect(c_2.getKey(), d_3.getKey(), 4);
//		d_g.connect(d_3.getKey(), a_0.getKey(), 7);
//		d_g.connect(d_3.getKey(), c_2.getKey(), 6);
//		d_g.connect(e_4.getKey(), d_3.getKey(), 2);
//		d_g.connect(e_4.getKey(), c_2.getKey(), 9);
//		d_g.connect(f_5.getKey(), b_1.getKey(), 3);
//		d_g.connect(f_5.getKey(), c_2.getKey(), 14);
//		d_g.connect(c_2.getKey(), f_5.getKey(), 20);

		List<Integer> targets=new ArrayList<>();
		targets.add(2);
		targets.add(7);
		g_a.init(d_g); // this graph is connected
		g_a.TSP(targets);
		
//		g_a.save("pleaseee.txt");

		//	System.out.println(g_a.isConnected());
		//System.out.println(g_a.shortestPath(3,1));
		
//		Graph_Algo ga = new Graph_Algo();
//		ga.graph_A.addNode(new Node(0,0,0,new Point3D(0, 0),"ra"));
//		ga.graph_A.addNode(new Node(1,0,0,new Point3D(-10,-10), "li"));
//		ga.graph_A.addNode(new Node(2,0,0,new Point3D(10,10), "ra"));
//		ga.graph_A.addNode(new Node(3,0,0,new Point3D(-10,10), "li"));
//		ga.graph_A.addNode(new Node(4,0,0,new Point3D(0,-20), "ra"));
//		ga.graph_A.addNode(new Node(5,0,0,new Point3D(10, -10),"li"));
//
//		ga.graph_A.connect(0,1, 1);
//		ga.graph_A.connect(1,0, 1.5);
//		ga.graph_A.connect(0,2, 0);
//		ga.graph_A.connect(2,0, 2);
//		ga.graph_A.connect(0,3, 5);
//		ga.graph_A.connect(3,0, 4);
//		ga.graph_A.connect(0,4, 2);
//		ga.graph_A.connect(4,0, 3);
//		ga.graph_A.connect(0,5, 1.2);
//		ga.graph_A.connect(5,0, 2.5);
//
//		List<Integer> targets=new ArrayList<>();
//		targets.add(1);
//		targets.add(0);
//		targets.add(2);
//		targets.add(3);
//		targets.add(4);
//		targets.add(5);
//
//		List<node_data> ans=ga.TSP(targets);
//		List<node_data> myAns=new ArrayList<>();
//		myAns.add(ga.graph_A.getNode(1));
//		myAns.add(ga.graph_A.getNode(0));
//		myAns.add(ga.graph_A.getNode(2));
//		myAns.add(ga.graph_A.getNode(0));
//		myAns.add(ga.graph_A.getNode(3));
//		myAns.add(ga.graph_A.getNode(0));
//		myAns.add(ga.graph_A.getNode(4));
//		myAns.add(ga.graph_A.getNode(0));
//		myAns.add(ga.graph_A.getNode(5));
//		
//		ans=ga.TSP(targets);
			
	}
}