package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)   //기본값이 false임
@RequiredArgsConstructor  //-> final이 있는 필드만을 가지고 생성자를 만들어줌
public class MemberService {
  private final MemberRepository memberRepository;

  @Transactional
  public Long join(Member member){
     validateDuplicateMember(member);  //중복 회원 검증
     memberRepository.save(member);
     return member.getId();   //em.persist 아직 db에 들어간 시점이 아니라도 값을 넣어줌
  }

  private void validateDuplicateMember(Member member) {
    //EXCEPTION
    List<Member> findMembers = memberRepository.findByName(member.getUserName()); //멀티쓰레드 예방으로 DB에서 member의 name을 unique로 지정
    if (!findMembers.isEmpty()){
      throw new IllegalStateException("이미 존재하는 회원입니다.");   //member수를 카운트해서 0보다 크다 라고 하면 더 최적화 되긴함
    }
  }

  /*
 @@회원 조회
  */
  public List<Member> findMembers(){
    return memberRepository.findAll(); //전체 조회
  }

  public Member findOne(Long memberId){
    return memberRepository.findOne(memberId);
  }

}
