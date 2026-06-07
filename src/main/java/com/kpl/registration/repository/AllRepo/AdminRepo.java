package com.kpl.registration.repository.AllRepo;


import com.kpl.registration.entity.AllEntity.AdminInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@EnableJpaAuditing
public interface AdminRepo extends JpaRepository<AdminInfo, Long> {

	@Query(value = "select id from admin_mst_table where user_id=?1 and password=?2", nativeQuery = true)
	String adminLoginValidation(String id, String password);

	Optional<AdminInfo> findByIdAndPassword(String id, String password);

	@Query(value = "select * from admin_mst_table where user_id=?1", nativeQuery = true)
	Optional<AdminInfo> findByUserId(String username);

}
