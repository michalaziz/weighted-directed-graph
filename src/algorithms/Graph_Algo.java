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
import java.util.List;
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
public class Graph_Algo implements graph_algorithms{
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
	public void save(String file_name) {
		graph graph= this.graph_A;
	    File f= new File (file_name); 
	    try 
	    {
			FileOutputStream fos= new FileOutputStream(f);
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(graph);
			oos.close();
			fos.close();
		} 
	    catch (FileNotFoundException e)
	    {
			System.out.println("file not saved");
		} catch (IOException e) {
		
			e.printStackTrace();
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
		
		
		
		
//		Collection<node_data> vertex = this.graph_A.getV();
//		if(vertex.size()==1)
//			return true;
//		if(vertex.size()==0)
//			throw new RuntimeException("no vertex");
//		ArrayList<Integer> arrSrc=new ArrayList();
//		ArrayList<Integer> arrDest=new ArrayList();
//		Iterator<node_data> iterV=vertex.iterator();
//		for(int i=0; i<vertex.size();i++)
//		{
//			node_data tempV=iterV.next();
//			Collection<edge_data> edges = graph_A.getE(tempV.getKey());
//			if(edges==null)
//				return false;
//			Iterator<edge_data> iterE=edges.iterator();
//			for(int j=0; j<edges.size(); j++)
//			{
//				edge_data tempE= iterE.next();
//				arrSrc.add(tempE.getSrc());
//				arrDest.add(tempE.getDest()); 
//			}
//		}
//		Iterator<node_data> iterV2=vertex.iterator();
//		for(int i=0; i<vertex.size(); i++)
//		{
//			node_data tempV2=iterV2.next();
//			if(!arrSrc.contains(tempV2.getKey())||!arrDest.contains(tempV2.getKey()))
//				return false;
//		}
//		return true;
	

	@Override
	public double shortestPathDist(int src, int dest) {
		Collection<node_data> vertex = this.graph_A.getV();
		Iterator<node_data> iterV=vertex.iterator();
	    node_data srcCurrent= this.graph_A.getNode(src);
	    srcCurrent.setTag(0);
	    node_data desT= this.graph_A.getNode(dest);
		while(iterV.hasNext())
		{
			iterV.next().setTag(0);
			iterV.next().setWeight( Double.MAX_VALUE);
		}
		Dijkstra(srcCurrent,desT);
		return this.graph_A.getNode(dest).getWeight();
	}

	public void Dijkstra(node_data src, node_data dest)
	{
	 if(src.getKey()==dest.getKey() || src.getTag()==1)
	 {
		 return;
	 }
		Collection<edge_data> srcEout = this.graph_A.getE(src.getKey());
		Iterator<edge_data> iterE=srcEout.iterator();

	 while(src.getTag()==0 && iterE!=null && iterE.hasNext())
	 {
		if(this.graph_A.getNode(iterE.next().getDest()).getWeight()>this.graph_A.getNode(iterE.next().getSrc()).getWeight() + iterE.next().getWeight());
		this.graph_A.getNode(iterE.next().getDest()).setWeight(this.graph_A.getNode(iterE.next().getSrc()).getWeight() + iterE.next().getWeight());
	 
	    src.setTag(1);
	    srcEout=this.graph_A.getE(iterE.next().getDest());
	    //צריך לעבור על רשימת הצלעות ולראות לאיזה קודקוד יש משקל הכי נמוך ואותו לשים בקולקשן הצלעות החדש
	 }
	 }
	
	
	
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		Graph_Algo gC= new Graph_Algo();
		this.save("temp.txt");
		gC.init("temp.txt");
		return gC.graph_A;
	}
	
	
	//helper functions to implement the algorithms
	
	
	//set the all vetrex's tags to 0
	private void setTag_0(graph graph) {
		Collection <node_data> v = graph.getV();
		Iterator<node_data> it=v.iterator();
		while(it.hasNext())
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
	
	
	
	
	public static void main(String args[])
	{
		Point3D x = new Point3D(1,2,3);
		Point3D y = new Point3D(-1,-2,-3);
		Point3D z = new Point3D(6,7,8);
		Point3D w = new Point3D(6,7,8);
		Point3D s = new Point3D(6,7,8);

		node_data a = new Node(1,3,2,x,"michal");
		node_data b = new Node(2,4,3,y,"yarden");
		node_data c = new Node(3,5,4,z,"sf");
		node_data d = new Node(4,5,6,s,"s");
		node_data e = new Node(5,9,6,w,"tt");

		
		
		DGraph d1 = new DGraph();
		d1.addNode(a);
		d1.addNode(b);
		d1.addNode(c);
		d1.addNode(d);
		d1.addNode(e);
		

		d1.connect(a.getKey(),c.getKey(),1);
		d1.connect(c.getKey(),b.getKey(),10);
		d1.connect(b.getKey(),a.getKey(),3);
		d1.connect(c.getKey(),b.getKey(),10);
		d1.connect(c.getKey(),d.getKey(),5);
		d1.connect(d.getKey(),a.getKey(),12);
		d1.connect(e.getKey(),c.getKey(),6);

	
		Graph_Algo check = new Graph_Algo(d1);
		System.out.println(check.isConnected());
	
	}
}