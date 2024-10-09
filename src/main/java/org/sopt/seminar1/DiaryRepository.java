package org.sopt.seminar1;

import org.sopt.seminar1.Main.UI.InvalidInputException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private static final int ID_INCREMENT_VALUE =1;
    private final Map<Long, Diary> storage = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();
    private static List<Diary> trashStorage = new ArrayList<>();



    void save(final String body){
        final long id = numbering.addAndGet(ID_INCREMENT_VALUE);
        storage.put(id, new Diary(id, body));
    }

    List<Diary> findAll(){
        List<Diary> diaryList = new ArrayList<>();

        for(long index =1; index<=numbering.longValue(); index++){
            if(Objects.isNull(storage.get(index)))
                continue;

            diaryList.add(storage.get(index));
        }

        return diaryList;
    }

    void patch(Long id, String body){
        isPresentDiary(id);
        Diary diary = storage.get(id);
        diary.setBody(body);
        storage.put(id, diary);
    }

    void delete(Long id)
    {
        isPresentDiary(id);
        trashStorage.add(storage.get(id));
        storage.remove(id, storage.get(id));
    }

    void restore(){
        for(int i=0; i<trashStorage.size(); i++){
            Diary restoreDiary = trashStorage.get(i);
            Long id = trashStorage.get(i).getId();
            storage.putIfAbsent(id, restoreDiary);
        }
        trashStorage.clear();
    }


    private void isPresentDiary(Long id) {
        if(storage.get(id)==null)
            throw new InvalidInputException();
    }




}
