package com.kpl.registration.repository;

import com.kpl.registration.entity.PlayerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface PlayerRepo2024 extends JpaRepository<PlayerRegistration, Long> {

    @Transactional
    @Modifying
    @Query(value="update player_registration_six  set image= :imageBytes WHERE reg_id = :i", nativeQuery = true)
    void updateImage(int i, byte[] imageBytes);

    @Transactional
    @Modifying
    @Query(value = "update player_registration_six set sold_amount=?2 , sold_team=?3 , sold_time=?4  where reg_id=?1", nativeQuery = true)
    void updateSoldamountAndTeam(Long regID, Long soldAmount, String soldTeam, LocalDateTime updationTime);

    @Query(value = "SELECT count(*) FROM player_registration_six where player_location_category='Overseas' and sold_team=?1", nativeQuery = true)
    Long countOfOverSeasPlayer(String soldTeam);

    @Query(value = "SELECT count(*) FROM player_registration_six where player_location_category='Local' and sold_team=?1", nativeQuery = true)
    Long countOfLocalPlayer(String soldTeam);

    @Query(value = "SELECT sum(sold_amount) FROM player_registration_six where sold_team=?1", nativeQuery = true)
    Long totalMoneySpend(String soldTeam);

    @Query(value = "SELECT * FROM player_registration_six where sold_team is not null ORDER BY sold_time desc", nativeQuery = true)
    List<PlayerRegistration> findBySoldUpdateTime();

    @Query(value = "select * from player_registration_six  where sold_team=?1 order by sold_amount DESC", nativeQuery = true)
    List<PlayerRegistration> findbyTeam(String soldTeam);

    @Query(value = "select * from player_registration_six where paid='Yes' and reg_id between ?1 and ?2 and category=?3 order by reg_id", nativeQuery = true)
    List<PlayerRegistration> findAllPlayer();
}
