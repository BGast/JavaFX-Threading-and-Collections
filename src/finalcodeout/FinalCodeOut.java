package finalcodeout;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * ClassName: CrapsSimulation
 * @author Brock Gast
 * @since 07/30/2018
 * Description - Starts a thread and builds JavaFX GUI inside of it
 */
public class FinalCodeOut extends Application {
    
    boolean threadStopCheck = true;

    private final Button btnExit = new Button("Exit");
    private final Button btnFileTransfer = new Button("Transfer Data");
    
    private Label lblFileTranferCheck = new Label();
    
    /**
     * @param primaryStage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Thread threadGUI = new Thread(() -> {
            Platform.runLater(() -> {
                
                BorderPane border = new BorderPane(); 
                border.setCenter(addLabelPane());
                HBox bottomPane = addHBox();
                border.setBottom(bottomPane);
                bottomPane.setPadding(new Insets(5, 0, 5, 11));
        
                btnExit.setOnAction( e -> closeApplication());
                btnFileTransfer.setOnAction(e ->{                    
                    Thread threadFileToCollection = new Thread(new Collection());
                    threadFileToCollection.start();     
                    
                    while(threadStopCheck) 
                        threadStopCheck = threadFileToCollection.isAlive();
                                        
                    lblFileTranferCheck.setText("The file is complete.");                    
                }); 
                
                Scene scene = new Scene(border, 300, 300);        
                primaryStage.setTitle("Bye Coleman");
                primaryStage.setScene(scene);
                primaryStage.show();
            });
        }); 
        
        threadGUI.start();
    }
    /**
     * @return the bottomPane
     */
    private HBox addHBox() {
        
        HBox bottomPane = new HBox();
        bottomPane.setSpacing(10);
        bottomPane.getChildren().addAll(btnFileTransfer, btnExit);
        bottomPane.setAlignment(Pos.BOTTOM_CENTER);
                
        return bottomPane;        
    }
    /**
     * @return the paneForLabel
     */
    private HBox addLabelPane(){
        
        lblFileTranferCheck.setText("Press 'Transfer Data' to move file!");
        
        HBox paneForLabel = new HBox(20);
        paneForLabel.setPadding(new Insets(5, 5, 5, 5));        
        paneForLabel.getChildren().addAll(lblFileTranferCheck);     
        paneForLabel.setAlignment(Pos.CENTER);
        
        return paneForLabel;
    }
    
    public void closeApplication(){
        System.exit(0);
    }
    /**
     * @param args 
     */
    public static void main(String[] args) {                
        launch(args);        
    }
}