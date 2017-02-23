-- Database: anserbackend

-- DROP DATABASE anserbackend;

CREATE DATABASE anserbackend
  WITH OWNER = augusto
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;

-- Table: customer

-- DROP TABLE customer;

CREATE TABLE customer
(
  id serial NOT NULL,
  name character varying(100) NOT NULL,
  age integer NOT NULL,
  CONSTRAINT customer_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE customer
  OWNER TO augusto;


-- Table: sale

-- DROP TABLE sale;

CREATE TABLE sale
(
  id serial NOT NULL,
  customer_id integer NOT NULL,
  date timestamp without time zone NOT NULL,
  amount numeric NOT NULL,
  external_id text,
  CONSTRAINT sale_pkey PRIMARY KEY (id),
  CONSTRAINT sale_customer_id_fkey FOREIGN KEY (customer_id)
      REFERENCES customer (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE sale
  OWNER TO augusto;