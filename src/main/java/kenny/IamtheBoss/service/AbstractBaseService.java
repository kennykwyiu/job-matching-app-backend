package kenny.IamtheBoss.service;


import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.model.AbstractAuditableEntity;
import kenny.IamtheBoss.repository.AbstractBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public abstract class AbstractBaseService<T extends AbstractAuditableEntity<T, ID>, ID
        extends Serializable> {
    @Autowired
    private final AbstractBaseRepository<T, ID> repository;

    public AbstractBaseService(AbstractBaseRepository<T, ID> repository) {
        this.repository = repository;
    }


    public T save(T entity) {
        return repository.save(entity);
    }

    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

//    public T findById(ID id) throws ResourceNotFoundException {
//        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
//    }

    public T findById(ID id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Entity of %s not found by id %s", getClass().getSimpleName(), id)
        ));
    }

    public void delete(T entity) {
        repository.delete(entity);
    }
}
