package users;

import java.util.Objects;
import java.util.Vector;

import other.Message;

public abstract class Employee extends User implements Comparable<Object>{
	private int salary;
	private Vector<Message> messages;
	
	{
		messages = new Vector<Message>();
	}
	
	public Employee() {
		
	}
	public Employee(String fullName, String username, String password, int salary) {
		super(fullName, username, password);
		this.salary = salary;
	}
	
	/**
	 * we can send messages 
	 */
	public void sendMessage(Employee employee, Message message) {
		employee.messages.add(message);
	}
	
	/**
	 * to get Employee messages
	 */
	public Vector<Message> getMessages() {
		return messages;
	}
	
	/**
	 * to set Employee messages
	 */
	public void setMessages(Vector<Message> messages) {
		this.messages = messages;
	}
	
	/**
	 * to get Employee salary
	 */
	public int getSalary() {
		return salary;
	}
	
	/**
	 * to set Employee salary
	 */
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	/**
	 * print Employee information and his salary
	 */
	public String toString() {
		return super.toString() + "Employee [salary=" + salary + "]";
	}
	@Override
	/**
	 * generate the hash values of objects
	 */
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(salary);
		return result;
	}
	
	/**
	 * we check inequality of Employee through salary
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return salary == other.salary;
	}
	
	/**
	 * we compare salaries of 2 objects
	 */
	public int compareTo(Object o) {
		if (super.compareTo(o) == 0) {
			Employee e = (Employee)o;
			if (getSalary() < e.getSalary()) return -1;
			else return 1;
		}
		return 0;
	}
	
}

