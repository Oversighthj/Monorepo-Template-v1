package com.example.app.booking;

import com.example.app.property.PropertyEntity;
import com.example.app.property.PropertyRepository;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository,
                          PropertyRepository propertyRepository,
                          UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public BookingEntity create(BookingDTO dto) {
        PropertyEntity property = propertyRepository.findById(dto.getPropertyId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "property not found"));
        UserEntity user = userRepository.findById(dto.getUserId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not found"));

        List<BookingEntity> overlaps = bookingRepository.findOverlapping(
            property.getId(), dto.getStart(), dto.getEnd());
        if (!overlaps.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "overlap");
        }

        BookingEntity entity = new BookingEntity();
        entity.setProperty(property);
        entity.setUser(user);
        entity.setStart(dto.getStart());
        entity.setEnd(dto.getEnd());
        entity.setStatus(dto.getStatus() != null ? dto.getStatus().name() : BookingStatus.PENDING.name());
        return bookingRepository.save(entity);
    }

    public List<BookingEntity> findAll(Long propertyId, Long userId) {
        if (propertyId != null && userId != null) {
            return bookingRepository.findByPropertyIdAndUserId(propertyId, userId);
        } else if (propertyId != null) {
            return bookingRepository.findByPropertyId(propertyId);
        } else if (userId != null) {
            return bookingRepository.findByUserId(userId);
        }
        return bookingRepository.findAll();
    }

    public BookingEntity findById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Transactional
    public BookingEntity update(Long id, BookingDTO dto) {
        BookingEntity entity = bookingRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (dto.getPropertyId() != null && !dto.getPropertyId().equals(entity.getProperty().getId())) {
            PropertyEntity property = propertyRepository.findById(dto.getPropertyId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "property not found"));
            entity.setProperty(property);
        }
        if (dto.getUserId() != null && !dto.getUserId().equals(entity.getUser().getId())) {
            UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not found"));
            entity.setUser(user);
        }
        if (dto.getStart() != null) {
            entity.setStart(dto.getStart());
        }
        if (dto.getEnd() != null) {
            entity.setEnd(dto.getEnd());
        }
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus().name());
        }
        return bookingRepository.save(entity);
    }

    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }
}
