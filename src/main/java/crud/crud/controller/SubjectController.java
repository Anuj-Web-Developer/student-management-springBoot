package crud.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crud.crud.entity.Subject;
import crud.crud.service.SubjectService;

@RestController
@RequestMapping("/subject")
@CrossOrigin("*")
public class SubjectController {

	@Autowired
	SubjectService subjectServ;
	
	// add subject
	@PostMapping("/addSubject/{studentId}")
	 public String addSubjectController(@PathVariable Integer studentId, @RequestBody Subject subject) {
		return subjectServ.addNewSubjectService(studentId, subject);
	}
	
	// edit student
	@PutMapping("/editSubject/{subjectId}")
	public String editSubjectController(@PathVariable Integer subjectId, @RequestBody Subject subject) {
		return subjectServ.editOldSubjectService(subjectId, subject);
	}
	
	//delete student
	@DeleteMapping("/deleteSubject/{subjectId}")
	public String deleteStudentController(@PathVariable Integer subjectId) {
		return subjectServ.deleteSubjectService(subjectId);
	}
	
	// get subject list
	@GetMapping("/getAllSubject/{studentId}")
	public List<Subject> getAllSubjectController(@PathVariable Integer studentId) {
		return subjectServ.getSubjectListController(studentId);
	}
}
