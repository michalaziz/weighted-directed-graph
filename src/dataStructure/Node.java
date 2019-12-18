package dataStructure;

import java.awt.List;
import java.util.ArrayList;

//import com.sun.tools.classfile.Opcode.Set;

import utils.Point3D;

public class Node implements node_data {

	int key;
	Point3D location;
	double weight= Double.MAX_VALUE;
	int tag;
	ArrayList<Node> neib =new ArrayList<Node>();
	
	Node(int k, Point3D p, double w,int t, ArrayList l)
	{
		this.key=k;
		this.location=p;
		this.weight=w;
		this.tag=t;
		this.neib=l;
	}
	Node(Node node)
	{
		this.key=node.key;
		this.location=node.location;
		this.weight=node.weight;
		this.tag=node.tag;
		this.neib=node.neib;
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
		this.location=p;
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

	public static void main(String [] args)
	{
		Point3D p =new Point3D(1,2,3);
		ArrayList<Integer> neighbors =new ArrayList<Integer>();
		neighbors.add(2);
		Node n= new Node(4,p,1,0,neighbors);
		Node n2= n;
		System.out.println(n2);
	}
}
