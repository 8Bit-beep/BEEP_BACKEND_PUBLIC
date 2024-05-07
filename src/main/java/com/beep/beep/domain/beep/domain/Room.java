package com.beep.beep.domain.beep.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_room")
@SuperBuilder
public class Room {
    @Id
    private String code;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private String floor;
}
