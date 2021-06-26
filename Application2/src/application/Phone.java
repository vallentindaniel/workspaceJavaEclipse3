package application;

public class Phone {
	
	private Integer id;
	private String name;
	private String mark;
	private float price;
	
	
	public Phone(Integer id, String name, String mark, float price) {
		this.id = id;
		this.name = name;
		this.mark = mark;
		this.price = price;
	}
	
	public Integer getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getMark() {
		return this.mark;
	}
	public float getPrice() {
		return this.price;
	}
	

}
