package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
	private graph graph;

	@Override
	public void init(graph g) {
		this.graph=g;	
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
		Collection<node_data> vertex = this.graph.getV();
		ArrayList<Integer> arrSrc=new ArrayList();
		ArrayList<Integer> arrDest=new ArrayList();
		Iterator<node_data> iterV=vertex.iterator();
		for(int i=0; i<vertex.size();i++)
		{
			Collection<edge_data> edges = graph.getE(iterV.next().getKey());
			Iterator<edge_data> iterE=edges.iterator();
			for(int j=0; j<edges.size(); j++)
			{
				arrSrc.add(iterE.next().getSrc());
				arrDest.add(iterE.next().getDest());
			}
		}
		Iterator<node_data> iterV2=vertex.iterator();
		for(int i=0; i<vertex.size(); i++)
		{
			if(!arrSrc.contains(iterV2.next().getKey())||!arrDest.contains(iterV2.next().getKey()))
				return false;
		}
		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
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
