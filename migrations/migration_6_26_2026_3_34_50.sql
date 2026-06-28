--
-- PostgreSQL database dump
--

\restrict FBXhbsT7DWRXmymIRpoXr8Nb5pcwm8hO1IBlrQGOtwbsIj6nIIo4Sxs4BWzxDQe

-- Dumped from database version 16.13 (Ubuntu 16.13-0ubuntu0.24.04.1)
-- Dumped by pg_dump version 16.13 (Ubuntu 16.13-0ubuntu0.24.04.1)

-- Started on 2026-06-26 15:35:04 -04

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 224 (class 1259 OID 41538)
-- Name: item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item (
    id integer NOT NULL,
    quantity integer,
    durability numeric(10,2),
    damage numeric(10,2),
    recup numeric(10,2),
    rarity character varying(120) NOT NULL,
    type character varying(140) NOT NULL
);


ALTER TABLE public.item OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 41537)
-- Name: item_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.item_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.item_id_seq OWNER TO postgres;

--
-- TOC entry 3488 (class 0 OID 0)
-- Dependencies: 223
-- Name: item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.item_id_seq OWNED BY public.item.id;


--
-- TOC entry 220 (class 1259 OID 41507)
-- Name: position; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."position" (
    id integer NOT NULL,
    x numeric(10,2) NOT NULL,
    y numeric(10,2) NOT NULL,
    world_id integer NOT NULL
);


ALTER TABLE public."position" OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 41506)
-- Name: position_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.position_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.position_id_seq OWNER TO postgres;

--
-- TOC entry 3489 (class 0 OID 0)
-- Dependencies: 219
-- Name: position_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.position_id_seq OWNED BY public."position".id;


--
-- TOC entry 216 (class 1259 OID 41486)
-- Name: save; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.save (
    id integer NOT NULL,
    last_position integer,
    last_played timestamp without time zone,
    created_date timestamp without time zone,
    name character varying(130)
);


ALTER TABLE public.save OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 41485)
-- Name: save_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.save_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.save_id_seq OWNER TO postgres;

--
-- TOC entry 3490 (class 0 OID 0)
-- Dependencies: 215
-- Name: save_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.save_id_seq OWNED BY public.save.id;


--
-- TOC entry 222 (class 1259 OID 41526)
-- Name: upgrade; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.upgrade (
    id integer NOT NULL,
    strength integer,
    velocity integer,
    health integer,
    recup integer,
    durability integer,
    save_id integer NOT NULL
);


ALTER TABLE public.upgrade OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 41525)
-- Name: upgrade_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.upgrade_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.upgrade_id_seq OWNER TO postgres;

--
-- TOC entry 3491 (class 0 OID 0)
-- Dependencies: 221
-- Name: upgrade_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.upgrade_id_seq OWNED BY public.upgrade.id;


--
-- TOC entry 218 (class 1259 OID 41493)
-- Name: world; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.world (
    id integer NOT NULL,
    name character varying(120),
    save_id integer NOT NULL
);


ALTER TABLE public.world OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 41492)
-- Name: world_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.world_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.world_id_seq OWNER TO postgres;

--
-- TOC entry 3492 (class 0 OID 0)
-- Dependencies: 217
-- Name: world_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.world_id_seq OWNED BY public.world.id;


--
-- TOC entry 3311 (class 2604 OID 41541)
-- Name: item id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item ALTER COLUMN id SET DEFAULT nextval('public.item_id_seq'::regclass);


--
-- TOC entry 3309 (class 2604 OID 41510)
-- Name: position id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position" ALTER COLUMN id SET DEFAULT nextval('public.position_id_seq'::regclass);


--
-- TOC entry 3307 (class 2604 OID 41489)
-- Name: save id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.save ALTER COLUMN id SET DEFAULT nextval('public.save_id_seq'::regclass);


--
-- TOC entry 3310 (class 2604 OID 41529)
-- Name: upgrade id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.upgrade ALTER COLUMN id SET DEFAULT nextval('public.upgrade_id_seq'::regclass);


--
-- TOC entry 3308 (class 2604 OID 41496)
-- Name: world id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.world ALTER COLUMN id SET DEFAULT nextval('public.world_id_seq'::regclass);


