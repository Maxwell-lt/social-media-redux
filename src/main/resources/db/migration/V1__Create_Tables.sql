create table user
(
    `id`                      bigint              not null primary key auto_increment,
    `username`                varchar(50) unique  not null,
    `password`                char(60)            not null,              -- BCrypt password field
    `email`                   varchar(254) unique not null,
    `creationDate`            datetime            not null,
    `currentLikes`            decimal(15, 2)      not null,
    `hasPublicLikes`          boolean             not null default true, -- Whether other user can view likes
    `hasAdminPermissions`     boolean             not null default false,
    `hasModeratorPermissions` boolean             not null default false,
    `isDeleted`               boolean             not null default false
);

create table post
(
    `id`        bigint       not null primary key auto_increment,
    `title`     varchar(100) not null,
    `imageId`   varchar(36), -- Refers to an image named with a UUID, without the file extension
    `text`      text,
    `user`      bigint       not null,
    `timestamp` datetime     not null,
    `deleted`   boolean      not null default false,
    foreign key (user) references user (id)
);

create table comment
(
    `id`        bigint        not null primary key auto_increment,
    `imageId`   varchar(36),
    `text`      varchar(5000) not null,
    `user`      bigint        not null,
    `post`      bigint        not null,
    `timestamp` datetime      not null,
    `deleted`   boolean       not null default false,
    foreign key (user) references user (id),
    foreign key (post) references post (id)
);

-- Likes purchased by the user
create table purchase
(
    `id`          bigint         not null primary key auto_increment,
    `pricePaid`   decimal(13, 4) not null, -- GAAP compatible money field
    `likesBought` int            not null,
    `user`        bigint         not null,
    `timestamp`   datetime       not null,
    foreign key (user) references user (id)
);

-- Likes spent on post
create table postlikes
(
    `id`        bigint not null primary key auto_increment,
    `likesUsed` int    not null,
    `post`      bigint not null,
    `user`      bigint not null,
    unique (post, user),
    foreign key (post) references post (id),
    foreign key (user) references user (id)
);