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
    @FXML
    public TableView<Cars> selectTable;
    
    @FXML
    public TableView<Cars> selectTableU;
    @FXML
    public TableColumn<Cars, Integer> tableColumnIdU;
    @FXML
    public TableColumn<Cars, String> tableColumnBrandU;
    @FXML
    public TableColumn<Cars, String> tableColumnNameU;
    @FXML
    public TableColumn<Cars, String> tableColumnEngineU;
    @FXML
    public TableColumn<Cars, Integer> tableColumnYearU;
    
    
    @FXML
    public TextField idTextF;
    @FXML
    public TextField brandTextF;
    @FXML
    public TextField nameTextF;
    @FXML
    public TextField engineTextF;
    @FXML
    public TextField yearTextF;
    
    
   
    @FXML
    public TextField brandTextU;
    @FXML
    public TextField nameTextU;
    @FXML
    public TextField engineTextU;
    @FXML
    public TextField yearTextU;
    
    private Integer id = 0;
    private Integer idU = 0;
    private boolean sem = false;
    private String strFind;

    private final String url = "jdbc:mysql://hosting2035506.online.pro:3306/00307732_olaru_valentin";
    private final String user = "00307732_olaru_valentin";
    private final String password = "olaru_valentin";
    
    
    public void initialize() {
    	   select();
    	   selectU();
    	
    	   selectTable.setOnMouseClicked(e ->{
    		
  	      
     	   System.out.println("line " + (selectTable.getSelectionModel().getSelectedCells().get(0)).getRow());  
     	   
     	   System.out.println("column " + (selectTable.getSelectionModel().getSelectedCells().get(0)).getColumn()); 
     	   
     	   System.out.println("column " + (selectTable.getSelectionModel().getSelectedCells().get(0))); 
     	   int x = (selectTable.getSelectionModel().getSelectedCells().get(0)).getRow();
     	   this.id = tableColumnId.getCellData(x);
     	  System.out.println("value "+ tableColumnId.getCellData(x));      
     	   
     	});
    	   
    	   
    	   selectTableU.setOnMouseClicked(e ->{
       		
    	  	      sem = true;
         	   System.out.println("line " + (selectTableU.getSelectionModel().getSelectedCells().get(0)).getRow());  
         	   
         	   System.out.println("column " + (selectTableU.getSelectionModel().getSelectedCells().get(0)).getColumn()); 
         	   
         	   System.out.println("column " + (selectTableU.getSelectionModel().getSelectedCells().get(0))); 
         	   int x = (selectTableU.getSelectionModel().getSelectedCells().get(0)).getRow();
         	   this.idU = tableColumnIdU.getCellData(x);
         	  System.out.println("value update "+ tableColumnIdU.getCellData(x));      
         	   
         	});
    	

    }
    
    
    
    
    public void select() {
    	ObservableList<Cars> carsList = this.getCarsList();

    	 tableColumnIdU.setCellValueFactory(new PropertyValueFactory<Cars,Integer>("id"));
         tableColumnBrandU.setCellValueFactory(new PropertyValueFactory<Cars,String>("brand"));
         tableColumnNameU.setCellValueFactory(new PropertyValueFactory<Cars,String>("name"));
         tableColumnEngineU.setCellValueFactory(new PropertyValueFactory<Cars,String>("engine"));
         tableColumnYearU.setCellValueFactory(new PropertyValueFactory<Cars,Integer>("year"));

        selectTable.setItems(carsList);
    }
    
    public void selectU() {
    	ObservableList<Cars> carsList = this.getCarsList();

    	 tableColumnId.setCellValueFactory(new PropertyValueFactory<Cars,Integer>("id"));
         tableColumnBrand.setCellValueFactory(new PropertyValueFactory<Cars,String>("brand"));
         tableColumnName.setCellValueFactory(new PropertyValueFactory<Cars,String>("name"));
         tableColumnEngine.setCellValueFactory(new PropertyValueFactory<Cars,String>("engine"));
         tableColumnYear.setCellValueFactory(new PropertyValueFactory<Cars,Integer>("year"));

        selectTableU.setItems(carsList);
    }
    
    
    

    public void insertHandle(ActionEvent actionEvent) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement st = connection.createStatement();
            String query = "INSERT INTO cars ( brand, name, engine, year) VALUES ('"
                    + brandText.getText() + "', '" + nameText.getText() + "', '"
                    + engineText.getText() + "', '"+ Integer.parseInt(yearText.getText()) +"')";
            System.out.println(query);
            st.executeUpdate(query);
            select();
            selectU();
            reset();
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void reset() {
    	brandText.setText("");
    	nameText.setText("");
    	engineText.setText("");
    	yearText.setText("");
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
    
    public ObservableList<Cars> getCarsList(String query) {
        ObservableList<Cars> userList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
           // String query = "SELECT * FROM cars";
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
    
    
    public void deleteHandle(ActionEvent actionEvent) {
    	String query = "DELETE FROM `cars` WHERE id ="+ this.id;
    	try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // String query = "SELECT * FROM cars";
             Statement statement = connection.createStatement();
             statement.executeUpdate(query);
             
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         }
    	select();
    	selectU();
    	
    }
    
    public void findHandle(ActionEvent actionEvent) {
    	 sem = true; // for update
    	 String query = "SELECT *  FROM `cars` WHERE ";
    	  strFind = "";
    	 if(!brandTextF.getText().isEmpty()) {
    		 strFind += " `brand` LIKE '"+brandTextF.getText()+"' AND ";
    	 }
    	 if(!nameTextF.getText().isEmpty()) {
    		 strFind += " `name` LIKE '"+nameTextF.getText()+"' AND ";
    	 }
    	 if(!engineTextF.getText().isEmpty()) {
    		 strFind += " `engine` LIKE '"+engineTextF.getText()+"' AND ";
    	 }
    	 if(!yearTextF.getText().isEmpty()) {
    		 strFind += " `year` LIKE '"+yearTextF.getText()+"' AND ";
    	 }
    	 strFind = strFind.substring(0,strFind.length()-4);
    	 query += strFind;
    	 
    	 
    	 System.out.println(query);
    	 
    	 brandTextF.setText("");
     	 nameTextF.setText("");
     	 engineTextF.setText("");
     	 yearTextF.setText("");
    	 
    	 ObservableList<Cars> carsList = this.getCarsList(query);

    	 tableColumnIdU.setCellValueFactory(new PropertyValueFactory<Cars,Integer>("id"));
         tableColumnBrandU.setCellValueFactory(new PropertyValueFactory<Cars,String>("brand"));
         tableColumnNameU.setCellValueFactory(new PropertyValueFactory<Cars,String>("name"));
         tableColumnEngineU.setCellValueFactory(new PropertyValueFactory<Cars,String>("engine"));
         tableColumnYearU.setCellValueFactory(new PropertyValueFactory<Cars,Integer>("year"));

        selectTableU.setItems(carsList);
        
        
    	
    }
    public void updatetHandle(ActionEvent actionEvent) {
    	String query = "UPDATE `cars` SET ";
	   // System.out.println("update: " + strFind);
	    
	    String strUpdate = "";
	    
	    
	    if(!brandTextU.getText().isEmpty())
	    {
	    	strUpdate += " `brand` = '"+ brandTextU.getText() + "',";
	    }
	    if(!nameTextU.getText().isEmpty())
	    {
	    	strUpdate += " `name` = '" + nameTextU.getText() + "',";
	    }
	    if(!engineTextU.getText().isEmpty())
	    {
	    	strUpdate += " `engine` = '" + engineTextU.getText() + "',";
	    }
	    if(!yearTextU.getText().isEmpty())
	    {
	    	strUpdate += " `year`= '" + yearTextU.getText() + "',";
	    }
	    strUpdate = strUpdate.substring(0, strUpdate.length()-1);
	    if(sem)
	    if(this.idU != 0) strFind = " `id` = " + idU + " ";
	    
	    System.out.println("update: "+ query + strUpdate + " WHERE " + strFind);
	    
	    String queryU = query + strUpdate + " WHERE " + strFind;
	    
	    try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // String query = "SELECT * FROM cars";
             Statement statement = connection.createStatement();
             statement.executeUpdate(queryU);
             
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         }
    	select();
    	selectU();
    	
	    idU=0;
	    
	     brandTextU.setText("");
    	 nameTextU.setText("");
    	 engineTextU.setText("");
    	 yearTextU.setText("");
    	 
    	 sem = false;
	    
	    
    }

    
    
}
