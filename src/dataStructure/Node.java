package dataStructure;

import java.io.Serializable;

//import java.util.HashMap;
import utils.Point3D;


public class Node implements node_data,Serializable {

	 private int key;
	 private double weight= Double.MAX_VALUE;
	 private int tag;
	 private Point3D location;
	 private String info;
	//HashMap<Integer,Edge>fromNode= new HashMap<Integer,Edge>();

	
	public Node( int k,double weight,int tag, Point3D p, String info )
	{
		this.key=k;
		this.location=p;
		this.weight=weight;
		this.tag=tag;
		this.info=String.valueOf(key);
		
	}
	public Node()
	{
		this.key=0;//maybe we will get node with key 0 so 0 is not the default
		this.tag=0;
		this.location=null;
		this.weight=0;
		this.info=null;
	}
    public Node(Node n){
        this.key=n.key;
        this.tag= n.tag;
        this.weight=n.weight;
        this.location= new Point3D(n.location);
        this.info=n.info;
    }
	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public Point3D getLocation() {
		return this.location;
	}

	@Override
	public void setLocation(Point3D p) {
		Point3D newP= new Point3D (p);
		this.location=newP;
		
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight=w;
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
