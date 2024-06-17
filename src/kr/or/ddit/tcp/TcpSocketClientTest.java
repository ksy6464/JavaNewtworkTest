package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpSocketClientTest {
	public static void main(String[] args) throws UnknownHostException, IOException {
		String serverIp = "127.0.0.1";
		// 자기자신 컴퓨터(호스트)를 만드는 방법
		// IP : 127.0.0.1
		// 도메인명 : localhost
		
		System.out.println(serverIp + " 서버에 접속 중 입니다. ");
		///클라이언트니깐 서버의 IP로 접속을 한다
		
		// 소켓을 설정해서 서버에 연결을 요청한다
		Socket socket = new Socket(serverIp, 7777);
		/// 클라이언트 소켓이 서버코켓의 Ip주소와 port번호로 연결한다
		
		// 연결이 되면 이후의 내용이 실행된다
		System.out.println("서버에 연결되었습니다.");
		
		// 서버에서 보내온 메시지 받기
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		
		System.out.println("서버로부터 받은 메세지 : "+dis.readUTF());
		
		System.out.println("클라이언트 연결 종료...");
		
		dis.close();
		
		socket.close();
	}
}
