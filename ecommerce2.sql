--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3
-- Dumped by pg_dump version 13.3

-- Started on 2021-07-16 14:30:41

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

DROP DATABASE ecommerce2;
--
-- TOC entry 3198 (class 1262 OID 17464)
-- Name: ecommerce2; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE ecommerce2 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';


\connect ecommerce2

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
-- TOC entry 200 (class 1259 OID 17465)
-- Name: address_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.address_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 212 (class 1259 OID 17489)
-- Name: addresss; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.addresss (
    id bigint NOT NULL,
    city character varying(255),
    contact_name character varying(255),
    contact_number character varying(255),
    default_address boolean,
    detail character varying(255),
    district character varying(255),
    payment_address boolean,
    street_and_number character varying(255),
    tag character varying(255),
    ward character varying(255),
    organization_id integer,
    user_id bigint
);


--
-- TOC entry 201 (class 1259 OID 17467)
-- Name: brand_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.brand_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 213 (class 1259 OID 17497)
-- Name: brands; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.brands (
    id integer NOT NULL,
    brand_name character varying(255),
    organization_id integer NOT NULL
);


--
-- TOC entry 214 (class 1259 OID 17502)
-- Name: categories; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.categories (
    id integer NOT NULL,
    category_name character varying(255),
    parent_category_id integer
);


--
-- TOC entry 202 (class 1259 OID 17469)
-- Name: category_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.category_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 215 (class 1259 OID 17507)
-- Name: customers; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.customers (
    user_id bigint NOT NULL,
    create_date timestamp without time zone NOT NULL,
    dob date,
    email character varying(255),
    name character varying(255),
    phone_number character varying(255),
    sex boolean
);


--
-- TOC entry 216 (class 1259 OID 17515)
-- Name: employees; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.employees (
    user_id bigint NOT NULL,
    create_date timestamp without time zone NOT NULL,
    dob date NOT NULL,
    email character varying(255),
    name character varying(255),
    phone_number character varying(255),
    sex boolean NOT NULL
);


--
-- TOC entry 203 (class 1259 OID 17471)
-- Name: order_detail_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.order_detail_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 217 (class 1259 OID 17523)
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
-- TOC entry 204 (class 1259 OID 17473)
-- Name: order_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.order_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 218 (class 1259 OID 17528)
-- Name: orders; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.orders (
    id bigint NOT NULL,
    create_date timestamp without time zone NOT NULL,
    payment_id character varying(255),
    payment_method character varying(255),
    payment_status smallint NOT NULL,
    status smallint NOT NULL,
    update_date timestamp without time zone NOT NULL,
    address_id bigint NOT NULL,
    user_id bigint NOT NULL
);


--
-- TOC entry 205 (class 1259 OID 17475)
-- Name: organization_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.organization_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 219 (class 1259 OID 17536)
-- Name: organizations; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.organizations (
    id integer NOT NULL,
    organization_name character varying(255),
    img smallint
);


--
-- TOC entry 206 (class 1259 OID 17477)
-- Name: origin_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.origin_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 220 (class 1259 OID 17541)
-- Name: origins; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.origins (
    id integer NOT NULL,
    country character varying(255)
);


--
-- TOC entry 207 (class 1259 OID 17479)
-- Name: product_detail_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.product_detail_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 221 (class 1259 OID 17546)
-- Name: product_details; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.product_details (
    id bigint NOT NULL,
    color character varying(255),
    price bigint NOT NULL,
    quantity integer NOT NULL,
    product_id bigint NOT NULL
);


--
-- TOC entry 208 (class 1259 OID 17481)
-- Name: product_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.product_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 222 (class 1259 OID 17551)
-- Name: products; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.products (
    id bigint NOT NULL,
    create_date timestamp without time zone NOT NULL,
    description character varying(255),
    img smallint,
    material character varying(255),
    model character varying(255),
    product_name character varying(255),
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
-- TOC entry 209 (class 1259 OID 17483)
-- Name: review_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.review_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 223 (class 1259 OID 17559)
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
-- TOC entry 210 (class 1259 OID 17485)
-- Name: role_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.role_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 224 (class 1259 OID 17564)
-- Name: roles; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.roles (
    id smallint NOT NULL,
    role_name character varying(60) NOT NULL
);


--
-- TOC entry 211 (class 1259 OID 17487)
-- Name: user_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.user_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 225 (class 1259 OID 17569)
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    password character varying(255),
    status smallint NOT NULL,
    username character varying(255),
    role_id smallint NOT NULL
);


