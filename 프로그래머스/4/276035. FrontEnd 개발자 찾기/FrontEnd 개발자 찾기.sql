SELECT  ID, EMAIL, FIRST_NAME, LAST_NAME
FROM    DEVELOPERS D
WHERE EXISTS (
    SELECT  1
    FROM    SKILLCODES S
    WHERE   CATEGORY = 'Front End'
    AND     D.SKILL_CODE & CODE > 0
)
ORDER BY 1