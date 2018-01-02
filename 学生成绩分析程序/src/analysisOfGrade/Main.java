package analysisOfGrade;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;

public class Main extends Application {
	@Override // Override the main method in the Application class
	public void start(Stage primaryStage) {
		BorderPane mainPane = new BorderPane(); // 定义主面板
		
		MenuPane menuPane = new MenuPane(primaryStage);
		GradeTablePane gradeTablePane = new GradeTablePane(primaryStage);
		AnalysePane analysePane = new AnalysePane();
		
	
		mainPane.setTop(menuPane);
		mainPane.setCenter(gradeTablePane); // 添加成绩单面板
		mainPane.setRight(analysePane); // 添加分析面板
		mainPane.setPadding(new Insets(10, 10, 10, 10));
	
		Scene scene = new Scene(mainPane, 730, 500); 
		primaryStage.setTitle("学生成绩分析程序"); // Set the stage title 
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.setResizable(false);
		primaryStage.show(); // Display the stage
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
