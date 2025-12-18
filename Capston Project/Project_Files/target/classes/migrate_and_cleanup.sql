-- 1. Migrate role data from 'user' table to 'user_roles' table
-- This ensures that your login works because the application looks at 'user_roles'.
INSERT IGNORE INTO user_roles (user_id, role_id)
SELECT user_id, role 
FROM user 
WHERE role IS NOT NULL;

-- 2. Remove the 'role' column from the 'user' table
-- As per your request to remove the field.
ALTER TABLE user DROP COLUMN role;
