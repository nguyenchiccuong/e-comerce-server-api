--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3
-- Dumped by pg_dump version 13.3

-- Started on 2021-07-08 09:38:07

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

DROP DATABASE ecommerce;
--
-- TOC entry 3136 (class 1262 OID 17088)
-- Name: ecommerce; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE ecommerce WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';


\connect ecommerce

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
-- TOC entry 200 (class 1259 OID 17273)
-- Name: brand_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.brand_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 17293)
-- Name: brands; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.brands (
    id integer NOT NULL,
    brand_name character varying(255) NOT NULL
);


--
-- TOC entry 211 (class 1259 OID 17298)
-- Name: categories; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.categories (
    id integer NOT NULL,
    category_name character varying(255) NOT NULL,
    parent_category_id integer
);


--
-- TOC entry 201 (class 1259 OID 17275)
-- Name: category_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.category_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 212 (class 1259 OID 17303)
-- Name: customers; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.customers (
    user_id bigint NOT NULL,
    address character varying(255),
    create_date timestamp without time zone NOT NULL,
    dob date,
    email character varying(255),
    name character varying(255) NOT NULL,
    phone_number character varying(255),
    sex boolean
);


--
-- TOC entry 213 (class 1259 OID 17311)
-- Name: employees; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.employees (
    user_id bigint NOT NULL,
    address character varying(255) NOT NULL,
    create_date timestamp without time zone NOT NULL,
    dob date NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    phone_number character varying(255) NOT NULL,
    sex boolean NOT NULL
);


--
-- TOC entry 202 (class 1259 OID 17277)
-- Name: order_detail_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.order_detail_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 214 (class 1259 OID 17319)
-- Name: order_details; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.order_details (
    id bigint NOT NULL,
    price bigint NOT NULL,
    quantity integer NOT NULL,
    order_id bigint NOT NULL,
    product_detail_id bigint NOT NULL
);


--
-- TOC entry 203 (class 1259 OID 17279)
-- Name: order_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.order_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 215 (class 1259 OID 17324)
-- Name: orders; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.orders (
    id bigint NOT NULL,
    address character varying(255) NOT NULL,
    create_date timestamp without time zone NOT NULL,
    payment_id character varying(255),
    payment_method character varying(255) NOT NULL,
    payment_status smallint NOT NULL,
    phone_number character varying(255) NOT NULL,
    receiver character varying(255) NOT NULL,
    status smallint NOT NULL,
    update_date timestamp without time zone NOT NULL,
    user_id bigint NOT NULL
);


--
-- TOC entry 204 (class 1259 OID 17281)
-- Name: origin_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.origin_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 216 (class 1259 OID 17332)
-- Name: origins; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.origins (
    id integer NOT NULL,
    country character varying(255) NOT NULL
);


--
-- TOC entry 205 (class 1259 OID 17283)
-- Name: product_detail_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.product_detail_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 217 (class 1259 OID 17337)
-- Name: product_details; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.product_details (
    id bigint NOT NULL,
    color character varying(255) NOT NULL,
    price bigint NOT NULL,
    quantity integer NOT NULL,
    product_id bigint NOT NULL
);


--
-- TOC entry 206 (class 1259 OID 17285)
-- Name: product_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.product_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 218 (class 1259 OID 17342)
-- Name: products; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.products (
    id bigint NOT NULL,
    create_date timestamp without time zone NOT NULL,
    description character varying(255),
    img smallint,
    material character varying(255),
    model character varying(255),
    product_name character varying(255) NOT NULL,
    size character varying(255),
    standard character varying(255),
    update_date timestamp without time zone,
    warranty smallint,
    weight real,
    brand_id integer,
    category_id integer NOT NULL,
    origin_id integer
);


--
-- TOC entry 207 (class 1259 OID 17287)
-- Name: review_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.review_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 219 (class 1259 OID 17350)
-- Name: reviews; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.reviews (
    id bigint NOT NULL,
    anonymous smallint NOT NULL,
    create_date timestamp without time zone NOT NULL,
    description character varying(255),
    img smallint,
    num_of_star smallint NOT NULL,
    status smallint NOT NULL,
    update_date timestamp without time zone,
    order_id bigint,
    product_detail_id bigint NOT NULL,
    user_id bigint NOT NULL
);


