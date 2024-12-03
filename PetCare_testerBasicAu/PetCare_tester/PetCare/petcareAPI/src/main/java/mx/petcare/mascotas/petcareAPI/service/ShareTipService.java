package mx.petcare.mascotas.petcareAPI.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import mx.petcare.mascotas.petcareAPI.model.ShareTip;
import mx.petcare.mascotas.petcareAPI.repository.ShareTipRepository;

@Service
@Transactional
public class ShareTipService {

    @Autowired
    private ShareTipRepository repository;

    public List<ShareTip> getAll() {
        return repository.findAll();
    }

    public Page<ShareTip> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ShareTip getByShareId(Integer shareId) {
        return repository.findById(shareId)
                .orElseThrow(() -> new NoSuchElementException("No share tip found with ID: " + shareId));
    }

    public ShareTip save(ShareTip shareTip) {
        return repository.save(shareTip);
    }

    public void delete(Integer shareId) {
        if (!repository.existsById(shareId)) {
            throw new NoSuchElementException("No share tip found with ID: " + shareId);
        }
        repository.deleteById(shareId);
    }

    public List<ShareTip> getAllPagination(int page, int pageSize) {
        PageRequest pageRq = PageRequest.of(page, pageSize);
        Page<ShareTip> ShareTip = repository.findAll(pageRq);
        return ShareTip.getContent();
    }
}
