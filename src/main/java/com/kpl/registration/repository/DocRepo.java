package com.kpl.registration.repository;

import com.kpl.registration.entity.DocInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DocRepo extends JpaRepository<DocInfo, Long> {

	@Query(value = "SELECT d.* FROM doc_info d JOIN player_registration p ON d.registration_id = p.registration_id WHERE p.payment_validation = 'ok'ORDER BY d.registration_id", nativeQuery = true)
	List<DocInfo> findAllImageByGenerue();

//	for live search data

	@Query(value = "select image from doc_info where registration_id=?1", nativeQuery = true)
	byte[] findByregistrationId(Long registrationId);

	@Query(value = "SELECT d.doc_image_front FROM doc_info d JOIN player_registration p ON d.registration_id = p.registration_id WHERE p.payment_validation = 'ok' ORDER BY d.registration_id", nativeQuery = true)
	List<byte[]> findAllDocFront();

	@Query(value = "SELECT d.doc_image_back FROM doc_info d JOIN player_registration p ON d.registration_id = p.registration_id WHERE p.payment_validation = 'ok' ORDER BY d.registration_id", nativeQuery = true)
	List<byte[]> findAllDocBack();

	@Transactional
	@Modifying
	@Query(value = "update doc_info set image=?2  where registration_id=?1", nativeQuery = true)
	void updateOwnImage(Long id,byte[] imageData);
}