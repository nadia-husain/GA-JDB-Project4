package com.gym.app.service;

import com.gym.app.exception.InformationNotFoundException;
import com.gym.app.model.Membership;
import com.gym.app.model.User;
import com.gym.app.model.enums.MembershipStatus;
import com.gym.app.repository.MembershipRepository;
import com.gym.app.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;

    @Autowired
    public MembershipService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public static User getCurrentLoggedInUser() {
        return ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

    public Membership createMembership(Membership membershipObject) {
        membershipObject.setCreatedBy(getCurrentLoggedInUser());
        membershipObject.setStatus(MembershipStatus.ACTIVE);
        return membershipRepository.save(membershipObject);
    }

    public Membership getMembershipById(Long membershipId) {
        return membershipRepository.findById(membershipId).orElseThrow(() -> new InformationNotFoundException(
                "Membership with ID " + membershipId + " does not exist."));
    }

    public List<Membership> getAllMemberships() {
        List<Membership> memberships = membershipRepository.findAll();
        if(memberships.isEmpty()) throw  new InformationNotFoundException("No membership was found.");
        return memberships;
    }

    public Membership updateMembership(Long membershipId, Membership membershipObject) {
        Membership membership = membershipRepository.findById(membershipId)
                .orElseThrow(() -> new InformationNotFoundException("Membership with ID " + membershipId + " does not exist."));
        membership.setStatus(membershipObject.getStatus());
        return membershipRepository.save(membershipObject);
    }

    public void deleteMembership(Long membershipId) {
        membershipRepository.findById(membershipId)
                .orElseThrow(() -> new InformationNotFoundException("Membership with ID " + membershipId + " does not exist."));
        membershipRepository.deleteById(membershipId);
    }
}
