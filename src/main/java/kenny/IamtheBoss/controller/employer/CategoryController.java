package kenny.IamtheBoss.controller.employer;

import jakarta.validation.Valid;
import kenny.IamtheBoss.dto_factory.CategoryFactory;
import kenny.IamtheBoss.dto_factory.PostDTOFactory;
import kenny.IamtheBoss.dto_request.CategoryRequestDTO;
import kenny.IamtheBoss.dto_response.CategoryResponseDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.model.Category;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.service.CategoryService;
import kenny.IamtheBoss.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employer/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostDTOFactory postDTOFactory;

    @Autowired
    private CategoryFactory categoryFactory;


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED) //OK
    public List<CategoryResponseDTO> getAllCategory(SystemUser systemUser) {
        return categoryService.findAll().stream()
                .map(categoryFactory::toResponseDTO)
                .toList();
    }

    @GetMapping("/deleted-category") //OK
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CategoryResponseDTO> getAllDeletedCategory(SystemUser systemUser) {
      return categoryService.findAllDeletedCategory().stream()
              .map(categoryFactory::toResponseDTO)
              .toList();
    }

    @PostMapping // OK
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDTO createCategory(@Valid @RequestBody CategoryRequestDTO requestDTO) {
        Category category = categoryFactory.toEntity(requestDTO);
        categoryService.save(category);
        return categoryFactory.toResponseDTO(category);
    }

    @PutMapping("/{categoryId}") //OK
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CategoryResponseDTO updateCategory(@PathVariable(name = "categoryId") Long categoryId,
                                              @Valid @RequestBody CategoryRequestDTO requestDTO) throws ResourceNotFoundException {
        Category category = categoryService.findById(categoryId);
        Category updatedCategory = categoryService.updateCategory(category, requestDTO);
        return categoryFactory.toResponseDTO(updatedCategory);
    }

    @DeleteMapping("/{categoryId}") //OK
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCategory(@PathVariable(name = "categoryId") Long categoryId) throws ResourceNotFoundException {
        categoryService.setDelete(categoryId);
    }
}
