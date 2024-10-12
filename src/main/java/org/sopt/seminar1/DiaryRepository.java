package org.sopt.seminar1;

import org.sopt.seminar1.Main.UI.InvalidInputException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private static final int ID_INCREMENT_VALUE =1;
    private final Map<Long, String> storage = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();



    void save(final Diary diary){
        final long id = numbering.addAndGet(ID_INCREMENT_VALUE);
        storage.put(id, diary.getBody());
    }

    List<Diary> findAll(){
        List<Diary> diaryList = new ArrayList<>();

        for(long index =1; index<=numbering.longValue(); index++){
            final String body = storage.get(index);

            if(body == null)
                continue;

            diaryList.add(new Diary(index, body));
        }

        return diaryList;
    }

    void patch(Long id, String body){
        isPresentDiary(id);
        storage.put(id, body);
    }

    void delete(Long id)
    {
        isPresentDiary(id);
        storage.remove(id, storage.get(id));
    }

    private void isPresentDiary(Long id) {
        if(storage.get(id)==null)
            throw new InvalidInputException();
    }





}
