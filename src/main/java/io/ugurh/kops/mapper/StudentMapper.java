package io.ugurh.kops.mapper;

import io.ugurh.kops.dto.StudentDto;
import io.ugurh.kops.entity.Student;
import org.mapstruct.factory.Mappers;

public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student mapDtoToEntity(StudentDto studentDto);

    StudentDto mapEntityToDto(Student student);
}
