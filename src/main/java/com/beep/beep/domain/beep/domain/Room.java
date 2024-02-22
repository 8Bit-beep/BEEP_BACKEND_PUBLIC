package com.beep.beep.domain.beep.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_room")
public class Room {
    @Id
    private String code;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private String floor;

    @Builder
    public Room(String code,String name,String floor){
        this.code = code;
        this.name = name;
        this.floor = floor;
    }
}
