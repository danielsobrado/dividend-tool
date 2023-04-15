package com.jds.dividend.dividendtool.scraper;

import com.jds.dividend.dividendtool.model.Dividend;

import java.io.IOException;
import java.util.List;

public interface DividendScraper {
    List<Dividend> scrapeDividends(String ticker) throws IOException;
}
