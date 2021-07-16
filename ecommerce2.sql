--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3
-- Dumped by pg_dump version 13.3

-- Started on 2021-07-16 13:58:11

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


-- Completed on 2021-07-16 13:58:12

--
-- PostgreSQL database dump complete
--

