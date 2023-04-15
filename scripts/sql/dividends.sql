CREATE TABLE dividends (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  ex_eff_date DATE NOT NULL,
  type VARCHAR(255) NOT NULL,
  cash_amount DECIMAL(19,4),
  declaration_date DATE,
  record_date DATE,
  payment_date DATE,
  source VARCHAR(255),
  ticker VARCHAR(255),
  exchange VARCHAR(255),
  created_at DATETIME,
  updated_at DATETIME
);
