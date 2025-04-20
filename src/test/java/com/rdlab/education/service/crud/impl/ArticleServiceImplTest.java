//package com.rdlab.education.service.crud.impl;
//
//import com.rdlab.education.domain.dto.article.ArticleDto;
//import com.rdlab.education.domain.entity.edu.Articles;
//import com.rdlab.education.domain.entity.image.Base64Images;
//import com.rdlab.education.domain.enums.CourseStatus;
//import com.rdlab.education.domain.mapper.ArticleMapper;
//import com.rdlab.education.domain.repository.edu.ArticlesRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class ArticleServiceImplTest {
//
//    @Mock
//    private ArticlesRepository repo;
//
//    @Mock
//    private ArticleMapper mapper;
//
//    @InjectMocks
//    private ArticleServiceImpl service;
//
//    private ArticleDto dto;
//    private Articles entity;
//
//    @BeforeEach
//    void setUp() {
//        dto = ArticleDto.builder()
//                .id(null)
//                .title("title")
//                .subTitle("sub")
//                .description("desc")
//                .status(CourseStatus.CREATED)
//                .file("base64==")
//                .build();
//
//        entity = new Articles(null, "title", "sub", "desc", CourseStatus.CREATED, null);
//    }
//
//    @Test
//    void create() {
//        var entityWithImage = new Articles(null, "title", "sub", "desc", CourseStatus.CREATED, new Base64Images("base64=="));
//        var savedEntity = new Articles(1L, "title", "sub", "desc", CourseStatus.CREATED, new Base64Images("base64=="));
//        var expectedDto = dto.toBuilder().id(1L).build();
//
//        when(mapper.toEntity(dto)).thenReturn(entity);
//        when(repo.save(entityWithImage)).thenReturn(savedEntity);
//        when(mapper.toDto(savedEntity)).thenReturn(expectedDto);
//
//        var result = service.create(dto);
//
//        assertThat(result).isEqualTo(expectedDto);
//    }
//
//    @Test
//    void findById() {
//        var entity = new Articles(1L, "t", "s", "d", CourseStatus.PUBLISHED, null);
//        var expectedDto = ArticleDto.builder()
//                .id(1L).title("t").subTitle("s").description("d")
//                .status(CourseStatus.PUBLISHED)
//                .file(null).build();
//
//        when(repo.findById(1L)).thenReturn(Optional.of(entity));
//        when(mapper.toDto(entity)).thenReturn(expectedDto);
//
//        var result = service.findById(1L);
//
//        assertThat(result).contains(expectedDto);
//    }
//
//    @Test
//    void update() {
//        var original = new Articles(1L, "Old", "s", "d", CourseStatus.CREATED, null);
//        var updatedDto = dto.toBuilder().title("New").status(CourseStatus.PUBLISHED).build();
//        var updatedEntity = new Articles(null, "New", "s", "d", CourseStatus.PUBLISHED, null);
//        var updatedWithFile = new Articles(1L, "New", "s", "d", CourseStatus.PUBLISHED, new Base64Images("base64=="));
//
//        when(repo.findById(1L)).thenReturn(Optional.of(original));
//        when(mapper.toEntity(updatedDto)).thenReturn(updatedEntity);
//        when(repo.save(any())).thenReturn(updatedWithFile);
//        when(mapper.toDto(updatedWithFile)).thenReturn(updatedDto.toBuilder().id(1L).build());
//
//        var result = service.update(1L, updatedDto);
//
//        assertThat(result.getTitle()).isEqualTo("New");
//        assertThat(result.getStatus()).isEqualTo(CourseStatus.PUBLISHED);
//    }
//
//    @Test
//    void delete() {
//        when(repo.existsById(1L)).thenReturn(true);
//        service.delete(1L);
//        verify(repo).deleteById(1L);
//    }
//
//    @Test
//    void findAll() {
//        var list = List.of(
//                new Articles(1L, "t", "s", "d", CourseStatus.CREATED, null)
//        );
//        var dtoList = List.of(
//                ArticleDto.builder()
//                        .id(1L).title("t").subTitle("s").description("d")
//                        .status(CourseStatus.CREATED).file(null).build()
//        );
//
//        when(repo.findAll()).thenReturn(list);
//        when(mapper.toDto(list.get(0))).thenReturn(dtoList.get(0));
//
//        var result = service.findAll();
//
//        assertThat(result).hasSize(1).containsExactly(dtoList.get(0));
//    }
//}