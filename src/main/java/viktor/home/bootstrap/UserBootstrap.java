package viktor.home.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import viktor.home.domain.FamilyMember;
import viktor.home.domain.Status;
import viktor.home.domain.Task;
import viktor.home.domain.User;
import viktor.home.repository.FamilyMemberRepository;
import viktor.home.repository.TaskRepository;
import viktor.home.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class UserBootstrap {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final FamilyMemberRepository familyMemberRepository;

    @Bean
    public void fillUsers(){
        List<User> users = new ArrayList<User>();
        users.add(User.builder().userName("viktor").password("viktor").build());
        users.add(User.builder().userName("david").password("david").build());

        userRepository.saveAll(users);

        if (!userRepository.findAll().isEmpty()){
            log.info("Test users Loaded");
        }
    }

    @Bean
    public void fillTasks(){
        List<Task> commonTasks = List.of(
                Task.builder().name("Clean house")
                        .description("clean living room, kitchen and bathroom floors and tables")
                        .status(Status.UNSTARTED)
                        .build(),
                Task.builder().name("Take the trash out")
                        .description("Take the trash our to the garbage")
                        .status(Status.UNSTARTED)
                        .build(),
                Task.builder().name("Clean house")
                        .description("clean living room, kitchen and bathroom floors and tables")
                        .status(Status.UNSTARTED)
                        .build(),
                Task.builder().name("Take the trash out")
                        .description("Take the trash our to the garbage")
                        .status(Status.UNSTARTED)
                        .build()
        );

        taskRepository.saveAll(commonTasks);
    }

    @Bean
    public void fillFamilyMembers(){
        List<FamilyMember> familyMembers = new ArrayList<FamilyMember>();
        familyMembers.add(FamilyMember.builder().name("Viktor").userAccount(User.builder().id(1L).build()).tasks(List.of(Task.builder().id(3L).build(),Task.builder().id(4L).build())).build());
        familyMembers.add(FamilyMember.builder().name("David").userAccount(User.builder().id(2L).build()).tasks(List.of(Task.builder().id(5L).build(),Task.builder().id(6L).build())).build());

        familyMemberRepository.saveAll(familyMembers);

        if (!familyMemberRepository.findAll().isEmpty()){
            log.info("Test family members Loaded");
        }
    }
}
