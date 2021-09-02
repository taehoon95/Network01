package Pack;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//�������� : �����ϴ� ����� + 1 ��ŭ ������ ������ �ִ�(1�� ���� ����)
//���ε� : �۾��� �ϱ����� ������ ���
public class Server {
	
	public static void main(String[] args) {
		System.out.println("Server Start");
		
		try {
			// ������ ���� ���� ����
			// ���� ������ �˾ƾ� �Ұ�
			// 1. ������ �ڱ� IP�ּҸ� �˾ƾ� �Ѵ�.
			// 2. �н����带 �˾ƾ��� 
			ServerSocket mss = new ServerSocket();
			System.out.println("���� ���� ���� ����");
			
			// ���ε� �� ��(IP�ּ�, PORT��ȣ)
			// ���������� localhost�� ����ϸ� ������ �ڱ��ּҸ� ����� localhost -> InetAddress.getLocalHost() �̰� �̿��ϴ°� ����
			// PORT : Ŭ���̾�Ʈ���� ���� �Ҷ� ����ϴ� ��ȣ(�����͸� �ְ� �޴� ���)
			// �ٸ� �ּҿ��� ������ ��� localhost -> 211.221.45.233(�ڱ��ּҷ�)
			mss.bind(new InetSocketAddress(InetAddress.getLocalHost(), 5001));
			System.out.println("���ε� �Ϸ�");
			// ���ŷ �Լ��̴�.(���� ���)
			
			Socket ss = mss.accept(); //2��° ����
			System.out.println("������ ������");
			InputStream inputStream = ss.getInputStream(); //��ü�� �޾Ƶ帰��.
			
			// ������ ���ۿ�
			byte[] data = new byte[512]; 
			// ���ŷ �Լ��̴�.
			int size = inputStream.read(data);
			// �����͸� ó���ϴ� �ڵ�
			String s = new String(data, 0, size);
			System.out.println(s + " ������ ����");
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new Scanner(System.in).nextInt();
		System.out.println("Server End");
	}
}
