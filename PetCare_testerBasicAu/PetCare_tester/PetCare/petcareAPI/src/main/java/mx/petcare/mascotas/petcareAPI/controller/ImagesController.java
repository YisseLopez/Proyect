package mx.petcare.mascotas.petcareAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.petcare.mascotas.petcareAPI.service.ImagesService;
import mx.petcare.mascotas.petcareAPI.model.ImagesResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@RestController
@RequestMapping("learningResources")
@Tag(name = "Images", description = "Images about tips")
public class ImagesController {

    @Autowired
    private ImagesService service;

    @Operation(summary = "Get all images paginated")
    @GetMapping
    public ResponseEntity<Page<ImagesResource>> getAll(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ImagesResource> images = service.getAllPaginated(pageable);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @Operation(summary = "Get an image by Search ID")
    @GetMapping("{searchId}")
    public ResponseEntity<ImagesResource> getBySearchId(@PathVariable String searchId) {
        ImagesResource resource = service.getBySearchId(searchId);
        return resource != null ? new ResponseEntity<>(resource, HttpStatus.OK)
                                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete an image by Search ID")
    @DeleteMapping("{searchId}")
    public ResponseEntity<String> deleteBySearchId(@PathVariable String searchId) {
        boolean deleted = service.deleteBySearchId(searchId);
        return deleted ? new ResponseEntity<>("Deleted record", HttpStatus.OK)
                       : new ResponseEntity<>("Image not found", HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Upload an image and store metadata")
    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile) {
        try {
            String url = service.upload(multipartFile);
            return new ResponseEntity<>("Image uploaded successfully. URL: " + url, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to upload image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
