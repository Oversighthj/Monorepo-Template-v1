
/* إعادة تسمية الأعمدة الموجودة */
ALTER TABLE messages RENAME COLUMN from_id TO sender_id;
ALTER TABLE messages RENAME COLUMN body     TO content;
ALTER TABLE messages ALTER COLUMN content TYPE TEXT;
ALTER TABLE messages RENAME COLUMN ts       TO sent_at;

/* إعادة تسمية الـ FK الحالي على sender_id */
ALTER TABLE messages RENAME CONSTRAINT fk_message_from TO fk_message_sender;

/* حذف FK الذى يرتبط بالعمود to_id ثم حذف العمود */
ALTER TABLE messages DROP CONSTRAINT fk_message_to;
ALTER TABLE messages DROP COLUMN to_id;

/* إضافة booking_id وربطه بالحجوزات */
ALTER TABLE messages ADD COLUMN booking_id BIGINT NOT NULL;
ALTER TABLE messages ADD CONSTRAINT fk_message_booking
  FOREIGN KEY (booking_id) REFERENCES bookings(id);
