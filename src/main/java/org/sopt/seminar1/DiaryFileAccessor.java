package org.sopt.seminar1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class DiaryFileAccessor {

    private static final String DIARY_FILE_NAME = "./src/main/java/org/sopt/seminar1/diary.txt";
    private static final String TRASH_FILE_NAME = "./src/main/java/org/sopt/seminar1/trash.txt";
    private static final String MODIFY_INFO_FILE_NAME = "./src/main/java/org/sopt/seminar1/modifyInfo.txt";
    private static final String COLONE = ":";
    private static final String DATE_SEPARATOR = "-";
    private static final String NEW_LINE = "\n";

    public DiaryFileAccessor() {
        File diaryFile = new File(DIARY_FILE_NAME);
        File trashFile = new File(TRASH_FILE_NAME);
        File modifyInfoFile = new File(MODIFY_INFO_FILE_NAME);
        createNewFile(diaryFile);
        createNewFile(trashFile);
        createNewFile(modifyInfoFile);
    }

    private void createNewFile(File file) {
        if (!file.exists()) {
            System.out.println(file.getName() + "not exists");
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new IllegalArgumentException("파일 생성 중 오류 발생");
            }
        }
    }


    ModifyInfo readModifyInfo() {
        try (
                FileReader fr = new FileReader(MODIFY_INFO_FILE_NAME);
                BufferedReader bis = new BufferedReader(fr);
        ) {
            String line;
            ModifyInfo modifyInfo = null;
            while ((line = bis.readLine()) != null) {
                modifyInfo = convertToModifyInfo(line);
            }
            if (modifyInfo == null)
                return new ModifyInfo(LocalDate.now(), 0);
            return modifyInfo;
        } catch (IOException e) {
            throw new IllegalArgumentException("파일을 읽는데 실패하였습니다.");
        }
    }

    void writeModifyInfo(ModifyInfo modifyInfo) {
        try (
                FileOutputStream fos = new FileOutputStream(MODIFY_INFO_FILE_NAME);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {
            String str = modifyInfo.getDate().toString() + COLONE +
                    modifyInfo.getModifyCount();
            bos.write(str.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalArgumentException("파일을 작성하는데 실패하였습니다.");
        }
    }

    private ModifyInfo convertToModifyInfo(String line) {
        String[] splitColone = line.split(COLONE);

        String[] splitLocalDate = splitColone[0].split(DATE_SEPARATOR);
        int year = Integer.parseInt(splitLocalDate[0]);
        int month = Integer.parseInt(splitLocalDate[1]);
        int day = Integer.parseInt(splitLocalDate[2]);

        return new ModifyInfo(LocalDate.of(year, month, day), Integer.parseInt(splitColone[1]));
    }

    ConcurrentMap<Long, Diary> readDiary() {
        try (
                FileReader fr = new FileReader(DIARY_FILE_NAME);
                BufferedReader bis = new BufferedReader(fr);
        ) {
            List<Diary> storedDiaries = new ArrayList<>();
            String line;
            while ((line = bis.readLine()) != null) {
                Diary diary = convertToDiary(line);
                storedDiaries.add(diary);
            }

            return storedDiaries.stream()
                    .collect(Collectors.toConcurrentMap(
                            Diary::getId,
                            diary -> diary
                    ));
        } catch (IOException e) {
            throw new IllegalArgumentException("파일을 읽는데 실패하였습니다.");
        }
    }

    private Diary convertToDiary(String s) {
        String[] split = s.split(COLONE);
        String id = split[0];
        String body = split[1];
        return new Diary(Long.parseLong(id), body);
    }


    void writeDiary(List<Diary> diaryList) {
        try (
                FileOutputStream fos = new FileOutputStream(DIARY_FILE_NAME);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {
            for (Diary diary : diaryList) {
                writeByConvertedString(diary, bos);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("파일을 작성하는데 실패하였습니다.");
        }
    }

    private void writeByConvertedString(Diary diary, BufferedOutputStream bos) throws IOException {
        String id = diary.getId().toString();
        String body = diary.getBody();
        String line = id + COLONE + body + NEW_LINE;
        bos.write(line.getBytes(StandardCharsets.UTF_8));
    }


    void trashWrite(List<Diary> diaryList) {
        try (
                FileOutputStream fos = new FileOutputStream(TRASH_FILE_NAME);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {
            for (Diary diary : diaryList) {
                writeByConvertedString(diary, bos);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("파일을 작성하는데 실패하였습니다.");
        }
    }


    List<Diary> trashRead() {
        try (
                FileReader fr = new FileReader(TRASH_FILE_NAME);
                BufferedReader bis = new BufferedReader(fr);
        ) {
            List<Diary> storedDiaries = new ArrayList<>();

            String line;
            while ((line = bis.readLine()) != null) {
                Diary diary = convertToDiary(line);
                storedDiaries.add(diary);
            }
            return storedDiaries;
        } catch (IOException e) {
            throw new IllegalArgumentException("삭제 보관 파일을 읽는데 실패하였습니다.");
        }
    }

    public void clearTrash() {
        try (
                FileOutputStream fos = new FileOutputStream(TRASH_FILE_NAME);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {
            bos.write("".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalArgumentException("삭제 보관 파일을 삭제하는데 실패하였습니다.");
        }
    }
}
