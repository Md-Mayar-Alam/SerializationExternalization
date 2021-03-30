package com;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * In this class we will see how InvalidClassException comes.
 * If we are serializing a class at some time and not providing serialVersioUID in it and after the serialization of class
 * if we make any changes in the class whose object is serialized and try to deserialize object then we will get
 * InvalidClassException.
 * 
 * @see InvalidClassExceptionSolution
 * @author Mayar
 */
public class InvalidClassExceptionExample {

	public static void main(String[] args) {
		
		/**
		 * To see InvalidClassException initially comment readEmployee() in main() and execute.
		 * Then after serialization uncomment the empSalary field of Employee class.
		 * Now comment writeEmployee() in main() and execute.
		 * 
		 * @see InvalidClassExceptionSolution to avoid InvalidClassException
		 */
		
		/*System.out.println("Writing Employee object...");
		writeEmployee();*/
		
		System.out.println("Reading Employee object...");
		readEmployee();
	}
	
	static void readEmployee() {
		
		try(FileInputStream fis = new FileInputStream(".\\file\\serialization.ser"); ObjectInputStream oos = new ObjectInputStream(fis)){
			Employee employee = (Employee)oos.readObject();
			
			System.out.println("Employee ID= "+ employee.getEmpId());
			System.out.println("Employee Name= "+ employee.getEmpName());
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static void writeEmployee() {
		try {
			FileOutputStream fos = new FileOutputStream(".\\file\\serialization.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			Employee employee = new Employee(100, "Mohammad");
			oos.writeObject(employee);
			
			System.out.println("Employee written successfully");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	static class Employee implements Serializable{
		
		private int empId;
		private String empName;
		
		/**
		 * Uncomment this for getting InvalidClassException
		 */
		private float empSalary;
		
		public Employee(){
			System.out.println("Default Constructor");
		}
		
		public Employee(int empId, String empName) {
			this.empId = empId;
			this.empName = empName;
		}
		
		/*public Employee(int empId, String empName, float empSalary) {
			this.empId = empId;
			this.empName = empName;
			this.empSalary = empSalary;
		}*/

		public int getEmpId() {
			return empId;
		}

		public void setEmpId(int empId) {
			this.empId = empId;
		}

		public String getEmpName() {
			return empName;
		}

		public void setEmpName(String empName) {
			this.empName = empName;
		}

		/**
		 * Uncomment these setter and getter for getting InvalidClassException.
		 */
		public float getEmpSalary() {
			return empSalary;
		}

		public void setEmpSalary(float empSalary) {
			this.empSalary = empSalary;
		}
	}
}