--
-- TOC entry 208 (class 1259 OID 17289)
-- Name: role_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.role_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 220 (class 1259 OID 17355)
-- Name: roles; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.roles (
    id smallint NOT NULL,
    role_name character varying(60) NOT NULL
);


--
-- TOC entry 209 (class 1259 OID 17291)
-- Name: user_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.user_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 221 (class 1259 OID 17360)
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    password character varying(255) NOT NULL,
    status smallint NOT NULL,
    username character varying(255) NOT NULL,
    role_id smallint NOT NULL
);


--
-- TOC entry 3119 (class 0 OID 17293)
-- Dependencies: 210
-- Data for Name: brands; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.brands (id, brand_name) VALUES (1, 'Elip');
INSERT INTO public.brands (id, brand_name) VALUES (2, 'Robo');
INSERT INTO public.brands (id, brand_name) VALUES (3, 'Thượng đình');
INSERT INTO public.brands (id, brand_name) VALUES (4, 'Yonex');


--
-- TOC entry 3120 (class 0 OID 17298)
-- Dependencies: 211
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (1, 'Bóng bàn', NULL);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (3, 'Vợt bóng bàn', 1);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (4, 'Quả bòng bàn', 1);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (5, 'Máy bắn bóng', 1);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (6, 'Phụ kiện bóng bàn', 1);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (7, 'Ghế massage', NULL);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (8, 'Ghế massage bán chạy nhất', 7);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (9, 'Ghế dòng thương gia', 7);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (10, 'Ghế massage phổ thông', 7);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (11, 'Ghế massage thanh lý', 7);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (12, 'Máy chạy bộ', NULL);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (13, 'Máy chạy bộ phòng thương gia', 12);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (14, 'Máy chạy bộ bán chạy nhất', 12);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (15, 'Máy chạy bộ dòng phổ thông', 12);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (16, 'Máy chạy bộ thanh lý', 12);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (17, 'Máy massage', NULL);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (18, 'Máy massage bụng', 17);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (19, 'Máy massage chân', 17);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (20, 'Máy massage đầu - mắt', 17);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (21, 'Đệm - Nệm massage', 17);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (22, 'Máy tập ngoài trời', NULL);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (23, 'Máy công viên phổ thông', 22);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (24, 'Máy công viên dự án', 22);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (25, 'Máy tập phòng GYM', NULL);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (26, 'Máy tập GYM Robo', 25);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (27, 'Máy tập GYM Actini', 25);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (28, 'Máy tập GYM PLutoni', 25);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (29, 'Máy tập GYM Strength', 25);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (30, 'Dàn tạ nhiều mặt', 25);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (31, 'Phụ kiện GYM', 25);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (32, 'Máy tập tại nhà', NULL);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (33, 'Ghế tập tạ', 32);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (34, 'Dàn ghế tập tạ đa năng', 32);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (35, 'Thiết bị thi đấu', NULL);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (36, 'Cầu lông', 35);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (37, 'Bóng đá', 35);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (38, 'Bóng rổ', 35);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (39, 'Bóng chuyền', 35);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (40, 'Xe đạp tập', NULL);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (41, 'Xe đạp tập bán chạy nhất', 40);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (42, 'Xe đạp tập dòng thương gia', 40);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (43, 'Xe đạp tập phổ thông', 40);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (44, 'Xe đạp tập thanh lý', 40);
INSERT INTO public.categories (id, category_name, parent_category_id) VALUES (2, 'Bàn bóng bàn', 1);


--
-- TOC entry 3121 (class 0 OID 17303)
-- Dependencies: 212
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3122 (class 0 OID 17311)
-- Dependencies: 213
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.employees (user_id, address, create_date, dob, email, name, phone_number, sex) VALUES (36, 'Q.1', '2021-07-06 01:06:29', '1999-12-28', 'altgutha1@monsaustraliaa.com', 'employee', '1234567', false);
INSERT INTO public.employees (user_id, address, create_date, dob, email, name, phone_number, sex) VALUES (37, 'Q.2', '2021-07-07 01:06:29', '1999-11-17', 'altgutha2@monsaustraliaa.com', 'manager', '1234576', true);
INSERT INTO public.employees (user_id, address, create_date, dob, email, name, phone_number, sex) VALUES (38, 'Q.3', '2021-07-08 01:06:29', '1999-12-28', 'altgutha3@monsaustraliaa.com', 'admin', '7654321', false);


