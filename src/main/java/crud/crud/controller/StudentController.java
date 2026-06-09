package crud.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crud.crud.entity.Student;
import crud.crud.service.StudentService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentController {
	
	@Autowired
	StudentService studentServ;
	
	@PostMapping("/saveStudent")
	public Student saveStudentController(@RequestBody Student student) {
		System.out.println("Student is : "+student);
		return studentServ.saveStudentService(student);
	}
	
	@GetMapping("/getStudents")
	public List<Student> getStudentController(){
		return studentServ.getStudentService();
	}
	
	@PutMapping("/updateStudent/{id}")
	public String updateStudentController(@PathVariable int id, @RequestBody Student student) {
		return studentServ.updateStudentService(id, student);
	}
	
	@DeleteMapping("/deleteStudent/{id}")
	public String deleteStudentController(@PathVariable int id) {
		return studentServ.deleteStudentService(id);
	}
	
	@GetMapping("/getStudent/{id}")
	public Student getStudentByIdController(@PathVariable Integer id) {
		return studentServ.getStudentByIdService(id);
	}
	
}