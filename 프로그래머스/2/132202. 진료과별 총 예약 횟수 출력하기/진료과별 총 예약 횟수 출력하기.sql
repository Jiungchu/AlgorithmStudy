SELECT MCDP_CD '진료과코드', COUNT(*) '5월예약건수'
FROM APPOINTMENT
WHERE month(APNT_YMD) = 5 
AND year(APNT_YMD) = 2022
GROUP BY 1
ORDER BY 2, 1