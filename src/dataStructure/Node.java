package dataStructure;

import utils.Point3D;

public class Node implements node_data {

	int key;
	Point3D location;
	double weight;

	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public Point3D getLocation() {
		if(location!=null)////////not sure/////////
			return this.location;
		return null;
	}

	@Override
	public void setLocation(Point3D p) {
		this.location=new Point3D(p);
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTag(int t) {
		// TODO Auto-generated method stub

	}

}