--
-- TOC entry 3179 (class 0 OID 17489)
-- Dependencies: 212
-- Data for Name: addresss; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.addresss (id, city, contact_name, contact_number, default_address, detail, district, payment_address, street_and_number, tag, ward, organization_id, user_id) VALUES (1, 'HCM', NULL, NULL, NULL, NULL, '11', NULL, 'test', 'Home', '11', NULL, 1);
INSERT INTO public.addresss (id, city, contact_name, contact_number, default_address, detail, district, payment_address, street_and_number, tag, ward, organization_id, user_id) VALUES (2, 'Long An', NULL, NULL, NULL, NULL, '10', NULL, 'test', 'Home', '12', NULL, 2);
INSERT INTO public.addresss (id, city, contact_name, contact_number, default_address, detail, district, payment_address, street_and_number, tag, ward, organization_id, user_id) VALUES (3, 'HCM', NULL, NULL, NULL, NULL, '12', NULL, 'test', 'Home', '15', NULL, 3);


--
-- TOC entry 3180 (class 0 OID 17497)
-- Dependencies: 213
-- Data for Name: brands; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.brands (id, brand_name, organization_id) VALUES (1, 'Elip', 1);
INSERT INTO public.brands (id, brand_name, organization_id) VALUES (2, 'Robo', 2);
INSERT INTO public.brands (id, brand_name, organization_id) VALUES (3, 'Thượng đình', 3);
INSERT INTO public.brands (id, brand_name, organization_id) VALUES (4, 'Yonex', 1);


--
-- TOC entry 3181 (class 0 OID 17502)
-- Dependencies: 214
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
-- TOC entry 3182 (class 0 OID 17507)
-- Dependencies: 215
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.customers (user_id, create_date, dob, email, name, phone_number, sex) VALUES (4, '2021-07-14 18:38:02.110584', NULL, 'altgutha@monsaustralia.com', 'test', '1234567899', true);


--
-- TOC entry 3183 (class 0 OID 17515)
-- Dependencies: 216
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3184 (class 0 OID 17523)
-- Dependencies: 217
-- Data for Name: order_details; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3185 (class 0 OID 17528)
-- Dependencies: 218
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3186 (class 0 OID 17536)
-- Dependencies: 219
-- Data for Name: organizations; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.organizations (id, organization_name, img) VALUES (1, 'OA', NULL);
INSERT INTO public.organizations (id, organization_name, img) VALUES (2, 'OB', NULL);
INSERT INTO public.organizations (id, organization_name, img) VALUES (3, 'OC', NULL);


--
-- TOC entry 3187 (class 0 OID 17541)
-- Dependencies: 220
-- Data for Name: origins; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.origins (id, country) VALUES (1, 'Đài Loan');
INSERT INTO public.origins (id, country) VALUES (2, 'Mỹ');
INSERT INTO public.origins (id, country) VALUES (3, 'Thái Lan');
INSERT INTO public.origins (id, country) VALUES (4, 'Trung Quốc');
INSERT INTO public.origins (id, country) VALUES (5, 'Việt Nam');


