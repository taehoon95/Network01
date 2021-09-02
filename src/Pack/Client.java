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

// Client�� ���ŷ �����°� ���� ������ �����尡 �ʿ� ����
public class Client extends Application {
	Socket cs = new Socket();
	
	@Override
	public void start(Stage arg0) throws Exception {
		VBox root = new VBox(); // VBox ���η� ����
		root.setPrefSize(400, 300);
		root.setSpacing(15);
		//-------------------------------------------- �ڵ� ����
		Button btn1 = new Button("����"); 
		Button btn2 = new Button("��ư2"); 
		
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
					// ��ſ� data
					String s = "apple : " + count++; // byte�� ���ϰ� ������ ����
					byte[] data = s.getBytes(); // String -> byteŸ������
					outputStream.write(data);
					System.out.println("������ ����");
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