/*package mx.petcare.mascotas.petcareAPI.controller;
import mx.petcare.mascotas.petcareAPI.model.Report;
import mx.petcare.mascotas.petcareAPI.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/reports")
@Tag(name = "Reports", description = "Reports about that users")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    
    
    @Operation(summary = "Get all reports")
    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

  
    @Operation(summary = "Get a report by id")
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id) {
        Report report = reportService.getReportById(id);
        return ResponseEntity.ok(report);
    }

   
    @Operation(summary = "Register a new report")
    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportService.createReport(report);
    }

   
    @Operation(summary = "Delete a report by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}*/
