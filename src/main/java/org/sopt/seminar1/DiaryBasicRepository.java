package org.sopt.seminar1;

import org.sopt.seminar1.Main.UI.InvalidInputException;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryBasicRepository implements DiaryRepository {
    private static final int ID_INCREMENT_VALUE = 1;
    private final Map<Long, Diary> storage = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();
    private List<Diary> trashStorage = new ArrayList<>();
    private ModifyInfo modifyInfo = new ModifyInfo(LocalDate.now(), 0);


    @Override
    public void save(final String body) {
        final long id = numbering.addAndGet(ID_INCREMENT_VALUE);
        storage.put(id, new Diary(id, body));
    }

    @Override
    public List<Diary> findAll() {
        List<Diary> diaryList = new ArrayList<>();
        for (long index = 1; index <= numbering.longValue(); index++) {
            if (Objects.isNull(storage.get(index)))
                continue;

            diaryList.add(storage.get(index));
        }

        return diaryList;
    }

    @Override
    public void patch(Long id, String body, ModifyInfo modifyInfo) {
        isPresentDiary(id);
        Diary diary = storage.get(id);
        diary.setBody(body);
        storage.put(id, diary);
        modifyInfo.addModifyCount();
    }

    @Override
    public void delete(Long id) {
        isPresentDiary(id);
        trashStorage.add(storage.get(id));
        storage.remove(id, storage.get(id));
    }

    @Override
    public ModifyInfo getModifyInfo() {
        return modifyInfo;
    }


    @Override
    public void restore() {
        for (int i = 0; i < trashStorage.size(); i++) {
            Diary restoreDiary = trashStorage.get(i);
            Long id = trashStorage.get(i).getId();
            storage.putIfAbsent(id, restoreDiary);
        }
        trashStorage.clear();
    }


    private void isPresentDiary(Long id) {
        if (storage.get(id) == null)
            throw new InvalidInputException();
    }


}
