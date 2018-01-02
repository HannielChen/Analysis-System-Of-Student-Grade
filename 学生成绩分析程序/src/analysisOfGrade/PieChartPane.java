package analysisOfGrade;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;

public class PieChartPane extends Pane {
	public PieChartPane() {

	}

	public PieChartPane(double excellentPortion, double goodPortion, double averagePortion, double qualifiedPortion, double failPortion) {
		drawPane(excellentPortion, goodPortion, averagePortion, qualifiedPortion, failPortion);
	}

	public void drawPane(double excellentPortion, double goodPortion, double averagePortion, double qualifiedPortion, double failPortion) {
		PieChart pieChart = new PieChart();
		pieChart.setData(getChartData(excellentPortion, goodPortion, averagePortion, qualifiedPortion, failPortion));
		pieChart.setTitle("�ɼ�ͳ��ͼ");
		pieChart.setLegendSide(Side.LEFT);
		pieChart.setClockwise(false);
		pieChart.setLabelsVisible(false);
		
		getChildren().add(pieChart);

	}
	  private ObservableList<PieChart.Data> getChartData(double excellentPortion, double goodPortion, double averagePortion,
			  	double qualifiedPortion, double failPortion) {
		    ObservableList<PieChart.Data> answer = FXCollections.observableArrayList();
		    answer.addAll(new PieChart.Data("<60�� "+ (int)failPortion + "%", (int)failPortion),
		            new PieChart.Data("60-69�� " + (int)qualifiedPortion + "%", (int)qualifiedPortion),
		            new PieChart.Data("70-79�� " + (int)averagePortion + "%",(int)averagePortion),
		            new PieChart.Data("80-89�� " + (int)goodPortion + "%", (int)goodPortion),
		            new PieChart.Data(">89�� " + (int)excellentPortion + "%", (int)excellentPortion)
		            );
		    return answer;
		  }

}
