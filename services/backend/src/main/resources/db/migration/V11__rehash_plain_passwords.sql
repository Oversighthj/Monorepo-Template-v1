-- Ensure pgcrypto extension is available for bcrypt hashing
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_extension WHERE extname = 'pgcrypto') THEN
        CREATE EXTENSION pgcrypto;
    END IF;
END$$;

-- Rehash any stored plain‑text passwords using bcrypt
UPDATE users
SET password_hash = crypt(password_hash, gen_salt('bf'))
WHERE length(password_hash) < 60; -- plain strings are < 60 chars, bcrypt ≥ 60

