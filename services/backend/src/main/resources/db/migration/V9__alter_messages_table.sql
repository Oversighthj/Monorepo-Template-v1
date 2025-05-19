ALTER TABLE messages
    RENAME COLUMN from_id TO sender_id;
ALTER TABLE messages
    RENAME COLUMN body TO content;
ALTER TABLE messages
    RENAME COLUMN ts TO sent_at;
ALTER TABLE messages
    DROP CONSTRAINT fk_message_to,
    DROP COLUMN to_id;
ALTER TABLE messages
    ADD COLUMN booking_id BIGINT NOT NULL;
ALTER TABLE messages
    ADD CONSTRAINT fk_message_booking FOREIGN KEY (booking_id) REFERENCES bookings(id),
    ADD CONSTRAINT fk_message_sender FOREIGN KEY (sender_id) REFERENCES users(id);
