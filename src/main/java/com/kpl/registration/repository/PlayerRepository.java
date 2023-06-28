 package com.kpl.registration.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kpl.registration.entity.PlayerInfo;

@Repository
 public interface PlayerRepository extends JpaRepository<PlayerInfo, Long> {

 @Query(value = "select * from player_registration where email_id=?1 or CAST(ph_no AS VARCHAR)=?1",nativeQuery = true)
 PlayerInfo findByMailOrPhNumber(String searchParam);

 @Query(value = "select ph_no from player_registration where ph_no=?1",nativeQuery = true)
 Long findByPhNumber(Long phNo);

 @Query(value = "select email_id from player_registration where email_id=?1",nativeQuery = true)
 String findByEmailID(String emailId);

 @Query(value = "select * from player_registration where generue=?1 order by registration_id",nativeQuery = true)
 List<PlayerInfo> findbyGenerue(String generue);

 @Query(value = "select image from player_registration where registration_id=?1",nativeQuery = true)
 byte[] findByregistrationId(Long registrationId);

 @Query(value = "select image from player_registration where generue=?1 order by registration_id",nativeQuery = true)
 List<byte[]> findAllImageByGenerue(String generue);

 @Transactional
 @Modifying
 @Query(value = "update player_registration set generue='List A' where registration_id in(?1)",nativeQuery = true)
 void updatePlayerCategory(List<Long> registartionIDS);

 @Query(value = "select * from player_registration order by registration_id",nativeQuery = true)
 List<PlayerInfo> findAllPlayer();

 @Query(value = "select doc_image from player_registration order by registration_id",nativeQuery = true)
 List<byte[]> findAllDoc();

 }