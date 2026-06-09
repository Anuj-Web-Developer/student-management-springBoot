package crud.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crud.crud.entity.Student;
import crud.crud.entity.Subject;
import crud.crud.repository.StudentRepository;
import crud.crud.repository.SubjectRepository;

@Service
public class SubjectService {

	@Autowired
	SubjectRepository subjectRepo;
	
	@Autowired
	StudentRepository studentRepo;
	
	// add new subject
	public String addNewSubjectService(Integer studentId, Subject subject) {
		Student student = studentRepo.findById(studentId).orElseThrow(()->new RuntimeException("Student not found"));
		subject.setStudent(student);
		subjectRepo.save(subject);
		return "Subject added successfully";
	}
	
	// edit subject
	public String editOldSubjectService(Integer id, Subject subject) {
		Subject old_subject = subjectRepo.findById(id).orElseThrow(()->new RuntimeException("Subject not found"));
		
		old_subject.setSubjectName(subject.getSubjectName());
		old_subject.setMax_marks(subject.getMax_marks());
		old_subject.setObtained_marks(subject.getObtained_marks());
		old_subject.setStudent(subject.getStudent());
		
		subjectRepo.save(old_subject);
		return "Subject updated successfully";
	}
	
	// delete subject
	public String deleteSubjectService(Integer subjectId) {
		subjectRepo.deleteById(subjectId);
		return "Subject delete successfully";
	}
	
	// get subject 
	public List<Subject> getSubjectListController(Integer student_id) {
		return subjectRepo.findByStudentId(student_id);
		
	}
}
