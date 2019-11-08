package lift;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Elevator {
	private boolean isDoorOpen; 
	private int currentFloor; //��ǰ¥��
	private int goalFoor;	  //Ŀ��¥��
	private int weight;
	final int CAPACITY = 1000; //�����������
	final int TOP_FLOOR = 12;  //�����ܸ߶�
	final int BOOTEM_FLOOR = 1; //�������¥��
	// upstair����������һ������¥������¥ ��Ĭ��Ϊ��
	boolean upstairs = true;
	
	Random r = new Random();
	
	List<Integer> list = new ArrayList<Integer>();
	//���캯����ʼ��
	public Elevator() {
		isDoorOpen = false; //��ʼ����
		currentFloor = 1;
		goalFoor = 0;
		weight = 0;
	}
	//����
	public void openDoor(){
		isDoorOpen = true;
	}
	//����
	public void closeDoor() throws InterruptedException{
		System.out.println("**���ڼ���Ƿ���**");
		Thread.sleep(1000);
		System.out.print("��ǰ�˿͵���������:"+weight+"����");
		if(weight > CAPACITY){
			System.out.print("���أ��޷����ţ�");
		}
		else{
			System.out.print("û�г���");
			isDoorOpen = false;
		}
		System.out.println();
	}
	//����
	public void input(){
		System.out.println("������Ҫǰ����¥�㣬�м��ÿո�ֿ���-1��־������");
		Scanner sc = new Scanner(System.in);
		int length = 0;
		while(true){ //����
			int num = sc.nextInt();
			if(num != -1) {
				if(num<=0) num = 1;
				if(!list.contains(num)) //���list����û�У��򱣴�list��
					list.add(num);
			}
			else  		  break;
		}
		System.out.println("���������������¥�㣡");
	}
	//����
	public void run() throws InterruptedException{
		weight = r.nextInt(1200);
		Collections.sort(list); //�� list ��������
		closeDoor();
		int len = list.size();
		int maxFloor = list.get(len-1);
		//���������ڵ�ǰ���ڲ㣬�����ϴε���ҲΪ��ʱ ִ���ϲ���
		if(maxFloor > currentFloor && upstairs){ 
			up(maxFloor);
			if(list.size()>0){ //����¥�����list�л���Ԫ��˵������Ҫ��¥
				down();
			}
		}else{                       //������
			down();
			if(list.size()>0){ //����¥�����list�л���Ԫ��˵������Ҫ��¥
				up(maxFloor);
			}
		}
	}
	//��¥
	public void up(int maxFloor) throws InterruptedException{
		int beforeFloor = currentFloor;
		for(int i = currentFloor;i<=maxFloor;i++){
			Thread.sleep(1000);
			System.out.println("-----"+i+"¥-----");
			if(list.contains(i)) {
				System.out.println("����"+i+"¥�����ţ�");
				weight = r.nextInt(1200);
				closeDoor();
				System.out.println("-----"+i+"¥-----");
				int index = list.indexOf(i);
				list.remove(index);
			}
			currentFloor = i;
		}
		upstairs = true;
	}
	//��¥
	public void down() throws InterruptedException{
		int minFloor = list.get(0); 
		for(int i = currentFloor;i>=minFloor;i--){
			Thread.sleep(1000);
			System.out.println("-"+i+"¥-");
			System.out.println("......");
			if(list.contains(i)) {
				System.out.println("����"+i+"¥�����ţ�");
				weight = r.nextInt(1200);
				closeDoor();
				int index = list.indexOf(i);
				list.remove(index);
			}
			currentFloor = i;
		}
		upstairs = false;
	}
	
}
