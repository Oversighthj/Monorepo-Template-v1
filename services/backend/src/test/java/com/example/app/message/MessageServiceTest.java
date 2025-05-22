package com.example.app.message;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.app.booking.BookingEntity;
import com.example.app.booking.BookingRepository;
import com.example.app.booking.BookingStatus;
import com.example.app.property.PropertyEntity;
import com.example.app.property.PropertyRepository;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import com.example.app.user.UserRole;          // ⬅️  الاستيراد الناقص
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class MessageServiceTest {

  @Autowired MessageRepository messageRepo;
  @Autowired BookingRepository bookingRepo;
  @Autowired PropertyRepository propertyRepo;
  @Autowired UserRepository userRepo;

  @Test
  void create_and_list_by_booking() {
    // 1️⃣ مستخدم ضيف صالح
    UserEntity guest =
        userRepo.save(new UserEntity(null, UserRole.GUEST, "g@example.com", "x"));

    // 2️⃣ مالك + عقار + حجز
    UserEntity owner =
        userRepo.save(new UserEntity(null, UserRole.ADMIN, "owner@example.com", "x"));
    PropertyEntity prop = propertyRepo.save(new PropertyEntity(null, "Prop", "addr", owner));
    BookingEntity booking =
        bookingRepo.save(
            new BookingEntity(
                null,
                prop,
                guest,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1),
                BookingStatus.CONFIRMED));

    // 3️⃣ خدمة الرسائل
    MessageService svc = new MessageService(messageRepo);

    MessageEntity msg =
        new MessageEntity(
            null, booking, guest, "hello", LocalDateTime.now());

    svc.create(msg);

    assertThat(svc.list(booking.getId())).hasSize(1);
    assertThat(svc.list(null)).hasSize(1);
  }
}
