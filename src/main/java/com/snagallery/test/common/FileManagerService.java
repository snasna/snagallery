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
	
	public final static String FILE_UPLOAD_PATH = "D:\\웹 개발 김재윤\\web\\202200907\\springProject\\snagram\\upload/";
	
	private static Logger logger = LoggerFactory.getLogger(FileManagerService.class);
	
	
	public static String saveFile(int userId, MultipartFile file) {
		
		
		if(file == null) {
			logger.error("FileManagerService - saveFile : file 객체 null");
			return null;
		}

		
		String directoryName = userId + "_" + System.currentTimeMillis() + "/";
		
		
		String filePath = FILE_UPLOAD_PATH + directoryName;
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			
			logger.error("FileManagerService - saveFile : 디렉토리 생성 실패 " + filePath);
			return null;
		}
		
		
		try {
			byte[] bytes = file.getBytes();
		
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
			
		} catch (IOException e) {
			logger.error("FileManagerService - saveFile : 파일 저장 실패");
			e.printStackTrace();
			
			return null;
			
		}
		
		
		
		return "/images/" + directoryName + file.getOriginalFilename();
		
	}
	
	public static boolean removeFile(String filePath) { 
		
		if(filePath == null) {
			
			return false;
		}
		
		String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/images/", "");
		
		Path path = Paths.get(realFilePath);
		
		
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				
				e.printStackTrace();
				
				return false;
			}
		}
		

		
		path = path.getParent();
		
		
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
