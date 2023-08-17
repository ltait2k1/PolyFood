package com.example.polyfood.repository;

import com.example.polyfood.models.Decentralization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDecentralizationRepository extends JpaRepository<Decentralization,Integer> {
}