--
-- TOC entry 3188 (class 0 OID 17546)
-- Dependencies: 221
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
-- TOC entry 3189 (class 0 OID 17551)
-- Dependencies: 222
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.products (id, create_date, description, img, material, model, product_name, size, standard, update_date, warranty, weight, brand_id, category_id, origin_id) VALUES (1, '2021-07-06 14:22:05', '<p>Vợt cầu lông Yonex Voltric 1 DG kết hợp sức mạnh đáng kinh ngạc và khả năng xử lý vợt nhanh lần đầu tiên, VOLTRIC là cây vợt hoàn hảo cho những người chơi tìm kiếm hiệu suất toàn diện.</p>', 3, 'Graphite', 'VT1DG', 'Vợt Cầu Lông Yonex Voltric 1DG', 'G3', '', NULL, 365, 88, 4, 36, 1);
INSERT INTO public.products (id, create_date, description, img, material, model, product_name, size, standard, update_date, warranty, weight, brand_id, category_id, origin_id) VALUES (2, '2021-07-06 14:23:05', '<p>Vợt cầu lông Yonex Nanoray Tour 7700 New 2019 là cây vợt nhẹ đầu, thân dẽo rất dễ để người chơi làm quen và phát huy hết được năng lực của mình. Vợt thích hợp với người chơi điều cầu, phản tạt.</p>', 1, 'Graphite', 'ARC TUOR 7700', 'Vợt Cầu Lông Yonex Nanoray Tour 7700', 'G4', '', NULL, 365, 88, 4, 36, 2);
INSERT INTO public.products (id, create_date, description, img, material, model, product_name, size, standard, update_date, warranty, weight, brand_id, category_id, origin_id) VALUES (3, '2021-07-06 14:24:05', 'Vợt cầu lông Yonex Astrox 88D dòng vợt chính hãng của Yonex cam kết chất lượng vượt trội được nhiều tuyển thủ câu lông lựa chọn để luyện tập và thi đấu. Mua vợt Yonex gọi ngay 1800 6884 giao hành tận nhà. ', 3, 'Than chì', 'AX88D', 'Vợt Cầu Lông Yonex Astrox 88 D ', 'G5', '', NULL, 60, 88, 4, 36, 3);
INSERT INTO public.products (id, create_date, description, img, material, model, product_name, size, standard, update_date, warranty, weight, brand_id, category_id, origin_id) VALUES (4, '2021-07-06 14:25:05', 'Vợt cầu lông Yonex Z Force ll là sự kết hợp sức mạnh đáng kinh ngạc và khả năng xử lý vợt nhanh lần đầu tiên, Z Force ll  là cây vợt hoàn hảo cho những người chơi tìm kiếm hiệu suất toàn diện.', 3, 'NANOMETRICS', 'VTZF2', 'Vợt Cầu Lông Yonex Z Force II', 'G4', '', NULL, 90, 83, 4, 36, 4);
INSERT INTO public.products (id, create_date, description, img, material, model, product_name, size, standard, update_date, warranty, weight, brand_id, category_id, origin_id) VALUES (5, '2021-07-06 14:25:05', 'Bảng rổ treo tường EB02 được thiết kế vô cùng chắc chắn, bảng rổ được làm bằng nhựa Composite chịu nhiệt, chịu lực  đạt chuẩn, đẹp, chịu được thời tiết nắng mưa khắc nhiệt.', 1, 'Composite', 'Elip-EB02', 'Bảng rổ treo tường Elip EB02', '1200 x 900mm', 'NBA', NULL, 30, 7.5, 1, 38, 5);
INSERT INTO public.products (id, create_date, description, img, material, model, product_name, size, standard, update_date, warranty, weight, brand_id, category_id, origin_id) VALUES (18, '2021-07-14 22:23:46.908006', NULL, NULL, NULL, NULL, 'test', NULL, NULL, NULL, NULL, NULL, 1, 1, 1);


--
-- TOC entry 3190 (class 0 OID 17559)
-- Dependencies: 223
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.reviews (id, anonymous, create_date, description, img, num_of_star, status, update_date, order_id, product_detail_id, user_id) VALUES (2, 0, '2021-07-14 18:45:44.796603', 'this is comment 2', 1, 5, 1, NULL, NULL, 10, 4);
INSERT INTO public.reviews (id, anonymous, create_date, description, img, num_of_star, status, update_date, order_id, product_detail_id, user_id) VALUES (3, 0, '2021-07-14 18:45:45.746577', 'this is comment 2', 1, 5, 1, NULL, NULL, 10, 4);


--
-- TOC entry 3191 (class 0 OID 17564)
-- Dependencies: 224
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.roles (id, role_name) VALUES (1, 'ROLE_CUSTOMER');
INSERT INTO public.roles (id, role_name) VALUES (2, 'ROLE_EMPLOYEE');
INSERT INTO public.roles (id, role_name) VALUES (3, 'ROLE_MANAGER');
INSERT INTO public.roles (id, role_name) VALUES (4, 'ROLE_ADMIN');
INSERT INTO public.roles (id, role_name) VALUES (5, 'ROLE_CUSTOMER_LOCKED');
INSERT INTO public.roles (id, role_name) VALUES (6, 'ROLE_EMPLOYEE_LOCKED');
INSERT INTO public.roles (id, role_name) VALUES (7, 'ROLE_MANAGER_LOCKED');
INSERT INTO public.roles (id, role_name) VALUES (8, 'ROLE_ADMIN_LOCKED');


