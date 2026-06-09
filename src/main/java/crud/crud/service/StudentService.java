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
		Student std = studentRepo.findById(id).orElseThrow(()->new RuntimeException("Data not found"));
		if(std==null) {
			return "Data not found";
		}
		std.setName(student.getName());
		std.setEmail(student.getEmail());
		std.setMobile(student.getMobile());
		std.setAddress(student.getAddress());
		
		studentRepo.save(std);
		return "Student detail update successfully";
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
