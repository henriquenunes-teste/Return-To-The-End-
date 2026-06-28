--
-- PostgreSQL database dump
--

\restrict 52zKSXdpkNLT7Qu1qjhXmKH32lp93Penc1MmQLWIUKaS14A03AmJ4KXDAGyqo1r

-- Dumped from database version 16.13 (Ubuntu 16.13-0ubuntu0.24.04.1)
-- Dumped by pg_dump version 16.13 (Ubuntu 16.13-0ubuntu0.24.04.1)

-- Started on 2026-06-27 17:23:06 -04

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
-- TOC entry 228 (class 1259 OID 41636)
-- Name: inventory; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inventory (
    id integer NOT NULL,
    max integer DEFAULT 12 NOT NULL
);


ALTER TABLE public.inventory OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 41635)
-- Name: inventory_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.inventory_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.inventory_id_seq OWNER TO postgres;

--
-- TOC entry 3534 (class 0 OID 0)
-- Dependencies: 227
-- Name: inventory_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.inventory_id_seq OWNED BY public.inventory.id;


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
-- TOC entry 3535 (class 0 OID 0)
-- Dependencies: 223
-- Name: item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.item_id_seq OWNED BY public.item.id;


--
-- TOC entry 230 (class 1259 OID 41644)
-- Name: item_inv; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_inv (
    item_id integer NOT NULL,
    id integer NOT NULL,
    inventory_id integer NOT NULL,
    amount integer NOT NULL
);


ALTER TABLE public.item_inv OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 41643)
-- Name: item_inv_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.item_inv_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.item_inv_id_seq OWNER TO postgres;

--
-- TOC entry 3536 (class 0 OID 0)
-- Dependencies: 229
-- Name: item_inv_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.item_inv_id_seq OWNED BY public.item_inv.id;


--
-- TOC entry 225 (class 1259 OID 41625)
-- Name: players; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.players (
    id integer NOT NULL,
    name character varying(120) NOT NULL,
    coins bigint NOT NULL,
    level integer NOT NULL
);


ALTER TABLE public.players OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 41628)
-- Name: players_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.players_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.players_id_seq OWNER TO postgres;

--
-- TOC entry 3537 (class 0 OID 0)
-- Dependencies: 226
-- Name: players_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.players_id_seq OWNED BY public.players.id;


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
-- TOC entry 3538 (class 0 OID 0)
-- Dependencies: 219
-- Name: position_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.position_id_seq OWNED BY public."position".id;


--
-- TOC entry 232 (class 1259 OID 49818)
-- Name: run; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.run (
    id integer NOT NULL,
    gain integer NOT NULL,
    exp integer NOT NULL,
    player_id integer NOT NULL,
    "time" bigint NOT NULL
);


ALTER TABLE public.run OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 49817)
-- Name: run_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.run_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.run_id_seq OWNER TO postgres;

--
-- TOC entry 3539 (class 0 OID 0)
-- Dependencies: 231
-- Name: run_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.run_id_seq OWNED BY public.run.id;


