CREATE TRIGGER IF NOT EXISTS TGR_UpdatePersonName AFTER INSERT
ON Person FOR EACH ROW UPDATE PERSON SET NAME = 'Neymar Santos' where NAME = 'Zeeshan Arif'



