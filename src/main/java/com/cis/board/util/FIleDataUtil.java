package com.cis.board.util;

import com.cis.board.vo.fileVO;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "file")
public class FIleDataUtil {


    private String uploadDir;
    private Path uploadDirPath;


    // 📌 Bean이 생성된 후 실행되도록 설정
    @PostConstruct
    public void init() {
        if (uploadDir == null || uploadDir.isEmpty()) {
            throw new IllegalStateException("파일 저장 경로 (uploadDir) 가 설정되지 않았습니다.");
        }

        this.uploadDirPath = Paths.get(uploadDir);
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs(); // 📌 폴더 없으면 자동 생성
        }
        System.out.println("📂 파일 저장 경로 설정 완료: " + uploadDirPath);
    }

    //배열로 이름 리턴

  //파일 저장시
    public List<fileVO> savefiles (List<MultipartFile> file) throws IOException {
       // String[] files = new String[file.size()];
        //파일 원본이름 변수
        List<fileVO> fileList = new ArrayList<fileVO>();

        for (int i = 0; i < file.size(); i++) {
            MultipartFile multipartFile = file.get(i);

            if (!multipartFile.isEmpty()) { // file이 empty가 아니라면
                // 1. 원본파일 이름
                String originalFilename = multipartFile.getOriginalFilename();
                UUID uuid = UUID.randomUUID(); // 랜덤 문자열 생성

                // 2. 저장 파일 이름 생성
                String saveName = uuid.toString() + "." + originalFilename.split("\\.")[1];

                // 3. 저장 경로 설정
                //String filePath = uploadDirFile + File.separator + saveName;
                Path filePath = uploadDirPath.resolve(saveName); // Path 객체를 통해 경로 생성

                // 4. 파일 저장
                multipartFile.transferTo(filePath.toFile()); // 파일 저장

                // 5. 원본 파일 이름을 배열에 저장
               // files[i] = saveName;

                //메타 데이터 생성
                fileVO filevo = fileVO.builder()
                        .original_name(originalFilename)
                        .save_name(saveName)
                        .file_size((int) multipartFile.getSize())
                        .build();
                fileList.add(filevo);


            }
        }

        return fileList; // 저장된 파일 이름 배열 반환
    }

    //file attached(첨부된파일갯수)
    public int attaced(List<MultipartFile> file) {

        return file.size();
    }

    //파일 삭제시
    public boolean deleteFile(String saveName) {
        File file = new File(uploadDir + File.separator + saveName);
        if (file.exists()) {
            System.out.println("파일삭제함");
            return file.delete(); // 파일 삭제
        }
        System.out.println("파일삭제못함");
        return false; // 파일이 없으면 false 반환
    }



}