--
-- TOC entry 3192 (class 0 OID 17569)
-- Dependencies: 225
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.users (id, password, status, username, role_id) VALUES (1, '$2a$10$.1R9XP.ET5JirY5gvLe1O.hwjpBvFjeBc4uSrfUGrFwGCOjKvBrjC', 1, 'user0001', 2);
INSERT INTO public.users (id, password, status, username, role_id) VALUES (3, '$2a$10$.1R9XP.ET5JirY5gvLe1O.hwjpBvFjeBc4uSrfUGrFwGCOjKvBrjC', 1, 'user0003', 4);
INSERT INTO public.users (id, password, status, username, role_id) VALUES (2, '$2a$10$.1R9XP.ET5JirY5gvLe1O.hwjpBvFjeBc4uSrfUGrFwGCOjKvBrjC', 1, 'user0002', 3);
INSERT INTO public.users (id, password, status, username, role_id) VALUES (4, '$2a$10$8dAPNe4qup6nYxIeUqUWDOtfNFdj5StNB2DXx4EdvVnZUvVmSjYFi', 1, 'custom01', 1);


--
-- TOC entry 3199 (class 0 OID 0)
-- Dependencies: 200
-- Name: address_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.address_sequence', 1, false);


--
-- TOC entry 3200 (class 0 OID 0)
-- Dependencies: 201
-- Name: brand_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.brand_sequence', 1, false);


--
-- TOC entry 3201 (class 0 OID 0)
-- Dependencies: 202
-- Name: category_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.category_sequence', 71, true);


--
-- TOC entry 3202 (class 0 OID 0)
-- Dependencies: 203
-- Name: order_detail_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.order_detail_sequence', 1, false);


--
-- TOC entry 3203 (class 0 OID 0)
-- Dependencies: 204
-- Name: order_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.order_sequence', 1, false);


--
-- TOC entry 3204 (class 0 OID 0)
-- Dependencies: 205
-- Name: organization_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.organization_sequence', 1, false);


--
-- TOC entry 3205 (class 0 OID 0)
-- Dependencies: 206
-- Name: origin_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.origin_sequence', 1, false);


--
-- TOC entry 3206 (class 0 OID 0)
-- Dependencies: 207
-- Name: product_detail_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.product_detail_sequence', 18, true);


--
-- TOC entry 3207 (class 0 OID 0)
-- Dependencies: 208
-- Name: product_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.product_sequence', 25, true);


--
-- TOC entry 3208 (class 0 OID 0)
-- Dependencies: 209
-- Name: review_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.review_sequence', 6, true);


--
-- TOC entry 3209 (class 0 OID 0)
-- Dependencies: 210
-- Name: role_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.role_sequence', 1, false);


--
-- TOC entry 3210 (class 0 OID 0)
-- Dependencies: 211
-- Name: user_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.user_sequence', 16, true);


--
-- TOC entry 2935 (class 2606 OID 17496)
-- Name: addresss addresss_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.addresss
    ADD CONSTRAINT addresss_pkey PRIMARY KEY (id);


--
-- TOC entry 2937 (class 2606 OID 17699)
-- Name: brands bn_index; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.brands
    ADD CONSTRAINT bn_index UNIQUE (brand_name);


--
-- TOC entry 2939 (class 2606 OID 17717)
-- Name: brands br_bn_index; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.brands
    ADD CONSTRAINT br_bn_index UNIQUE (brand_name);


--
-- TOC entry 2941 (class 2606 OID 17501)
-- Name: brands brands_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.brands
    ADD CONSTRAINT brands_pkey PRIMARY KEY (id);


--
-- TOC entry 2945 (class 2606 OID 17719)
-- Name: categories ca_cn_index; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT ca_cn_index UNIQUE (category_name);


--
-- TOC entry 2947 (class 2606 OID 17506)
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- TOC entry 2952 (class 2606 OID 17514)
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2959 (class 2606 OID 17522)
-- Name: employees employees_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2974 (class 2606 OID 17703)
-- Name: organizations on_index; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.organizations
    ADD CONSTRAINT on_index UNIQUE (organization_name);


