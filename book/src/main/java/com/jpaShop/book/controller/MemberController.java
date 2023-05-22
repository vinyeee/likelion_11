package com.jpaShop.book.controller;

import com.jpaShop.book.domain.Address;
import com.jpaShop.book.domain.Member;
import com.jpaShop.book.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm",new MemberForm());
        return "/members/createMemberForm";

    }

    @PostMapping("members/new")
    public String create(@Valid MemberForm form, BindingResult result){
        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        //멤버 객체 생성해서 바로 회원가입
        Member member = new Member();
        Address address = new Address(form.getCity(),form.getStreet(),form.getZipcode());
        member.setAddress(address);
        member.setName(form.getName());

        //회원가입
        memberService.join(member);

        return "redirect:/";

    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }




}
