-- Товары
CREATE TABLE products (
      id BIGSERIAL PRIMARY KEY,
      category_id BIGINT REFERENCES categories(id),
      sku VARCHAR(50) UNIQUE NOT NULL,
      name VARCHAR(255) NOT NULL,
      slug VARCHAR(255) UNIQUE NOT NULL,
      description TEXT,
      price DECIMAL(10,2) NOT NULL,
      cost_price DECIMAL(10,2),
      quantity INTEGER DEFAULT 0,
      reserved_quantity INTEGER DEFAULT 0,
      weight DECIMAL(10,3),
      is_active BOOLEAN DEFAULT true,
      is_featured BOOLEAN DEFAULT false,
      views_count BIGINT DEFAULT 0,
      purchases_count BIGINT DEFAULT 0,
      rating DECIMAL(3,2) DEFAULT 0,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Изображения товаров
CREATE TABLE product_images (
        id BIGSERIAL PRIMARY KEY,
        product_id BIGINT REFERENCES products(id) ON DELETE CASCADE,
        image_url VARCHAR(500) NOT NULL,
        sort_order INTEGER DEFAULT 0,
        is_main BOOLEAN DEFAULT false,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Характеристики товаров
CREATE TABLE product_attributes (
        id BIGSERIAL PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        type VARCHAR(50) DEFAULT 'string'
);

-- Связь товаров с характеристиками
CREATE TABLE product_attribute_link (
        id BIGSERIAL PRIMARY KEY,
        product_id BIGINT REFERENCES products(id) ON DELETE CASCADE,
        attribute_id BIGINT REFERENCES product_attributes(id),
        value TEXT NOT NULL
);
