package analysisOfGrade;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GradeTablePane extends Pane implements Data {

	private TableView<Student> table = new TableView<>();

	final HBox hb = new HBox();

	public GradeTablePane() {

	}

	public GradeTablePane(Stage stage) {
		drawTable(stage);
	}

	protected void drawTable(Stage stage) {

		final Label label = new Label("成绩单");
		label.setFont(new Font("Arial", 18));

		table.setEditable(true);

		TableColumn<Student, String> idCol = new TableColumn<>("学号");
		idCol.setMinWidth(100);
		idCol.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
		idCol.setCellFactory(TextFieldTableCell.forTableColumn());
		idCol.setOnEditCommit(new EventHandler<CellEditEvent<Student, String>>() {
			@Override
			public void handle(CellEditEvent<Student, String> t) {
				((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId(t.getNewValue());
			}
		});

		TableColumn<Student, String> nameCol = new TableColumn<>("姓名");
		nameCol.setMinWidth(100);
		nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
		nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		nameCol.setOnEditCommit(new EventHandler<CellEditEvent<Student, String>>() {
			@Override
			public void handle(CellEditEvent<Student, String> t) {
				((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
			}
		});

		TableColumn<Student, String> gradeCol = new TableColumn<>("成绩");
		gradeCol.setMinWidth(100);
		gradeCol.setCellValueFactory(new PropertyValueFactory<Student, String>("grade"));
		gradeCol.setCellFactory(TextFieldTableCell.forTableColumn());
		gradeCol.setOnEditCommit(new EventHandler<CellEditEvent<Student, String>>() {
			@Override
			public void handle(CellEditEvent<Student, String> t) {
				((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setGrade(t.getNewValue());
			}
		});

		table.setItems(data);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getColumns().addAll(idCol, nameCol, gradeCol);

		final TextField addId = new TextField();
		addId.setPromptText("学号");
		addId.setMaxWidth(idCol.getPrefWidth());

		final TextField addName = new TextField();
		addName.setPromptText("姓名");
		addName.setMaxWidth(nameCol.getPrefWidth());

		final TextField addGrade = new TextField();
		addGrade.setPromptText("成绩(1-100)");
		addGrade.setMaxWidth(gradeCol.getPrefWidth());

		final Button addButton = new Button("add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (isGrade(addGrade.getText())) {
					data.add(new Student(addId.getText(), addName.getText(), addGrade.getText()));
					addId.clear();
					addName.clear();
					addGrade.clear();
				}
			}
		});

		final Button saveButton = new Button("save");
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				FileChooser fileChooser = new FileChooser();
				configureFileChooser(fileChooser);
				File file = fileChooser.showSaveDialog(stage);
				ArrayList<DataToWrite> list = new ArrayList<>();
				for (Student i : data) {
					list.add(new DataToWrite(i.getId(), i.getName(), i.getGrade()));
				}

				try {
					ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
					output.writeObject(list);
					output.close();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}
		});

		hb.getChildren().addAll(addId, addName, addGrade, addButton, saveButton);
		hb.setSpacing(3);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table, hb);
		getChildren().addAll(vbox);
	}

	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("保存文件");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("DAT", "*.dat"));
	}

	private static Boolean isGrade(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

/*class EditingCell extends TableCell<Student, String> {

	private TextField textField;

	public EditingCell() {
	}

	@Override
	public void startEdit() {
		if (!isEmpty()) {
			super.startEdit();
			createTextField();
			setText(null);
			setGraphic(textField);
			textField.selectAll();
		}
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();

		setText((String) getItem());
		setGraphic(null);
	}

	@Override
	public void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);

		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			if (isEditing()) {
				if (textField != null) {
					textField.setText(getString());
				}
				setText(null);
				setGraphic(textField);
			} else {
				setText(getString());
				setGraphic(null);
			}
		}
	}

	private void createTextField() {
		textField = new TextField(getString());
		textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
		textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				if (!arg2) {
					commitEdit(textField.getText());
				}
			}
		});
	}

	private String getString() {
		return getItem() == null ? "" : getItem().toString();
	}
}
*/