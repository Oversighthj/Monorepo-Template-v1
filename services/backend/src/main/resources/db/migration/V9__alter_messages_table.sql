ALTER TABLE messages RENAME COLUMN from_id TO sender_id;
ALTER TABLE messages RENAME CONSTRAINT fk_message_from TO fk_message_sender;
ALTER TABLE messages DROP COLUMN to_id;
ALTER TABLE messages RENAME COLUMN body TO content;
ALTER TABLE messages ALTER COLUMN content TYPE TEXT;
ALTER TABLE messages RENAME COLUMN ts TO sent_at;
ALTER TABLE messages ADD COLUMN booking_id BIGINT NOT NULL;
ALTER TABLE messages ADD CONSTRAINT fk_message_booking FOREIGN KEY (booking_id) REFERENCES bookings(id);
