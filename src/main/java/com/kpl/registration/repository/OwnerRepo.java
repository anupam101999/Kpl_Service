package com.kpl.registration.repository;

import java.util.LinkedList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kpl.registration.entity.OwnerInfo;

@Repository
public interface OwnerRepo extends JpaRepository<OwnerInfo, Long> {

	@Query(value = "select * from owner_information where team_name=?1", nativeQuery = true)
	Optional<OwnerInfo> ownerInformation(String teamName);

	@Query(value = "SELECT distinct(team_name)\r\n"
			+ "FROM owner_information", nativeQuery = true)
	LinkedList<String> getDistinctTeam();
}