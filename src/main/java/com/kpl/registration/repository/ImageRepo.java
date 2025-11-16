 package com.kpl.registration.repository;

 import com.kpl.registration.entity.ImageInfo;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.jpa.repository.Query;
 import org.springframework.stereotype.Repository;
 @Repository
 public interface ImageRepo extends JpaRepository<ImageInfo, Long> {
 @Query(value = "select image from master_image_details where image_name=?1",nativeQuery = true)
 byte[] findByImageName(String name);

 }