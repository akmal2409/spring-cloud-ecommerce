CREATE TABLE IF NOT EXISTS `inventory-service`.`inventory` (
    id BIGINT NOT NULL auto_increment,
    sku_code VARCHAR(255),
    stock INT,
    PRIMARY KEY (id)
);
