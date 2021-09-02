package Pack;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// TCP/IP 통신
// 선생님이 말하는 알아갔으면 하는 개념 : 웹서버 개념(아파치서버)을 잡으라고 이왕에 했으니까 채팅도 해보자
// UI 달면서 해보기(Javafx)

class ClientThread extends Thread {

	Socket socket;

	public ClientThread(Socket socket) {
		this.socket = socket;
	}

	// 리딩처리를 한다. -> 읽는 스레드를 하나더 만든다.
	public void run() {
		InputStream inputStream;
		try {
			inputStream = socket.getInputStream(); // 객체를 받아드린다.

			while(true) {
				// 데이터 전송용
				byte[] data = new byte[512];
				int size = inputStream.read(data); // read(data) 블로킹 함수이다.
				// 데이터를 처리하는 코드(받은 data를 String으로 변환)
				String s = new String(data, 0, size);
				System.out.println(s + " 데이터 받음");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class ConnectThread extends Thread {
	public void run() {
		try {
			ServerSocket mss = new ServerSocket();
			System.out.println("메인 서버 소켓 생성");

			mss.bind(new InetSocketAddress(InetAddress.getLocalHost(), 5001));
			System.out.println("바인딩 완료");

			while(true) {
				// mss.accept() 블로킹이 되면서 main이 일을 못하기 때문에 스레드를 이용해야 한다.
				// 스레드없으면 프로그램이 멈춤
				// 두번째 사람이 접속하기 전까지는 accept는 풀 수 없다.
				Socket ss = mss.accept(); 
				System.out.println("누군가 접속함");
				new ClientThread(ss).start();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class Server extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
		VBox root = new VBox(); // VBox 세로로 생김
		root.setPrefSize(400, 300);
		root.setSpacing(15);
		//-------------------------------------------- 코드 시작
		Button btn1 = new Button("서버 오픈"); 
		Button btn2 = new Button("버튼2"); 
		
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				new ConnectThread().start();
			}
		});
		
		root.getChildren().addAll(btn1, btn2);
		//--------------------------------------------
		Scene scene = new Scene(root);
		arg0.setTitle("Server");
		arg0.setScene(scene);
		arg0.show();
	}

	public static void main(String[] args) {
		launch();
	}

}

//서버입장 : 접속하는 사용자 + 1 만큼 소켓을 가지고 있다(1은 메인 소켓)
//바인딩 : 작업을 하기위한 기준을 기억
//public class Server {
//
//	public static void main(String[] args) {
//		System.out.println("Server Start");
//
//		try {
//			// 서버의 메인 소켓 생성
//			// 메인 소켓이 알아야 할것
//			// 1. 스스로 자기 IP주소를 알아야 한다.
//			// 2. 패스워드를 알아야함
//			ServerSocket mss = new ServerSocket();
//			System.out.println("메인 서버 소켓 생성");
//
//			// 바인딩 할 것(IP주소, PORT번호)
//			// 서버에서는 localhost를 사용하면 스스로 자기주소를 끌고옴 localhost -> InetAddress.getLocalHost()
//			// 이걸 이용하는게 좋다
//			// PORT : 클라이언트에서 접속 할때 사용하는 번호(데이터를 주고 받는 통로)
//			// 다른 주소에서 연결할 경우 localhost -> 211.221.45.233(자기주소로)
//			mss.bind(new InetSocketAddress(InetAddress.getLocalHost(), 5001));
//			System.out.println("바인딩 완료");
//			// 블로킹 함수이다.(무한 대기)
//
//			Socket ss = mss.accept(); // 2번째 소켓
//			System.out.println("누군가 접속함");
//			InputStream inputStream = ss.getInputStream(); // 객체를 받아드린다.
//
//			// 데이터 전송용
//			byte[] data = new byte[512];
//			int size = inputStream.read(data); // 블로킹 함수이다.
//			// 데이터를 처리하는 코드(받은 data를 String으로 변환)
//			String s = new String(data, 0, size);
//			System.out.println(s + " 데이터 받음");
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		new Scanner(System.in).nextInt();
//		System.out.println("Server End");
//	}
//}
