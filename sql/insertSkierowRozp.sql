-- Dopisanie do tabeli skierowa� warto�ci z tabeli przyj��
INSERT INTO Skierow( Opis )
  SELECT DISTINCT rtrim( Sskierow ) FROM Przyjecie 
  WHERE upper( rtrim( Sskierow ) )
    NOT IN ( SELECT DISTINCT upper( rtrim( Opis ) ) FROM Skierow );

-- Dopisanie do tabeli rozpozna� warto�ci z tabeli przyj��
INSERT INTO Rozpoznanie( Opis )
  SELECT DISTINCT rtrim( Rozpwst )  FROM Przyjecie 
  WHERE upper( rtrim( Rozpwst ) )
    NOT IN ( SELECT DISTINCT upper( rtrim( Opis ) ) FROM Rozpoznanie );
