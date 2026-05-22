--
-- PostgreSQL database dump
--

-- Dumped from database version 14.7 (Debian 14.7-1.pgdg110+1)
-- Dumped by pg_dump version 14.15 (Ubuntu 14.15-0ubuntu0.22.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: tomee; Type: SCHEMA; Schema: -; Owner: tomee
--

CREATE SCHEMA tomee;


ALTER SCHEMA tomee OWNER TO tomee;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: user_roles; Type: TABLE; Schema: tomee; Owner: tomee
--

CREATE TABLE tomee.user_roles (
    username character varying(255) NOT NULL,
    role_name character varying(255) NOT NULL
);


ALTER TABLE tomee.user_roles OWNER TO tomee;

--
-- Name: users; Type: TABLE; Schema: tomee; Owner: tomee
--

CREATE TABLE tomee.users (
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL
);


ALTER TABLE tomee.users OWNER TO tomee;

--
-- Data for Name: user_roles; Type: TABLE DATA; Schema: tomee; Owner: tomee
--

COPY tomee.user_roles (username, role_name) FROM stdin;
CSLMG26	ValidationUser
MKT01	ValidationUser
BUY01	ValidationUser
IT01	ValidationUser
CAT01	ValidationUser
ADV01	ValidationUser
DIG01	ValidationUser
CUSTENG01	ValidationUser
S01	ValidationUser
S29S38	ValidationUser
UFFPROMO01	ValidationUser
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: tomee; Owner: tomee
--

COPY tomee.users (username, password) FROM stdin;
CSLMG26	Esselunga1
MKT01	Esselunga1
BUY01	Esselunga1
IT01	Esselunga1
CAT01	Esselunga1
ADV01	Esselunga1
DIG01	Esselunga1
CUSTENG01	Esselunga1
S01	Esselunga1
S29S38	Esselunga1
UFFPROMO01	Esselunga1
\.


--
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: tomee; Owner: tomee
--

ALTER TABLE ONLY tomee.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (username, role_name);


--
-- Name: users users_pkey1; Type: CONSTRAINT; Schema: tomee; Owner: tomee
--

ALTER TABLE ONLY tomee.users
    ADD CONSTRAINT users_pkey1 PRIMARY KEY (username);


--
-- Name: user_roles user_roles_username_fkey; Type: FK CONSTRAINT; Schema: tomee; Owner: tomee
--

ALTER TABLE ONLY tomee.user_roles
    ADD CONSTRAINT user_roles_username_fkey FOREIGN KEY (username) REFERENCES tomee.users(username);


--
-- PostgreSQL database dump complete
--

