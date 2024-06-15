package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread{
	private Scanner scan;
	private String name;
	private DataOutputStream dos;
	
	///생성자가 호출될때 초기화하기
	public Sender(Socket socket) {
		name = "[" + socket.getLocalAddress()+" : "+socket.getLocalAddress()+"]";
		scan = new Scanner(System.in);
		
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
		while (dos!=null) {
			
			try {
				dos.writeUTF(name+" >>> "+ scan.nextLine()); // 입력 문자열 전송하기...
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
}
