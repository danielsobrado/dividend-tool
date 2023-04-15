package com.jds.dividend.dividendtool.service;

import com.jds.dividend.dividendtool.model.Portfolio;
import com.jds.dividend.dividendtool.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public List<Portfolio> findAll() {
        return portfolioRepository.findAll();
    }

    public Optional<Portfolio> findById(Long id) {
        return portfolioRepository.findById(id);
    }

    public Portfolio save(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }

    public Optional<Portfolio> update(Long id, Portfolio portfolio) {
        return portfolioRepository.findById(id)
                .map(existingPortfolio -> {
                    portfolio.setId(existingPortfolio.getId());
                    return portfolioRepository.save(portfolio);
                });
    }

    public void delete(Long id) {
        portfolioRepository.deleteById(id);
    }
}
