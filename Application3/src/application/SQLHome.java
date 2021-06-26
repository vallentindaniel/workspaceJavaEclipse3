package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SQLHome {
	
	
	private  String url = "jdbc:mysql://hosting2035506.online.pro:3306/00307732_olaru_valentin";
    private  String user = "00307732_olaru_valentin";
    private  String password = "olaru_valentinolaru_valentin";
    
    private Integer count;
    
    
    public void insert(Movie m) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement st = connection.createStatement();
            String query = "INSERT INTO movie ( name, stage_director, year) VALUES ('"
                    + m.getName() + "', '" + m.getStageDirector()
                    +  "', '"+ m.getYear() +"')";
            System.out.println(query);
            st.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        
    }
    
    public ObservableList<Movie> getMovieList(String query) {
        ObservableList<Movie> movieList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            Movie movie;
            count = 0;
            while(resultSet.next()) {
                movie = new Movie(resultSet.getInt("id"), resultSet.getString("name"),
                resultSet.getString("stage_director"), resultSet.getInt("year"));
                movieList.add(movie);     
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return movieList;
    }
    
    public ObservableList<Movie> getMovieList() {
    	String query = "select * from movie";
    	return this.getMovieList(query);
    }
    
    public void update(String query) {
	    try (Connection connection = DriverManager.getConnection(url, user, password)) {
	        Statement statement = connection.createStatement();
	        statement.executeUpdate(query);
	   } catch (SQLException throwables) {
	        throwables.printStackTrace();
	   }   
    }
    public void delete(String query) {
    	try (Connection connection = DriverManager.getConnection(url, user, password)) {
             Statement statement = connection.createStatement();
             statement.executeUpdate(query);    
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         }
    }
    
    public Integer getCount() {
    	return count;
    }

}
