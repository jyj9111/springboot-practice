package com.example.mybatis.mapper;

import com.example.mybatis.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // MyBatis가 Mapper가 붙은 클래스를 데이터베이스 통신에 사용할 준비
public interface StudentMapper {
    // CRUD
    // INSERT INTO students (name, age, phone, email)
    // VALUES (?, ?, ?, ?);

    @Insert("INSERT INTO students (name, age, phone, email)" + "VALUES (#{name}, #{age}, #{phone}, #{email})")
    void insertStudent(Student student);
    // SELECT * FROM students; 를 실행할 메소드를 만드는데
    // 복수개의 Students를 반환하게 하는 return 타입 -> List<Student>

    @Select("SELECT * FROM students")
    List<Student> selectStudentAll();

    @Select("SELECT * FROM students WHERE id = #{id}")
    Student selectStudent(Long id);

    @Update("UPDATE students SET " +
            "name = #{name}," +
            "age = #{age}," +
            "phone = #{phone}," +
            "email = #{email}," +
            "WHERE id = #{id}")
    void updateStudent(Student student);

    @Delete("DELETE FROM students " +
            "WHERE id = #{id}")
    void deleteStudent(Long id);

    // dml SELECT, INSERT, UPDATE, DELETE
}