--
-- TOC entry 3123 (class 0 OID 17319)
-- Dependencies: 214
-- Data for Name: order_details; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3124 (class 0 OID 17324)
-- Dependencies: 215
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3125 (class 0 OID 17332)
-- Dependencies: 216
-- Data for Name: origins; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.origins (id, country) VALUES (1, 'Đài Loan');
INSERT INTO public.origins (id, country) VALUES (2, 'Mỹ');
INSERT INTO public.origins (id, country) VALUES (3, 'Thái Lan');
INSERT INTO public.origins (id, country) VALUES (4, 'Trung Quốc');
INSERT INTO public.origins (id, country) VALUES (5, 'Việt Nam');


--
-- TOC entry 3126 (class 0 OID 17337)
-- Dependencies: 217
-- Data for Name: product_details; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.product_details (id, color, price, quantity, product_id) VALUES (1, 'Đen', 3000000, 20, 1);
INSERT INTO public.product_details (id, color, price, quantity, product_id) VALUES (2, 'Đỏ', 2150000, 30, 1);
INSERT INTO public.product_details (id, color, price, quantity, product_id) VALUES (3, 'Đỏ', 3000000, 40, 2);
INSERT INTO public.product_details (id, color, price, quantity, product_id) VALUES (4, 'Tím', 2999000, 0, 2);
INSERT INTO public.product_details (id, color, price, quantity, product_id) VALUES (5, 'Đen', 1750000, 50, 3);
INSERT INTO public.product_details (id, color, price, quantity, product_id) VALUES (6, 'Hồng', 1800000, 60, 3);
INSERT INTO public.product_details (id, color, price, quantity, product_id) VALUES (7, 'Đen', 2100000, 70, 4);
INSERT INTO public.product_details (id, color, price, quantity, product_id) VALUES (8, 'Trắng', 2000000, 80, 4);
INSERT INTO public.product_details (id, color, price, quantity, product_id) VALUES (9, 'Lục', 1600000, 15, 5);
INSERT INTO public.product_details (id, color, price, quantity, product_id) VALUES (10, 'Xanh', 1500000, 16, 5);


--
-- TOC entry 3127 (class 0 OID 17342)
-- Dependencies: 218
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.products (id, create_date, description, img, material, model, product_name, size, standard, update_date, warranty, weight, brand_id, category_id, origin_id) VALUES (1, '2021-07-06 14:22:05', '<p>Vợt cầu lông Yonex Voltric 1 DG kết hợp sức mạnh đáng kinh ngạc và khả năng xử lý vợt nhanh lần đầu tiên, VOLTRIC là cây vợt hoàn hảo cho những người chơi tìm kiếm hiệu suất toàn diện.</p>', 3, 'Graphite', 'VT1DG', 'Vợt Cầu Lông Yonex Voltric 1DG', 'G3', NULL, NULL, 365, 88, 4, 36, 1);
INSERT INTO public.products (id, create_date, description, img, material, model, product_name, size, standard, update_date, warranty, weight, brand_id, category_id, origin_id) VALUES (2, '2021-07-06 14:23:05', '<p>Vợt cầu lông Yonex Nanoray Tour 7700 New 2019 là cây vợt nhẹ đầu, thân dẽo rất dễ để người chơi làm quen và phát huy hết được năng lực của mình. Vợt thích hợp với người chơi điều cầu, phản tạt.</p>', 1, 'Graphite', 'ARC TUOR 7700', 'Vợt Cầu Lông Yonex Nanoray Tour 7700', 'G4', NULL, NULL, 365, 88, 4, 36, 2);
INSERT INTO public.products (id, create_date, description, img, material, model, product_name, size, standard, update_date, warranty, weight, brand_id, category_id, origin_id) VALUES (3, '2021-07-06 14:24:05', 'Vợt cầu lông Yonex Astrox 88D dòng vợt chính hãng của Yonex cam kết chất lượng vượt trội được nhiều tuyển thủ câu lông lựa chọn để luyện tập và thi đấu. Mua vợt Yonex gọi ngay 1800 6884 giao hành tận nhà. ', 3, 'Than chì', 'AX88D', 'Vợt Cầu Lông Yonex Astrox 88 D ', 'G5', NULL, NULL, 60, 88, 4, 36, 3);
INSERT INTO public.products (id, create_date, description, img, material, model, product_name, size, standard, update_date, warranty, weight, brand_id, category_id, origin_id) VALUES (4, '2021-07-06 14:25:05', 'Vợt cầu lông Yonex Z Force ll là sự kết hợp sức mạnh đáng kinh ngạc và khả năng xử lý vợt nhanh lần đầu tiên, Z Force ll  là cây vợt hoàn hảo cho những người chơi tìm kiếm hiệu suất toàn diện.', 3, 'NANOMETRICS', 'VTZF2', 'Vợt Cầu Lông Yonex Z Force II', 'G4', NULL, NULL, 90, 83, 4, 36, 4);
INSERT INTO public.products (id, create_date, description, img, material, model, product_name, size, standard, update_date, warranty, weight, brand_id, category_id, origin_id) VALUES (5, '2021-07-06 14:25:05', 'Bảng rổ treo tường EB02 được thiết kế vô cùng chắc chắn, bảng rổ được làm bằng nhựa Composite chịu nhiệt, chịu lực  đạt chuẩn, đẹp, chịu được thời tiết nắng mưa khắc nhiệt.', 1, 'Composite', 'Elip-EB02', 'Bảng rổ treo tường Elip EB02', '1200 x 900mm', 'NBA', NULL, 30, 7.5, 1, 38, 5);


