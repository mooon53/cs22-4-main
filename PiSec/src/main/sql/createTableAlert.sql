CREATE TABLE IF NOT EXISTS alert (
    aid integer PRIMARY KEY AUTOINCREMENT,
    date text NOT NULL,
    time text NOT NULL,
    recording text);