package org.sopt.diary.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.stereotype.Service;

@Service
public class DateTimeUtil {


    private static final String ZONE = "Asia/Seoul";

    LocalDateTime nowTime(){
        return LocalDateTime.now(ZoneId.of(ZONE));
    }


    boolean isValidTimeToWriteDiary(LocalDateTime createdAt){
        return createdAt.plusMinutes(5).isAfter(nowTime());
    }


}
