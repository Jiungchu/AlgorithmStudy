SELECT  ID,
if(percent <= 0.25, 'CRITICAL', if(
  percent <= 0.5, 'HIGH', if(
  percent <= 0.75, 'MEDIUM', 'LOW'))) COLONY_NAME
FROM    (
    SELECT ID, 
    PERCENT_RANK() OVER (ORDER BY SIZE_OF_COLONY DESC) percent
    FROM ECOLI_DATA
) E
ORDER BY ID