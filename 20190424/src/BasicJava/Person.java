package BasicJava;

public class Person {
	String name;
	int age;
	int sex;
	
	public void study() {
		System.out.println("Study!");
	}
	public void showAge() {
		System.out.println("Age: " + age);
	}
	public void addAge(int i) {
		age = age + i;
	}
	
}
