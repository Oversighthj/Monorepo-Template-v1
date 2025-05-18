package com.example.app.property;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public PropertyEntity create(PropertyEntity property) {
        return propertyRepository.save(property);
    }

    public List<PropertyEntity> findAll() {
        return propertyRepository.findAll();
    }

    public List<PropertyEntity> findByOwnerId(Long ownerId) {
        return propertyRepository.findByOwnerId(ownerId);
    }

    public PropertyEntity findById(Long id) {
        return propertyRepository.findById(id).orElse(null);
    }

    public PropertyEntity update(Long id, PropertyEntity property) {
        property.setId(id);
        return propertyRepository.save(property);
    }

    public void delete(Long id) {
        propertyRepository.deleteById(id);
    }
}
