package de.hsrm.mi.swt.ui.application;
import de.hsrm.mi.swt.anwendungslogik.model.Planverwaltung;
import de.hsrm.mi.swt.ui.scenes.planerScene.PlanerSceneController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application{
    Planverwaltung planverwaltung;
    PlanerSceneController planerSceneController;
    @Override
    public void start(Stage primaryStage){
        
        Pane root = new FlowPane();
        Text text = new Text("Hallo Welt");
        root.getChildren().add(text);
        Scene scene = new Scene(root, 1600, 1000);
        
        scene.setRoot(planerSceneController.getRootView());
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ein StudienPlaner");
        primaryStage.show();


        primaryStage.setOnCloseRequest(e -> {
			System.exit(0);
		});
 
    }
    public void init(){
        
        planverwaltung = new Planverwaltung();
        planerSceneController = new PlanerSceneController(planverwaltung);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
