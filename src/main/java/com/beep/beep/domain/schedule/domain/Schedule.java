package com.beep.beep.domain.schedule.domain;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.DayOfWeek;

@Entity
@Getter
@SuperBuilder
@Table(name = "tb_schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cause; // 원인

    private String room;

    @Enumerated(EnumType.STRING)
    private TimeTable timeTable;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user_id")
    private User user; // 유저 정보

}
