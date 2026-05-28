package br.com.progirls.api.portal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageResponseDTO<T> {
    private List<T> content;
    private Long totalElements;
    private int totalPages;
    private int currentPage;
    private int size;
    private int numberOfElements;

    public PageResponseDTO(Page<T> page) {
        this.content = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.currentPage = page.getNumber();
        this.size = page.getSize();
        this.numberOfElements = page.getNumberOfElements();
    }
}
