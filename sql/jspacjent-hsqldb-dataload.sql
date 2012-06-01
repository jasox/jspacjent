-- -------------------------------------------------------------------
--     Nazwa pliku: jspacjent-hsqldb-dataload.sql
--       Utworzony: 04.12.2006
--           Autor: Janusz Swó³ ©2006
--           Firma: PLL 'LOT' SA
--       Dzia³anie: Testowe dane systemu JSPacjent
--        Podstawa:
-- Etap realizacji: Test
--           Uwagi:
-- -------------------------------------------------------------------
--
--
DELETE FROM przyjecie;
DELETE FROM rodzbad;
DELETE FROM rodzlecz;
DELETE FROM rozpoznanie;
DELETE FROM skierow;
DELETE FROM lekarz;
DELETE FROM pacjent;
DELETE FROM kasach;
--
--
INSERT INTO kasach(id_kasa, opis) VALUES (1, 'Podkarpacka');
INSERT INTO kasach(id_kasa, opis) VALUES (2, 'Mazowiecka');
INSERT INTO kasach(id_kasa, opis) VALUES (3, 'Wojskowa');
--
--
INSERT INTO pacjent(id_pacj, pesel, nazwisko, imie, id_kasa) VALUES ( 1, 11111111001,'Adamowicz', 'Adolf', 1);
INSERT INTO pacjent(id_pacj, pesel, nazwisko, imie, id_kasa) VALUES ( 2, 11111111002,'Bogdanowicz', 'Boles³aw', 2);
INSERT INTO pacjent(id_pacj, pesel, nazwisko, imie, id_kasa) VALUES ( 3, 11111111003,'Æwiek³owski', 'Œwiatos³aw', 2);
INSERT INTO pacjent(id_pacj, pesel, nazwisko, imie, id_kasa) VALUES ( 4, 11111111004,'¥cka', '£ucja', 2);
INSERT INTO pacjent(id_pacj, pesel, nazwisko, imie, id_kasa) VALUES ( 5, 11111111005,'Ñusiñski', '¯elis³aw', 3);
INSERT INTO pacjent(id_pacj, pesel, nazwisko, imie, id_kasa) VALUES ( 6, 11111111006,'¯e³kowski', 'Æwielis³aw', 2);
INSERT INTO pacjent(id_pacj, pesel, nazwisko, imie, id_kasa) VALUES ( 7, 11111111007,'Êckiewicz', 'Qumran', 1);
INSERT INTO pacjent(id_pacj, pesel, nazwisko, imie, id_kasa) VALUES ( 8, 11111111008,'Adamowicz', 'Jan', 2);
INSERT INTO pacjent(id_pacj, pesel, nazwisko, imie, id_kasa) VALUES ( 9, 11111111009,'Adamowicz', 'Jan', 3);
INSERT INTO pacjent(id_pacj, pesel, nazwisko, imie, id_kasa) VALUES (10, 11111111010,'Adamowicz-Œwiatope³kiewiczówna', 'Genowefa-Justyna-Maria', 2);
INSERT INTO pacjent(id_pacj, pesel, nazwisko, imie, id_kasa) VALUES (11, 11111111011,'Fus', 'Maria', 2);
INSERT INTO pacjent(id_pacj, pesel, nazwisko, imie, id_kasa) VALUES (12, 11111111012,'Oborski', 'Ursus', 1);
INSERT INTO pacjent(id_pacj, pesel, nazwisko, imie, id_kasa) VALUES (13, 11111111013,'Zuber', 'Justyna', 2);
--
--
INSERT INTO lekarz(id_lekarz, nazwisko, imie) VALUES (1, 'Kowalski',  'Adolf');
INSERT INTO lekarz(id_lekarz, nazwisko, imie) VALUES (2, 'Nowak',     'Jan');
INSERT INTO lekarz(id_lekarz, nazwisko, imie) VALUES (3, 'Jab³oñski', 'Marian');
--
--
INSERT INTO skierow(id_skier, opis) VALUES ( 1, 'Szpital Nr 1 w P³ocku');
INSERT INTO skierow(id_skier, opis) VALUES ( 2, 'Szpital Nr 2 w Bliznach Du¿ych');
INSERT INTO skierow(id_skier, opis) VALUES ( 3, 'Szpital Nr 3 w W¹chocku');
INSERT INTO skierow(id_skier, opis) VALUES ( 4, 'Przychodnia Nr 1 w Worach Ma³ych');
--
--
INSERT INTO rozpoznanie(id_rozp, opis) VALUES (1, 'Okropny guz mózgu');
INSERT INTO rozpoznanie(id_rozp, opis) VALUES (2, 'Rozleg³y, okropny guz mózgu');
INSERT INTO rozpoznanie(id_rozp, opis) VALUES (3, 'Zaropia³y, okropny guz mózgu');
--
--
INSERT INTO rodzlecz(id_rodzl, opis) VALUES (1, 'Operacyjne');
INSERT INTO rodzlecz(id_rodzl, opis) VALUES (2, 'Zachowawcze');
INSERT INTO rodzlecz(id_rodzl, opis) VALUES (3, 'Takie i owakie');
INSERT INTO rodzlecz(id_rodzl, opis) VALUES (4, 'Tylko owakie');
--
--
INSERT INTO rodzbad(id_rodzb, opis) VALUES (1, 'Histopatologiczne');
INSERT INTO rodzbad(id_rodzb, opis) VALUES (2, '£opatologiczne');
INSERT INTO rodzbad(id_rodzb, opis) VALUES (3, 'Morfopatologiczne');
INSERT INTO rodzbad(id_rodzb, opis) VALUES (4, 'Histo³opatologiczne');
--
--
INSERT INTO przyjecie(id_przyj, nrGlowny, nrOddzial, id_pacj, data_prz, data_wyp, id_lekarz) VALUES ( 1, 101, 11,  1, '2004-01-08 09:00', '2004-03-01 09:01', 1);
INSERT INTO przyjecie(id_przyj, nrGlowny, nrOddzial, id_pacj, data_prz, data_wyp, id_lekarz) VALUES ( 2, 102, 12,  1, '2006-01-04 10:00', '2006-02-06 19:05', 2);
INSERT INTO przyjecie(id_przyj, nrGlowny, nrOddzial, id_pacj, data_prz, data_wyp, id_lekarz) VALUES ( 3, 103, 13,  2, '2006-01-01 11:22', '2006-01-01 16:00', 3);
INSERT INTO przyjecie(id_przyj, nrGlowny, nrOddzial, id_pacj, data_prz, data_wyp, id_lekarz) VALUES ( 4, 104, 14,  1, '2003-01-01 21:59', '2003-02-01 08:30', 2);
INSERT INTO przyjecie(id_przyj, nrGlowny, nrOddzial, id_pacj, data_prz, data_wyp, id_lekarz) VALUES ( 5, 105, 15, 10, '2003-01-01 00:01', '2003-02-01 09:15', 2);
INSERT INTO przyjecie(id_przyj, nrGlowny, nrOddzial, id_pacj, data_prz, data_wyp, id_lekarz) VALUES ( 6, 106, 16,  6, '2003-01-01 05:21', '2003-02-01 09:13', 1);
--
--
INSERT INTO leczenie(id_lecz, id_przyj, data, id_rodzl, opis) VALUES (1, 1, '2004-01-09', 4, 'Coœ siê zdarzy³o w tym leczeniu');
INSERT INTO leczenie(id_lecz, id_przyj, data, id_rodzl, opis) VALUES (2, 1, '2004-01-19', 3, 'Znów coœ siê zdarzy³o w tym leczeniu');
INSERT INTO leczenie(id_lecz, id_przyj, data, id_rodzl, opis) VALUES (3, 1, '2004-01-29', 2, 'I jeszcze raz coœ siê zdarzy³o w tym leczeniu');
--
--
INSERT INTO badanie(id_badan, id_przyj, data, id_rodzb, opis) VALUES (1, 1, '2004-01-09', 4, 'Badanie 1');
INSERT INTO badanie(id_badan, id_przyj, data, id_rodzb, opis) VALUES (2, 1, '2004-01-19', 3, 'Badanie 2');
INSERT INTO badanie(id_badan, id_przyj, data, id_rodzb, opis) VALUES (3, 1, '2004-01-30', 2, 'Badanie 3');







