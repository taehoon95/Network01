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
			//Ŭ���̾�Ʈ ���� ����
			Socket cs = new Socket();
			
			System.out.println("���ڸ� �Է��ϸ� ������ �õ��մϴ�.");
			new Scanner(System.in).nextInt(); //�Է��� ������ �����ϰ� �ϱ����� ���

			//���� �õ� , �ڱ� �ּҶ� localhost
			cs.connect(new InetSocketAddress(InetAddress.getLocalHost(), 5001));
			System.out.println("���ڸ� �Է��ϸ� �����͸� �����մϴ�.");
			new Scanner(System.in).nextInt(); //�Է��� ������ �����ϰ� �ϱ����� ���
			
			OutputStream outputStream = cs.getOutputStream();
			
			// ��ſ� data
			String s = "apple"; // byte�� ���ϰ� ������ ����
			byte[] data = s.getBytes(); // String -> byteŸ������
			outputStream.write(data);
			System.out.println("������ ����");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new Scanner(System.in).nextInt();
		System.out.println("Client End");

	}
}
