package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.sql.*;

public class Controller {

    @FXML
    public TextField idText;
    @FXML
    public TextField brandText;
    @FXML
    public TextField nameText;
    @FXML
    public TextField engineText;
    @FXML
    public TextField yearText;
    @FXML
    public Button insertButton;
    @FXML
    public TableView<Cars> tableViewCars;
    @FXML
    public TableColumn<Cars, Integer> tableColumnId;
    @FXML
    public TableColumn<Cars, String> tableColumnBrand;
    @FXML
    public TableColumn<Cars, String> tableColumnName;
    @FXML
    public TableColumn<Cars, String> tableColumnEngine;
    @FXML
    public TableColumn<Cars, Integer> tableColumnYear;
    @FXML
    public Button showButton;

    private final String url = "jdbc:mysql://hosting2035506.online.pro:3306/00307732_olaru_valentin";
    private final String user = "00307732_olaru_valentin";
    private final String password = "olaru_valentin";
    
    
    public void initialize() {
    	
    	tableViewCars.setOnMouseClicked(e ->{
    		
    	      
    	   System.out.println("line " + (tableViewCars.getSelectionModel().getSelectedCells().get(0)).getRow());  
    	   
    	   System.out.println("column " + (tableViewCars.getSelectionModel().getSelectedCells().get(0)).getColumn()); 
    	   
    	   System.out.println("column " + (tableViewCars.getSelectionModel().getSelectedCells().get(0))); 
    	   
    	   
    	   
    	        
    	   
    	});
    	
    	
    	

    }
    
    
    

    public void insertHandle(ActionEvent actionEvent) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement st = connection.createStatement();
            String query = "INSERT INTO cars ( brand, name, engine, year) VALUES ('"
                    + brandText.getText() + "', '" + nameText.getText() + "', '"
                    + engineText.getText() + "', '"+ Integer.parseInt(yearText.getText()) +"')";
            System.out.println(query);
            st.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ObservableList<Cars> getCarsList() {
        ObservableList<Cars> userList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM cars";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            Cars cars;
            while(resultSet.next()) {
            	
                cars = new Cars(resultSet.getInt("id"), resultSet.getString("brand"),
                        resultSet.getString("name"), resultSet.getString("engine"),resultSet.getInt("year"));
                
                userList.add(cars);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public void showHandle(ActionEvent actionEvent) {
        ObservableList<Cars> carsList = this.getCarsList();

        tableColumnId.setCellValueFactory(new PropertyValueFactory<Cars,Integer>("id"));
        tableColumnBrand.setCellValueFactory(new PropertyValueFactory<Cars,String>("brand"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<Cars,String>("name"));
        tableColumnEngine.setCellValueFactory(new PropertyValueFactory<Cars,String>("engine"));
        tableColumnYear.setCellValueFactory(new PropertyValueFactory<Cars,Integer>("year"));

        tableViewCars.setItems(carsList);
    }
    
    
}
