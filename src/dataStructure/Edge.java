package dataStructure;

<<<<<<< HEAD
import java.io.Serializable;

public class Edge implements edge_data,Serializable {
=======
public class Edge implements edge_data{
>>>>>>> branch 'master' of https://github.com/michalaziz/oop-Ex2.git

	private int src; 
	private int dest;
	private double weight;
	private int tag;
	private String info;
    
	public Edge(int src, int dest, double weigth, int tag, String info)
	{
		this.src= src;
		this.dest=dest;
		this.weight=weigth;
		this.tag=tag;
        this.info=info;
	}

	public Edge(Edge e)
	{
		this.src=e.src;
		this.dest=e.dest;
		this.weight=e.weight;
		this.tag=e.tag;
		this.info=e.info;
	}

    public Edge(int src, int dest, double weigth)
    {
    	this.src=src;
    	this.dest=dest;
    	this.weight=weigth;
    	this.info="";
    	this.tag=0;
    }
	@Override
	public int getSrc() {
		return this.src;
	}

	@Override
	public int getDest() {
		return this.dest;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public String getInfo() {
		return this.info;
	}

	@Override
	public void setInfo(String s) {
	this.info=s;
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
