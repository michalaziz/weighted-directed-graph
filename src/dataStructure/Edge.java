package dataStructure;

public class Edge implements edge_data{

	Node src; 
	Node dest;
	double weight;
	int tag;

	Edge(Node s, Node d, double w, int t)
	{
		this.src= s;
		this.dest=d;
		this.weight=w;
		this.tag=t;

	}

	Edge(Edge e)
	{
		this.src=e.src;
		this.dest=e.dest;
		this.weight=e.weight;
		this.tag=e.tag;
	}


	@Override
	public int getSrc() {
		return this.src.getKey();
	}

	@Override
	public int getDest() {
		return this.dest.getKey();
	}

	@Override
	public double getWeight() {
		return this.weight;
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
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag=t;
	}

}
