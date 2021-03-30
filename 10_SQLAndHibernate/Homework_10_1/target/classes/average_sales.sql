SELECT
purchaselist.course_name AS course_name,
COUNT(purchaselist.student_name) / (MAX(Month(purchaselist.subscription_date)) - MIN(Month(purchaselist.subscription_date)) + 1) AS average_sales
FROM purchaselist
WHERE YEAR(purchaselist.subscription_date) = 2018
GROUP BY course_name
ORDER BY average_sales DESC