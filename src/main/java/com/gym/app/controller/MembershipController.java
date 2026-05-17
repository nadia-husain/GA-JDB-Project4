package com.gym.app.controller;

import com.gym.app.model.Membership;
import com.gym.app.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/membership/")
public class MembershipController {
    private MembershipService membershipService;

    @Autowired
    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping("new")
    public Membership createMembership(@RequestBody Membership membership) {
        System.out.println("calling createMembership ==> ");
        return membershipService.createMembership(membership);
    }

    @GetMapping("all")
    public List<Membership> getAllMemberships() {
        System.out.println("calling getAllMemberships => ==> ");
        return membershipService.getAllMemberships();
    }

    @GetMapping(path = "{membershipId}")
    public Membership getMembershipById(@PathVariable Long membershipId) {
        System.out.println("calling getMembershipById ==> ");
        return membershipService.getMembershipById(membershipId);
    }

    @PatchMapping(path = "{membershipId}")
    public Membership updateMembership(@PathVariable Long membershipId, @RequestBody Membership membershipObject) {
        System.out.println("calling updateMembership ==> ");
        return membershipService.updateMembership(membershipId, membershipObject);
    }

    @DeleteMapping("{membershipId}")
    public void deleteMembership(@PathVariable("membershipId") Long membershipId) {
        System.out.println("calling deleteMembership ==> ");
        membershipService.deleteMembership(membershipId);
    }
}
