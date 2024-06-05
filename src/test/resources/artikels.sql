insert into artikels(naam,aankoopprijs,verkoopprijs,soort,houdbaarheid,artikelgroepId)
values
('banana',4.50,5.90,'F',3, (select id from artikelgroepen where naam='test'));
-- ("komkomers",2.50,4.10,'F',4(select id from artikelgroepen where naam='test1')