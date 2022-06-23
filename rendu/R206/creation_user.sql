DROP TABLE IF EXISTS `user`;

CREATE TABLE user
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    username   TEXT,
    password   TEXT CHECK (LENGTH(password) = 128),
    updated_at DATETIME
);

-- pnr:motdepasse
INSERT INTO user (username, password, updated_at)
VALUES ('pnr',
        '72905e7b32d847468edcdbf99f7d218e466cd828300306f1d9f8c3e0512e44fe4394644b581ed52656a2870c9a67c592bc40ca322099aa52bf528c54f9cabde0',
        NOW());

-- admin:admin
INSERT INTO user (username, password, updated_at)
VALUES ('admin',
        'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec',
        NOW());