package com;

import java.io.BufferedReader;
import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class ExternalizationExample {

	public static void main(String[] args) {
		try {
			/*BufferedReader reader = new BufferedReader(new FileReader(".\\file\\externalization.txt"));
			
			String abc = reader.readLine();
			
			System.out.println("Hii");*/
			
			FileOutputStream fos = new FileOutputStream(".\\file\\externalization.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			Car car = new Car("Audi", 2021, 4, 1000.0f, true);
			
			oos.writeObject(car);
			
			System.out.println("Object Written Successfully");
			
			FileInputStream fis = new FileInputStream(".\\file\\externalization.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			Car newCar = (Car) ois.readObject();
			
			System.out.println("Object read successfully");
			System.out.println("Name= "+ newCar.getName());
			System.out.println("Launch Year= "+ newCar.getLaunchYear());
			System.out.println("No of Tires= "+ newCar.getNoOfTires());
			System.out.println("Price= "+ newCar.getPrice());
			System.out.println("Is Luxury= "+ newCar.isLuxury());
			
			/*
			 * In this example we can see that the data we are writing by writeExternal() method
			 * and the data we are reading from readExternal() method must be in the same sequence
			 * otherwise the data may corrupt or we may get EOFException.
			 * So, please be very careful while writing and reading data.
			 */
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

class Car implements Externalizable{

	private String name;
	private int launchYear;
	private int noOfTires;
	private float price;
	private boolean isLuxury;
	
	//For Externalization default constructor is mandatory
	public Car() {
		System.out.println("Default constructor called");
	}
	
	public Car(String name, int launchYear, int noOfTires, float price, boolean isLuxury) {
		this.name = name;
		this.launchYear = launchYear;
		this.noOfTires = noOfTires;
		this.price = price;
		this.isLuxury = isLuxury;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLaunchYear() {
		return launchYear;
	}

	public void setLaunchYear(int launchYear) {
		this.launchYear = launchYear;
	}

	public int getNoOfTires() {
		return noOfTires;
	}

	public void setNoOfTires(int noOfTires) {
		this.noOfTires = noOfTires;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isLuxury() {
		return isLuxury;
	}

	public void setLuxury(boolean isLuxury) {
		this.isLuxury = isLuxury;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("Inside readExternal reading object");
		
		//normal case
		
		/*this.name = (String)in.readObject();
		this.launchYear = in.readInt();
		this.noOfTires = in.readInt();
		this.price = in.readFloat();
		this.isLuxury = in.readBoolean();*/
		
		// java.io.EOFException case example
		
		/*this.name = (String)in.readObject();
		this.launchYear = in.readInt();
		this.noOfTires = in.readInt();
		this.price = in.readFloat();
		this.isLuxury = in.readBoolean();*/
		
		// corrupted data case here in readExternal() method
		// we will be trying to readFloat value first where as
		// in writeExternal() method we have written int value 
		// first. And this case will give us conclusion that the
		// data we are writing in writeExternal() and the data
		// we are reading in the readExternal() must be in the
		// same sequence.
		
		this.name = (String)in.readObject();
		this.price = in.readFloat();
		this.launchYear = in.readInt();
		this.noOfTires = in.readInt();
		this.isLuxury = in.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Inside writeExternal writing object");
		
		//normal case
		
		/*out.writeObject(name);
		out.writeInt(launchYear);
		out.writeInt(noOfTires);
		out.writeFloat(price);
		out.writeBoolean(isLuxury);*/
		
		// java.io.EOFException example here in this case we will be
		// not writing value for launchYear and will be trying to read it
		// in the readExternal method
		
		/*out.writeObject(name);
		//out.writeInt(launchYear);
		out.writeInt(noOfTires);
		out.writeFloat(price);
		out.writeBoolean(isLuxury);*/
		
		// corrupted data case example here in writeExternal()
		// we are writing int first where as we will try
		// to read float value first in readExternal()
		// and the data will be corrupted.
		// So, this gives us conclusion that the sequence of data
		// in readExternal() and writeExternal() must be same.
		
		out.writeObject(name);
		out.writeInt(launchYear);
		out.writeInt(noOfTires);
		out.writeFloat(price);
		out.writeBoolean(isLuxury);
		
		out.flush();
	}
}
