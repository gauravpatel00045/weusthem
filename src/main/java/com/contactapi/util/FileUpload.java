package com.contactapi.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.contactapi.enums.ResponseStatusEnum;
import com.contactapi.exceptionhandler.ResponseException;

/**
 * Refer to
 * <a href= "https://www.bezkoder.com/spring-boot-upload-multiple-files/">File
 * upload</a>
 * 
 * This class use to upload the files such as .pdf, .jpg, .doc
 */
@Component
public class FileUpload {

	Logger logger = LoggerFactory.getLogger(FileUpload.class);

	private String parentDirectoryName = "uploads/";

	private Path root = Paths.get(parentDirectoryName);

	/**
	 * To initialize the directory.
	 * 
	 * @param directoryName Directory name such as task, project
	 */
	public void initDirectory(String directoryName) {
		if (directoryName != null && !directoryName.isEmpty()) {
			new StringBuilder().append(parentDirectoryName).append(directoryName);
			root = Paths.get(new StringBuilder().append(parentDirectoryName).append(directoryName).toString());
		}
		try {
			if (Files.notExists(root)) {
				// It creates the parent directory and then the child directory
				Files.createDirectories(root);
			}
		} catch (IOException e) {
			throw new ResponseException(e, "Could not initialize folder for upload!", ResponseStatusEnum.BAD_REQUEST);
		}
	}

	/**
	 * To save the files.
	 * 
	 * @param file     File that needs to be saved
	 * @param fileName Custom file name such as organization's short name that
	 *                 append with the original file name like Company name is
	 *                 Facebook then pass fileName like Fb: Example: Fb_fileName
	 * @throws ResponseException It throws an exception if any issues
	 */
	public void save(MultipartFile file, String fileName) {
		try {
			// for check unique file
//			Files.copy(file.getInputStream(),
//					this.root.resolve((fileName != null) ? fileName : file.getOriginalFilename()));
// 			Below function use if we want to store duplicate files.
			Files.copy(file.getInputStream(), this.root.resolve((fileName != null) ? fileName : file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			logger.error("saveM " + e.getMessage());
			logger.error("saveLM " + e.getLocalizedMessage());
			throw new ResponseException(e, "Could not store the file. Error: ", ResponseStatusEnum.BAD_REQUEST);
		}
	}

	/**
	 * To load the file.
	 * 
	 * @param fileName file that needs to be read from the directory.
	 */
	public Resource load(String fileName) {
		try {
			Path file = root.resolve(fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new ResponseException(new RuntimeException(), "Could not read the file!",
						ResponseStatusEnum.BAD_REQUEST);
			}
		} catch (MalformedURLException e) {
			throw new ResponseException(e, " Error: ", ResponseStatusEnum.BAD_REQUEST);
		}
	}

	/**
	 * To load all the files from the directory
	 */
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new ResponseException(e, "Could not load the files!", ResponseStatusEnum.BAD_REQUEST);
		}
	}

	/**
	 * To remove all the files from the directory
	 */
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

}
