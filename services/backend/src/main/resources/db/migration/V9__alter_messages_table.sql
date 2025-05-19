
/*****************************************************************************************
 * V9__alter_messages_table.sql
 *
 * Bring the MESSAGES table up to the Phase-B schema:
 *   • rename from_id  → sender_id     (FK → USERS)
 *   • drop to_id      and its FK
 *   • rename body     → content       (TEXT)
 *   • rename ts       → sent_at
 *   • add  booking_id (NOT NULL, FK → BOOKINGS)
 *   • re-create necessary foreign-key constraints
 *****************************************************************************************/
ALTER TABLE messages
    /* remove the old FKs so we can rename columns */
    DROP CONSTRAINT fk_message_from,
    DROP CONSTRAINT fk_message_to,

    /* rename + drop old columns */
    RENAME COLUMN from_id TO sender_id,
    RENAME COLUMN body     TO content,
    RENAME COLUMN ts       TO sent_at,
    DROP   COLUMN to_id,

    /* add new column */
    ADD COLUMN booking_id BIGINT NOT NULL;

-- widen the content column to TEXT
ALTER TABLE messages
    ALTER COLUMN content TYPE TEXT;

-- recreate FK constraints on the new columns
ALTER TABLE messages
    ADD CONSTRAINT fk_message_sender  FOREIGN KEY (sender_id)  REFERENCES users(id),
    ADD CONSTRAINT fk_message_booking FOREIGN KEY (booking_id) REFERENCES bookings(id);