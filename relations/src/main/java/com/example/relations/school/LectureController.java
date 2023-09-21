package com.example.relations.school;

import com.example.relations.school.entity.Instructor;
import com.example.relations.school.entity.Lecture;
import com.example.relations.school.repo.InstructorRepository;
import com.example.relations.school.repo.LectureRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("lectures")
@RequiredArgsConstructor
public class LectureController {
    private final LectureRepository lectureRepository;
    private final InstructorRepository instructorRepository;

    // 강의에 강사를 배정한다
    @PutMapping("{id}/instructor/{instructorId}")
    // 응답 바디가 없을 것이다.
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLectureInstructor(
            @PathVariable("id") Long id,
            @PathVariable("instructorId") Long instructorID
    ) {
        Optional<Lecture> optionalLecture = lectureRepository.findById(id);
        if (optionalLecture.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorID);
        if (optionalInstructor.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Lecture lecture = optionalLecture.get();
        Instructor instructor = optionalInstructor.get();

        // 그냥 Java 객체 쓰듯이
        lecture.setInstructor(instructor);
        lectureRepository.save(lecture);
    }

    // id 강의의 강사를 반환하는 엔드포인트다.
    // @Getmapping("{id}/instructor")
    public void readLectureInstructor(Long id) {
        Optional<Lecture> optionalLecture = lectureRepository.findById(id);
        if (optionalLecture.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Lecture lecture = optionalLecture.get();
        // 해당 강의에 배정된 강사를 불러오기
        Instructor instructor = lecture.getInstructor();

        log.info(optionalLecture.get().getInstructor().toString());
    }
}
