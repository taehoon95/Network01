package Pack;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//서버입장 : 접속하는 사용자 + 1 만큼 소켓을 가지고 있다(1은 메인 소켓)
//바인딩 : 작업을 하기위한 기준을 기억
public class Server {
	
	public static void main(String[] args) {
		System.out.println("Server Start");
		
		try {
			// 서버의 메인 소켓 생성
			// 메인 소켓이 알아야 할것
			// 1. 스스로 자기 IP주소를 알아야 한다.
			// 2. 패스워드를 알아야함 
			ServerSocket mss = new ServerSocket();
			System.out.println("메인 서버 소켓 생성");
			
			// 바인딩 할 것(IP주소, PORT번호)
			// 서버에서는 localhost를 사용하면 스스로 자기주소를 끌고옴 localhost -> InetAddress.getLocalHost() 이걸 이용하는게 좋다
			// PORT : 클라이언트에서 접속 할때 사용하는 번호(데이터를 주고 받는 통로)
			// 다른 주소에서 연결할 경우 localhost -> 211.221.45.233(자기주소로)
			mss.bind(new InetSocketAddress(InetAddress.getLocalHost(), 5001));
			System.out.println("바인딩 완료");
			// 블로킹 함수이다.(무한 대기)
			
			Socket ss = mss.accept(); //2번째 소켓
			System.out.println("누군가 접속함");
			InputStream inputStream = ss.getInputStream(); //객체를 받아드린다.
			
			// 데이터 전송용
			byte[] data = new byte[512]; 
			// 블로킹 함수이다.
			int size = inputStream.read(data);
			// 데이터를 처리하는 코드
			String s = new String(data, 0, size);
			System.out.println(s + " 데이터 받음");
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new Scanner(System.in).nextInt();
		System.out.println("Server End");
	}
}
