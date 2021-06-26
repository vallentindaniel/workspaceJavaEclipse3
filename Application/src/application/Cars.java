package application;

public class Cars {
	private Integer id;
    private String  brand;
    private String  name;
    private String  engine;
    private Integer year;
    
    public Cars(Integer id, String brand, String name, String engine, Integer year) {
    	this.id  = id;
    	this.brand = brand;
    	this.name = name;
    	this.engine = engine;
    	this.year = year;
    }
    
    public Integer getId() {
        return this.id;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getName() {
        return this.name;
    }

    public String getEngine() {
        return this.engine;
    }
    
    public Integer getYear() {
        return this.year;
    }
    
}
