package com.example.crud;

import com.example.crud.model.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    // 복수의 StudentDTO를 담는 변수
    private List<StudentDTO> studentDTOList = new ArrayList<>();
    private Long nextId = 1L;

    // 테스트를 위한 임시 생성자
    public StudentService() {
        createStudent("alpha", "alpha@gmail.com");
        createStudent("beta", "beta@gmail.com");
        createStudent("ghama", "ghama@gmail.com");
    }

    // 새로운 StudentDTO를 생성하는 메소드
    public StudentDTO createStudent(String name, String email) {
        StudentDTO newStudent = new StudentDTO(
                nextId, name, email
        );

        nextId++;
        studentDTOList.add(newStudent);
        return newStudent;
    }

    public List<StudentDTO> readStudentAll() {
        return studentDTOList;
    }

    // Service에서 단일 StudentDTO를 주는 메소드를 생성
    // 받을 인자는? Long id: DB에서 Primary key 역할

    public StudentDTO readStudent(Long id) {
        // TODO
        // Java 콛
        for (StudentDTO studentDTO : studentDTOList) {
            if (studentDTO.getId().equals(id))
                return studentDTO;
        }

        return null;
//         스트림 사용 방법
//        return studentDTOList
//                .stream()
//                .filter(studentDTO -> studentDTO.getId().equals(id))
//                .findFirst()
//                .orElse(null);
    }
    // 어떤 학생 데이터를 갱신할 것인지
    // 그 학생의 갱신될 데이터
    public StudentDTO updateStudent(Long id, String name, String email) {
        // 하나의 StudentDTO를 찾아서
        int target = -1;
        // studentDTOList 크기만큼 반복
        for (int i = 0; i < studentDTOList.size(); i++) {
            // id가 동일한 studentDTO를 찾았으면
            if(studentDTOList.get(i).getId().equals(id)) {
                // 인덱스 기록
                target = i;
                // 반복 종료
                break;
            }
        }
        // 대상을 찾았 다면
        if(target != -1) {
            // name과 email을 바꿔주자
            studentDTOList.get(target).setName(name);
            studentDTOList.get(target).setName(email);
            return studentDTOList.get(target);
        }
        else return null;
    }
    public boolean deleteStudent(Long id) {
        int target = -1;
        // 학생 리스트 탐색
        for (int i = 0; i < studentDTOList.size(); i++) {
            if(studentDTOList.get(i).getId().equals(id)) {
                target = i;
                break;
            }
        }

        // 검색 성공시 true 반환
        if(target != -1) {
            studentDTOList.remove(target);
            return true;
        }
        // 없거나 실패시 false 반환
        else return false;
    }
}
