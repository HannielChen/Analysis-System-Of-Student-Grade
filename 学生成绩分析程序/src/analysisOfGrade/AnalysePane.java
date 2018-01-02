package analysisOfGrade;

import java.util.ArrayList;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AnalysePane extends VBox implements Data {
	private GridPane pane = new GridPane();

	private TextField highestGrade = new TextField();
	private TextField lowestGrade = new TextField();
	private TextField averageGrade = new TextField();
	private TextField excellentNumber = new TextField();
	private TextField excellentPercent = new TextField();
	private TextField goodNumber = new TextField();
	private TextField goodPercent = new TextField();
	private TextField averageNumber = new TextField();
	private TextField averagePercent = new TextField();
	private TextField qualifiedNumber = new TextField();
	private TextField qualifiedPercent = new TextField();
	private TextField failNumber = new TextField();
	private TextField failPercent = new TextField();

	private Button btBarChart = new Button("显示柱形分析图");
	private Button btPieChart = new Button("显示饼形分析图");
	private Button btAnalyse = new Button("分析成绩");

	private int excellentSum = 0;
	private int goodSum = 0;
	private int averageSum = 0;
	private int qualifiedSum = 0;
	private int failSum = 0;
	
	private double excellentPortion = 0;
	private double goodPortion = 0;
	private double averagePortion = 0;
	private double qualifiedPortion = 0;
	private double failPortion = 0;
	
	

	public AnalysePane() {
		drawPane();
	}

	public void drawPane() {
		// 设置文本域不可编辑
		highestGrade.setEditable(false);
		lowestGrade.setEditable(false);
		averageGrade.setEditable(false);
		excellentNumber.setEditable(false);
		excellentPercent.setEditable(false);
		goodNumber.setEditable(false);
		goodPercent.setEditable(false);
		averageNumber.setEditable(false);
		averagePercent.setEditable(false);
		qualifiedNumber.setEditable(false);
		qualifiedPercent.setEditable(false);
		failNumber.setEditable(false);
		failPercent.setEditable(false);

		// 设置文本域宽度
		highestGrade.setMaxWidth(110);
		lowestGrade.setMaxWidth(110);
		averageGrade.setMaxWidth(110);
		excellentNumber.setMaxWidth(110);
		excellentPercent.setMaxWidth(60);
		goodNumber.setMaxWidth(110);
		goodPercent.setMaxWidth(60);
		averageNumber.setMaxWidth(110);
		averagePercent.setMaxWidth(60);
		qualifiedNumber.setMaxWidth(110);
		qualifiedPercent.setMaxWidth(60);
		failNumber.setMaxWidth(110);
		failPercent.setMaxWidth(60);

		pane.addRow(0, new Label("最高分"), highestGrade);
		pane.addRow(1, new Label("最低分"), lowestGrade);
		pane.addRow(2, new Label("平均分"), averageGrade);
		pane.addRow(3, new Label("优秀(90-100)"), excellentNumber, new Label("人, 占"), excellentPercent, new Label("%"));
		pane.addRow(4, new Label("良好(80-89)"), goodNumber, new Label("人, 占"), goodPercent, new Label("%"));
		pane.addRow(5, new Label("中等(70-79)"), averageNumber, new Label("人, 占"), averagePercent, new Label("%"));
		pane.addRow(6, new Label("及格(60-69)"), qualifiedNumber, new Label("人, 占"), qualifiedPercent, new Label("%"));
		pane.addRow(7, new Label("不及格(0-60)"), failNumber, new Label("人, 占"), failPercent, new Label("%"));

		pane.setVgap(25);
		pane.setHgap(10);

		btBarChart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Stage stage = new Stage();
				BarChartPane pane = new BarChartPane(excellentSum, goodSum, averageSum, qualifiedSum, failSum);
				Scene scene = new Scene(pane, 500, 400);
				stage.setTitle("柱状统计图");
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
		});
		
		btPieChart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Stage stage = new Stage();
				PieChartPane pane = new PieChartPane(excellentPortion, goodPortion, averagePortion, qualifiedPortion, failPortion);
				Scene scene = new Scene(pane, 500, 400);
				stage.setTitle("饼状统计图");
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
		});
		
		

		btAnalyse.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				analyse();
			}
		});

		HBox hBox = new HBox();
		hBox.getChildren().addAll(btBarChart, btPieChart, btAnalyse);
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(15);

		setMargin(pane, new Insets(10));
		getChildren().addAll(pane, hBox);
		setSpacing(20);//设间距  
	}

	public void analyse() {
		ArrayList<Integer> list = new ArrayList<>();
		for (Student e : data) {
			try {
				int temp = Integer.parseInt(e.getGrade());
				list.add(temp);
			} catch (Exception e1) {

			}

		}

		highestGrade.setText(Collections.max(list) + "");
		lowestGrade.setText(Collections.min(list) + "");
		int sum = 0;
		excellentSum = goodSum = averageSum = qualifiedSum = failSum = 0;

		for (Integer e : list) {
			sum += e;
			if (e >= 90 && e <= 100)
				excellentSum++;
			else if (e >= 80 && e <= 89)
				goodSum++;
			else if (e >= 70 && e <= 79)
				averageSum++;
			else if (e >= 60 && e <= 69)
				qualifiedSum++;
			else
				failSum++;
		}
		averageGrade.setText((sum / list.size()) + "");
		
		excellentPortion = ((double) excellentSum / list.size() * 100) ;
		goodPortion = ((double) goodSum / list.size() * 100);
		averagePortion = ((double) averageSum / list.size() * 100);
		qualifiedPortion = ((double) qualifiedSum / list.size() * 100); 
		failPortion = ((double) failSum / list.size() * 100);
		
		excellentNumber.setText(excellentSum + "");
		excellentPercent.setText(excellentPortion + "");
		goodNumber.setText(goodSum + "");
		goodPercent.setText(goodPortion + "");
		averageNumber.setText(averageSum + "");
		averagePercent.setText(averagePortion + "");
		qualifiedNumber.setText(qualifiedSum + "");
		qualifiedPercent.setText(qualifiedPortion + "");
		failNumber.setText(failSum + "");
		failPercent.setText(failPortion + "");
	}

}
