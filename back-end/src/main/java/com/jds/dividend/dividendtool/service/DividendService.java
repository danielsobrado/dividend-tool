package com.jds.dividend.dividendtool.service;

import com.jds.dividend.dividendtool.config.AppConfig;
import com.jds.dividend.dividendtool.model.Dividend;
import com.jds.dividend.dividendtool.model.Holding;
import com.jds.dividend.dividendtool.repository.DividendRepository;
import com.jds.dividend.dividendtool.repository.HoldingRepository;
import com.jds.dividend.dividendtool.scraper.DividendScraper;
import com.jds.dividend.dividendtool.scraper.NasdaqScraper;
import io.vavr.control.Try;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DividendService {

    private final DividendRepository dividendRepository;
    private final HoldingRepository holdingRepository;

    private final AppConfig appConfig;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @PostConstruct
    public void update() {
        List<Holding> holdings = holdingRepository.findAll();
        List<String> tickers = holdings.stream()
                .map(Holding::getTicker)
                .distinct()
                .collect(Collectors.toList());

        tickers.forEach(ticker -> {
            long initialDelay = randomDelay();
            long period = 30L * 24 * 60 * 60; // 30 days in seconds
            scheduler.scheduleAtFixedRate(() -> {
                Try.run(() -> scrapeDividendData(ticker))
                        .onFailure(ex -> log.error("Failed to scrape dividend data for ticker: {}", ticker, ex));
            }, initialDelay, period, TimeUnit.SECONDS);
        });
    }

    public void scrapeDividendData(String ticker) throws IOException {
        DividendScraper nasdaqScraper = new NasdaqScraper(appConfig);
        List<Dividend> dividends = nasdaqScraper.scrapeDividends(ticker);
        dividends.forEach(dividendRepository::save);
    }

    private Document fetchHtmlContent(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    void parseHtmlAndSaveDividends(Document doc, String ticker, String url) {
        Elements rows = doc.select("table.dividend-history__table tbody tr.dividend-history__row--data");

        for (Element row : rows) {
            Dividend dividend = Dividend.builder()
                    .exEffDate(LocalDate.parse(row.child(0).text(), formatter))
                    .type(row.child(1).text())
                    .cashAmount(new BigDecimal(row.child(2).text().substring(1)))
                    .declarationDate(LocalDate.parse(row.child(3).text(), formatter))
                    .recordDate(LocalDate.parse(row.child(4).text(), formatter))
                    .paymentDate(LocalDate.parse(row.child(5).text(), formatter))
                    .source(url)
                    .ticker(ticker)
                    .exchange("NASDAQ")
                    .build();

            dividendRepository.save(dividend);
        }
    }

    private long randomDelay() {
        int daysInMonth = LocalDate.now().lengthOfMonth();
        int randomDay = new Random().nextInt(daysInMonth) + 1;
        int randomHour = new Random().nextInt(24);
        int randomMinute = new Random().nextInt(60);
        int randomSecond = new Random().nextInt(60);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime scheduledTime = LocalDateTime.of(now.toLocalDate().withDayOfMonth(randomDay), LocalTime.of(randomHour, randomMinute, randomSecond));

        if (scheduledTime.isBefore(now)) {
            scheduledTime = scheduledTime.plusMonths(1);
        }

        return ChronoUnit.SECONDS.between(now, scheduledTime);
    }

    public List<Dividend> findAll() {
        return dividendRepository.findAll();
    }

    public Optional<Dividend> findById(Long id) {
        return dividendRepository.findById(id);
    }

    public Dividend save(Dividend dividend) {
        return dividendRepository.save(dividend);
    }

    public Optional<Dividend> update(Long id, Dividend dividend) {
        return dividendRepository.findById(id)
                .map(existingDividend -> {
                    dividend.setId(existingDividend.getId());
                    return dividendRepository.save(dividend);
                });
    }

    public void deleteById(Long id) {
        dividendRepository.deleteById(id);
    }

}
