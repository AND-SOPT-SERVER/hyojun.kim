package org.sopt.seminar1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private final Map<Long, String> storage = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();



    public void save(final Diary diary){

        final long id = numbering.addAndGet(1);
        storage.put(id, diary.getBody());
    }

    public List<Diary> findAll(){

        List<Diary> diaryList = new ArrayList<>();
        for(long index =1; index<=numbering.longValue(); index++){

            final String body = storage.get(index);

            if(body == null)
                continue;

            diaryList.add(new Diary(index, body));
        }

        return diaryList;
    }

    public void patch(Long id, String comment){
        storage.put(id, comment);
    }


    public void delete(Long id){
        storage.remove(id);
    }


}
