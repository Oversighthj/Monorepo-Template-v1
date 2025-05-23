-- Only run for PostgreSQL, skip for H2
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM pg_catalog.pg_proc WHERE proname = 'version') THEN
        -- Ensure pgcrypto extension is available for bcrypt hashing
        IF NOT EXISTS (SELECT 1 FROM pg_extension WHERE extname = 'pgcrypto') THEN
            CREATE EXTENSION pgcrypto;
        END IF;

        -- Rehash any stored plain‑text passwords using bcrypt
        UPDATE users
        SET password_hash = crypt(password_hash, gen_salt('bf'))
        WHERE length(password_hash) < 60; -- plain strings are < 60 chars, bcrypt ≥ 60
    END IF;
END$$;
