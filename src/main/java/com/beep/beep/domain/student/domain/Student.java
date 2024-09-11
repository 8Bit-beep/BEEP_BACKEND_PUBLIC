package com.beep.beep.domain.student.domain;

import com.beep.beep.domain.room.domain.Room;
import com.beep.beep.domain.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@SuperBuilder
@Table(name = "tb_student")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer grade;
    private Integer cls;
    private Integer num;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_study_room_id")
    private Room studyRoom;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_room_id")
    private Room room;

    @LastModifiedDate
    protected LocalDateTime modifiedDate;

    public void updateRoom(Room room){
        this.room = room;
    }
    public void updateDate(){
        this.modifiedDate = LocalDateTime.now();
    }

}
