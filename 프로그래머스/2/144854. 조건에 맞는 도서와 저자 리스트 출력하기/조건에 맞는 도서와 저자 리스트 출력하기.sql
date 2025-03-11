SELECT BOOK_ID, AUTHOR_NAME, 
date_format(PUBLISHED_DATE,'%Y-%m-%d') 
FROM BOOK as b
    JOIN AUTHOR as a
    on(a.author_id = b.author_id)
WHERE CATEGORY='경제'
ORDER BY 3
