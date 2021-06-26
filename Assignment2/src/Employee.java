public class Employee {
	private String name;
	private float HWorked;
	private float BPay;
	private float salary;

	public Employee(String name, float HWorked, float BPay) {
		this.name=name;
		this.HWorked = HWorked;
		this.BPay = BPay;
	}
	
	public void Display() {
		// verify if the payment is okay
		if(BPay< 4.0) System.out.println("Error. Base Pay for: "+name+" is to low.");
		// verify if the employee works overtime
		else if(HWorked > 60.0) System.out.println("Error. The employee: "+name+" works overtime.");
		else { // the payment and work time is optim...
			if(HWorked <= 40) salary = HWorked*BPay; // normal salary
			else salary = (float) (40 * BPay+ (HWorked-40)*BPay*1.5); // overtime salary
			// Dispaly info about Employee and salary
			System.out.println("Employee: "+name+" Hours Worked: "+HWorked+" Base Pay:"+BPay+ " Salary: "+salary);
		}
	}
}
