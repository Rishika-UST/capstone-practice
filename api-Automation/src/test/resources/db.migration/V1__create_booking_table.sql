CREATE TABLE bookings (
    id VARCHAR(100) PRIMARY KEY,
    pnr VARCHAR(50),
    emp_id VARCHAR(50),
    journey_type VARCHAR(20),
    inventory_id VARCHAR(100),
    state VARCHAR(50),
    refundable BOOLEAN,
    amount_paise INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);