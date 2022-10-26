CREATE TABLE IF NOT EXISTS alert (
    aid integer PRIMARY KEY AUTOINCREMENT,
    date_time datetime NOT NULL,
    recording text);