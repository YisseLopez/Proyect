package mx.petcare.mascotas.petcareAPI.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.multipart.MultipartFile;

import mx.petcare.mascotas.petcareAPI.model.ImagesResource;
import mx.petcare.mascotas.petcareAPI.repository.ImagesRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Service
@Transactional
public class ImagesService {

    @Autowired
    private ImagesRepository repo;

    @Autowired
    private MongoTemplate mongoTemplate;

    public String upload(MultipartFile multipartFile) {
        try {
            String fileName = UUID.randomUUID().toString().concat(getExtension(multipartFile.getOriginalFilename()));
            File file = convertToFile(multipartFile, fileName);
            String url = uploadFileToFirebase(file, fileName);
            file.delete();

            ImagesResource resource = new ImagesResource();
            resource.setSearchId(UUID.randomUUID().toString());
            resource.setUrl(url);
            resource.setValidated(true);

            repo.save(resource);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return "The image could not be uploaded";
        }
    }

    public Page<ImagesResource> getAllPaginated(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public ImagesResource getBySearchId(String searchId) {
        Query query = new Query(Criteria.where("searchId").is(searchId));
        return mongoTemplate.findOne(query, ImagesResource.class);
    }

    public boolean deleteBySearchId(String searchId) {
        Query query = new Query(Criteria.where("searchId").is(searchId));
        ImagesResource resource = mongoTemplate.findAndRemove(query, ImagesResource.class);
        return resource != null;
    }

    public void add(ImagesResource resource) {
        if (resource == null || resource.getUrl() == null || resource.getUrl().isEmpty()) {
            throw new IllegalArgumentException("Image resource must not be null and URL must be valid");
        }
        repo.save(resource);
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }

    private String uploadFileToFirebase(File file, String fileName) throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("firebase-private-key.json");
    if (inputStream == null) {
        throw new FileNotFoundException("Firebase private key file not found");
    }

    Credentials credentials = GoogleCredentials.fromStream(inputStream);
    Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

    
    String bucketName = "petcare-3b141.appspot.com";
    BlobId blobId = BlobId.of(bucketName, fileName);
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
    storage.create(blobInfo, Files.readAllBytes(file.toPath()));

    
    return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
            bucketName, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
}

}
