package com.kpl.registration.repository;

import com.kpl.registration.entity.PlayerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerInfo, Long> {

	@Query(value = "select * from player_registration where email_id=?1 or CAST(ph_no AS VARCHAR)=?1 and password=?2", nativeQuery = true)
	PlayerInfo findByMailOrPhNumberandpassword(String id, String password);

	@Query(value = "select registration_id from player_registration where CAST(ph_no AS VARCHAR)=?1", nativeQuery = true)
	Long findByPhNu(String id);

	@Query(value = "select * from player_registration where email_id=?1 or CAST(ph_no AS VARCHAR)=?1 ", nativeQuery = true)
	PlayerInfo findByMailOrPhNumber(String id);

	@Query(value = "select ph_no from player_registration where ph_no=?1", nativeQuery = true)
	Long findByPhNumber(Long phNo);

	@Query(value = "select email_id from player_registration where email_id=?1", nativeQuery = true)
	String findByEmailID(String emailId);

	@Query(value = "select aadhar_no from player_registration where aadhar_no=?1", nativeQuery = true)
	String findByAadhaarID(Long aadharNo);

	@Query(value = "select * from player_registration where generue=?1 order by registration_id", nativeQuery = true)
	List<PlayerInfo> findbyGenerue(String generue);

	@Query(value = "select * from player_registration where generue=?1 and player_location_category=?2 and payment_validation='ok' order by registration_id", nativeQuery = true)
	List<PlayerInfo> findbyCategoryLocation(String category, String locaton);

	@Transactional
	@Modifying
	@Query(value = "update player_registration set generue='List A' where registration_id in(?1)", nativeQuery = true)
	void updatePlayerCategory(List<Long> registartionIDS);

	@Transactional
	@Modifying
	@Query(value = "update player_registration set payment_validation='ok' where registration_id in(?1)", nativeQuery = true)
	void paymentUpdate(List<Long> registartionIDS);

	@Query(value = "select * from player_registration where payment_validation='ok' order by registration_id", nativeQuery = true)
	List<PlayerInfo> findAllPlayer();

	@Query(value = "select registration_id from player_registration where payment_validation='ok' order by registration_id", nativeQuery = true)
	List<Long> findAllDocImageFrontRegID();

	@Query(value = "select pin_code from player_registration where ph_no=?1", nativeQuery = true)
	Long findByPinCode(Long pinCode);

	@Query(value = "select ph_no from player_registration where aadhar_no=?1", nativeQuery = true)
	Long findByAaddharNo(Long aadharNo);

	@Transactional
	@Modifying
	@Query(value = "update player_registration set password=?1 where ph_no=?2", nativeQuery = true)
	void updatePassword(String password, Long phNo);

	@Query(value = "SELECT * FROM player_registration where player_registration.registration_id=?1", nativeQuery = true)
	PlayerInfo findDataByregistrationId(Long regID);

	@Query(value = "SELECT * from player_registration p,doc_info d where p.registration_id=d.registration_id\r\n"
			+ "and p.registration_id=?1", nativeQuery = true)
	PlayerInfo findDataByregistrationIdLiveFeed(Long regID);

	@Transactional
	@Modifying
	@Query(value = "update player_registration set sold_amount=?2 , sold_team=?3 , sold_time=?4  where registration_id=?1", nativeQuery = true)
	void updateSoldamountAndTeam(Long regID, Long soldAmount, String soldTeam, LocalDateTime updationTime);

	@Query(value = "select * from player_registration  where sold_team=?1 order by player_first_name", nativeQuery = true)
	List<PlayerInfo> findbyTeam(String soldTeam);

	@Query(value = "SELECT distinct(sold_team)\r\n"
			+ "FROM player_registration where sold_team is not null", nativeQuery = true)
	LinkedList<String> getDistinctTeam();

	@Query(value = "SELECT count(*) FROM player_registration where player_location_category='Overseas' and sold_team=?1", nativeQuery = true)
	Long countOfOverSeasPlayer(String soldTeam);

	@Query(value = "SELECT count(*) FROM player_registration where player_location_category='Local' and sold_team=?1", nativeQuery = true)
	Long countOfLocalPlayer(String soldTeam);

	@Query(value = "SELECT sum(sold_amount) FROM player_registration where sold_team=?1", nativeQuery = true)
	Long totalMoneySpend(String soldTeam);

	@Query(value = "select * from player_registration where registration_id in(?1)", nativeQuery = true)
	List<PlayerInfo> findByRegistriondList(List<Long> registartionIDS);

	@Query(value = "SELECT * FROM player_registration where sold_team is not null ORDER BY sold_time desc", nativeQuery = true)
	List<PlayerInfo> findBySoldUpdateTime();

	@Query(value = "SELECT * FROM player_registration registration_time where registration_time < ?1 and registration_time > ?2\r\n"
			+ "order by registration_id", nativeQuery = true)
	List<PlayerInfo> todaySignedUpPlayerList(LocalDateTime todayTime, LocalDateTime yesterdayTime);

	@Query(value = "SELECT * FROM player_registration  where payment_validation is null order by registration_id", nativeQuery = true)
	List<PlayerInfo> paymentRem();

	@Query(value = "SELECT * FROM player_registration  where payment_validation is not null and registration_id between ?1 and ?2 order by registration_id", nativeQuery = true)
	List<PlayerInfo> paymentDone (Long lb,Long ub);
	
	
	@Query(value = "SELECT * FROM player_registration where registration_time < ?1 and registration_time >= ?2\r\n"
			+ "order by registration_id", nativeQuery = true)
	List<PlayerInfo> todaySignedUp15minPlayerList(LocalDateTime timeNow, LocalDateTime time15minBack);

	@Query(value = "SELECT count(ph_no) FROM player_registration  where ph_no=?1", nativeQuery = true)
	Long findCount(Long phNo);

	@Query(value = "SELECT * FROM player_registration  where sold_time < ?1 and sold_time >= ?2\r\n"
			+ "order by registration_id", nativeQuery = true)
	List<PlayerInfo> sellOnLast5min(LocalDateTime timeNow, LocalDateTime time5minBack);

	@Transactional
	@Modifying
	@Query(value = "update player_registration set generue='Emerging Player' where registration_id in(?1)", nativeQuery = true)
	void updateEmergingPlayer(List<Long> registartionIDS);

	@Transactional
	@Modifying
	@Query(value = "update player_registration set payment_validation='' where registration_id in(?1)", nativeQuery = true)
	void paymentUpdateRevoke(Long registartionIDS);
	
	@Query(value = "SELECT registration_id FROM public.player_registration WHERE date_of_birth > current_date - interval '19 years' ORDER BY registration_id asc", nativeQuery = true)
	List<Long> emergingPlayerList();

	@Query(value = "SELECT * FROM player_registration where player_registration.registration_id=?1", nativeQuery = true)
	PlayerInfo findInfo(Long regID);
}