package analysisOfGrade;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

public class BarChartPane extends Pane {
	public BarChartPane() {
		
	}

	public BarChartPane(int excellentSum, int goodSum, int averageSum, int qualifiedSum, int failSum) {
		drawPane(excellentSum, goodSum, averageSum, qualifiedSum, failSum);
	}

	public void drawPane(int excellentSum, int goodSum, int averageSum, int qualifiedSum, int failSum) {
		final NumberAxis yAxis = new NumberAxis();
		final CategoryAxis xAxis = new CategoryAxis();
		final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
		bc.setTitle("�ɼ�����ͳ��ͼ");
		xAxis.setLabel("������");
		yAxis.setLabel("����");
		
		XYChart.Series series = new XYChart.Series();
        series.setName("�ð�ɼ�");       
        series.getData().add(new XYChart.Data("<60��", failSum));
        series.getData().add(new XYChart.Data("60-69��", qualifiedSum));
        series.getData().add(new XYChart.Data("70-79��", averageSum));
        series.getData().add(new XYChart.Data("80-89��", goodSum));
        series.getData().add(new XYChart.Data(">89��", excellentSum));
        
        bc.getData().add(series);
        
        getChildren().add(bc);
	}
}
