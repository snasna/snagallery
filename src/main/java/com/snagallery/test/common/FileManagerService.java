package com.snagallery.test.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.snagallery.test.common.FileManagerService;

public class FileManagerService {
	
	public static final String FILE_UPLOAD_PATH = "D:\\웹 개발 김재윤\\web\\20220927\\spring\\snagallery\\upload";
	
private static Logger logger = LoggerFactory.getLogger(FileManagerService.class);
	
	public static String saveFile(int userId, MultipartFile file) {
		
		String directoryName = "/" + userId + "_" + System.currentTimeMillis() + "/";
		
		
		String filePath = FILE_UPLOAD_PATH + directoryName;
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			
			logger.error("FileManagerService - saveFile : file 디렉토리 생성 에러 " + filePath);
			return null;
		}
		
		try {
			byte[] bytes = file.getBytes();
			
			String fileFullPath = filePath + file.getOriginalFilename();
			Path path = Paths.get(fileFullPath);
			Files.write(path, bytes);
			
		} catch (IOException e) {
			e.printStackTrace();
			
			// 파일 저장 실패
			logger.error("FileManagerService - saveFile : 파일 저장 실패");
			return null;
		}
		
		
		return "/images" + directoryName + file.getOriginalFilename();
		
	}
	
	
	
	// 파일 삭제 기능
	public static boolean removeFile(String filePath) { // /images/2_90812098510/test.png
		
		
		if(filePath == null) {
			return true;
		}
		
		String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/images", "");
		
		Path path = Paths.get(realFilePath);
		// 파일이 있는지 확인
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				
				e.printStackTrace();
				return false;
			}
		}
		
		
		path = path.getParent();
		
		// 디렉토리 존재하는
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				
				e.printStackTrace();
				
				return false;
			}
		}
		
		return true;
		
	}

}
