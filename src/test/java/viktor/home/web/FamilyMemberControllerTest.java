package viktor.home.web;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import viktor.home.service.FamilyMemberService;

class FamilyMemberControllerTest {
    FamilyMemberService familyMemberService = mock(FamilyMemberService.class);
    FamilyMemberController sut = new FamilyMemberController(familyMemberService);

    @Test
    void get_all_should_call_service() {
        sut.getAllFamilyMembers();
        verify(familyMemberService).findAll();
    }
}