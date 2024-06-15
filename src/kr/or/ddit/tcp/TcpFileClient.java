package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.imageio.stream.FileImageOutputStream;

// 클라이언트는 서버에 접속하여 서버가 보내주는 파일을 d:/D_Other/down_files 폴더에 저장한다.

public class TcpFileClient {
	private Socket socket;
	private FileOutputStream fos;
	private DataInputStream dis;
	private DataOutputStream dos;
	private Scanner scan;
	
	public TcpFileClient() {
		scan = new Scanner(System.in);
	}
	
	public void clientstart() {
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			
			socket = new Socket("192.168.36.131", 7777);
			
			// 소캣접속이 성고하면 받고싶은 파일명을 보낸다.
			System.out.println("파일명>> ");
			String fileName = scan.next();
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(fileName);
			/// 생성자에서 안하고 이시점에 초기화 하는 이유는? 소캣이 필요하므로 지금하는거다
			
			dis = new DataInputStream(socket.getInputStream());
			
			String resultMsp = dis.readUTF();
			
			if (resultMsp.equals("OK")) {
				File downDir = new File("d:/D_Other/down_files");
				
				if (!downDir.exists()) {
					downDir.mkdir();
				}
				
				File file = new File(downDir, fileName); //파일 저장하기 위한 객체 생성
				
				fos = new FileOutputStream(file);
				
				bis = new BufferedInputStream(socket.getInputStream());
				bos = new BufferedOutputStream(fos);
				
				int data = 0;
				while ((data=dis.read()) !=-1) {
					bos.write(data);
				}
				System.out.println("파일 다운로드 완료...");
			}else {
				System.out.println(resultMsp);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
				bos.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		new TcpFileClient().clientstart();
	}
	

}
