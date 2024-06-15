package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {

	private DatagramSocket ds; ///소캣역할을한다
	private DatagramPacket dp; /// 실제 데이터를 보낼때 바이트로 전달되는데 바이트를 담아서 목적지로 보낸다
	
	
	private byte[] msg; //패킷송수신을 위한 바이트배열 선언
	
	public UdpServer(int port) {
		try {
			// 메시지 수신을 위한 포트번호 설정
			ds = new DatagramSocket(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() throws IOException {
		while (true) {
			// 데이터를 수신하기 위한 패킷객체를 생성한다
			msg = new byte[1];
			dp = new DatagramPacket(msg, msg.length);
			
			System.out.println("패킷 수진 대기 중...");
			
			// 패킷 객체를 통해 데이터(바이트 배열)를 수신한다
			ds.receive(dp);
			
			System.out.println("패킷 수신 완료...");
			
			// 수신한 패킷정보를 확인하여 보낸사람의 IP주소와 포트번호를 알아낸다.
			InetAddress ipAddr = dp.getAddress();
			int port = dp.getPort();
			
			// 서버의 현재 시간을 시분초 형태([hh:mm:ss]) 문자열로 보내준다.
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			msg = time.getBytes(); //시간문자열을 바이트배열로 변환한다.
			
			// 패킷에 바이트배열 데이터를 담아 상대방에게 보내준다.
			dp = new DatagramPacket(msg, msg.length, ipAddr, port);
			ds.send(dp);
			
		}
		
	}
	
//	public static void main(String[] args) {
//		new 
//	}
}



