--
-- TOC entry 216 (class 1259 OID 41486)
-- Name: save; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.save (
    id integer NOT NULL,
    last_position integer,
    last_played timestamp without time zone,
    created_date timestamp without time zone,
    name character varying(130),
    player_id integer
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
-- TOC entry 3540 (class 0 OID 0)
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
-- TOC entry 3541 (class 0 OID 0)
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
-- TOC entry 3542 (class 0 OID 0)
-- Dependencies: 217
-- Name: world_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.world_id_seq OWNED BY public.world.id;


--
-- TOC entry 3333 (class 2604 OID 41639)
-- Name: inventory id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inventory ALTER COLUMN id SET DEFAULT nextval('public.inventory_id_seq'::regclass);


--
-- TOC entry 3331 (class 2604 OID 41541)
-- Name: item id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item ALTER COLUMN id SET DEFAULT nextval('public.item_id_seq'::regclass);


--
-- TOC entry 3335 (class 2604 OID 41647)
-- Name: item_inv id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_inv ALTER COLUMN id SET DEFAULT nextval('public.item_inv_id_seq'::regclass);


--
-- TOC entry 3332 (class 2604 OID 41629)
-- Name: players id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.players ALTER COLUMN id SET DEFAULT nextval('public.players_id_seq'::regclass);


--
-- TOC entry 3329 (class 2604 OID 41510)
-- Name: position id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position" ALTER COLUMN id SET DEFAULT nextval('public.position_id_seq'::regclass);


--
-- TOC entry 3336 (class 2604 OID 49821)
-- Name: run id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.run ALTER COLUMN id SET DEFAULT nextval('public.run_id_seq'::regclass);


--
-- TOC entry 3327 (class 2604 OID 41489)
-- Name: save id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.save ALTER COLUMN id SET DEFAULT nextval('public.save_id_seq'::regclass);


--
-- TOC entry 3330 (class 2604 OID 41529)
-- Name: upgrade id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.upgrade ALTER COLUMN id SET DEFAULT nextval('public.upgrade_id_seq'::regclass);


--
-- TOC entry 3328 (class 2604 OID 41496)
-- Name: world id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.world ALTER COLUMN id SET DEFAULT nextval('public.world_id_seq'::regclass);


--
-- TOC entry 3524 (class 0 OID 41636)
-- Dependencies: 228
-- Data for Name: inventory; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inventory (id, max) FROM stdin;
\.


--
-- TOC entry 3520 (class 0 OID 41538)
-- Dependencies: 224
-- Data for Name: item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item (id, quantity, durability, damage, recup, rarity, type) FROM stdin;
\.


--
-- TOC entry 3526 (class 0 OID 41644)
-- Dependencies: 230
-- Data for Name: item_inv; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_inv (item_id, id, inventory_id, amount) FROM stdin;
\.


--
-- TOC entry 3521 (class 0 OID 41625)
-- Dependencies: 225
-- Data for Name: players; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.players (id, name, coins, level) FROM stdin;
1	meu_player	0	1
2	adass	0	1
\.


--
-- TOC entry 3516 (class 0 OID 41507)
-- Dependencies: 220
-- Data for Name: position; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."position" (id, x, y, world_id) FROM stdin;
\.


--
-- TOC entry 3528 (class 0 OID 49818)
-- Dependencies: 232
-- Data for Name: run; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.run (id, gain, exp, player_id, "time") FROM stdin;
\.


--
-- TOC entry 3512 (class 0 OID 41486)
-- Dependencies: 216
-- Data for Name: save; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.save (id, last_position, last_played, created_date, name, player_id) FROM stdin;
2	\N	2026-06-27 09:36:44.355977	2026-06-27 09:36:44.355993	aleatorio	2
\.


--
-- TOC entry 3518 (class 0 OID 41526)
-- Dependencies: 222
-- Data for Name: upgrade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.upgrade (id, strength, velocity, health, recup, durability, save_id) FROM stdin;
\.


--
-- TOC entry 3514 (class 0 OID 41493)
-- Dependencies: 218
-- Data for Name: world; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.world (id, name, save_id) FROM stdin;
\.


--
-- TOC entry 3543 (class 0 OID 0)
-- Dependencies: 227
-- Name: inventory_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.inventory_id_seq', 1, false);


--
-- TOC entry 3544 (class 0 OID 0)
-- Dependencies: 223
-- Name: item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.item_id_seq', 1, false);


--
-- TOC entry 3545 (class 0 OID 0)
-- Dependencies: 229
-- Name: item_inv_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.item_inv_id_seq', 1, false);


--
-- TOC entry 3546 (class 0 OID 0)
-- Dependencies: 226
-- Name: players_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.players_id_seq', 2, true);


--
-- TOC entry 3547 (class 0 OID 0)
-- Dependencies: 219
-- Name: position_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.position_id_seq', 1, false);


--
-- TOC entry 3548 (class 0 OID 0)
-- Dependencies: 231
-- Name: run_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.run_id_seq', 1, false);


--
-- TOC entry 3549 (class 0 OID 0)
-- Dependencies: 215
-- Name: save_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.save_id_seq', 2, true);


--
-- TOC entry 3550 (class 0 OID 0)
-- Dependencies: 221
-- Name: upgrade_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.upgrade_id_seq', 1, false);


--
-- TOC entry 3551 (class 0 OID 0)
-- Dependencies: 217
-- Name: world_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.world_id_seq', 1, false);


--
-- TOC entry 3354 (class 2606 OID 41642)
-- Name: inventory inventory_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inventory
    ADD CONSTRAINT inventory_pkey PRIMARY KEY (id);


--
-- TOC entry 3356 (class 2606 OID 41649)
-- Name: item_inv item_inv_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_inv
    ADD CONSTRAINT item_inv_pkey PRIMARY KEY (id);


--
-- TOC entry 3350 (class 2606 OID 41543)
-- Name: item item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);


