package com.ssafy.gilbut.domain.attraction.mapper;

import com.ssafy.gilbut.domain.attraction.model.entity.Attraction;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

@Mapper
public interface AttractionMapper {

    List<Attraction> searchAttractionByCourseId(
            @Param("courseId") String courseId,
            @Param("contentTypeId") Integer contentTypeId,
            @Param("pageable") Pageable pageable
    );


    int countAttractionByCourseId(
            @Param("courseId") String courseId,
            @Param("contentTypeId") Integer contentTypeId
    );

}
