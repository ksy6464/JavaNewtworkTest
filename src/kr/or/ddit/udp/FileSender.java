package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class FileSender {
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private InetAddress receiveAddr;
	private int port;
	private File file;
	
	public FileSender(String receiveIp, int port) {
		try {
			ds = new DatagramSocket();
			this.port = port; 
			receiveAddr = InetAddress.getByName(receiveIp); 
			///String을 원하는게 아니라 String을 기반으로 한 InetAddress를 원해서 그걸 바꿔줬다
			file = new File("d:/D_Other/쵸파.jpg");
			
			if (!file.exists()) {
				System.out.println("파일이 존재하지 않습니다.");
				System.exit(0);
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public void start() {
		long fileSize = file.length();
		long totalReadBytes = 0;
		
		long startTime = System.currentTimeMillis();
		
		try {
			sendData("start".getBytes()); // 전송 시작을 알려주기 위한 문자열 전송
			
			sendData(file.getName().getBytes()); //파일명 전송
			
			sendData(String.valueOf(fileSize).getBytes()); //총 파일 크기 전송(bytes)
			
			FileInputStream fis = new FileInputStream(file);
			
			byte[] buffer = new byte[1000]; /// 1000바이트식 보낼고 그냥 지정한거임
			
			while (true) {
				Thread.sleep(10); // 패킷 전송 간의 시간 간격을 주기 위해서...
				
				int readBytes = fis.read(buffer, 0, buffer.length);
				
				if (readBytes == -1) { // 파일 내용을 다 읽은 경우...
					break;
				}
				
				sendData(buffer, readBytes); // 읽어온 파일 내용 전송하기
				
				totalReadBytes +=readBytes;
				System.out.println("진행상태 : "+totalReadBytes+"/"+fileSize+"bytes(s) ("
						+ (totalReadBytes*100 / fileSize) +"%)");
			}
			
			long endTime = System.currentTimeMillis();
			long diffTime = endTime - startTime;
			double transferSpeed = fileSize / diffTime;
			
			System.out.println("걸린 시간 : "+diffTime+" (ms)");
			System.out.println("평균 전송속도 : "+transferSpeed +" Bytes/ms");
			
			System.out.println("전송 완료...");
			
			fis.close();
			ds.close();
					
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	/**
	 * 바이트 배열 데이터 전송하기
	 * @param data 보낼 바이트배열 데이터
	 * @param length 보낼 바이트배열 크기
	 * @throws IOException
	 */
	public void sendData(byte[] data, int length) throws IOException {
		dp = new DatagramPacket(data, length, receiveAddr,port);
		ds.send(dp);
	}///전체 데이터를 보내는게 아니라 일부만 보낼때
	
	/**
	 * 바이트 배열 데이터 전송햐기
	 * @param data
	 * @throws IOException
	 */
	public void sendData(byte[] data) throws IOException {
		sendData(data, data.length);
	}///자기 자신의 전체 데이터를 보낼때 사용
	
	public static void main(String[] args) {
//		new FileSender("192.168.36.131", 8888).start();
		new FileSender("192.168.36.110", 8888).start();
	}
	
	

}



















