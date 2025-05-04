package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.entity.edu.Material;
import com.rdlab.education.domain.entity.edu.Notification;
import com.rdlab.education.domain.enums.CourseStatus;
import com.rdlab.education.domain.exceptions.NotFoundException;
import com.rdlab.education.domain.repository.edu.MaterialRepository;
import com.rdlab.education.domain.repository.edu.TagsRepository;
import com.rdlab.education.service.crud.MaterialService;
import com.rdlab.education.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.rdlab.education.domain.enums.NotificationEntityType.MATERIAL;
import static com.rdlab.education.service.crud.impl.ArticleServiceImpl.getCurrentUrl;
import static com.rdlab.education.utils.codes.ProductCode.MAIN_INFO_URI;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final TagsRepository tagsRepository;
    private final NotificationService notificationService;


    public List<Material> getAll() {
        return materialRepository.findAll();
    }

    @Override
    public PageableDto<Material> findAll(int page, int size) {
        Page<Material> materials = materialRepository.findAll(PageRequest.of(page, size));
        return new PageableDto<>(materials.getTotalPages(), materials.getContent());
    }

    public Material getById(Long id) {
        return materialRepository.findById(id).orElseThrow();
    }

    public Material save(Material material) {

        if (!material.getTagIdList().isEmpty()) {
            material.setTags(material.getTagIdList()
                    .stream()
                    .distinct()
                    .map(tagsRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList());
        }

        return materialRepository.save(material);
    }

    public Material update(Long id, Material material) {
        var materialOld = materialRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
        var saved = materialRepository.save(material);
        if (CourseStatus.CREATED.equals(materialOld.getStatus())
            && CourseStatus.PUBLISHED.equals(material.getStatus())) {
            notificationService.createNotification(Notification.builder()
                    .title("Добавлен новый материал")
                    .description("Материал о " + material.getStatus() + " по ссылке: " + getCurrentUrl(MAIN_INFO_URI) + id)
                    .entityId(id)
                    .entity(MATERIAL)
                    .build());

        }
        return saved;
    }

    public void delete(Long id) {
        materialRepository.deleteById(id);
    }
}
