package com.kpl.registration.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Repository;

import com.kpl.registration.entity.AdminInfo;

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
}