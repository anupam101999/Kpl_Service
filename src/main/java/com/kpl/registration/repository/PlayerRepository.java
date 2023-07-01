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

	@Query(value = "select * from player_registration where email_id=?1 or CAST(ph_no AS VARCHAR)=?1 and password=?2", nativeQuery = true)
	PlayerInfo findByMailOrPhNumberandpassword(String id, String password);

	@Query(value = "select * from player_registration where email_id=?1 or CAST(ph_no AS VARCHAR)=?1 ", nativeQuery = true)
	PlayerInfo findByMailOrPhNumber(String id);

	@Query(value = "select ph_no from player_registration where ph_no=?1", nativeQuery = true)
	Long findByPhNumber(Long phNo);

	@Query(value = "select email_id from player_registration where email_id=?1", nativeQuery = true)
	String findByEmailID(String emailId);

	@Query(value = "select * from player_registration where generue=?1 order by registration_id", nativeQuery = true)
	List<PlayerInfo> findbyGenerue(String generue);

	@Query(value = "select image from player_registration where registration_id=?1", nativeQuery = true)
	byte[] findByregistrationId(Long registrationId);

	@Query(value = "select image from player_registration where generue=?1 order by registration_id", nativeQuery = true)
	List<byte[]> findAllImageByGenerue(String generue);

	@Transactional
	@Modifying
	@Query(value = "update player_registration set generue='List A' where registration_id in(?1)", nativeQuery = true)
	void updatePlayerCategory(List<Long> registartionIDS);

	@Transactional
	@Modifying
	@Query(value = "update player_registration set payment_validation='ok' where registration_id in(?1)", nativeQuery = true)
	void paymentUpdate(List<Long> registartionIDS);

	@Query(value = "select * from player_registration order by registration_id", nativeQuery = true)
	List<PlayerInfo> findAllPlayer();

	@Query(value = "select doc_image from player_registration order by registration_id", nativeQuery = true)
	List<byte[]> findAllDoc();

	@Query(value = "select pin_code from player_registration where ph_no=?1", nativeQuery = true)
	Long findByPinCode(Long pinCode);

	@Query(value = "select ph_no from player_registration where aadhar_no=?1", nativeQuery = true)
	Long findByAaddharNo(Long aadharNo);

	@Transactional
	@Modifying
	@Query(value = "update player_registration set password=?1 where ph_no=?2", nativeQuery = true)
	void updatePassword(String password, Long phNo);

	@Query(value = "select * from player_registration  where registration_id=?1", nativeQuery = true)
	PlayerInfo findDataByregistrationId(Long regID);

	@Transactional
	@Modifying
	@Query(value = "update player_registration set sold_amount=?2 and sold_team=?3  where registration_id=?1", nativeQuery = true)
	void updateSoldamountAndTeam(Long regID, Long soldAmount, String soldTeam);

	@Query(value = "select * from player_registration  where sold_team=?1", nativeQuery = true)
	List<PlayerInfo> findbyTeam(String soldTeam);

	@Query(value = "SELECT distinct(sold_team)\r\n" + "FROM player_registration", nativeQuery = true)
	List<String> getDistinctTeam();

	@Query(value = "SELECT count(*) FROM player_registration where player_location_category='Overseas' and sold_team=?1", nativeQuery = true)
	Long countOfOverSeasPlayer(String soldTeam);

	@Query(value = "SELECT count(*) FROM player_registration where player_location_category='Local' and sold_team=?1", nativeQuery = true)
	Long countOfLocalPlayer(String soldTeam);

	@Query(value = "SELECT sum(sold_amount) FROM player_registration where sold_team=?1", nativeQuery = true)
	Long totalMoneySpend(String soldTeam);

	@Query(value = "select * from player_registration where registration_id in(?1)", nativeQuery = true)
	List<PlayerInfo> findByRegistriondList(List<Long> registartionIDS);

//	@Transactional
//	@Modifying
//	@Query(value = "select registration_id from player_registration where registration_id not in (?1)", nativeQuery = true)
//	List<Long> findOutSideID(List<Long> idList);

}