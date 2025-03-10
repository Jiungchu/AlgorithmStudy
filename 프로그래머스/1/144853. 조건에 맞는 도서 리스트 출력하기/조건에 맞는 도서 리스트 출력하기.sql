SELECT  BOOK_ID, date_format(PUBLISHED_DATE,'%Y-%m-%d') as PUBLISHED_DATE
FROM BOOK
WHERE YEAR(PUBLISHED_DATE)=2021 AND CATEGORY='인문'
ORDER BY PUBLISHED_DATE