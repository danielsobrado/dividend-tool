package com.jds.dividend.dividendtool.service;

import com.jds.dividend.dividendtool.model.Operation;
import com.jds.dividend.dividendtool.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;

    public List<Operation> findAll() {
        return operationRepository.findAll();
    }

    public Optional<Operation> findById(Long id) {
        return operationRepository.findById(id);
    }

    public Operation save(Operation operation) {
        return operationRepository.save(operation);
    }

    public Optional<Operation> update(Long id, Operation operation) {
        return operationRepository.findById(id)
                .map(existingOperation -> {
                    operation.setId(existingOperation.getId());
                    return operationRepository.save(operation);
                });
    }

    public void deleteById(Long id) {
        operationRepository.deleteById(id);
    }
}
