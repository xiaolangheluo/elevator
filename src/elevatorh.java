import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class elevatorh {

	
	private boolean isDoorOpen; 
	private int currentFloor; //��ǰ¥��
	private int goalFoor;	  //Ŀ��¥��
	private int weight;
	private int nowfloor=1;
	final int CAPACITY = 1000; //�����������
	final int TOP_FLOOR = 12;  //�����ܸ߶�
	final int BOOTEM_FLOOR = 1; //�������¥��
	// upstair����������һ������¥������¥ ��Ĭ��Ϊ��
	boolean upstairs = true;
	
	Random r = new Random();
	Set<Integer> allfloor = new TreeSet<>();
	//�޲εĹ��췽�������н��ͻ�ʹ��
	public elevatorh() {
		//��ʼ������
		isDoorOpen = false; //��ʼ����
		currentFloor = 1;
		goalFoor = 0;
		weight = 0;
		
	}
	public void addfloor() {
		// TODO Auto-generated method stub
		System.out.println("������Ҫǰ����¥�㣬�м��ÿո�ֿ���-1��־������");
		Scanner sc = new Scanner(System.in);
		while(true) {
			int a=sc.nextInt();
			if(a!=-1) {
				if(a>=BOOTEM_FLOOR&&a<=TOP_FLOOR) {
					allfloor.add(a);
				}else {
					System.out.println("¥����������Ѿ�������¥��ɾ��������������");
				}
			}else {
				if(allfloor.size()>0) {
					//������-1���˳�
					break;	
				}else {
					System.out.println("�����ʽ������������");
				}
			}
		}
		/*for(Integer value1: allfloor) {
		System.out.println("allfloor"+value1);
		}*/
		/*Iterator<Integer> value2 = allfloor.iterator();
		while(value2.hasNext()) {
			System.out.println("allfloor"+value2.next());
		}*/
	}
	public void addweight() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("**���ڼ���Ƿ���**");
		Thread.sleep(1000);
		int weight = (int) (Math.random()*1200+1);
		System.out.print("��ǰ�˿͵���������:"+weight+"����");
		if(weight > CAPACITY){
			System.out.println("���أ��޷����ţ�");
		}
		else{
			System.out.println("û�г���");
			isDoorOpen = false;
		}	
		System.out.println("���ݿ�ʼ����!");
	}
	public void FloorRun() throws Exception {
		// TODO Auto-generated method stub
		//���Ϊ����,��ȡ��������
		while(allfloor.size()>0) {
			if(upstairs) {
				System.out.println("��������");			
				for(Integer value1: allfloor) {
					goalFoor=value1;
				}
				
				for(int i=nowfloor;i<=goalFoor;i++) {
					Thread.sleep(1000);
					System.out.println("-"+i+"¥-");
					Iterator<Integer> FRvalue2 = allfloor.iterator();
					while(FRvalue2.hasNext()) {
						int FRa=FRvalue2.next();
						if(i==FRa) {
		                    System.out.println("����"+i+"¥�����ţ�");
		        			addweight();
							FRvalue2.remove();
							nowfloor =i;
						}
					}
					//��ʼ��¥
					
				}
				upstairs=false;
				System.out.println("��ʼ��¥");

		}else{//�����½�
			System.out.println("============�����½�");
			for(Integer value1: allfloor) {
				goalFoor=value1;
				System.out.println("value1="+value1);
				break;
			}
			for(int i=nowfloor;i>=goalFoor;i--) {
				Thread.sleep(1000);
				System.out.println("-"+i+"¥-");
				Iterator<Integer> FRvalue3 = allfloor.iterator();
				while(FRvalue3.hasNext()) {
					int FRa=FRvalue3.next();
					if(i==FRa) {
	                    System.out.println("����"+i+"¥�����ţ�");
						FRvalue3.remove();
						nowfloor =i;
					}
				}
				//��ʼ��¥
				
			}
			upstairs=true;
			//ɾ��
			Thread.sleep(1000);
			System.out.println("��ʼ��¥");
			}
		}
	}
}
