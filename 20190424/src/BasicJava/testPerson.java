package BasicJava;

public class testPerson {

	public static void main(String[] args) {
		
		Person person = new Person();
		
		person.name = "Tom";
		person.age = 20;
		person.sex = 1;
		
		person.study();
		person.showAge();
		person.addAge(2);
		person.showAge();
	}

}
