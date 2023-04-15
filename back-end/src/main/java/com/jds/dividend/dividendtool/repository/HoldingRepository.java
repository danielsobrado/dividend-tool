package com.jds.dividend.dividendtool.repository;

import com.jds.dividend.dividendtool.model.Holding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldingRepository extends JpaRepository<Holding, Long> {
}
