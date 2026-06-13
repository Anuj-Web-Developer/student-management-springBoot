package crud.crud.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import crud.crud.entity.Student;
import crud.crud.service.StudentService;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentController {

	@Autowired
	StudentService studentServ;

	@PostMapping(value = "/saveStudent", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Student saveStudentController(@RequestPart("student") Student student,
			@RequestPart("image") MultipartFile image) throws java.io.IOException {

		student.setImage(image.getBytes());
		return studentServ.saveStudentService(student);
	}

	@PutMapping(value = "/updateStudent/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String updateStudentController(@PathVariable int id, @RequestPart("student") Student student,
			@RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

		if (image != null && !image.isEmpty()) {
			student.setImage(image.getBytes());
		}

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

	@GetMapping("/getStudents")
	public List<Student> getAllStudentController(){
		return studentServ.getStudentService();
	}
}