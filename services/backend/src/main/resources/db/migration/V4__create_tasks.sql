CREATE TABLE tasks (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    booking_id BIGINT NOT NULL,
    cleaner_id BIGINT NOT NULL,
    type VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    due TIMESTAMP NOT NULL,
    CONSTRAINT fk_task_booking FOREIGN KEY (booking_id) REFERENCES bookings(id),
    CONSTRAINT fk_task_cleaner FOREIGN KEY (cleaner_id) REFERENCES users(id)
);
