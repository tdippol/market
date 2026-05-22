UPDATE MUIPROMO.MENU
SET  URL = REPLACE( URL, 'tmonequality', 'tmoneprod')
WHERE URL LIKE '%tmonequality%';

