DROP TABLE IF EXISTS `user`;

CREATE TABLE user
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    username   TEXT,
    password   TEXT CHECK (LENGTH(password) = 128),
    updated_at DATETIME
);

-- test:password
INSERT INTO user (username, password, updated_at)
VALUES ('test',
        'b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86',
        NOW());