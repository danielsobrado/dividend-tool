CREATE TABLE portfolios (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  user_id BIGINT,
  created_at DATETIME,
  updated_at DATETIME,
  FOREIGN KEY (user_id) REFERENCES users (id)
);

