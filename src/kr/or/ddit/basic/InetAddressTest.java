package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	
	public static void main(String[] args) throws UnknownHostException {
		
		// InetAddress클래스 => IP주소 정보를 다루기 위한 클래스
		
		// getByName()은 www.naver.com 또는 SEM-PC 등과 같은 머신이름이나 IP주소를 통해 유효한 InetAddress
		// 객체를 제공한다. IP주소 자체를 넣으면 주소 구성 자체의 유효성 정도만 체크해준다.
		
		// 네이버 사이트의 IP정보 가져오기
		InetAddress naverIp = InetAddress.getByName("www.naver.com");///유효성 체크해주고 있다
//		InetAddress naverIp = InetAddress.getByName("www.naverqqq.com");///UnknownHostException 이런호스트느 없다는뜻
//		InetAddress naverIp = InetAddress.getByName("192.168.36.111");///유효성 체크해주고 있다 ,  PC-07
		System.out.println("Host Name => "+ naverIp.getHostName());
		System.out.println("Host Address => "+ naverIp.getHostAddress());
		System.out.println();
		
		// 자기 자신의 IP주소 정보 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터의 Host Name => "+localIp.getHostName());
		System.out.println("내 컴퓨터의 Host Address => "+localIp.getHostAddress());
		
		// IP주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] naverIps = InetAddress.getAllByName("www.naver.com");
		for (InetAddress iAddr : naverIps) {
			System.out.println(iAddr.toString());
		}
	}
}
