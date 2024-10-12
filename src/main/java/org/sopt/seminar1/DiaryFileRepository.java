package org.sopt.seminar1;

import java.time.LocalDate;
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
        numbering = new AtomicLong(getReadDiary().size() + getReadTrashStorage().size());
    }

    @Override
    public void save(String body) {
        ConcurrentMap<Long, Diary> storage = getReadDiary();
        final long id = numbering.addAndGet(ID_INCREMENT_VALUE);
        storage.put(id, new Diary(id, body));
        List<Diary> diaryList = convertMapToList(storage);
        diaryFileAccessor.writeDiary(diaryList);
    }

    @Override
    public List<Diary> findAll() {
        List<Diary> diaryList = new ArrayList<>();
        ConcurrentMap<Long, Diary> storage = getReadDiary();

        for (long index = 1L; index <= numbering.longValue(); index++) {
            if (Objects.isNull(storage.get(index))) {
                continue;
            }
            diaryList.add(storage.get(index));
        }
        return diaryList;
    }

    @Override
    public void patch(Long id, String body, ModifyInfo modifyInfo) {
        ConcurrentMap<Long, Diary> storage = getReadDiary();

        isPresentDiary(storage, id);

        Diary diary = storage.get(id);
        diary.setBody(body);
        storage.put(id, diary);

        List<Diary> diaryList = convertMapToList(storage);

        modifyInfo.addModifyCount();
        writeToFile(modifyInfo, diaryList);
    }




    @Override
    public void restore() {
        ConcurrentMap<Long, Diary> storage = getReadDiary();
        List<Diary> trashStorage = getReadTrashStorage();

        for(int i=0; i<trashStorage.size(); i++){
            Diary restoreDiary = trashStorage.get(i);
            Long id = trashStorage.get(i).getId();
            storage.putIfAbsent(id, restoreDiary);
        }

        trashStorage.clear();
        diaryFileAccessor.clearTrash();
        diaryFileAccessor.writeDiary(convertMapToList(storage));
    }

    private List<Diary> getReadTrashStorage() {
        return diaryFileAccessor.trashRead();
    }

    @Override
    public void delete(Long id) {
        ConcurrentMap<Long, Diary> storage = getReadDiary();
        List<Diary> trashStorage = getReadTrashStorage();
        isPresentDiary(storage, id);

        trashStorage.add(storage.get(id));
        storage.remove(id, storage.get(id));

        diaryFileAccessor.writeDiary(convertMapToList(storage));
        diaryFileAccessor.trashWrite(trashStorage);
    }


    private ConcurrentMap<Long, Diary> getReadDiary() {
        return diaryFileAccessor.readDiary();
    }

    private void writeToFile(ModifyInfo modifyInfo, List<Diary> diaryList) {
        diaryFileAccessor.writeDiary(diaryList);
        diaryFileAccessor.writeModifyInfo(modifyInfo);
    }


    public ModifyInfo getModifyInfo(){
        return diaryFileAccessor.readModifyInfo();
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
