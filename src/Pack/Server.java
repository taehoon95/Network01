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

// TCP/IP ���
// �������� ���ϴ� �˾ư����� �ϴ� ���� : ������ ����(����ġ����)�� ������� �̿տ� �����ϱ� ä�õ� �غ���
// UI �޸鼭 �غ���(Javafx)

class ClientThread extends Thread {

	Socket socket;

	public ClientThread(Socket socket) {
		this.socket = socket;
	}

	// ����ó���� �Ѵ�. -> �д� �����带 �ϳ��� �����.
	public void run() {
		InputStream inputStream;
		try {
			inputStream = socket.getInputStream(); // ��ü�� �޾Ƶ帰��.

			while(true) {
				// ������ ���ۿ�
				byte[] data = new byte[512];
				int size = inputStream.read(data); // read(data) ���ŷ �Լ��̴�.
				// �����͸� ó���ϴ� �ڵ�(���� data�� String���� ��ȯ)
				String s = new String(data, 0, size);
				System.out.println(s + " ������ ����");
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
			System.out.println("���� ���� ���� ����");

			mss.bind(new InetSocketAddress(InetAddress.getLocalHost(), 5001));
			System.out.println("���ε� �Ϸ�");

			while(true) {
				// mss.accept() ���ŷ�� �Ǹ鼭 main�� ���� ���ϱ� ������ �����带 �̿��ؾ� �Ѵ�.
				// ����������� ���α׷��� ����
				// �ι�° ����� �����ϱ� �������� accept�� Ǯ �� ����.
				Socket ss = mss.accept(); 
				System.out.println("������ ������");
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
		VBox root = new VBox(); // VBox ���η� ����
		root.setPrefSize(400, 300);
		root.setSpacing(15);
		//-------------------------------------------- �ڵ� ����
		Button btn1 = new Button("���� ����"); 
		Button btn2 = new Button("��ư2"); 
		
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

//�������� : �����ϴ� ����� + 1 ��ŭ ������ ������ �ִ�(1�� ���� ����)
//���ε� : �۾��� �ϱ����� ������ ���
//public class Server {
//
//	public static void main(String[] args) {
//		System.out.println("Server Start");
//
//		try {
//			// ������ ���� ���� ����
//			// ���� ������ �˾ƾ� �Ұ�
//			// 1. ������ �ڱ� IP�ּҸ� �˾ƾ� �Ѵ�.
//			// 2. �н����带 �˾ƾ���
//			ServerSocket mss = new ServerSocket();
//			System.out.println("���� ���� ���� ����");
//
//			// ���ε� �� ��(IP�ּ�, PORT��ȣ)
//			// ���������� localhost�� ����ϸ� ������ �ڱ��ּҸ� ����� localhost -> InetAddress.getLocalHost()
//			// �̰� �̿��ϴ°� ����
//			// PORT : Ŭ���̾�Ʈ���� ���� �Ҷ� ����ϴ� ��ȣ(�����͸� �ְ� �޴� ���)
//			// �ٸ� �ּҿ��� ������ ��� localhost -> 211.221.45.233(�ڱ��ּҷ�)
//			mss.bind(new InetSocketAddress(InetAddress.getLocalHost(), 5001));
//			System.out.println("���ε� �Ϸ�");
//			// ���ŷ �Լ��̴�.(���� ���)
//
//			Socket ss = mss.accept(); // 2��° ����
//			System.out.println("������ ������");
//			InputStream inputStream = ss.getInputStream(); // ��ü�� �޾Ƶ帰��.
//
//			// ������ ���ۿ�
//			byte[] data = new byte[512];
//			int size = inputStream.read(data); // ���ŷ �Լ��̴�.
//			// �����͸� ó���ϴ� �ڵ�(���� data�� String���� ��ȯ)
//			String s = new String(data, 0, size);
//			System.out.println(s + " ������ ����");
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		new Scanner(System.in).nextInt();
//		System.out.println("Server End");
//	}
//}
