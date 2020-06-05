package viktor.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import viktor.home.domain.FamilyMember;
import viktor.home.repository.FamilyMemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FamilyMemberService {
    private final FamilyMemberRepository familyMemberRepository;

    public FamilyMember createFamilyMember(FamilyMember familyMember){
        return familyMemberRepository.save(familyMember);
    }

    public List<FamilyMember> findAll(){
        return familyMemberRepository.findAll();
    }
}
