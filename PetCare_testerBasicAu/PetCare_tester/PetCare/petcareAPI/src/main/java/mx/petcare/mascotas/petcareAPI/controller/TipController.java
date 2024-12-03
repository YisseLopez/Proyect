/*package mx.petcare.mascotas.petcareAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.NoSuchElementException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.petcare.mascotas.petcareAPI.model.Tip;
import mx.petcare.mascotas.petcareAPI.service.TipService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@RestController
@RequestMapping("tips")
@Tag(name = "Tips", description = "Tips that users write")
@CrossOrigin(origins = "*")
public class TipController {

    @Autowired
    private TipService service;

    @Operation(summary = "Get all tip")
    @ApiResponse(responseCode = "200", description = "Tips retrieved", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Tip.class))) })
    @GetMapping
    public ResponseEntity<List<Tip>> getAll() {
        List<Tip> tip = service.getAll();
        return new ResponseEntity<>(tip, HttpStatus.OK);
    }

    @Operation(summary = "Get all tips paginated")
    @ApiResponse(responseCode = "200", description = "Tips retrieved", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Tip.class))) })
    @GetMapping("/paginated")
    public ResponseEntity<List<Tip>> getAll(@RequestParam(defaultValue = "0") int page, 
                                             @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Tip> tipPage = service.getAll(pageable);
        return new ResponseEntity<>(tipPage.getContent(), HttpStatus.OK);
    }

    @Operation(summary = "Get tip by ID")
    @ApiResponse(responseCode = "200", description = "Tip retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tip.class)))
    @ApiResponse(responseCode = "404", description = "Tip not found")
    @GetMapping("{idTip}")
    public ResponseEntity<Tip> getByIdTip(@PathVariable Integer idTip) {
        Tip tip = service.getByidTip(idTip); 
        if (tip != null) {
            return new ResponseEntity<>(tip, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create a new tip")
    @ApiResponse(responseCode = "201", description = "Tip created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tip.class)))
    @PostMapping
    public ResponseEntity<Tip> create(@RequestBody Tip tip) {
        Tip createdTip = service.save(tip);
        return new ResponseEntity<>(createdTip, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a tip")
    @ApiResponse(responseCode = "200", description = "Tip updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tip.class)))
    @ApiResponse(responseCode = "404", description = "Tip not found")
    @PutMapping("{idTip}")
    public ResponseEntity<Tip> update(@RequestBody Tip tipDetails, @PathVariable Integer idTip) {
      
        Tip existingTip = service.getByidTip(idTip);

        existingTip.setTitle(tipDetails.getTitle());
        existingTip.setDescription(tipDetails.getDescription());
        existingTip.setDate(tipDetails.getDate()); 

        Tip updatedTip = service.save(existingTip);
        return new ResponseEntity<>(updatedTip, HttpStatus.OK);
    }

    @Operation(summary = "Delete a tip")
    @ApiResponse(responseCode = "200", description = "Tip deleted")
    @ApiResponse(responseCode = "404", description = "Tip not found")
    @DeleteMapping("{idTip}")
    public ResponseEntity<String> delete(@PathVariable Integer idTip) {
        try {
            service.delete(idTip);
            return new ResponseEntity<>("Tip deleted succesfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Tip not found", HttpStatus.NOT_FOUND);
        }
    }
}*/
