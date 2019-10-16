package com.openclassrooms.project5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.openclassrooms.project5.domain.Firestation;

import java.util.List;

@Repository
public interface FirestationRepository extends JpaRepository<Firestation,Long> {

     List<Firestation> findAllByOrderByIdDesc();
}