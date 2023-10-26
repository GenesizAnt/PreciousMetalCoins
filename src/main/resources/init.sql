CREATE TABLE Coins(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    denomination  INT,
    metal         VARCHAR(100) NOT NULL,
    weight        DECIMAL,
    catalogNumber VARCHAR(100) NOT NULL,
    cost          INT
);

INSERT INTO coins (name, denomination, metal, weight, catalog_number, cost)
VALUES ('Веселая карусель № 1', 3, 'серебро 925/1000', 31.1, '5111-0476', 6500);

INSERT INTO coins (name, denomination, metal, weight, catalog_number, cost)
VALUES ('Достоевский-21', 2, 'серебро 925/1000', 15.55, '5110-0168', 2500);

INSERT INTO coins (name, denomination, metal, weight, catalog_number, cost)
VALUES ('Некрасов-21', 2, 'серебро 925/1000', 15.55, '5110-0169', 2500);
