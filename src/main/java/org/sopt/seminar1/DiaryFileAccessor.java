package org.sopt.seminar1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class DiaryFileAccessor {

    private static final String DIARY_FILE_NAME = "./src/main/java/org/sopt/seminar1/diary.txt";
    private static  final String TRASH_FILE_NAME = "./src/main/java/org/sopt/seminar1/trash.txt";

    public DiaryFileAccessor() {

        File diaryFile = new File(DIARY_FILE_NAME);
        File trashFile = new File(TRASH_FILE_NAME);
        if(!diaryFile.exists()){
            System.out.println("diary file not exists");
            try{
                diaryFile.createNewFile();
            } catch (IOException e) {
                throw new IllegalArgumentException("파일 생성 중 오류 발생");
            }
        }
        if(!trashFile.exists()){
            System.out.println("diary file not exists");
            try{
                trashFile.createNewFile();
            } catch (IOException e) {
                throw new IllegalArgumentException("파일 생성 중 오류 발생");
            }
        }
    }

    ConcurrentMap<Long, Diary> readDiary(){
        try(
            FileReader fr = new FileReader(DIARY_FILE_NAME);
            BufferedReader bis = new BufferedReader(fr);
            ){
            List<Diary> storedDiaries = new ArrayList<>();
            String line;
            while((line=bis.readLine())!=null) {
                Diary diary = convertToDiary(line);
                storedDiaries.add(diary);
            }

            return storedDiaries.stream()
                .collect(Collectors.toConcurrentMap(
                    Diary::getId,
                    diary -> diary
                ));
        }catch (IOException e){
            throw new IllegalArgumentException("파일을 읽는데 실패하였습니다.");
        }
    }

    private Diary convertToDiary(String s) {
        String[] split = s.split(":");
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
                writeDiary(diary, bos);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("파일을 작성하는데 실패하였습니다.");
        }
    }

    private void writeDiary(Diary diary, BufferedOutputStream bos) throws IOException {
        String id = diary.getId().toString();
        String body = diary.getBody();
        String line = id + ":"+body +"\n";
        bos.write(line.getBytes(StandardCharsets.UTF_8));
    }


    void trashWrite(List<Diary> diaryList) {
        try (
            FileOutputStream fos = new FileOutputStream(TRASH_FILE_NAME);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ) {
            for (Diary diary : diaryList) {
                writeDiary(diary, bos);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("파일을 작성하는데 실패하였습니다.");
        }
    }


    List<Diary> trashRead(){
        try(
            FileReader fr = new FileReader(TRASH_FILE_NAME);
            BufferedReader bis = new BufferedReader(fr);
            ){
            List<Diary> storedDiaries = new ArrayList<>();

            String line;
            while((line=bis.readLine())!=null) {
                Diary diary = convertToDiary(line);
                storedDiaries.add(diary);
            }
            return storedDiaries;
        }catch (IOException e){
            throw new IllegalArgumentException("삭제 보관 파일을 읽는데 실패하였습니다.");
        }
    }

    public void clearTrash() {
        try(
            FileOutputStream fos = new FileOutputStream(TRASH_FILE_NAME);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
        )
        {
            bos.write("".getBytes(StandardCharsets.UTF_8));
        }catch (IOException e){
            throw new IllegalArgumentException("삭제 보관 파일을 삭제하는데 실패하였습니다.");
        }
    }
}
