CREATE TABLE post (
    id        BIGINT          NOT NULL,
    post_text      TEXT            NOT NULL,
    author    BIGINT  DEFAULT NULL,
    likes     INT             NOT NULL,
    CONSTRAINT post_pk PRIMARY KEY (id)
);

CREATE TABLE users (
    id          BIGINT               NOT NULL,
    name        VARCHAR(255)         NOT NULL,
    email       VARCHAR(255) DEFAULT NULL,
    password    VARCHAR(255)         NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (id)
);

CREATE TABLE comment (
    id          BIGINT          NOT NULL,
    id_post     BIGINT          NOT NULL REFERENCES post (id) ON UPDATE CASCADE ON DELETE CASCADE,
    comment_text        TEXT            NOT NULL,
    author      BIGINT  DEFAULT NULL,
    likes       INT             NOT NULL,
    CONSTRAINT comment_pk PRIMARY KEY (id)
);

CREATE TABLE admin (
    id BIGINT NOT NULL REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT admin_pk PRIMARY KEY (id)
);