package com.beep.beep.domain.room.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@Table(name = "tb_room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room {
    @Id
    private String code;

    private String name;

    private Integer floor;

    @Enumerated(EnumType.STRING)
    private Club club;

}
