package analysisOfGrade;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;

public class Main extends Application {
	@Override // Override the main method in the Application class
	public void start(Stage primaryStage) {
		BorderPane mainPane = new BorderPane(); // ���������
		
		MenuPane menuPane = new MenuPane(primaryStage);
		GradeTablePane gradeTablePane = new GradeTablePane(primaryStage);
		AnalysePane analysePane = new AnalysePane();
		
	
		mainPane.setTop(menuPane);
		mainPane.setCenter(gradeTablePane); // ��ӳɼ������
		mainPane.setRight(analysePane); // ��ӷ������
		mainPane.setPadding(new Insets(10, 10, 10, 10));
	
		Scene scene = new Scene(mainPane, 730, 500); 
		primaryStage.setTitle("ѧ���ɼ���������"); // Set the stage title 
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.setResizable(false);
		primaryStage.show(); // Display the stage
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
