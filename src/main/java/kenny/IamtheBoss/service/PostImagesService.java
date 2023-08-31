package kenny.IamtheBoss.service;

import jakarta.transaction.Transactional;
import kenny.IamtheBoss.model.PostImages;
import kenny.IamtheBoss.repository.AbstractBaseRepository;
import kenny.IamtheBoss.repository.PostImagesRepository;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PostImagesService extends AbstractBaseService<PostImages, Long> {

    private final PostImagesRepository repository;
    public PostImagesService(PostImagesRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
