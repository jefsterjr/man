create schema if not exists man;

create table if not exists man.configuration
(
    id   serial not null,
    code varchar                                                             not null,
    type varchar                                                             not null,
    constraint configuration_pk
        primary key (id)
);

alter table man.configuration
    owner to postgres;

create unique index if not exists code_index
    on man.configuration (code);

create table if not exists man.feature
(
    id   serial  not null,
    name varchar                                                       not null,
    constraint feature_pk
        primary key (id)
);

alter table man.feature
    owner to postgres;

create table if not exists man.feature_configuration
(
    id               serial not null,
    configuration_id integer                                                                     not null,
    feature_id       integer,
    type             varchar,
    constraint feature_configuration_pk
        primary key (id),
    constraint configuration_fk
        foreign key (configuration_id) references man.configuration,
    constraint feature_fk
        foreign key (feature_id) references man.feature
);

alter table man.feature_configuration
    owner to postgres;

create table if not exists man.vehicle
(
    uuid_vin uuid                                                          not null,
    vin      varchar(17)                                                   not null,
    id       serial not null,
    constraint vehicle_pk
        primary key (id),
    constraint uuid_key
        unique (uuid_vin),
    constraint vin_key
        unique (vin)
);

alter table man.vehicle
    owner to postgres;

create unique index if not exists vin_uindex
    on man.vehicle (vin);

create unique index if not exists uuid_vin
    on man.vehicle (uuid_vin);

create table if not exists man.vehicle_configuration
(
    id               serial not null,
    configuration_id bigint                                                                      not null,
    vehicle_id       bigint                                                                      not null,
    constraint vehicle_configuration_pk
        primary key (id),
    constraint vehicle_fk
        foreign key (vehicle_id) references man.vehicle
);

alter table man.vehicle_configuration
    owner to postgres;

create index if not exists configuration_id
    on man.vehicle_configuration (configuration_id);

create index if not exists vehicle_id
    on man.vehicle_configuration (vehicle_id);

create table if not exists man.file
(
    id          serial not null,
    name        varchar                                                    not null,
    insert_date timestamp                                                  not null,
    file_key    text                                                       not null,
    type        varchar                                                    not null,
    constraint file_pk
        primary key (id)
);

alter table man.file
    owner to postgres;

create unique index if not exists file_id_uindex
    on man.file (id);

create unique index if not exists file_file_key_uindex
    on man.file (file_key);