--
-- TOC entry 3482 (class 0 OID 41538)
-- Dependencies: 224
-- Data for Name: item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item (id, quantity, durability, damage, recup, rarity, type) FROM stdin;
\.


--
-- TOC entry 3478 (class 0 OID 41507)
-- Dependencies: 220
-- Data for Name: position; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."position" (id, x, y, world_id) FROM stdin;
\.


--
-- TOC entry 3474 (class 0 OID 41486)
-- Dependencies: 216
-- Data for Name: save; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.save (id, last_position, last_played, created_date, name) FROM stdin;
1	\N	2026-05-11 18:56:44.966128	2026-05-11 18:56:44.966144	adasd
\.


--
-- TOC entry 3480 (class 0 OID 41526)
-- Dependencies: 222
-- Data for Name: upgrade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.upgrade (id, strength, velocity, health, recup, durability, save_id) FROM stdin;
\.


--
-- TOC entry 3476 (class 0 OID 41493)
-- Dependencies: 218
-- Data for Name: world; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.world (id, name, save_id) FROM stdin;
\.


--
-- TOC entry 3493 (class 0 OID 0)
-- Dependencies: 223
-- Name: item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.item_id_seq', 1, false);


--
-- TOC entry 3494 (class 0 OID 0)
-- Dependencies: 219
-- Name: position_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.position_id_seq', 1, false);


--
-- TOC entry 3495 (class 0 OID 0)
-- Dependencies: 215
-- Name: save_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.save_id_seq', 1, true);


--
-- TOC entry 3496 (class 0 OID 0)
-- Dependencies: 221
-- Name: upgrade_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.upgrade_id_seq', 1, false);


--
-- TOC entry 3497 (class 0 OID 0)
-- Dependencies: 217
-- Name: world_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.world_id_seq', 1, false);


--
-- TOC entry 3325 (class 2606 OID 41543)
-- Name: item item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);


--
-- TOC entry 3319 (class 2606 OID 41512)
-- Name: position position_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position"
    ADD CONSTRAINT position_pkey PRIMARY KEY (id);


--
-- TOC entry 3321 (class 2606 OID 41514)
-- Name: position position_x_y_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position"
    ADD CONSTRAINT position_x_y_key UNIQUE (x, y);


--
-- TOC entry 3313 (class 2606 OID 41491)
-- Name: save save_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.save
    ADD CONSTRAINT save_pkey PRIMARY KEY (id);


--
-- TOC entry 3323 (class 2606 OID 41531)
-- Name: upgrade upgrade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.upgrade
    ADD CONSTRAINT upgrade_pkey PRIMARY KEY (id);


--
-- TOC entry 3315 (class 2606 OID 41500)
-- Name: world world_name_save_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.world
    ADD CONSTRAINT world_name_save_id_key UNIQUE (name, save_id);


--
-- TOC entry 3317 (class 2606 OID 41498)
-- Name: world world_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.world
    ADD CONSTRAINT world_pkey PRIMARY KEY (id);


--
-- TOC entry 3328 (class 2606 OID 41515)
-- Name: position position_world_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position"
    ADD CONSTRAINT position_world_id_fkey FOREIGN KEY (world_id) REFERENCES public.world(id);


--
-- TOC entry 3326 (class 2606 OID 41520)
-- Name: save save_position_ref; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.save
    ADD CONSTRAINT save_position_ref FOREIGN KEY (last_position) REFERENCES public."position"(id);


--
-- TOC entry 3329 (class 2606 OID 41532)
-- Name: upgrade upgrade_save_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.upgrade
    ADD CONSTRAINT upgrade_save_id_fkey FOREIGN KEY (save_id) REFERENCES public.save(id);


--
-- TOC entry 3327 (class 2606 OID 41501)
-- Name: world world_save_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.world
    ADD CONSTRAINT world_save_id_fkey FOREIGN KEY (save_id) REFERENCES public.save(id);


-- Completed on 2026-06-26 15:35:04 -04

--
-- PostgreSQL database dump complete
--

\unrestrict FBXhbsT7DWRXmymIRpoXr8Nb5pcwm8hO1IBlrQGOtwbsIj6nIIo4Sxs4BWzxDQe

