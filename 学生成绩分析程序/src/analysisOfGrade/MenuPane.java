package analysisOfGrade;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MenuPane extends Pane implements Data{

	private ComboBox<String> cbo = new ComboBox<>();
	private String[] flagTitles = {"新建成绩单", "打开成绩单"};
	
	public MenuPane() {
		
	}
	
	public MenuPane(Stage stage) {
		drawPane(stage);
	}
	public void drawPane(Stage stage) {
		cbo.setPrefWidth(110);
		cbo.setValue("文件");
		ObservableList<String> items = 
				FXCollections.observableArrayList(flagTitles);
		cbo.getItems().addAll(items);
		cbo.setOnAction(e -> handle(items.indexOf(cbo.getValue()), stage));
		getChildren().add(cbo);
	}
	
	public void handle(int index, Stage stage2) {
		if(index == 0) { // 新建成绩单
			 Stage stage = new Stage();
			 NewGradePane gradePane = new NewGradePane(stage);
			 Scene scene = new Scene(gradePane);
			 stage.setScene(scene);
			 stage.setTitle("新建成绩单");
			 stage.show();
		}
		if(index == 1){ // 打开成绩单
			final FileChooser fileChooser = new FileChooser();
			configureFileChooser(fileChooser);
			File file = fileChooser.showOpenDialog(stage2);
			if (file != null) {
				try {
					ObjectInputStream input =
							new ObjectInputStream(new FileInputStream(file));
					ArrayList<DataToWrite> list = new ArrayList<>();
					list.addAll((ArrayList<DataToWrite>) (input.readObject()));
					data.clear();
					for(DataToWrite i: list ) {
						data.add(new Student(i.getId(), i.getName(), i.getGrade()));
					}
					
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("选择要打开的成绩单");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("DAT", "*.dat"));
	}

}
