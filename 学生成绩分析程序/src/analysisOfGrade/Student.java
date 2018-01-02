package analysisOfGrade;

import java.io.Serializable;


import javafx.beans.property.SimpleStringProperty;

public class Student implements Serializable {
	private SimpleStringProperty id;
	private SimpleStringProperty name;
	private SimpleStringProperty grade;
	
	public Student() {
		
	}
	
	public Student(String id, String name, String grade) {
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.grade = new SimpleStringProperty(grade);
	}
	
    /*
     * 设置get和set以便于访问私有数据
     */
	
	public String getId() {
		return id.get();
	}
	
	public void setId(String id) {
		this.id.set(id);
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getGrade() {
		return grade.get();
	}
	
	public void setGrade(String grade) {
		this.grade.set(grade);
	}
}
