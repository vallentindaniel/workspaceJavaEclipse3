package application;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeController {
	
	@FXML
    public TableView<Movie> selectTable;
	@FXML
	public TableColumn<Movie, Integer> tableColumnId;
	@FXML
	public TableColumn<Movie, String> tableColumnName;
	@FXML
	public TableColumn<Movie, String> tableColumnStageDirector;
	@FXML
	public TableColumn<Movie, Integer> tableColumnYear;
	
	@FXML
    public TableView<Movie> selectTableU;
	@FXML
	public TableColumn<Movie, Integer> tableColumnIdU;
	@FXML
	public TableColumn<Movie, String> tableColumnNameU;
	@FXML
	public TableColumn<Movie, String> tableColumnStageDirectorU;
	@FXML
	public TableColumn<Movie, Integer> tableColumnYearU;
	
	@FXML
	public Pane panelInsert;
	@FXML
    public Pane panelSearch;
	@FXML
	public Pane panelUpdateOrDelete;
    @FXML
    public Pane panelUpdate;
    @FXML
    public Pane panelDelete;
	
	@FXML
	public TextField nameText;
	@FXML
	public TextField stageDirectorText;
	@FXML
	public TextField yearText;
	
	@FXML
	public TextField nameTextF;
	@FXML
	public TextField stageDirectorTextF;
	@FXML
	public TextField yearTextF;
	
	@FXML
	public TextField nameTextU;
	@FXML
	public TextField stageDirectorTextU;
	@FXML
	public TextField yearTextU;
	
	@FXML
	public Label count_Items;
	@FXML
	public Label err_insert;
	
	private SQLHome sql = new SQLHome();
	private Movie m;
	private String strFind;
	private Integer id;
	
	 public void initialize() {
		 id = 0;
		 showInTable();
		 
		 // event onclick mouse for table for update or delete
		 selectTableU.setOnMouseClicked(e ->{
		  		int x = (selectTableU.getSelectionModel().getSelectedCells().get(0)).getRow();
			  	 this.id = tableColumnId.getCellData(x);
		  	 System.out.println("id: "+this.id);
	    	panelUpdateOrDelete.setVisible(true);
		 });
		 
	 }
    
    public void displayInsert() {
    	panelInsert.setVisible(true);
    	panelSearch.setVisible(false);
    	System.out.println("insert page");
    	
    	
    	
    }
    
    public void displayUpdateAndDelete() {
    	panelInsert.setVisible(false);
    	panelSearch.setVisible(true);
    	System.out.println("Update/Delete page");
    	showInTableU();
    }
    
    public void insert() {
    	System.out.println("insert");
    	if(!nameText.getText().isEmpty() && !stageDirectorText.getText().isEmpty() && !yearText.getText().isEmpty()) {
    		err_insert.setVisible(false);
    		Integer x = 1;
    		m = new Movie(x,nameText.getText(),stageDirectorText.getText(), Integer.parseInt(yearText.getText()));
        	sql.insert(m);
        	showInTable();	
        	nameText.setText("");
        	stageDirectorText.setText("");
        	yearText.setText("");
        	
        	PauseTransition delay = new PauseTransition(Duration.seconds(2));
        	delay.setOnFinished(e -> 
        	            err_insert.setVisible(false)
        			);
    		err_insert.setText("Added");
        	err_insert.setVisible(true);
        	delay.play();
    	}else {
    		PauseTransition delay = new PauseTransition(Duration.seconds(2));
        	delay.setOnFinished(e -> 
        	            err_insert.setVisible(false)
        			);
    		err_insert.setText("Please complete the form");
        	err_insert.setVisible(true);
        	delay.play();	
    	}
    }
    
    public void showInTable() {
        ObservableList<Movie> movieList = sql.getMovieList();
   	    tableColumnId.setCellValueFactory(new PropertyValueFactory<Movie,Integer>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<Movie,String>("name"));
        tableColumnStageDirector.setCellValueFactory(new PropertyValueFactory<Movie,String>("stageDirector"));
        tableColumnYear.setCellValueFactory(new PropertyValueFactory<Movie,Integer>("year"));
        selectTable.setItems(movieList);
    }
    
    public void showInTableU(String query) {
    	ObservableList<Movie> movieList = sql.getMovieList(query);
   	    tableColumnIdU.setCellValueFactory(new PropertyValueFactory<Movie,Integer>("id"));
        tableColumnNameU.setCellValueFactory(new PropertyValueFactory<Movie,String>("name"));
        tableColumnStageDirectorU.setCellValueFactory(new PropertyValueFactory<Movie,String>("stageDirector"));
        tableColumnYearU.setCellValueFactory(new PropertyValueFactory<Movie,Integer>("year"));
        selectTableU.setItems(movieList);	
    }
    
    public void showInTableU() {
    	showInTableU("select * from movie");
    }
    
    public void find() {
    	System.out.println("Find");
    	panelDelete.setVisible(false);
    	panelUpdate.setVisible(false);
    	panelUpdateOrDelete.setVisible(false);
    	String query = "SELECT *  FROM `movie` WHERE ";
  	     strFind = "";
	   	 if(!nameTextF.getText().isEmpty()) {
	   		 strFind += " `name` LIKE '"+nameTextF.getText()+"' AND ";
	   	 }
	   	 if(!stageDirectorTextF.getText().isEmpty()) {
	   		 strFind += " `stage_director` LIKE '"+stageDirectorTextF.getText()+"' AND ";
	   	 }
	   	 if(!yearTextF.getText().isEmpty()) {
	   		 strFind += " `year` LIKE '"+yearTextF.getText()+"' AND";
	   	 }
	   	 
	   	 if(!strFind.isEmpty()) 
               strFind = strFind.substring(0,strFind.length()-4); // delete the last word " AND" from string
	   	 else {
	   		strFind = "1";
	   	 }
	   	 query += strFind; // create query for search
	   	 // show in table
	   	 System.out.println("...Query search:  "+query);
	   	 showInTableU(query);
	   	
	   	// reset form
	   	nameTextF.setText("");
	   	stageDirectorTextF.setText("");
	   	yearTextF.setText("");
	   	if(sql.getCount()>0) { // if we have result in table i show the option update and delete
	   	    // hide option delete and update
	   		count_Items.setText(sql.getCount()+" items found");
	   		count_Items.setVisible(true);
		   	panelDelete.setVisible(false);
	    	panelUpdate.setVisible(false);
	    	// show the panel with buttons update and delete
	    	panelUpdateOrDelete.setVisible(true);
	   	}
    }
    
    public void reset() { // reset the table and form
    	System.out.println("Reset search");
    	// reset the search to initial
    	PauseTransition delay = new PauseTransition(Duration.seconds(3));
    	delay.setOnFinished(e -> 
    	            count_Items.setVisible(false)
    			);
    	count_Items.setText("Reseted");
    	count_Items.setVisible(true);
    	
    	delay.play();
    	
    	panelUpdate.setVisible(false);
    	panelDelete.setVisible(false);
    	panelUpdateOrDelete.setVisible(false);
    	// reset the form
    	nameTextF.setText("");
	   	stageDirectorTextF.setText("");
	   	yearTextF.setText("");
    	// reset table
    	showInTableU();
    	id = 0;
	   	strFind = "";
    }
    
    public void update() {
    	System.out.println("Update line(s)");
    	panelUpdate.setVisible(false);
    
    	String query = "UPDATE `movie` SET ";
    	String strUpdate = "";
    	if(!nameTextU.getText().isEmpty()){
	    	strUpdate += " `name` = '"+ nameTextU.getText() + "',";
	    }
	    if(!stageDirectorTextU.getText().isEmpty()){
	    	strUpdate += " `stage_director` = '" + stageDirectorTextU.getText() + "',";
	    }
	    if(!yearTextU.getText().isEmpty()){
	    	strUpdate += " `year` = '" + yearTextU.getText() + "',";
	    }
	    strUpdate = strUpdate.substring(0, strUpdate.length()-1);
	    if(this.id != 0) strFind = " `id` = " + id + " ";
	    
	    String queryU = query + strUpdate + " WHERE " + strFind;
	    if(!strUpdate.isEmpty()) { // if the form is not empty make update
	    	sql.update(queryU);
	    }
	    // reset form
    	nameTextU.setText("");
	   	stageDirectorTextU.setText("");
	   	yearTextU.setText("");
	   	// reset table
	   	showInTableU();
	   	// reset id
	   	id = 0;
	   	strFind = "";
    }
    
    
    public void delete(){
    	System.out.println("Delete line(s)");
    	panelDelete.setVisible(false);
    	if(this.id != 0) strFind = " `id` = " + id + " ";
    	String query = "DELETE FROM `movie` WHERE "+ strFind;
    	System.out.println("Query delete: "+ query );
    	sql.delete(query);
    	// reset table
	   	showInTableU();
	   	// reset id
	   	id = 0;
	   	strFind = "";
    }     
    
    public void displayUpdate() {
    	System.out.println("Display update page");
    	panelUpdateOrDelete.setVisible(false);
    	panelUpdate.setVisible(true);	
    }    
    
    public void displayDelete() {
    	System.out.println("Display delete page");
    	panelUpdateOrDelete.setVisible(false);
    	panelDelete.setVisible(true);
    }
    
    public void logout(ActionEvent actionEvent) throws IOException {
    	System.out.println("Logout");
    	 Stage stage = new Stage();
		   Node node = (Node) actionEvent.getSource();
         Stage primaryStage = (Stage) node.getScene().getWindow();
		   Parent board = FXMLLoader.load(getClass().getResource("login.fxml"));
		   Scene scene = new Scene(board,350,300);
		   stage.setScene(scene);
		   stage.show();
		   //primaryStage.hide();
		   primaryStage.close();
    }
 
  
}
