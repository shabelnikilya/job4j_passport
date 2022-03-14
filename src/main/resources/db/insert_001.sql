INSERT INTO passports(series, number, created, expired)
VALUES('11', '22', TIMESTAMP '2004-10-19 10:23:54', TIMESTAMP '2004-10-18 10:00:00');
INSERT INTO peoples(name, surname, created, passports_id)
VALUES('ilya', 'Romashkin', TIMESTAMP '1993-05-11 10:00:00', 1);