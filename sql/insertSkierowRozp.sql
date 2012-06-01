-- Dopisanie do tabeli skierowañ wartoœci z tabeli przyjêæ
INSERT INTO Skierow( Opis )
  SELECT DISTINCT rtrim( Sskierow ) FROM Przyjecie 
  WHERE upper( rtrim( Sskierow ) )
    NOT IN ( SELECT DISTINCT upper( rtrim( Opis ) ) FROM Skierow );

-- Dopisanie do tabeli rozpoznañ wartoœci z tabeli przyjêæ
INSERT INTO Rozpoznanie( Opis )
  SELECT DISTINCT rtrim( Rozpwst )  FROM Przyjecie 
  WHERE upper( rtrim( Rozpwst ) )
    NOT IN ( SELECT DISTINCT upper( rtrim( Opis ) ) FROM Rozpoznanie );
