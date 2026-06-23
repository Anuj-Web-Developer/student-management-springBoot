package crud.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import crud.crud.entity.Otp;
import jakarta.transaction.Transactional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Integer> {

	Optional<Otp> findByEmail(String email);
	
	@Modifying
    @Transactional
    @Query("delete from Otp o where o.email = :email")
	void deleteAllByEmail(String email);
}
