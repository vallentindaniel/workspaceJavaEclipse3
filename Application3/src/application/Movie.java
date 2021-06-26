package application;

public class Movie {
	private Integer id;
	private String name;
	private String stage_director;
	private Integer year;
	public Movie(Integer id, String name, String stage_director, Integer year) {
		this.id = id;
		this.name = name;
		this.stage_director = stage_director;
		this.year = year;
	}
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getStageDirector() {
		return stage_director;
	}
	public Integer getYear() {
		return year;
	}
}
