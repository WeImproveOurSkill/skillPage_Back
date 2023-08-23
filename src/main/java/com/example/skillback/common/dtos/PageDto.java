package com.example.skillback.common.dtos;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDto {

    int size;
    int page;
    String sortBy;
    boolean isAsc;
    String keyword;

    public Pageable toPageable() {
        if (Objects.isNull(sortBy)) {
            return PageRequest.of(page, size, Sort.by("createdAt").descending());
        } else {
            return PageRequest.of(page, size, Sort.by(sortBy).descending());
        }
    }

}
