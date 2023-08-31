package kenny.IamtheBoss.service;

import jakarta.transaction.Transactional;
import kenny.IamtheBoss.dto_request.CategoryRequestDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.model.Category;
import kenny.IamtheBoss.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryService extends AbstractBaseService<Category, Long> {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Category updateCategory(Category category, CategoryRequestDTO requestDTO) {
        category.setName(requestDTO.getName());
        category.setDescription(requestDTO.getDescription());
        save(category);
        return category;
    }

    public void setDelete(Long categoryId) throws ResourceNotFoundException {
        findById(categoryId).setIsDelete((byte) 1);
    }

    public List<Category> findAllDeletedCategory() {
       return repository.findAllDeletedCategory().stream().toList();
    }
}
