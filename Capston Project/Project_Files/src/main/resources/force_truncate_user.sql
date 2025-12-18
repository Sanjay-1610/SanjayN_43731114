-- Force Truncate User Table
-- TRUNCATE is normally blocked by Foreign Keys. We must disable checks temporarily.

SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE TABLE user; 
SET FOREIGN_KEY_CHECKS = 1;

SELECT 'User table truncated successfully' as result;
