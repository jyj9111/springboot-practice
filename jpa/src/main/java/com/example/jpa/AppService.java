package com.example.jpa;

import com.example.jpa.dto.StudentDto;
import com.example.jpa.entities.StudentEntity;
import com.example.jpa.repos.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppService {
    private final AppRepository repository;

    // JpaRepository
    private final StudentRepository studentRepository;

    public AppService(StudentRepository studentRepository, AppRepository repository) {
        this.studentRepository = studentRepository;
        this.repository = repository;
    }

    // CREATE
    public StudentDto createStudent(
            String name,
            Integer age,
            String phone,
            String email
    ) {
        // 새로운(new) Student(Entity)를 만들고 싶다
        StudentEntity newEntity = new StudentEntity();
        newEntity.setName(name);
        newEntity.setAge(age);
        newEntity.setPhone(phone);
        newEntity.setEmail(email);
        // repository.save()
        // save() 메소드는 생성된 엔티티를 반환한다.
        newEntity = this.studentRepository.save(newEntity);
        return StudentDto.fromEntity(newEntity);
    }
    // READ
    public void readStudent(Long id) {
        System.out.println(this.studentRepository.findById(id));
    }

    // READ ALL
    public List<StudentDto> readStudentAll() {
        System.out.println(this.studentRepository.findAll());
        List<StudentEntity> studentEntities = this.studentRepository.findAll();

        // Dto로 분리
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (StudentEntity studentEntity :
            this. studentRepository.findAll()) {
//            StudentDto studentDto = new StudentDto();
//            studentDto.setId(studentEntity.getId());
//            studentDto.setName(studentEntity.getName());
//            studentDto.setEmail(studentEntity.getEmail());
//            studentDtoList.add(studentDto);
            studentDtoList.add(StudentDto.fromEntity(studentEntity));
        }
        return studentDtoList;
    }

    // UPDATE
    public void updateStudent(
            // 어떤 대상을 수정할지 지정해 줘야 함.
            Long id,
            // 수정할 데이터
            String name
    ) {
        // 수정할 Entity 를 회수
        Optional<StudentEntity> optionalEntity =
                this.studentRepository.findById(id);
        // 수정할 Entity를 찾은 경우
        if(optionalEntity.isPresent()) {
            // 실제 객체 회수
            StudentEntity target = optionalEntity.get();
            // 수정할 데이터 적용
            target.setName(name);
            // save
            this.studentRepository.save(target);
        } else {
            // 없으면 없다고 알려줌
            System.out.println("could not find");
        }
    }
    // DELETE
    public void deleteStudent(Long id) {
        Optional<StudentEntity> optionalEntity =
                this.studentRepository.findById(id);
        // 삭제할 엔티티를 찾은 경우
        if(optionalEntity.isPresent()) {
            StudentEntity studentEntity = optionalEntity.get();
            // 삭제
            this.studentRepository.delete(studentEntity);
        } else {
            System.out.println("could not find");
        }
    }
    
    // findAllBy
    public void findAllByTest() {
        System.out.println("-- findAllByOrderByName --");
        List<StudentEntity> studentEntities =
                this.studentRepository.findAllByOrderByName();
        for (int i = 0; i < 5; i++) {
            System.out.println(studentEntities.get(i));
        }
        System.out.println("...");

        System.out.println("-- findAllByOrderByAgeDesc --");
         studentEntities =
                this.studentRepository.findAllByOrderByAge();
        for (int i = 0; i < 5; i++) {
            System.out.println(studentEntities.get(i));
        }
        System.out.println("...");

        System.out.println("-- findAllByAgeLessThan --");
        studentEntities =
                this.studentRepository.findAllByAgeLessThan(21);
        for (int i = 0; i < 5; i++) {
            System.out.println(studentEntities.get(i));
        }
        System.out.println("...");

        System.out.println("-- findAllByPhoneStartingWith --");
        studentEntities =
                this.studentRepository.findAllByPhoneStartingWith("010");
        for (int i = 0; i < 5; i++) {
            System.out.println(studentEntities.get(i));
        }
        System.out.println("...");
    }






    // 주된 비즈니스 로직이 구현되는 공간
    // Controller -> Service
    // Service
    // 1. 데이터베이스 조회
    // 2. Component 사용
    // 3. 모은 데이터를 가지고 응답(의사결정)

//    public List<Object> readStudentAll() {
//        List<Object> queryResult = repository.selectStudentAll();
//        // some business logic
//        return queryResult;
//    }
}
