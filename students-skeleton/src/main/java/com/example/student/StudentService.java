package com.example.student;

import com.example.student.dto.StudentDto;
import com.example.student.entity.StudentEntity;
import com.example.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
//@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;
    // 서비스와 레포지토리 연결
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public StudentDto createStudent(StudentDto dto) {
        StudentEntity newStudent = new StudentEntity();
        newStudent.setName(dto.getName());
        newStudent.setAge(dto.getAge());
        newStudent.setPhone(dto.getPhone());
        newStudent.setEmail(dto.getEmail());

        return StudentDto.fromEntity(repository.save(newStudent));
    }

    // READ
    public StudentDto readStudent(Long id) {
        Optional<StudentEntity> optionalEntity = repository.findById(id);
        if(optionalEntity.isPresent()) {
            return StudentDto.fromEntity(optionalEntity.get());
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // READ ALL
    public List<StudentDto> readStudentAll() {
        List<StudentDto> studentDtoList = new ArrayList<>();
        // 1. for-each loop
        for(StudentEntity entity : this.repository.findAll()) {
            studentDtoList.add(StudentDto.fromEntity(entity));
        }
//        // 2. foreach method
//        studentDtoList.forEach(entity -> studentDtoList.add(StudentDto.fromEntity(entity)));
//        return studentDtoList;
//
//        // 3. stream
//        studentDtoList = repository.findAll().stream().map(StudentDto::)
        return  studentDtoList;
    }

    // UPDATE
    public StudentDto updateStudent(Long id, StudentDto dto) {
        Optional<StudentEntity> optionalEntity = repository.findById(id);
        if(optionalEntity.isPresent()) {
            StudentEntity targetEntity = optionalEntity.get();
            targetEntity.setName(dto.getName());
            targetEntity.setAge(dto.getAge());
            targetEntity.setPhone(dto.getPhone());
            targetEntity.setEmail(dto.getEmail());
            return StudentDto.fromEntity(repository.save(targetEntity));
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // DELETE
    public void deleteStudent(Long id) {
//        repository.deleteById(id);
        if(repository.existsById(id))
            repository.deleteById(id);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
