-- Insert categories
INSERT INTO category (description, name) VALUES
     ('Electronics and gadgets', 'Electronics'),
     ('Kitchen appliances and cookware', 'Kitchen'),
     ('Furniture and home decor', 'Home & Living'),
     ('Sports equipment and accessories', 'Sports & Outdoors'),
     ('Books and stationery', 'Books & Stationery');

-- Insert products
INSERT INTO products (description, name_product, available_quantity, price, category_id)
                                                                                VALUES
-- Electronics
    ('High-performance smartphone', 'SmartPhone X', 100, 799.99, 1),
    ('4K Ultra HD Smart TV', 'SmartTV 55"', 50, 599.99, 1),
    ('Noise-canceling wireless headphones', 'AudioPhonic Pro', 200, 249.99, 1),

    -- Kitchen
    ('Versatile stand mixer', 'KitchenMix 5000', 75, 299.99, 2),
    ('Stainless steel cookware set', 'CookMaster Set', 60, 199.99, 2),
    ('High-power blender', 'BlendPro X', 100, 129.99, 2),

    -- Home & Living
    ('Comfortable ergonomic office chair', 'ErgoChair Deluxe', 40, 249.99, 3),
    ('Modern coffee table', 'ModernLiving Table', 30, 179.99, 3),
    ('Soft area rug', 'CozyHome Rug 5x7', 50, 89.99, 3),

    -- Sports & Outdoors
    ('Professional basketball', 'ProBounce Ball', 150, 29.99, 4),
    ('Durable hiking backpack', 'TrailMaster 40L', 80, 79.99, 4),
    ('Adjustable dumbbell set', 'FlexWeight Set', 60, 199.99, 4),

    -- Books & Stationery
    ('Bestselling novel', 'The Midnight Chronicle', 200, 24.99, 5),
    ('Premium fountain pen', 'ScribeElite Pen', 100, 49.99, 5),
    ('Hardcover journal', 'CreativeMinds Notebook', 150, 19.99, 5);