package crud.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crud.crud.entity.Student;
import crud.crud.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepo;
	
	// save student
	public Student saveStudentService(Student student) {
		return studentRepo.save(student);
	}
	
	// read student
	public List<Student> getStudentService() {
		return studentRepo.findAll();
	}
	
	// update student
	public String updateStudentService(int id, Student student) {

	    Student oldStudent = studentRepo.findById(id).orElse(null);

	    oldStudent.setName(student.getName());
	    oldStudent.setEmail(student.getEmail());
	    oldStudent.setMobile(student.getMobile());
	    oldStudent.setAddress(student.getAddress());

	    if (student.getImage() != null) {
	        oldStudent.setImage(student.getImage());
	    }

	    studentRepo.save(oldStudent);

	    return "Student Updated Successfully";
	}
	
	// delete student
	public String deleteStudentService(int id) {
		Student student = studentRepo.findById(id).orElseThrow(()->new RuntimeException("Data not found"));
		studentRepo.delete(student);
		return "Student deleted successfully";
	}
	
	// get student by id
	public Student getStudentByIdService(Integer id) {
		return studentRepo.findById(id).orElseThrow(()->new RuntimeException("Student Not Found"));
	}
}
