package gcmClient;

import java.util.List;

import gcmClasses.Member;



public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Member> m = ServiceFunctions.getMembers();
	
	
		for(Member member : m) {
			  //ol.add(m);
			  System.out.println(member);
			}

	}

}
