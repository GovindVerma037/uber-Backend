package com.project.uber.repository;

import com.project.uber.entity.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip,Long> {
 // jpql query to find trip by distance
//    @Query("select t from Trip t where t.distance = ?1 ")   // ?1 first parameter , (= : distance)
//    List<Trip> findTripByDistance(String distance);


    @Query("select t from Trip t where t.fare >= :fare")
    List<Trip> findTripByFare(@Param("fare") double fare);



    // native sql
   @Query(value="select * from trip where fare = :fare",nativeQuery = true)
    List<Trip> findByFare(@Param("fare") double fare);


   // drived
   List<Trip> findByDistanceGreaterThan(int distance);



   Page<Trip> findAll(Pageable pageable);
}
