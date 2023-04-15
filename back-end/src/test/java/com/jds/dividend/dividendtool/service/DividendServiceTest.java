package com.jds.dividend.dividendtool.service;

import com.jds.dividend.dividendtool.config.AppConfig;
import com.jds.dividend.dividendtool.model.Dividend;
import com.jds.dividend.dividendtool.repository.DividendRepository;
import com.jds.dividend.dividendtool.scraper.NasdaqScraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DividendServiceTest {

    @Mock
    private DividendRepository dividendRepository;

    @InjectMocks
    private DividendService dividendService;

    private AppConfig appConfig;

    @BeforeEach
    void setUp() {
        appConfig = new AppConfig();
        appConfig.setDividendScraperUrl("https://www.example.com/{ticker}");
        dividendService = new DividendService(dividendRepository, null, appConfig);
    }

    @Test
    void parseHtmlAndSaveDividends() throws IOException {
        String ticker = "AAPL";
        String url = appConfig.getDividendScraperUrl().replace("{ticker}", ticker);

        Path path = Paths.get("src/test/resources/example_dividend_page.html");
        File file = path.toFile();
        Document doc = Jsoup.parse(file, "UTF-8", url);

        NasdaqScraper nasdaqScraper = new NasdaqScraper(appConfig);
        nasdaqScraper.parseHtmlAndSaveDividends(doc, ticker, url)
                .forEach(dividend -> {
                    verify(dividendRepository, times(1)).save(dividend);
                    assertDividend(dividend);
                });
    }

    private void assertDividend(Dividend dividend) {
        assertEquals(LocalDate.parse("05/17/2023", dividendService.formatter), dividend.getExEffDate());
        assertEquals("CASH", dividend.getType());
        assertEquals(new BigDecimal("0.68"), dividend.getCashAmount());
        assertEquals(LocalDate.parse("03/14/2023", dividendService.formatter), dividend.getDeclarationDate());
        assertEquals(LocalDate.parse("05/18/2023", dividendService.formatter), dividend.getRecordDate());
        assertEquals(LocalDate.parse("06/08/2023", dividendService.formatter), dividend.getPaymentDate());
    }
}
