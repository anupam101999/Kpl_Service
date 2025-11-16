package com.kpl.registration.repository;


import com.kpl.registration.entity.AdminInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
//@EnableJpaAuditing
public interface AdminRepo extends JpaRepository<AdminInfo, Long> {

	@Query(value = "select id from admin_mst_table where id=?1 and password=?2", nativeQuery = true)
	String adminLoginValidation(String id, String password);

	@Transactional
	@Modifying
	@Query(value = "delete from admin_mst_table where admin_id=?1", nativeQuery = true)
	void deletebyAdminId(Long adminId);

	Optional<AdminInfo> findByIdAndPassword(String id, String password);

	@Query(value = "select * from admin_mst_table where id=?1", nativeQuery = true)
	Optional<AdminInfo> findByUserId(String username);

	@Modifying
	@Transactional
	@Query("update AdminInfo a set a.id =:password where a.id=:id")
	void updateTest(String id, String password);

//	@Transactional
//	@Modifying
//	@Query(value = "update AdminInfo a set a.id =:password where a.id=:id",nativeQuery = true)
//	void updateTest(String id, String password);
}