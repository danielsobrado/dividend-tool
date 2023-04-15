CREATE TABLE operations (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  ticker VARCHAR(255),
  operation_type VARCHAR(255),
  quantity DECIMAL(19,4),
  price DECIMAL(19,4),
  portfolio_id BIGINT,
  created_at DATETIME,
  updated_at DATETIME,
  FOREIGN KEY (portfolio_id) REFERENCES portfolios (id)
);
