package kenny.IamtheBoss;

import jakarta.annotation.PostConstruct;
import kenny.IamtheBoss.model.*;
import kenny.IamtheBoss.repository.RatingRepository;
import kenny.IamtheBoss.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataLoader {
    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JobOrderService jobOrderService;

    @Autowired
    private PostService postService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private RatingRepository ratingRepository;


    @PostConstruct
    private void init() throws RepeatedRatingException {
        SystemUser systemUserAdmin = SystemUser.builder()
                .email("Bob.head@example.com")
                .fullName("Bob Head")
                .userName("Bob.head")
                .externalUserId("qwv3wvlwZRSJVAs9TXArGLcBfBs1")
                .userClaims(List.of(UserClaim.EMPLOYEE, UserClaim.EMPLOYER, UserClaim.ADMIN))
                .build();

        SystemUser systemUserEmployee = SystemUser.builder()
                .email("Bob.hea1d@example.com")
                .fullName("Bob Head")
                .userName("Bob.head")
                .externalUserId("KoQc9PKGsFZALTO6vVB7e0Awqvf2")
                .userClaims(List.of(UserClaim.EMPLOYEE))
                .build();

        SystemUser systemUserEmployer = SystemUser.builder()
                .email("Bob.head2@example.com")
                .fullName("Bob Head")
                .userName("Bob.head")
                .externalUserId("umMhb2pWLZaVuCf6OpP0t2jkTJ63")
                .userClaims(List.of(UserClaim.EMPLOYER))
                .build();

        systemUserService.save(systemUserAdmin);
        systemUserService.save(systemUserEmployee);
        systemUserService.save(systemUserEmployer);

        Category softwareDevelop = Category.builder()
                .name(String.valueOf(JobCategory.SOFTWARE_DEVELOP))
                .description(String.valueOf(JobCategory.SOFTWARE_DEVELOP))
                .build();

        Category personalTutor = Category.builder()
                .name(String.valueOf(JobCategory.PERSONAL_TUTORIAL))
                .description(String.valueOf(JobCategory.PERSONAL_TUTORIAL))
                .build();

        Category graphicDesign = Category.builder()
                .name(String.valueOf(JobCategory.GRAPHIC_DESIGN))
                .description(String.valueOf(JobCategory.GRAPHIC_DESIGN))
                .build();

        Category hairStyle = Category.builder()
                .name(String.valueOf(JobCategory.HAIRSTYLE))
                .description(String.valueOf(JobCategory.HAIRSTYLE))
                .build();

        Category videoEdit = Category.builder()
                .name(String.valueOf(JobCategory.VIDEO_EDIT))
                .description(String.valueOf(JobCategory.VIDEO_EDIT))
                .build();

        categoryService.save(softwareDevelop);
        categoryService.save(personalTutor);
        categoryService.save(graphicDesign);
        categoryService.save(hairStyle);
        categoryService.save(videoEdit);

        Post softwarePost = Post.builder()
                .title("Backend Job")
                .jobDescription("USD1000 for Develop a ecommerce web by using SpringBoot")
                .category(softwareDevelop)
                .systemUser(systemUserAdmin)
                .status(PostStatus.OPEN)
                .build();
        postService.save(softwarePost);

        Post videoEditPost = Post.builder()
                .title("Edit 5 videos each day")
                .jobDescription("USD1000 for Edit 5 videos each day")
                .category(videoEdit)
                .status(PostStatus.OPEN)
                .systemUser(systemUserAdmin)
                .build();
        postService.save(videoEditPost);


        Post personalTutorPost = Post.builder()
                .title("1 hour each day")
                .jobDescription("USD1000 for body build")
                .category(personalTutor)
                .status(PostStatus.OPEN)
                .systemUser(systemUserAdmin)
                .build();
        postService.save(personalTutorPost);

        JobOrder jobOrder1 = JobOrder.builder()
                .post(softwarePost)
                .systemUser(systemUserEmployee)
                .status(JobOrderStatus.COMPLETED)
                .build();
        jobOrderService.save(jobOrder1);

        JobOrder jobOrder2 = JobOrder.builder()
                .post(videoEditPost)
                .systemUser(systemUserEmployee)
                .status(JobOrderStatus.COMPLETED)
                .build();
        jobOrderService.save(jobOrder2);

        JobOrder jobOrder3 = JobOrder.builder()
                .post(personalTutorPost)
                .systemUser(systemUserEmployee)
                .status(JobOrderStatus.COMPLETED)
                .build();
        jobOrderService.save(jobOrder3);

        Rating rating1 = Rating.builder()
                .fromUser(systemUserEmployer)
                .toUser(systemUserEmployee)
                .jobOrder(jobOrder1)
                .rating(BigDecimal.TEN)
                .build();

        ratingService.createRating(rating1);

        Rating rating2 = Rating.builder()
                .fromUser(systemUserEmployer)
                .toUser(systemUserEmployee)
                .jobOrder(jobOrder2)
                .rating(new BigDecimal("5"))
                .build();
        ratingService.createRating(rating2);

        Rating rating3 = Rating.builder()
                .fromUser(systemUserEmployer)
                .toUser(systemUserEmployee)
                .jobOrder(jobOrder3)
                .rating(new BigDecimal("3"))
                .build();
        ratingService.createRating(rating3);

        Rating rating4 = Rating.builder()
                .fromUser(systemUserEmployee)
                .toUser(systemUserEmployer)
                .jobOrder(jobOrder1)
                .rating(new BigDecimal("7"))
                .build();
        ratingService.createRating(rating4);


    }

}
