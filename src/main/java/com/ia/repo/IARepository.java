package com.ia.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ia.model.PillsCounterVO;

@Repository
public interface IARepository extends JpaRepository<PillsCounterVO, Integer>{

}
