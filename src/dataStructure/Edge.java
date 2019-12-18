package dataStructure;

public class Edge implements edge_data{
	
	Node src; 
	Node dest;
	double weight;
	int tag;
	
	Edge(Node s, Node d, double w, int t)
	{
		this.src= new Node(s);
		this.dest=new Node(d);
		this.weight=w;
		this.tag=t;
		
	}
	
	Edge(Edge e)
	{
		//this.src=
	}
	

	@Override
	public int getSrc() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDest() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTag() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTag(int t) {
		// TODO Auto-generated method stub
		
	}

}
