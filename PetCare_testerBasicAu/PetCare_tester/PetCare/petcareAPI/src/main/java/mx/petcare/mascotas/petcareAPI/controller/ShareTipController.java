package mx.petcare.mascotas.petcareAPI.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.petcare.mascotas.petcareAPI.model.ShareTip;
import mx.petcare.mascotas.petcareAPI.service.ShareTipService;

@RestController
@RequestMapping("/api/sharetips")
@Tag(name = "ShareTip", description = "Manage shared pet care tips")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
public class ShareTipController {

    @Autowired
    private ShareTipService service;

    @Operation(summary = "Get all shared tips with pagination")
    @ApiResponse(responseCode = "200", description = "List all shared tips with pagination", content = {
    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ShareTip.class))) })
    /*
     * @GetMapping(value = "pagination", params = {"page","pagesize"})
     * public List<ShareTip> getAllPagination(@RequestParam(value = "page",
     * defaultValue = "0", required = false)int page,
     * 
     * @RequestParam(value = "pageSize", defaultValue = "5", required = false)int
     * pageSize){
     * List<ShareTip> shareTip = service.getAllPagination(page, pageSize);
     * return shareTip;
     * }
     */
     @GetMapping(value = "/pagination", params = { "page", "pagesize" })
    public List<ShareTip> getAllPagination(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pagesize", defaultValue = "5") int pageSize) {
        return service.getAllPagination(page, pageSize);
    }
                                            
    //public Page<ShareTip> getAll(Pageable pageable) {
      //  return service.getAll(pageable);


    @Operation(summary = "Get a specific shared tip by ID")
    @ApiResponse(responseCode = "200", description = "Found the share tip", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ShareTip.class)) })
    @GetMapping("/{shareId}")
    public ResponseEntity<ShareTip> getByShareId(@PathVariable Integer shareId) {
        ShareTip shareTip = service.getByShareId(shareId);
        if (shareTip == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(shareTip);
    }

    @Operation(summary = "Post a new shared tip")
    @ApiResponse(responseCode = "201", description = "Registered the share tip", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ShareTip.class)) })
    @PostMapping
    public ResponseEntity<ShareTip> register(@RequestBody ShareTip shareTip) {
        ShareTip createdTip = service.save(shareTip);
        return new ResponseEntity<>(createdTip, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a share tip")
    @ApiResponse(responseCode = "200", description = "Update the share tip", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ShareTip.class)) })
    @PutMapping("/{shareId}")
    public ResponseEntity<?> update(@RequestBody ShareTip shareTip, @PathVariable Integer shareId) {
        try {
            ShareTip existingShareTip = service.getByShareId(shareId);
            shareTip.setShareId(existingShareTip.getShareId());
            ShareTip updatedTip = service.save(shareTip);
            return ResponseEntity.ok(updatedTip);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No record found with the provided ID");
        }
    }

    @Operation(summary = "Delete a shared tip")
    @ApiResponse(responseCode = "204", description = "Deleted the shared tip")
    @DeleteMapping("/{shareId}")
    public ResponseEntity<Void> delete(@PathVariable Integer shareId) {
        service.delete(shareId);
        return ResponseEntity.noContent().build();
    }
}