--
-- TOC entry 2976 (class 2606 OID 17725)
-- Name: organizations or_on_index; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.organizations
    ADD CONSTRAINT or_on_index UNIQUE (organization_name);


--
-- TOC entry 2966 (class 2606 OID 17527)
-- Name: order_details order_details_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_details
    ADD CONSTRAINT order_details_pkey PRIMARY KEY (id);


--
-- TOC entry 2972 (class 2606 OID 17535)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- TOC entry 2978 (class 2606 OID 17540)
-- Name: organizations organizations_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.organizations
    ADD CONSTRAINT organizations_pkey PRIMARY KEY (id);


--
-- TOC entry 2983 (class 2606 OID 17545)
-- Name: origins origins_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.origins
    ADD CONSTRAINT origins_pkey PRIMARY KEY (id);


--
-- TOC entry 2988 (class 2606 OID 17550)
-- Name: product_details product_details_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_details
    ADD CONSTRAINT product_details_pkey PRIMARY KEY (id);


--
-- TOC entry 2996 (class 2606 OID 17558)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- TOC entry 2999 (class 2606 OID 17563)
-- Name: reviews reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);


--
-- TOC entry 3008 (class 2606 OID 17568)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2949 (class 2606 OID 17580)
-- Name: categories uk41g4n0emuvcm3qyf1f6cn43c0; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT uk41g4n0emuvcm3qyf1f6cn43c0 UNIQUE (category_name);


--
-- TOC entry 2954 (class 2606 OID 17582)
-- Name: customers uk6v6x92wb400iwh6unf5rwiim4; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT uk6v6x92wb400iwh6unf5rwiim4 UNIQUE (phone_number);


--
-- TOC entry 3010 (class 2606 OID 17600)
-- Name: roles uk716hgxp60ym1lifrdgp67xt5k; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT uk716hgxp60ym1lifrdgp67xt5k UNIQUE (role_name);


--
-- TOC entry 3005 (class 2606 OID 17598)
-- Name: reviews ukax0dnqwsuwq42709men8lm78f; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT ukax0dnqwsuwq42709men8lm78f UNIQUE (order_id, product_detail_id, user_id);


--
-- TOC entry 2980 (class 2606 OID 17592)
-- Name: organizations ukdkw1d2tc2fic4y5vkxeu6fqss; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.organizations
    ADD CONSTRAINT ukdkw1d2tc2fic4y5vkxeu6fqss UNIQUE (organization_name);


--
-- TOC entry 2961 (class 2606 OID 17586)
-- Name: employees ukg6512s2t9cous2oxa17he4irp; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT ukg6512s2t9cous2oxa17he4irp UNIQUE (phone_number);


--
-- TOC entry 2943 (class 2606 OID 17578)
-- Name: brands ukgds2u6k2vfeo1tkrtgwcyqj36; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.brands
    ADD CONSTRAINT ukgds2u6k2vfeo1tkrtgwcyqj36 UNIQUE (brand_name);


--
-- TOC entry 2985 (class 2606 OID 17594)
-- Name: origins uki5s4smn616kbjd2wq4as7frox; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.origins
    ADD CONSTRAINT uki5s4smn616kbjd2wq4as7frox UNIQUE (country);


--
-- TOC entry 2963 (class 2606 OID 17588)
-- Name: employees ukj9xgmd0ya5jmus09o0b8pqrpb; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT ukj9xgmd0ya5jmus09o0b8pqrpb UNIQUE (email);


--
-- TOC entry 2990 (class 2606 OID 17596)
-- Name: product_details ukk0p7qfjfec62m4p4fclrk5670; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_details
    ADD CONSTRAINT ukk0p7qfjfec62m4p4fclrk5670 UNIQUE (product_id, color);


--
-- TOC entry 2968 (class 2606 OID 17590)
-- Name: order_details ukpoi7jp1yyis7makpb233jnpjn; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_details
    ADD CONSTRAINT ukpoi7jp1yyis7makpb233jnpjn UNIQUE (order_id, product_detail_id);


