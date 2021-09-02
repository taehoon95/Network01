package Pack;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
	public static void main(String[] args) {
		System.out.println("Client Start");

		
		try {
			//클라이언트 소켓 생성
			Socket cs = new Socket();
			
			System.out.println("숫자를 입력하면 접속을 시도합니다.");
			new Scanner(System.in).nextInt(); //입력을 받으면 접속하게 하기위해 사용

			//접속 시도 , 자기 주소라 localhost
			cs.connect(new InetSocketAddress(InetAddress.getLocalHost(), 5001));
			System.out.println("숫자를 입력하면 데이터를 전송합니다.");
			new Scanner(System.in).nextInt(); //입력을 받으면 접속하게 하기위해 사용
			
			OutputStream outputStream = cs.getOutputStream();
			
			// 통신용 data
			String s = "apple"; // byte로 편하게 보내기 위해
			byte[] data = s.getBytes(); // String -> byte타입으로
			outputStream.write(data);
			System.out.println("데이터 보냄");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new Scanner(System.in).nextInt();
		System.out.println("Client End");

	}
}
