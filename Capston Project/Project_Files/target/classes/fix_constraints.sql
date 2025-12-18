-- Fix for Error 1451: Cannot delete or update a parent row
-- This script drops the restrictive Foreign Key and adds one with ON DELETE CASCADE

-- 1. Drop the existing foreign key (Use the name from your error message if different, but this is the standard generated one usually or the one you saw)
-- Based on your screenshot: FK55itppkw3i07do3h7qoclqd4k
ALTER TABLE user_roles DROP FOREIGN KEY FK55itppkw3i07do3h7qoclqd4k;

-- 2. Add the new foreign key with ON DELETE CASCADE
ALTER TABLE user_roles 
ADD CONSTRAINT fk_user_roles_user 
FOREIGN KEY (user_id) REFERENCES user (user_id) 
ON DELETE CASCADE;

-- Now 'DELETE FROM user WHERE ...' will automatically remove the related rows in user_roles.
