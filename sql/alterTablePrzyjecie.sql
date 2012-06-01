-- ALTER TABLE Przyjecie DROP COLUMN Ksg;
ALTER TABLE Przyjecie ADD COLUMN Ksg VARCHAR(4);
--
UPDATE Przyjecie SET Ksg = '07' 
WHERE Ksg IS NULL;