--
-- TOC entry 3012 (class 2606 OID 17602)
-- Name: users ukr43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- TOC entry 2956 (class 2606 OID 17584)
-- Name: customers ukrfbvkrffamfql7cjmen8v976v; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT ukrfbvkrffamfql7cjmen8v976v UNIQUE (email);


--
-- TOC entry 3014 (class 2606 OID 17711)
-- Name: users un_index; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT un_index UNIQUE (username);


--
-- TOC entry 3016 (class 2606 OID 17738)
-- Name: users us_un_index; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT us_un_index UNIQUE (username);


--
-- TOC entry 3018 (class 2606 OID 17576)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2930 (class 1259 OID 17714)
-- Name: ad_cn_index; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX ad_cn_index ON public.addresss USING btree (contact_name);


--
-- TOC entry 2931 (class 1259 OID 17715)
-- Name: ad_cn_index2; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX ad_cn_index2 ON public.addresss USING btree (contact_number);


--
-- TOC entry 2932 (class 1259 OID 17713)
-- Name: ad_oi_index; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX ad_oi_index ON public.addresss USING btree (organization_id);


--
-- TOC entry 2933 (class 1259 OID 17712)
-- Name: ad_ui_index; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX ad_ui_index ON public.addresss USING btree (user_id);


--
-- TOC entry 2950 (class 1259 OID 17720)
-- Name: cu_n_index; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX cu_n_index ON public.customers USING btree (name);


--
-- TOC entry 2957 (class 1259 OID 17721)
-- Name: em_n_index; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX em_n_index ON public.employees USING btree (name);


--
-- TOC entry 2964 (class 1259 OID 17722)
-- Name: od_mulitindex1; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX od_mulitindex1 ON public.order_details USING btree (order_id, product_detail_id);


--
-- TOC entry 2981 (class 1259 OID 17726)
-- Name: or_c_index; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX or_c_index ON public.origins USING btree (country);


--
-- TOC entry 2969 (class 1259 OID 17723)
-- Name: or_mulitindex1; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX or_mulitindex1 ON public.orders USING btree (create_date, update_date);


--
-- TOC entry 2970 (class 1259 OID 17739)
-- Name: or_ui_index; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX or_ui_index ON public.orders USING btree (user_id);


--
-- TOC entry 2986 (class 1259 OID 17727)
-- Name: pd_pi_index; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX pd_pi_index ON public.product_details USING btree (product_id);


--
-- TOC entry 2997 (class 1259 OID 17708)
-- Name: pdi_index; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX pdi_index ON public.reviews USING btree (product_detail_id);


--
-- TOC entry 2991 (class 1259 OID 17730)
-- Name: pr_bi_index; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX pr_bi_index ON public.products USING btree (brand_id);


--
-- TOC entry 2992 (class 1259 OID 17728)
-- Name: pr_ci_index; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX pr_ci_index ON public.products USING btree (category_id);


--
-- TOC entry 2993 (class 1259 OID 17729)
-- Name: pr_mulitindex1; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX pr_mulitindex1 ON public.products USING btree (create_date, update_date);


--
-- TOC entry 2994 (class 1259 OID 17731)
-- Name: pr_oi_index; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX pr_oi_index ON public.products USING btree (origin_id);


--
-- TOC entry 3006 (class 1259 OID 17736)
-- Name: ro_rn_index; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX ro_rn_index ON public.roles USING btree (role_name);


--
-- TOC entry 3000 (class 1259 OID 17733)
-- Name: rv_mulitindex1; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rv_mulitindex1 ON public.reviews USING btree (create_date, update_date);


--
-- TOC entry 3001 (class 1259 OID 17735)
-- Name: rv_oi_index; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rv_oi_index ON public.reviews USING btree (order_id);


--
-- TOC entry 3002 (class 1259 OID 17732)
-- Name: rv_pdi_index; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rv_pdi_index ON public.reviews USING btree (product_detail_id);


--
-- TOC entry 3003 (class 1259 OID 17734)
-- Name: rv_ui_index; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX rv_ui_index ON public.reviews USING btree (user_id);


--
-- TOC entry 3032 (class 2606 OID 17668)
-- Name: products fk1u1t8sglrimm443lswfdh5sa2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk1u1t8sglrimm443lswfdh5sa2 FOREIGN KEY (origin_id) REFERENCES public.origins(id);


