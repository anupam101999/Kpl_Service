package com.kpl.registration.config;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class UTCtoISTConverter implements Converter<LocalDateTime, LocalDateTime> {
    @Override
    public LocalDateTime convert(MappingContext<LocalDateTime, LocalDateTime> context) {
        LocalDateTime utcDateTime = context.getSource();

        // Set the timezone for Indian Standard Time (IST)
        ZoneId indianTimeZone = ZoneId.of("Asia/Kolkata");

        // Convert the UTC date and time to Indian Standard Time
        ZonedDateTime indianDateTime = utcDateTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(indianTimeZone);

        return indianDateTime.toLocalDateTime();
    }
}
