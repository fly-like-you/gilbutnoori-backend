package com.ssafy.gilbut.attraction.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishList {
    private Long id;
    private Long attractionId;
    private Long travelId;
}