--
-- TOC entry 3352 (class 2606 OID 41634)
-- Name: players players_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.players
    ADD CONSTRAINT players_pkey PRIMARY KEY (id);


--
-- TOC entry 3344 (class 2606 OID 41512)
-- Name: position position_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position"
    ADD CONSTRAINT position_pkey PRIMARY KEY (id);


--
-- TOC entry 3346 (class 2606 OID 41514)
-- Name: position position_x_y_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position"
    ADD CONSTRAINT position_x_y_key UNIQUE (x, y);


--
-- TOC entry 3359 (class 2606 OID 49823)
-- Name: run run_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.run
    ADD CONSTRAINT run_pkey PRIMARY KEY (id);


--
-- TOC entry 3338 (class 2606 OID 41491)
-- Name: save save_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.save
    ADD CONSTRAINT save_pkey PRIMARY KEY (id);


--
-- TOC entry 3348 (class 2606 OID 41531)
-- Name: upgrade upgrade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.upgrade
    ADD CONSTRAINT upgrade_pkey PRIMARY KEY (id);


--
-- TOC entry 3340 (class 2606 OID 41500)
-- Name: world world_name_save_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.world
    ADD CONSTRAINT world_name_save_id_key UNIQUE (name, save_id);


--
-- TOC entry 3342 (class 2606 OID 41498)
-- Name: world world_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.world
    ADD CONSTRAINT world_pkey PRIMARY KEY (id);


--
-- TOC entry 3357 (class 1259 OID 49829)
-- Name: fki_p; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_p ON public.run USING btree (player_id);


--
-- TOC entry 3365 (class 2606 OID 41655)
-- Name: item_inv inventory_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_inv
    ADD CONSTRAINT inventory_fk FOREIGN KEY (inventory_id) REFERENCES public.inventory(id);


--
-- TOC entry 3366 (class 2606 OID 41650)
-- Name: item_inv item_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_inv
    ADD CONSTRAINT item_fk FOREIGN KEY (item_id) REFERENCES public.item(id);


--
-- TOC entry 3360 (class 2606 OID 41660)
-- Name: save player_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.save
    ADD CONSTRAINT player_id FOREIGN KEY (player_id) REFERENCES public.players(id) NOT VALID;


--
-- TOC entry 3363 (class 2606 OID 41515)
-- Name: position position_world_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position"
    ADD CONSTRAINT position_world_id_fkey FOREIGN KEY (world_id) REFERENCES public.world(id);


--
-- TOC entry 3367 (class 2606 OID 49824)
-- Name: run run_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.run
    ADD CONSTRAINT run_fk FOREIGN KEY (player_id) REFERENCES public.players(id) NOT VALID;


--
-- TOC entry 3361 (class 2606 OID 41520)
-- Name: save save_position_ref; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.save
    ADD CONSTRAINT save_position_ref FOREIGN KEY (last_position) REFERENCES public."position"(id);


--
-- TOC entry 3364 (class 2606 OID 41532)
-- Name: upgrade upgrade_save_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.upgrade
    ADD CONSTRAINT upgrade_save_id_fkey FOREIGN KEY (save_id) REFERENCES public.save(id);


--
-- TOC entry 3362 (class 2606 OID 41501)
-- Name: world world_save_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.world
    ADD CONSTRAINT world_save_id_fkey FOREIGN KEY (save_id) REFERENCES public.save(id);


-- Completed on 2026-06-27 17:23:06 -04

--
-- PostgreSQL database dump complete
--

\unrestrict 52zKSXdpkNLT7Qu1qjhXmKH32lp93Penc1MmQLWIUKaS14A03AmJ4KXDAGyqo1r