--
-- TOC entry 3128 (class 0 OID 17350)
-- Dependencies: 219
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3129 (class 0 OID 17355)
-- Dependencies: 220
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.roles (id, role_name) VALUES (1, 'ROLE_CUSTOMER');
INSERT INTO public.roles (id, role_name) VALUES (2, 'ROLE_EMPLOYEE');
INSERT INTO public.roles (id, role_name) VALUES (3, 'ROLE_MANAGER');
INSERT INTO public.roles (id, role_name) VALUES (4, 'ROLE_ADMIN');
INSERT INTO public.roles (id, role_name) VALUES (5, 'ROLE_CUSTOMER_LOCKED');
INSERT INTO public.roles (id, role_name) VALUES (6, 'ROLE_EMPLOYEE_LOCKED');
INSERT INTO public.roles (id, role_name) VALUES (7, 'ROLE_MANAGER_LOCKED');
INSERT INTO public.roles (id, role_name) VALUES (9, 'ROLE_ADMIN_LOCKED');


--
-- TOC entry 3130 (class 0 OID 17360)
-- Dependencies: 221
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.users (id, password, status, username, role_id) VALUES (37, '$2a$10$.1R9XP.ET5JirY5gvLe1O.hwjpBvFjeBc4uSrfUGrFwGCOjKvBrjC', 1, 'user0002', 3);
INSERT INTO public.users (id, password, status, username, role_id) VALUES (38, '$2a$10$.1R9XP.ET5JirY5gvLe1O.hwjpBvFjeBc4uSrfUGrFwGCOjKvBrjC', 1, 'user0003', 4);
INSERT INTO public.users (id, password, status, username, role_id) VALUES (36, '$2a$10$.1R9XP.ET5JirY5gvLe1O.hwjpBvFjeBc4uSrfUGrFwGCOjKvBrjC', 1, 'user0001', 2);


--
-- TOC entry 3137 (class 0 OID 0)
-- Dependencies: 200
-- Name: brand_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.brand_sequence', 1, false);


--
-- TOC entry 3138 (class 0 OID 0)
-- Dependencies: 201
-- Name: category_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.category_sequence', 67, true);


--
-- TOC entry 3139 (class 0 OID 0)
-- Dependencies: 202
-- Name: order_detail_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.order_detail_sequence', 1, false);


--
-- TOC entry 3140 (class 0 OID 0)
-- Dependencies: 203
-- Name: order_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.order_sequence', 1, false);


