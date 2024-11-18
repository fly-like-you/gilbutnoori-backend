package com.ssafy.gilbut.domain.attraction.mapper;

import com.ssafy.gilbut.domain.attraction.model.dto.AttractionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface AttractionMapper {

    List<AttractionDTO> searchAttractionByCourseId(String courseId, Integer contentTypeId, @Param("pageable") Pageable pageable);


    int countAllAttraction();
    int countAttractionByCourseId(@Param("courseId") String courseId);

}
