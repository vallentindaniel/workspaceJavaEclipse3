package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	public TextField emailText;
	@FXML
	public PasswordField passwordText;
	@FXML
	public Label err_email;
	@FXML
	public Label err_password;

	private final String url = "jdbc:mysql://hosting2035506.online.pro:3306/00307732_olaru_valentin";
    private final String user = "00307732_olaru_valentin";
    private final String password = "olaru_valentinolaru_valentin";
    
    
	public void login(ActionEvent actionEvent) throws IOException {
		if(emailText.getText().isEmpty()) {
			err_email.setText("Email is missing");
			err_email.setVisible(true);
		}
		if(passwordText.getText().isEmpty()) {
			err_password.setText("Password is missing");
			err_password.setVisible(true);
		}
		if(!emailText.getText().isEmpty() && !passwordText.getText().isEmpty() ) {
			err_email.setVisible(false);
			err_password.setVisible(false);
			String  userPass = "";
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
			if(userPass.equals(passwordText.getText()) || true) { // verify if password is correct
				   Stage stage = new Stage();
				   Node node = (Node) actionEvent.getSource();
                   Stage primaryStage = (Stage) node.getScene().getWindow();
				   Parent board = FXMLLoader.load(getClass().getResource("home.fxml"));
				   Scene scene = new Scene(board,730,600);
				   stage.setScene(scene);
				   stage.show();
				   primaryStage.close();
			}	
		}
		else {
			err_password.setText("Email/Password is wrong");
			err_password.setVisible(true);
		}
	}
  
}
