SELECT sum(SCORE) 'SCORE' ,he.EMP_NO, EMP_NAME, POSITION, EMAIL
FROM  HR_EMPLOYEES as he
    JOIN HR_GRADE as hg
    ON(he.EMP_NO = hg.EMP_NO)
GROUP BY he.EMP_NO
ORDER BY 1 DESC
LIMIT 1