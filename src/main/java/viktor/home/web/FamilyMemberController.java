package viktor.home.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import viktor.home.domain.FamilyMember;
import viktor.home.repository.FamilyMemberRepository;
import viktor.home.service.FamilyMemberService;

import java.util.List;

@RestController
@RequestMapping(value = "family-members")
@RequiredArgsConstructor
public class FamilyMemberController {
    private final FamilyMemberService familyMemberService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<FamilyMember> getAllFamilyMembers(){
        return familyMemberService.findAll();
    }
}
