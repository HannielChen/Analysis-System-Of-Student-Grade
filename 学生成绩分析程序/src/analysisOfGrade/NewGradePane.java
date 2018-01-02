package analysisOfGrade;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NewGradePane extends Pane implements Data {

	public NewGradePane() {

	}

	public NewGradePane(Stage stage) {
		drawPane(stage);
	}

	public void drawPane(Stage stage) {

		final Label label = new Label("��ѡ������γ�,��ѡ�γ�����");
		label.setFont(new Font("Arial", 14));

		VBox vbox = new VBox();
		vbox.getChildren().add(label);
		TextField textField = new TextField();
		textField.setPrefWidth(100);
		textField.setEditable(true);
		vbox.getChildren().add(textField);

		//ClassLoader.getSystemClassLoader().getResourceAsStream("course.txt");
		//������ܽ���ȡjar���е��ļ�
		File file = new File("course.txt");
		if (!file.exists()) {
			System.out.println("�γ��ļ�������");
		}

		ToggleGroup group = new ToggleGroup();
		try {
			Scanner input = new Scanner(file
					);
			while (input.hasNext()) {
				String str = input.nextLine().trim();
				RadioButton button = new RadioButton(str);
				button.setToggleGroup(group);
				button.setOnAction(e -> {
					if (button.isSelected()) {
						textField.setText(str);
					}
				});
				vbox.getChildren().add(button);
			}
			input.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		final Label remind = new Label("��ѡ��༶");
		remind.setFont(new Font("Arial", 14));
		vbox.getChildren().add(remind);
		final FileChooser fileChooser = new FileChooser();
		final Button openButton = new Button("ѡ��༶����");
		vbox.getChildren().add(openButton);
		openButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				configureFileChooser(fileChooser);
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					Scanner input;
					try {
						input = new Scanner(file);
						data.clear();// ��ձ�������
						while (input.hasNext()) {
							String s = input.nextLine().trim();
							Scanner input1 = new Scanner(s);
							data.add(new Student(input1.next(), input1.next(), ""));
							input1.close();
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}

				}

			}
		});

		vbox.setSpacing(20);
		vbox.setPadding(new Insets(10, 10, 10, 10));

		BorderPane borderPane = new BorderPane();
		GradeTablePane gradeTablePane = new GradeTablePane(stage);
		borderPane.setCenter(gradeTablePane);
		borderPane.setRight(vbox);
		borderPane.setPadding(new Insets(10, 0, 0, 10));
		getChildren().add(borderPane);
	}

	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("ѡ��༶����");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT", "*.txt"));
	}

}