--
-- TOC entry 3028 (class 2606 OID 17648)
-- Name: orders fk32ql8ubntj5uh44ph9659tiih; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk32ql8ubntj5uh44ph9659tiih FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3024 (class 2606 OID 17628)
-- Name: employees fk69x3vjuy1t5p18a5llb8h2fjx; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT fk69x3vjuy1t5p18a5llb8h2fjx FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3022 (class 2606 OID 17618)
-- Name: categories fk9il7y6fehxwunjeepq0n7g5rd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT fk9il7y6fehxwunjeepq0n7g5rd FOREIGN KEY (parent_category_id) REFERENCES public.categories(id);


--
-- TOC entry 3030 (class 2606 OID 17658)
-- Name: products fka3a4mpsfdf4d2y6r8ra3sc8mv; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fka3a4mpsfdf4d2y6r8ra3sc8mv FOREIGN KEY (brand_id) REFERENCES public.brands(id);


--
-- TOC entry 3020 (class 2606 OID 17608)
-- Name: addresss fkcdsaghsofwoosc2de7j63kdsm; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.addresss
    ADD CONSTRAINT fkcdsaghsofwoosc2de7j63kdsm FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3035 (class 2606 OID 17683)
-- Name: reviews fkcgy7qjc1r99dp117y9en6lxye; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT fkcgy7qjc1r99dp117y9en6lxye FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3034 (class 2606 OID 17678)
-- Name: reviews fkdii2jr3liylnwp5l6xowl2qyk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT fkdii2jr3liylnwp5l6xowl2qyk FOREIGN KEY (product_detail_id) REFERENCES public.product_details(id);


--
-- TOC entry 3019 (class 2606 OID 17603)
-- Name: addresss fkdk834wdc3cy247cf3x8cntp6g; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.addresss
    ADD CONSTRAINT fkdk834wdc3cy247cf3x8cntp6g FOREIGN KEY (organization_id) REFERENCES public.organizations(id);


--
-- TOC entry 3025 (class 2606 OID 17633)
-- Name: order_details fkjyu2qbqt8gnvno9oe9j2s2ldk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_details
    ADD CONSTRAINT fkjyu2qbqt8gnvno9oe9j2s2ldk FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- TOC entry 3026 (class 2606 OID 17638)
-- Name: order_details fkl9w7hjxo9qo8s0glyr4ngkm1d; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_details
    ADD CONSTRAINT fkl9w7hjxo9qo8s0glyr4ngkm1d FOREIGN KEY (product_detail_id) REFERENCES public.product_details(id);


--
-- TOC entry 3027 (class 2606 OID 17643)
-- Name: orders fklqaug1fp15rq0tysd4ht0nvr; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fklqaug1fp15rq0tysd4ht0nvr FOREIGN KEY (address_id) REFERENCES public.addresss(id);


--
-- TOC entry 3029 (class 2606 OID 17653)
-- Name: product_details fknfvvq3meg4ha3u1bju9k4is3r; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_details
    ADD CONSTRAINT fknfvvq3meg4ha3u1bju9k4is3r FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- TOC entry 3031 (class 2606 OID 17663)
-- Name: products fkog2rp4qthbtt2lfyhfo32lsw9; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fkog2rp4qthbtt2lfyhfo32lsw9 FOREIGN KEY (category_id) REFERENCES public.categories(id);


--
-- TOC entry 3036 (class 2606 OID 17688)
-- Name: users fkp56c1712k691lhsyewcssf40f; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkp56c1712k691lhsyewcssf40f FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 3021 (class 2606 OID 17613)
-- Name: brands fkq646smhet4r1djxbbad0hjwjq; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.brands
    ADD CONSTRAINT fkq646smhet4r1djxbbad0hjwjq FOREIGN KEY (organization_id) REFERENCES public.organizations(id);


--
-- TOC entry 3033 (class 2606 OID 17673)
-- Name: reviews fkqwgq1lxgahsxdspnwqfac6sv6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT fkqwgq1lxgahsxdspnwqfac6sv6 FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- TOC entry 3023 (class 2606 OID 17623)
-- Name: customers fkrh1g1a20omjmn6kurd35o3eit; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT fkrh1g1a20omjmn6kurd35o3eit FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2021-07-16 14:30:42

--
-- PostgreSQL database dump complete
--

