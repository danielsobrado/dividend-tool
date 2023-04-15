package com.jds.dividend.dividendtool.service;

import com.jds.dividend.dividendtool.model.Holding;
import com.jds.dividend.dividendtool.repository.HoldingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HoldingService {

    private final HoldingRepository holdingRepository;

    public List<Holding> findAll() {
        return holdingRepository.findAll();
    }

    public Optional<Holding> findById(Long id) {
        return holdingRepository.findById(id);
    }

    public Holding save(Holding holding) {
        return holdingRepository.save(holding);
    }

    public Optional<Holding> update(Long id, Holding holding) {
        return holdingRepository.findById(id)
                .map(existingHolding -> {
                    holding.setId(existingHolding.getId());
                    return holdingRepository.save(holding);
                });
    }

    public void delete(Long id) {
        holdingRepository.deleteById(id);
    }
}
