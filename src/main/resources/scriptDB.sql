CREATE DATABASE ZoomarketTest;

CREATE TABLE product_warehouse
(
    id        SERIAL NOT NULL,
    available BIGINT,
    sold      BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE product
(
    id                SERIAL NOT NULL,
    name              VARCHAR(50),
    description       VARCHAR(255),
    photo             VARCHAR(255),
    price             double precision,
    product_warehouse BIGINT REFERENCES product_warehouse (id),
    PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id      SERIAL       NOT NULL,
    name    VARCHAR(50)  NOT NULL,
    mail    VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    comment VARCHAR(255),
    active  BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE orders_product
(
    product_id INT REFERENCES product (id),
    orders_id  INT REFERENCES orders (id),
    PRIMARY KEY (product_id, orders_id)
);

INSERT INTO product_warehouse (available, sold)
VALUES (100, 0),
       (100, 0),
       (100, 0),
       (100, 0),
       (100, 0),
       (100, 0),
       (100, 0);

INSERT INTO product(name, description, photo, price, product_warehouse)
VALUES ('Аквариум', 'Вместительный аквариум вместит до 20 рыбок.', '/img/acvarium.jpg', 5700, 1),
       ('Клетка для попугая', 'Широкая клетка, способная вместить большую птицу.', '/img/birdcage.jpg', 3300, 2),
       ('Жакет для собаки',
        'Легкая куртка для собак Chill Stopper предназначена для прогулок в холодную и ветреную погоду. Удобный и мягкий флисовый материал, который используется при изготовлении одежды для людей.',
        '/img/DogJacket.jpg', 3000, 3),
       ('Подарочный сертификат', 'Сертификат на 2000 рублей в нашем магазине.', '/img/giftcard.jpg', 2000, 4),
       ('Royale Conine',
        'Продукт оснащен всеми необходимыми полезными витаминами, что так нужны собаке. Примерно 20 порций.',
        '/img/royalConin.jpg', 6767, 5),
       ('Когтеточка', 'Займет вашу кошку на долгое время.', '/img/kogtitochka.jpg', 700, 6),
       ('Домик для хомяка', 'Простой домик для хомяка.', '/img/houseHomyak.jpg', 1500, 7);

INSERT INTO orders (id, name, mail, address, comment, active)
VALUES ('Иван Ююкин', 'ivan.yuyuk@gmail.com', 'Лыткина 10', 'Оставьте посылку у сторожа на вахте.', TRUE),
       ('Магнус Карслен', 'Magnus@gmail.com', 'New-York', 'Упакуйте посылку в двойной пакет!.', TRUE),
       ('Ян Непомнящий', 'iqachess@gmail.com', 'Москва Улица строителей 20',
        'Можно ли у вас приобрести вещи в кредит?.', TRUE);

INSERT into orders_product (product_id, orders_id)
VALUES (1, 1),
       (2, 1),
       (4, 2),
       (5, 2),
       (1, 2),
       (7, 3);
