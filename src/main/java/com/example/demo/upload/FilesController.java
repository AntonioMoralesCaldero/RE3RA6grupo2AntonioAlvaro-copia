//
////package com.example.demo.upload;
////
////import java.io.IOException;
////import java.util.stream.Collectors;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.core.io.Resource;
////import org.springframework.http.HttpHeaders;
////import org.springframework.http.ResponseEntity;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.web.bind.annotation.ExceptionHandler;
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.PathVariable;
////import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.bind.annotation.RequestParam;
////import org.springframework.web.bind.annotation.ResponseBody;
////import org.springframework.web.multipart.MultipartFile;
////import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
////import org.springframework.web.servlet.mvc.support.RedirectAttributes;
////
////import com.example.demo.upload.StorageFileNotFoundException;
////import com.example.demo.upload.StorageService;
////
////@Controller 
////public class FilesController {
////
////	private final StorageService storageService;
////
////	@Autowired
////	public FilesController(StorageService storageService) {
////		this.storageService = storageService;
////	}
////
////	@GetMapping("/")
////	public String listUploadedFiles(Model model) throws IOException {
////
////		model.addAttribute("files", storageService.loadAll().map(
////				path -> MvcUriComponentsBuilder.fromMethodName(FilesController.class,
////						"serveFile", path.getFileName().toString()).build().toUri().toString())
////				.collect(Collectors.toList()));
////
////		return "uploadForm";
////	}
////
////	@GetMapping("/files/{filename:.+}")
////	@ResponseBody
////	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
////
////		Resource file = storageService.loadAsResource(filename);
////
////		if (file == null)
////			return ResponseEntity.notFound().build();
////
////		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
////				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
////	}
////
////	@PostMapping("/")
////	public String handleFileUpload(@RequestParam("file") MultipartFile file,
////			RedirectAttributes redirectAttributes) {
////
////		storageService.store(file);
////		redirectAttributes.addFlashAttribute("message",
////				"You successfully uploaded " + file.getOriginalFilename() + "!");
////
////		return "redirect:/";
////	}
////
////	@ExceptionHandler(StorageFileNotFoundException.class)
////	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
////		return ResponseEntity.notFound().build();
////	}
////
////}
//package com.example.demo.upload;
//
//import java.io.IOException;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.example.demo.upload.StorageFileNotFoundException;
//import com.example.demo.upload.StorageService;
//
//@Controller 
//public class FilesController {
//
//	private final StorageService storageService;
//
//	@Autowired
//	public FilesController(StorageService storageService) {
//		this.storageService = storageService;
//	}
//
//	@GetMapping("/update")
//	public String listUploadedFiles(Model model) throws IOException {
//
//		model.addAttribute("files", storageService.loadAll().map(
//				path -> MvcUriComponentsBuilder.fromMethodName(FilesController.class,
//						"serveFile", path.getFileName().toString()).build().toUri().toString())
//				.collect(Collectors.toList()));
//
//		return "uploadForm";
//	}
//
//	@GetMapping("/files/{filename:.+}")
//	@ResponseBody
//	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//		Resource file = storageService.loadAsResource(filename);
//
//		if (file == null)
//			return ResponseEntity.notFound().build();
//
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
//	}
//
//	@PostMapping("/upload")
//	public String handleFileUpload(@RequestParam("file") MultipartFile file,
//			RedirectAttributes redirectAttributes) {
//
//		storageService.store(file);
//		redirectAttributes.addFlashAttribute("message",
//				"You successfully uploaded " + file.getOriginalFilename() + "!");
//
//		return "redirect:/";
//	}
//
//	@ExceptionHandler(StorageFileNotFoundException.class)
//	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
//		return ResponseEntity.notFound().build();
//	}
//
//}
