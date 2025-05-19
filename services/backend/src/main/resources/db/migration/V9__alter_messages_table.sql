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

-- 1️⃣  drop old FKs so we can rename / delete columns safely
ALTER TABLE messages DROP CONSTRAINT fk_message_from;
ALTER TABLE messages DROP CONSTRAINT fk_message_to;

-- 2️⃣  rename & remove columns
ALTER TABLE messages RENAME COLUMN from_id TO sender_id;
ALTER TABLE messages DROP    COLUMN to_id;
ALTER TABLE messages RENAME COLUMN body   TO content;
ALTER TABLE messages ALTER  COLUMN content TYPE TEXT;
ALTER TABLE messages RENAME COLUMN ts     TO sent_at;

-- 3️⃣  add the new booking relation
ALTER TABLE messages
  ADD COLUMN booking_id BIGINT NOT NULL;

-- 4️⃣  recreate foreign keys with clear names
ALTER TABLE messages
  ADD CONSTRAINT fk_message_sender
      FOREIGN KEY (sender_id)  REFERENCES users(id);

ALTER TABLE messages
  ADD CONSTRAINT fk_message_booking
      FOREIGN KEY (booking_id) REFERENCES bookings(id);
