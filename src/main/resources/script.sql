CREATE SCHEMA goods_schema;

Set search_path TO goods_schema;

CREATE TABLE item (
  id   BIGSERIAL PRIMARY KEY,
  code INTEGER NOT NULL UNIQUE
);

CREATE TABLE attribute (
  id      BIGSERIAL PRIMARY KEY,
  en_name VARCHAR(256) NOT NULL,
  ru_name VARCHAR(256) NOT NULL
);

CREATE TABLE item_attribute (
  item_id      BIGINT NOT NULL REFERENCES goods_schema.item (id),
  attribute_id BIGINT NOT NULL REFERENCES goods_schema.attribute (id)
);

CREATE TABLE item_translation (
  id       BIGSERIAL PRIMARY KEY,
  item_id  BIGINT       NOT NULL REFERENCES goods_schema.item (id),
  language VARCHAR(2)   NOT NULL,
  name     VARCHAR(256) NOT NULL
);

INSERT INTO goods_schema.item (code) VALUES
  (123);

INSERT INTO goods_schema.attribute (en_name, ru_name) VALUES
  ('Model', 'Модель'),
  ('Manufacture', 'Производитель'),
  ('Screen Size', 'Размер экрана');

INSERT INTO goods_schema.item_attribute (item_id, attribute_id) VALUES
  ((SELECT i.id
    FROM goods_schema.item i
    WHERE i.code = 123),
   (SELECT a.id
    FROM goods_schema.attribute a
    WHERE a.en_name = 'Model')),
  ((SELECT i.id
    FROM goods_schema.item i
    WHERE i.code = 123),
   (SELECT a.id
    FROM goods_schema.attribute a
    WHERE a.en_name = 'Manufacture')),
  ((SELECT i.id
    FROM goods_schema.item i
    WHERE i.code = 123),
   (SELECT a.id
    FROM goods_schema.attribute a
    WHERE a.en_name = 'Screen Size'));

INSERT INTO goods_schema.item_translation (item_id, language, name) VALUES
  ((SELECT i.id
    FROM goods_schema.item i
    WHERE i.code = 123),
   'EN', 'Telephone'),
  ((SELECT i.id
    FROM goods_schema.item i
    WHERE i.code = 123),
   'RU', 'Телефон');
