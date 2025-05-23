UPDATE users
SET password_hash = crypt(password_hash, gen_salt('bf'))
WHERE length(password_hash) < 60; -- plain strings are < 60 chars, bcrypt ≥ 60

