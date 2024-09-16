package com.beep.beep.domain.student.domain.repository;

import com.beep.beep.domain.room.domain.Room;
import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.user.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentJpaRepo extends JpaRepository<Student, Long> {

    @EntityGraph(attributePaths = {"user"})
    List<Student> findAllByStudyRoom(Room room); // 룸에서 club을 찾아서

    @EntityGraph(attributePaths = {"user"})
    List<Student> findAllByRoom(Room room);

    @EntityGraph(attributePaths = {"user"})
    List<Student> findAllByGradeAndCls(Integer grade, Integer cls);

    @EntityGraph(attributePaths = {"user"})
    Student findByUser(User user);

    @EntityGraph(attributePaths = {"user"})
    void deleteByUser(User user);

    @EntityGraph(attributePaths = {"user"})
    boolean existsByUser(User user);

}
