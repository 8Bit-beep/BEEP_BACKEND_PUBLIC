package com.beep.beep.domain.attendLog.domain;


import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.user.domain.enums.RoomCode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Getter
@SuperBuilder
@Table(name = "tb_attend_log")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttendLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private RoomCode currentRoom;

    private LocalDateTime lastUpdated;

    private LocalDateTime currentDt;

    @Enumerated(EnumType.STRING)
    private TimeTable timeTable;
}
