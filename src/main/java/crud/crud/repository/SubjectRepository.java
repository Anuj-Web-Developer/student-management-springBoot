package crud.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crud.crud.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer >{

	List<Subject> findByStudentId(Integer student_id);
}
