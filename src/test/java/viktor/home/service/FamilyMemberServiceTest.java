package viktor.home.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import viktor.home.domain.FamilyMember;
import viktor.home.domain.Task;
import viktor.home.domain.User;
import viktor.home.repository.FamilyMemberRepository;

import java.util.List;

class FamilyMemberServiceTest {
    private FamilyMemberRepository familyMemberRepository = mock(FamilyMemberRepository.class);

    FamilyMemberService sut = new FamilyMemberService(familyMemberRepository);

    @Test
    void create_family_member_should_create() {
        FamilyMember familyMember = FamilyMember.builder()
                .tasks(List.of(Task.builder().description("desc").build()))
                .name("Name")
                .userAccount(User.builder().id(1L).build()).build();

        when(familyMemberRepository.save(familyMember)).thenReturn(familyMember);

        assertThat(sut.createFamilyMember(familyMember)).isEqualTo(familyMember);
    }

    @Test
    void create_family_member_should_find_all() {
        FamilyMember familyMember1 = FamilyMember.builder()
                .tasks(List.of(Task.builder().description("desc").build()))
                .name("Name")
                .userAccount(User.builder().id(1L).build()).build();

        FamilyMember familyMember2 = FamilyMember.builder()
                .tasks(List.of(Task.builder().description("desc").build()))
                .name("Name")
                .userAccount(User.builder().id(1L).build()).build();

        List<FamilyMember> familyList = List.of(familyMember1,familyMember2);

        when(familyMemberRepository.findAll()).thenReturn(familyList);

        assertThat(sut.findAll()).isEqualTo(familyList);
        verify(familyMemberRepository).findAll();
    }
}