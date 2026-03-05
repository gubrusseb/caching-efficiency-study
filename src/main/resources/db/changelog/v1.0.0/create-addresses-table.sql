-- Адреса доставки
CREATE TABLE addresses (
   id BIGSERIAL PRIMARY KEY,
   user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
   line1 VARCHAR(255) NOT NULL,
   line2 VARCHAR(255),
   city VARCHAR(100) NOT NULL,
   postal_code VARCHAR(20),
   country VARCHAR(100) DEFAULT 'Россия',
   is_default BOOLEAN DEFAULT false,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
