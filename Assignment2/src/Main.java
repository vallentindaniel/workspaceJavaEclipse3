public class Main {
	public static void main(String[] args) {
		// create array with  Employee
		Employee[] emp = new Employee[3];
		emp[0] = new Employee("Alin",40, 3);
		emp[1] = new Employee("Diana",60, 4);
		emp[2] = new Employee("Maria",66, 4);
		// display all Employee info or error;
		for(int i=0;i< emp.length;i++)
		{
			emp[i].Display();
		}	
	}
}
