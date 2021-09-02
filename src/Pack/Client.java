package Pack;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Client는 블로킹 잡히는게 없기 때문에 스레드가 필요 없다
public class Client extends Application {
	Socket cs = new Socket();
	
	@Override
	public void start(Stage arg0) throws Exception {
		VBox root = new VBox(); // VBox 세로로 생김
		root.setPrefSize(400, 300);
		root.setSpacing(15);
		//-------------------------------------------- 코드 시작
		Button btn1 = new Button("접속"); 
		Button btn2 = new Button("버튼2"); 
		
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					cs.connect(new InetSocketAddress(InetAddress.getLocalHost(), 5001));
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		btn2.setOnAction(new EventHandler<ActionEvent>() {
			int count = 0;
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					OutputStream outputStream = cs.getOutputStream();
					// 통신용 data
					String s = "apple : " + count++; // byte로 편하게 보내기 위해
					byte[] data = s.getBytes(); // String -> byte타입으로
					outputStream.write(data);
					System.out.println("데이터 보냄");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		root.getChildren().addAll(btn1, btn2);
		//--------------------------------------------
		Scene scene = new Scene(root);
		arg0.setTitle("Client");
		arg0.setScene(scene);
		arg0.show();
	}

	public static void main(String[] args) {
		launch();
	}

}