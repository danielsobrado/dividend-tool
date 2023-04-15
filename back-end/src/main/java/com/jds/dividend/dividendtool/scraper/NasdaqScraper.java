package com.jds.dividend.dividendtool.scraper;

import com.jds.dividend.dividendtool.config.AppConfig;
import com.jds.dividend.dividendtool.model.Dividend;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NasdaqScraper implements DividendScraper {
    private final AppConfig appConfig;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public NasdaqScraper(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public List<Dividend> scrapeDividends(String ticker) throws IOException {
        String url = appConfig.getDividendScraperUrl().replace("{ticker}", ticker);
        Document doc = Jsoup.connect(url).get();
        return parseHtmlAndSaveDividends(doc, ticker, url);
    }

    public List<Dividend> parseHtmlAndSaveDividends(Document doc, String ticker, String url) {
        List<Dividend> dividends = new ArrayList<>();
        Elements rows = doc.select("table.dividend-history__table tbody tr.dividend-history__row--data");

        for (Element row : rows) {
            if (row.childrenSize() >= 6 && !row.child(0).text().isBlank()) {
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

                dividends.add(dividend);
            }
        }

        return dividends;
    }

}
