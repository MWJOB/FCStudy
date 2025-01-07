package org.example.bookmanager.domain.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.bookmanager.repository.dto.BookStatus;

@Converter(autoApply = true)
public class BookStatusConverter implements AttributeConverter<BookStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BookStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public BookStatus convertToEntityAttribute(Integer dbData) {
        return dbData != null ? new BookStatus(dbData) : null;
    }
}
