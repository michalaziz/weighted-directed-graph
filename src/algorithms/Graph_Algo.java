package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	private graph graph_A;

	Graph_Algo()
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
		// TODO Auto-generated method stub

	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isConnected() {
		Collection<node_data> vertex = this.graph_A.getV();
		if(vertex.size()==1)
			return true;
		if(vertex.size()==0)
			throw new RuntimeException("no vertex");
		ArrayList<Integer> arrSrc=new ArrayList();
		ArrayList<Integer> arrDest=new ArrayList();
		Iterator<node_data> iterV=vertex.iterator();
		for(int i=0; i<vertex.size();i++)
		{
			node_data tempV=iterV.next();
			Collection<edge_data> edges = graph_A.getE(tempV.getKey());
			if(edges==null)
				return false;
			Iterator<edge_data> iterE=edges.iterator();
			for(int j=0; j<edges.size(); j++)
			{
				edge_data tempE= iterE.next();
				arrSrc.add(tempE.getSrc());
				arrDest.add(tempE.getDest()); 
			}
		}
		Iterator<node_data> iterV2=vertex.iterator();
		for(int i=0; i<vertex.size(); i++)
		{
			node_data tempV2=iterV2.next();
			if(!arrSrc.contains(tempV2.getKey())||!arrDest.contains(tempV2.getKey()))
				return false;
		}
		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		
		return 0;
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
		// TODO Auto-generated method stub
		return null;
	}
}
