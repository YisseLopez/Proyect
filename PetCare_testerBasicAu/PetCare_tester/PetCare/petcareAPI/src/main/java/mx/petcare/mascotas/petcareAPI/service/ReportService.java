package mx.petcare.mascotas.petcareAPI.service;
import mx.petcare.mascotas.petcareAPI.model.Report;
import mx.petcare.mascotas.petcareAPI.repository.ReportRepository;
import mx.petcare.mascotas.petcareAPI.exception.ReportNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReportService {

    private final ReportRepository reportRepository;

    
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

   
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

   
    public Report getReportById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException("Report not found with id: " + id));
    }

    
    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    
    public void deleteReport(Long id) {
        if (!reportRepository.existsById(id)) {
            throw new ReportNotFoundException("Cannot delete. Report not found with id: " + id);
        }
        reportRepository.deleteById(id);
    }
    
}
