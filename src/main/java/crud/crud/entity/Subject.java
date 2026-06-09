package crud.crud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String subjectName;
	private Integer max_marks;
	private Integer obtained_marks;
	
	@ManyToOne
	private Student student;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getMax_marks() {
		return max_marks;
	}

	public void setMax_marks(Integer max_marks) {
		this.max_marks = max_marks;
	}

	public Integer getObtained_marks() {
		return obtained_marks;
	}

	public void setObtained_marks(Integer obtained_marks) {
		this.obtained_marks = obtained_marks;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject(Integer id, String subjectName, Integer max_marks, Integer obtained_marks, Student student) {
		super();
		this.id = id;
		this.subjectName = subjectName;
		this.max_marks = max_marks;
		this.obtained_marks = obtained_marks;
		this.student = student;
	}

	public Subject() {
		super();
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", subjectName=" + subjectName + ", max_marks=" + max_marks + ", obtained_marks="
				+ obtained_marks + ", student=" + student + "]";
	}
	
}
