package com.jds.dividend.dividendtool.repository;

import com.jds.dividend.dividendtool.model.Dividend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DividendRepository extends JpaRepository<Dividend, Long> {
    List<Dividend> findByTicker(String ticker);
}