--
-- TOC entry 3141 (class 0 OID 0)
-- Dependencies: 204
-- Name: origin_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.origin_sequence', 1, false);


--
-- TOC entry 3142 (class 0 OID 0)
-- Dependencies: 205
-- Name: product_detail_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.product_detail_sequence', 1, false);


--
-- TOC entry 3143 (class 0 OID 0)
-- Dependencies: 206
-- Name: product_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.product_sequence', 1, false);


--
-- TOC entry 3144 (class 0 OID 0)
-- Dependencies: 207
-- Name: review_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.review_sequence', 1, false);


--
-- TOC entry 3145 (class 0 OID 0)
-- Dependencies: 208
-- Name: role_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.role_sequence', 4, true);


--
-- TOC entry 3146 (class 0 OID 0)
-- Dependencies: 209
-- Name: user_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.user_sequence', 36, true);


--
-- TOC entry 2918 (class 2606 OID 17297)
-- Name: brands brands_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.brands
    ADD CONSTRAINT brands_pkey PRIMARY KEY (id);


--
-- TOC entry 2922 (class 2606 OID 17302)
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- TOC entry 2926 (class 2606 OID 17310)
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2932 (class 2606 OID 17318)
-- Name: employees employees_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2938 (class 2606 OID 17323)
-- Name: order_details order_details_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_details
    ADD CONSTRAINT order_details_pkey PRIMARY KEY (id);


--
-- TOC entry 2942 (class 2606 OID 17331)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- TOC entry 2944 (class 2606 OID 17336)
-- Name: origins origins_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.origins
    ADD CONSTRAINT origins_pkey PRIMARY KEY (id);


--
-- TOC entry 2948 (class 2606 OID 17341)
-- Name: product_details product_details_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_details
    ADD CONSTRAINT product_details_pkey PRIMARY KEY (id);


--
-- TOC entry 2952 (class 2606 OID 17349)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- TOC entry 2954 (class 2606 OID 17354)
-- Name: reviews reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);


--
-- TOC entry 2958 (class 2606 OID 17359)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2924 (class 2606 OID 17371)
-- Name: categories uk41g4n0emuvcm3qyf1f6cn43c0; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT uk41g4n0emuvcm3qyf1f6cn43c0 UNIQUE (category_name);


--
-- TOC entry 2928 (class 2606 OID 17373)
-- Name: customers uk6v6x92wb400iwh6unf5rwiim4; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT uk6v6x92wb400iwh6unf5rwiim4 UNIQUE (phone_number);


--
-- TOC entry 2960 (class 2606 OID 17389)
-- Name: roles uk716hgxp60ym1lifrdgp67xt5k; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT uk716hgxp60ym1lifrdgp67xt5k UNIQUE (role_name);


--
-- TOC entry 2956 (class 2606 OID 17387)
-- Name: reviews ukax0dnqwsuwq42709men8lm78f; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT ukax0dnqwsuwq42709men8lm78f UNIQUE (order_id, product_detail_id, user_id);


--
-- TOC entry 2934 (class 2606 OID 17377)
-- Name: employees ukg6512s2t9cous2oxa17he4irp; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT ukg6512s2t9cous2oxa17he4irp UNIQUE (phone_number);


--
-- TOC entry 2920 (class 2606 OID 17369)
-- Name: brands ukgds2u6k2vfeo1tkrtgwcyqj36; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.brands
    ADD CONSTRAINT ukgds2u6k2vfeo1tkrtgwcyqj36 UNIQUE (brand_name);


--
-- TOC entry 2946 (class 2606 OID 17383)
-- Name: origins uki5s4smn616kbjd2wq4as7frox; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.origins
    ADD CONSTRAINT uki5s4smn616kbjd2wq4as7frox UNIQUE (country);


--
-- TOC entry 2936 (class 2606 OID 17379)
-- Name: employees ukj9xgmd0ya5jmus09o0b8pqrpb; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT ukj9xgmd0ya5jmus09o0b8pqrpb UNIQUE (email);


--
-- TOC entry 2950 (class 2606 OID 17385)
-- Name: product_details ukk0p7qfjfec62m4p4fclrk5670; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_details
    ADD CONSTRAINT ukk0p7qfjfec62m4p4fclrk5670 UNIQUE (product_id, color);


