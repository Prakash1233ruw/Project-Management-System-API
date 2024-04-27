CREATE TABLE IF NOT EXISTS Project (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL,
    DESCRIPTION TEXT NOT NULL,
    STARTDATE DATE NOT NULL,
    ENDDATE DATE NOT NULL
);
