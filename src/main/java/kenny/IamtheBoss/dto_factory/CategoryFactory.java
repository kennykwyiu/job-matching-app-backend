package kenny.IamtheBoss.dto_factory;

import kenny.IamtheBoss.dto_request.CategoryRequestDTO;
import kenny.IamtheBoss.dto_response.CategoryResponseDTO;
import kenny.IamtheBoss.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryFactory {

    public CategoryResponseDTO toResponseDTO(Category category) {
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    // no save to repo
    public CategoryResponseDTO toResponseDTO(CategoryRequestDTO requestDTO) {

        return CategoryResponseDTO.builder()
                .id(toEntity(requestDTO).getId())
                .name(requestDTO.getName())
                .description(requestDTO.getDescription())
                .build();
    }

    public Category toEntity(CategoryRequestDTO requestDTO) {
        return Category.builder()
                .name(requestDTO.getName())
                .description(requestDTO.getDescription())
                .build();
    }

}
