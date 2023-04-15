package com.jds.dividend.dividendtool.repository;

import com.jds.dividend.dividendtool.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
