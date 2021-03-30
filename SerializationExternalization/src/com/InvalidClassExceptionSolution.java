package com;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Solution for InvalidClassException
 * 
 * @see InvalidClassExceptionExample
 * @author Mayar
 */
public class InvalidClassExceptionSolution {
	public static void main(String[] args) {
		
		/*System.out.println("Writing Employee object...");
		writeEmployee();*/

		System.out.println("Reading Employee object...");
		readEmployee();
	}

	static void readEmployee() {

		try (FileInputStream fis = new FileInputStream(".\\file\\serialization.ser");
				ObjectInputStream oos = new ObjectInputStream(fis)) {
			Employee employee = (Employee) oos.readObject();

			System.out.println("Employee ID= " + employee.getEmpId());
			System.out.println("Employee Name= " + employee.getEmpName());
		} catch (IOException | ClassNotFoundException e) {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static class Employee implements Serializable {

		/**
		 * The solution of prevent InvalidClassException is to provide serialVersionUID
		 */
		private static final long serialVersionUID = 100L;
		
		private int empId;
		private String empName;

		/**
		 * Uncomment this for getting InvalidClassException
		 */
		private float empSalary;

		public Employee() {
			System.out.println("Default Constructor");
		}

		public Employee(int empId, String empName) {
			this.empId = empId;
			this.empName = empName;
		}

		/*
		 * public Employee(int empId, String empName, float empSalary) { this.empId =
		 * empId; this.empName = empName; this.empSalary = empSalary; }
		 */

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
		/*public float getEmpSalary() {
			return empSalary;
		}

		public void setEmpSalary(float empSalary) {
			this.empSalary = empSalary;
		}*/
	}
}
