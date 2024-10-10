package org.sopt.seminar1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.sopt.seminar1.Main.UI.InvalidInputException;

public class DiaryFileRepository implements DiaryRepository {

    private static final int ID_INCREMENT_VALUE = 1;
    private final AtomicLong numbering;
    private final DiaryFileAccessor diaryFileAccessor = new DiaryFileAccessor();

    public DiaryFileRepository() {
        numbering = new AtomicLong(diaryFileAccessor.readDiary().size() + diaryFileAccessor.trashRead().size());
    }

    @Override
    public void save(String body) {
        ConcurrentMap<Long, Diary> storage = diaryFileAccessor.readDiary();
        final long id = numbering.addAndGet(ID_INCREMENT_VALUE);
        storage.put(id, new Diary(id, body));
        List<Diary> diaryList = convertMapToList(storage);
        diaryFileAccessor.writeDiary(diaryList);
    }

    @Override
    public List<Diary> findAll() {
        List<Diary> diaryList = new ArrayList<>();
        ConcurrentMap<Long, Diary> storage = diaryFileAccessor.readDiary();

        for (long index = 1L; index <= numbering.longValue(); index++) {
            if (Objects.isNull(storage.get(index))) {
                continue;
            }
            diaryList.add(storage.get(index));
        }
        return diaryList;
    }

    @Override
    public void patch(Long id, String body) {
        ConcurrentMap<Long, Diary> storage = diaryFileAccessor.readDiary();

        isPresentDiary(storage, id);
        Diary diary = storage.get(id);
        diary.setBody(body);
        storage.put(id, diary);
        List<Diary> diaryList = convertMapToList(storage);

        diaryFileAccessor.writeDiary(diaryList);
    }


    @Override
    public void restore() {
        ConcurrentMap<Long, Diary> storage = diaryFileAccessor.readDiary();
        List<Diary> trashStorage = diaryFileAccessor.trashRead();
        for(int i=0; i<trashStorage.size(); i++){
            Diary restoreDiary = trashStorage.get(i);
            Long id = trashStorage.get(i).getId();
            storage.putIfAbsent(id, restoreDiary);
        }
        trashStorage.clear();

        diaryFileAccessor.clearTrash();
        diaryFileAccessor.writeDiary(convertMapToList(storage));
    }

    @Override
    public void delete(Long id) {
        ConcurrentMap<Long, Diary> storage = diaryFileAccessor.readDiary();
        List<Diary> trashStorage = diaryFileAccessor.trashRead();
        isPresentDiary(storage, id);
        trashStorage.add(storage.get(id));
        storage.remove(id, storage.get(id));
        diaryFileAccessor.writeDiary(convertMapToList(storage));
        diaryFileAccessor.trashWrite(trashStorage);
    }

    private void isPresentDiary(Map<Long, Diary> storage, Long id) {
        if (storage.get(id) == null) {
            throw new InvalidInputException();
        }
    }


    private List<Diary> convertMapToList(ConcurrentMap<Long, Diary> storage) {
        return storage.entrySet().stream().sorted(Entry.comparingByKey())
            .map(Entry::getValue).toList();
    }
}
