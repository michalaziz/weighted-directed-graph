package dataStructure;

//import java.util.HashMap;
import utils.Point3D;


public class Node implements node_data {

	 private int key,tag;
	 private Point3D location;
	 private double weight= Double.MAX_VALUE;
	 private String info;
	//HashMap<Integer,Edge>fromNode= new HashMap<Integer,Edge>();

	
	public Node( int k,int tag, Point3D p,double weight, String info )
	{
		this.key=k;
		this.location=p;
		this.weight=weight;
		this.tag=tag;
		this.info=info;
	}
	public Node()
	{
		this.key=0;//maybe we will get node with key 0 so 0 is not the default
		this.tag=0;
		this.location=null;
		this.weight=0;
		this.info=null;
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
