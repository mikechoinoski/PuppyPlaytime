package com.choinoski.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Convert from sqlDate to LocalDate. This will be used by Hibernate.
 * Class based on: http://www.thoughts-on-java.org/persist-localdate-localdatetime-jpa/
 *
 * @author mrchoinoski
 */
@Converter(autoApply = true)
public class TimestampAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
        return (locDateTime == null ? null : Timestamp.valueOf(locDateTime));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        return (sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime());
    }
}