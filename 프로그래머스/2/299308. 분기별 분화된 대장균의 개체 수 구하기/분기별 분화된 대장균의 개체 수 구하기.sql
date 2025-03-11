SELECT concat(if(month(DIFFERENTIATION_DATE)<=3,1,
                  if(month(DIFFERENTIATION_DATE)<=6,2,
                    if(month(DIFFERENTIATION_DATE)<=9,3,4))),'Q') 'QUARTER',
COUNT(*) 'ECOLI_COUNT'                
FROM ECOLI_DATA 
GROUP BY 1
ORDER BY 1

