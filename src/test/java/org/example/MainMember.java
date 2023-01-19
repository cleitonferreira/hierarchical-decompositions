package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainMember {

  public static void main(String[] args) {
    Member memberOne = new Member(1);
    Member memberTwo = new Member(2);
    Member memberThree = new Member(3);
    Member memberFour = new Member(4);
    Member memberFive = new Member(5);
    Member memberSix = new Member(6);
    Member memberSeven = new Member(7);

    memberTwo.setChildren(Arrays.asList(memberThree, memberFour));
    memberFour.setChildren(Arrays.asList(memberFive, memberSix));

    List<Member> memberList = Arrays.asList(memberOne, memberTwo, memberSeven);
    List<Member> flatList = new ArrayList<>();
    List<Member> convertedList = convertToFlatList(memberList, flatList);
    System.out.println(convertedList);

    List<Member> convertedList3 = memberList.stream()
        .flatMap(Member::streamAll)
        .collect(Collectors.toList());
    System.out.println(convertedList3);

    List<Member> convertedList4 = convertToFlatList4(memberList);
    System.out.println(convertedList4);
  }

  private static List<Member> convertToFlatList(List<Member> memberList, List<Member> flatList) {
    for (Member member : memberList) {
      if (member.getChildren() != null) {
        convertToFlatList(member.getChildren(), flatList);
      } else {
        flatList.add(member);
      }
    }
    return flatList;
  }

  private static List<Member> convertToFlatList2(List<Member> memberList, List<Member> flatList) {
    for (Member member : memberList) {
      // Always add the member to flatList
      flatList.add(member);

      // If it has children, add them toore
      if (member.getChildren() != null) {
        convertToFlatList(member.getChildren(), flatList);
      }
    }
    return flatList;
  }

  private static List<Member> convertToFlatList4(List<Member> memberList) {
    return memberList.stream().flatMap(i -> {
      if (Objects.nonNull(i.getChildren())) {
        return Stream.concat(Stream.of(i), convertToFlatList4(i.getChildren()).stream());
      }
      return Stream.of(i);

    }).collect(Collectors.toList());

  }
}

class Member {

  private List<Member> children;

  private int memberId;

  Member(int memberId) {
    this.memberId = memberId;
  }

  List<Member> getChildren() {
    return children;
  }

  void setChildren(List<Member> children) {
    this.children = children;
  }

  int getMemberId() {
    return memberId;
  }

  void setMemberId(int memberId) {
    this.memberId = memberId;
  }

  @Override
  public String toString() {
    return String.valueOf(this.memberId);
  }

  public Stream<Member> streamAll() {
    if (getChildren() == null) {
      return Stream.of(this);
    }
    return Stream.concat(Stream.of(this), getChildren().stream().flatMap(Member::streamAll));
  }
}
