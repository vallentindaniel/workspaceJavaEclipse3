package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
	
	@FXML
    public TableView<Phone> selectTable;
	@FXML
	public TableColumn<Phone, Integer> tableColumnId;
	@FXML
	public TableColumn<Phone, String> tableColumnName;
	@FXML
	public TableColumn<Phone, String> tableColumnMark;
	@FXML
	public TableColumn<Phone, Integer> tableColumnPrice;
	
	@FXML
	public TextField nameText;
	@FXML
	public TextField markText;
	@FXML
	public TextField priceText;
	
	@FXML
	public TextField nameTextF;
	@FXML
	public TextField markTextF;
	@FXML
	public TextField priceTextF;
	
	@FXML
	public TextField nameTextU;
	@FXML
	public TextField markTextU;
	@FXML
	public TextField priceTextU;
	
	private final String url = "jdbc:mysql://hosting2035506.online.pro:3306/00307732_bogdan";
    private final String user = "00307732_bogdan";
    private final String password = "bogdanbogdan";
    
    private Integer id=0;
    private String strFind;
    
    public void initialize() {
	 	   refresh();
	 	   selectTable.setOnMouseClicked(e ->{   
	  	   int x = (selectTable.getSelectionModel().getSelectedCells().get(0)).getRow();
	  	   this.id = tableColumnId.getCellData(x);
	  	});
    }
    
    public void refresh() {
    	 ObservableList<Phone> carsList = this.getPhoneList();
    	 tableColumnId.setCellValueFactory(new PropertyValueFactory<Phone,Integer>("id"));
         tableColumnName.setCellValueFactory(new PropertyValueFactory<Phone,String>("name"));
         tableColumnMark.setCellValueFactory(new PropertyValueFactory<Phone,String>("mark"));
         tableColumnPrice.setCellValueFactory(new PropertyValueFactory<Phone,Integer>("price"));
         selectTable.setItems(carsList);
    }
    public void reset() {
    	nameText.setText("");
    	markText.setText("");
    	priceText.setText("");
    }
    
    public void resetF() {
    	nameTextF.setText("");
    	markTextF.setText("");
    	priceTextF.setText("");
    }
    public void resetU() {
    	nameTextU.setText("");
    	markTextU.setText("");
    	priceTextU.setText("");
    }  
    
    public void insert(ActionEvent actionEvent) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement st = connection.createStatement();
            String query = "INSERT INTO phone ( name, mark, price) VALUES ('"
                    + nameText.getText() + "', '" + markText.getText()
                    +  "', '"+ Integer.parseInt(priceText.getText()) +"')";
            System.out.println(query);
            st.executeUpdate(query);
            refresh();
            reset();  
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public ObservableList<Phone> getPhoneList() {
        ObservableList<Phone> phoneList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM phone";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            Phone phone;
            while(resultSet.next()) {	
                phone = new Phone(resultSet.getInt("id"), resultSet.getString("name"),
                resultSet.getString("mark"), resultSet.getInt("price"));  
                phoneList.add(phone); 
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return phoneList;
    }
    
    public ObservableList<Phone> getPhoneList(String query) {
        ObservableList<Phone> phoneList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            Phone phone;
            while(resultSet.next()) {
                phone = new Phone(resultSet.getInt("id"), resultSet.getString("name"),
                resultSet.getString("mark"), resultSet.getInt("price"));
                phoneList.add(phone);     
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return phoneList;
    }
    
    public void delete(ActionEvent actionEvent) {
    	if(this.id != 0) strFind = " `id` = " + id + " ";
    	String query = "DELETE FROM `phone` WHERE "+ strFind;
    	try (Connection connection = DriverManager.getConnection(url, user, password)) {
             Statement statement = connection.createStatement();
             statement.executeUpdate(query);    
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         }
    	refresh();
    	id = 0;
    }
    
    public void find(ActionEvent actionEvent) {
   	     String query = "SELECT *  FROM `phone` WHERE ";
   	 
   	     strFind = "";
	   	 if(!nameTextF.getText().isEmpty()) {
	   		 strFind += " `name` LIKE '"+nameTextF.getText()+"' AND ";
	   	 }
	   	 if(!markTextF.getText().isEmpty()) {
	   		 strFind += " `mark` LIKE '"+markTextF.getText()+"' AND ";
	   	 }
	   	 if(!priceTextF.getText().isEmpty()) {
	   		 strFind += " `price` LIKE '"+priceTextF.getText()+"' AND ";
	   	 }
	   	 strFind = strFind.substring(0,strFind.length()-4);
	   	 query += strFind;
	   	 resetF();
         select(query);
   }  
    
    public void select(String query) {
    	ObservableList<Phone> carsList = this.getPhoneList(query);
   	    tableColumnId.setCellValueFactory(new PropertyValueFactory<Phone,Integer>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<Phone,String>("name"));
        tableColumnMark.setCellValueFactory(new PropertyValueFactory<Phone,String>("mark"));
        tableColumnPrice.setCellValueFactory(new PropertyValueFactory<Phone,Integer>("price"));
        selectTable.setItems(carsList);
    }
    
    public void update(ActionEvent actionEvent) {
    	String query = "UPDATE `phone` SET ";
	    String strUpdate = "";
	    if(!nameTextU.getText().isEmpty()){
	    	strUpdate += " `name` = '"+ nameTextU.getText() + "',";
	    }
	    if(!markTextU.getText().isEmpty()){
	    	strUpdate += " `mark` = '" + markTextU.getText() + "',";
	    }
	    if(!priceTextU.getText().isEmpty()){
	    	strUpdate += " `price` = '" + priceTextU.getText() + "',";
	    }
	    strUpdate = strUpdate.substring(0, strUpdate.length()-1);
	    if(this.id != 0) strFind = " `id` = " + id + " ";
	    String queryU = query + strUpdate + " WHERE " + strFind;
	    if(!strUpdate.isEmpty()) {
	    	try (Connection connection = DriverManager.getConnection(url, user, password)) {
	            // String query = "SELECT * FROM cars";
	             Statement statement = connection.createStatement();
	             statement.executeUpdate(queryU);
	         } catch (SQLException throwables) {
	             throwables.printStackTrace();
	         }
	    }
    	refresh();
	    id=0;
	    resetU();   
    }
    public void resetTable(ActionEvent actionEvent) {
    	refresh();
    	strFind = "";
    	id = 0;
    }
}
