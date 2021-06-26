package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController extends Application{
	
	@FXML
	public PasswordField passwordText;
	@FXML
	public TextField emailText;
	@FXML
    public Button loginButton;
	@FXML
	public Label err_email_ID;
	@FXML
	public Label err_pass_ID;
	
	@FXML
	public Scene app;
	
	
	private String  userPass = "";
	
	
	private final String url = "jdbc:mysql://hosting2035506.online.pro:3306/00307732_olaru_valentin";
    private final String user = "00307732_olaru_valentin";
    private final String password = "olaru_valentin";
	
	
	public void verify_and_login(ActionEvent actionEvent) throws IOException {
		
		if(emailText.getText().isEmpty()) 
		{
			err_email_ID.setText("Email is missing!");
			err_email_ID.setVisible(true);
			userPass = "";
		}
		else
		{
			err_email_ID.setVisible(false);
			 try (Connection connection = DriverManager.getConnection(url, user, password)) {
		            String query = "SELECT * FROM login";
		            Statement statement = connection.createStatement();
		            ResultSet resultSet = statement.executeQuery(query);
		            
		            while(resultSet.next()) {
		            	 userPass = resultSet.getString("password");
		            }
		        } catch (SQLException throwables) {
		            throwables.printStackTrace();
		        }
		}
		
		if(passwordText.getText().isEmpty())
		{
			err_pass_ID.setText("Password is missing!");
			err_pass_ID.setVisible(true);
		}
		else
		{
			err_pass_ID.setVisible(false);	
			if(userPass.equals(passwordText.getText())) {
				/// Login
				   Stage stage = new Stage();
				   
				   Node node = (Node) actionEvent.getSource();
                   Stage primaryStage = (Stage) node.getScene().getWindow();
                   
				   Parent board = FXMLLoader.load(getClass().getResource("sample.fxml"));
				   Scene scene = new Scene(board,900,600);

				   stage.setScene(scene);
				   stage.show(); 
				   
				   primaryStage.hide();
				
			}
			else {
				err_pass_ID.setText("Email or Password is wrong!");
				err_pass_ID.setVisible(true);
				
			}
		}
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
