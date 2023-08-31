package kenny.IamtheBoss.controller;

import jakarta.validation.Valid;
import kenny.IamtheBoss.dto_factory.JobOrderFactory;
import kenny.IamtheBoss.dto_request.JobOrderRequestDTO;
import kenny.IamtheBoss.dto_response.JobOrderResponseDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.exception.UnauthorizedOperationException;
import kenny.IamtheBoss.model.*;
import kenny.IamtheBoss.service.JobOrderService;
import kenny.IamtheBoss.service.PostService;
import kenny.IamtheBoss.service.SystemUserService;
import kenny.IamtheBoss.exception.UnacceptedOrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/employee/job-order")
public class EmployeeJobOrderController {

    @Autowired
    private JobOrderService jobOrderService;
    @Autowired
    private JobOrderFactory jobOrderFactory;
    @Autowired
    private PostService postService;
    @Autowired
    private SystemUserService systemUserService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<JobOrderResponseDTO> getAllExistingJobOrder(SystemUser systemUser) {
        return jobOrderService.findAll().stream()
                .map(jobOrderFactory::toResponseDTO)
                .toList();
    }

    @GetMapping("/deletedJobOrder") // OK
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<JobOrderResponseDTO> getAllHistoricalJobOrder(SystemUser systemUser) {
        return jobOrderService.getAllDeletedJobOrder().stream()
                .map(jobOrderFactory::toResponseDTO)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // work, need to fix status after took by employee
    public JobOrderResponseDTO addPostToJobOrder(@Valid @RequestBody JobOrderRequestDTO requestDTO,
                                                 SystemUser systemUser) throws ResourceNotFoundException,
            UnauthorizedOperationException, UnacceptedOrderException {

        jobOrderService.validateJobOrderRequest(requestDTO, systemUser);
        JobOrder jobOrder = jobOrderFactory.toEntity(requestDTO, systemUser);
        jobOrderService.changeJobOrderAndPostStatus(jobOrder);
        return jobOrderFactory.toResponseDTO(jobOrder);
    }

    @DeleteMapping("/{jobOrderId}") // OK
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void cancelJobOrder(@PathVariable(name = "jobOrderId") Long jobOrderId,
                               SystemUser systemUser) throws ResourceNotFoundException {
        JobOrder jobOrder = jobOrderService.findById(jobOrderId);
        jobOrderService.setDelete(jobOrder);
    }
}
