
drop table if exists pub cascade;

create table pub (
    id integer not null,
    pub_name varchar(255),
    has_free_parking boolean,
    has_good_beer boolean,
    link_more_info varchar(255),
    teenager_friendly boolean,
    toddler_friendly boolean,
    primary key (id)
);

insert into pub
values (1,'Het Anker',1,1,'www.hetanker.be',0,0);

INSERT INTO pub (ID, PUB_NAME, LINK_MORE_INFO, HAS_FREE_PARKING, TODDLER_FRIENDLY, HAS_GOOD_BEER, TEENAGER_FRIENDLY)
VALUES (2, 't Schuurke', 'www.schuurke.be', 1, 0, 1, 1);

INSERT INTO PUB (ID, PUB_NAME, LINK_MORE_INFO, HAS_FREE_PARKING, TODDLER_FRIENDLY, HAS_GOOD_BEER, TEENAGER_FRIENDLY)
VALUES (3, 'De zwaan', 'www.dezwaan.be', 1, 1, 0, 1);

INSERT INTO PUB (ID, PUB_NAME, LINK_MORE_INFO, HAS_FREE_PARKING, TODDLER_FRIENDLY, HAS_GOOD_BEER, TEENAGER_FRIENDLY)
VALUES (4, 'De Ton', 'www.deton.be', 0, 1, 1, 1);