package com.ssafy.gilbut.domain.weather.model.entity;

import java.util.Arrays;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum City {
    SEOUL("Seoul", "서울", "37.5665", "126.9780"),
    BUSAN("Busan", "부산", "35.1796", "129.0756"),
    INCHEON("Incheon", "인천", "37.4563", "126.7052"),
    DAEGU("Daegu", "대구", "35.8714", "128.6014"),
    DAEJEON("Daejeon", "대전", "36.3504", "127.3845"),
    GWANGJU("Gwangju", "광주", "35.1595", "126.8526"),
    SUWON("Suwon", "수원", "37.2636", "127.0286"),
    ULSAN("Ulsan", "울산", "35.5384", "129.3114"),
    JEONJU("Jeonju", "전주", "35.8242", "127.1480"),
    CHEONGJU("Cheongju", "청주", "36.6424", "127.4890"),
    CHANGWON("Changwon", "창원", "35.2280", "128.6811"),
    GOYANG("Goyang", "고양", "37.6583", "126.8320"),
    YONGIN("Yongin", "용인", "37.2410", "127.1775"),
    SEONGNAM("Seongnam", "성남", "37.4449", "127.1389"),
    JEJU("Jeju", "제주", "33.4996", "126.5312");

    private final String name;
    private final String koreanName;
    private final String latitude;
    private final String longitude;

    public static Optional<City> findByName(String name) {
        return Arrays.stream(City.values())
                .filter(city -> city.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}