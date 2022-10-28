CREATE TABLE IF NOT EXISTS user (
    uid integer PRIMARY KEY AUTOINCREMENT,
    login text UNIQUE NOT NULL,
    password text NOT NULL,
    salt text NOT NULL);