--
-- TOC entry 2940 (class 2606 OID 17381)
-- Name: order_details ukpoi7jp1yyis7makpb233jnpjn; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_details
    ADD CONSTRAINT ukpoi7jp1yyis7makpb233jnpjn UNIQUE (order_id, product_detail_id);


--
-- TOC entry 2962 (class 2606 OID 17391)
-- Name: users ukr43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- TOC entry 2930 (class 2606 OID 17375)
-- Name: customers ukrfbvkrffamfql7cjmen8v976v; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT ukrfbvkrffamfql7cjmen8v976v UNIQUE (email);


--
-- TOC entry 2964 (class 2606 OID 17367)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2974 (class 2606 OID 17437)
-- Name: products fk1u1t8sglrimm443lswfdh5sa2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk1u1t8sglrimm443lswfdh5sa2 FOREIGN KEY (origin_id) REFERENCES public.origins(id);


--
-- TOC entry 2970 (class 2606 OID 17417)
-- Name: orders fk32ql8ubntj5uh44ph9659tiih; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk32ql8ubntj5uh44ph9659tiih FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2967 (class 2606 OID 17402)
-- Name: employees fk69x3vjuy1t5p18a5llb8h2fjx; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT fk69x3vjuy1t5p18a5llb8h2fjx FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2965 (class 2606 OID 17392)
-- Name: categories fk9il7y6fehxwunjeepq0n7g5rd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT fk9il7y6fehxwunjeepq0n7g5rd FOREIGN KEY (parent_category_id) REFERENCES public.categories(id);


--
-- TOC entry 2972 (class 2606 OID 17427)
-- Name: products fka3a4mpsfdf4d2y6r8ra3sc8mv; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fka3a4mpsfdf4d2y6r8ra3sc8mv FOREIGN KEY (brand_id) REFERENCES public.brands(id);


--
-- TOC entry 2977 (class 2606 OID 17452)
-- Name: reviews fkcgy7qjc1r99dp117y9en6lxye; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT fkcgy7qjc1r99dp117y9en6lxye FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2976 (class 2606 OID 17447)
-- Name: reviews fkdii2jr3liylnwp5l6xowl2qyk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT fkdii2jr3liylnwp5l6xowl2qyk FOREIGN KEY (product_detail_id) REFERENCES public.product_details(id);


--
-- TOC entry 2968 (class 2606 OID 17407)
-- Name: order_details fkjyu2qbqt8gnvno9oe9j2s2ldk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_details
    ADD CONSTRAINT fkjyu2qbqt8gnvno9oe9j2s2ldk FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- TOC entry 2969 (class 2606 OID 17412)
-- Name: order_details fkl9w7hjxo9qo8s0glyr4ngkm1d; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_details
    ADD CONSTRAINT fkl9w7hjxo9qo8s0glyr4ngkm1d FOREIGN KEY (product_detail_id) REFERENCES public.product_details(id);


--
-- TOC entry 2971 (class 2606 OID 17422)
-- Name: product_details fknfvvq3meg4ha3u1bju9k4is3r; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_details
    ADD CONSTRAINT fknfvvq3meg4ha3u1bju9k4is3r FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- TOC entry 2973 (class 2606 OID 17432)
-- Name: products fkog2rp4qthbtt2lfyhfo32lsw9; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fkog2rp4qthbtt2lfyhfo32lsw9 FOREIGN KEY (category_id) REFERENCES public.categories(id);


--
-- TOC entry 2978 (class 2606 OID 17457)
-- Name: users fkp56c1712k691lhsyewcssf40f; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkp56c1712k691lhsyewcssf40f FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 2975 (class 2606 OID 17442)
-- Name: reviews fkqwgq1lxgahsxdspnwqfac6sv6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT fkqwgq1lxgahsxdspnwqfac6sv6 FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- TOC entry 2966 (class 2606 OID 17397)
-- Name: customers fkrh1g1a20omjmn6kurd35o3eit; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT fkrh1g1a20omjmn6kurd35o3eit FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2021-07-08 09:38:08

--
-- PostgreSQL database dump complete
--

