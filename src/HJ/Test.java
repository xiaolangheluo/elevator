package lift;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		Elevator elevator = new Elevator();
		System.out.println("��ǰ������¥��Ϊ"+elevator.TOP_FLOOR+"�㣬��һ����Ϊ��һ��");
		while(true) {
			elevator.input();
			if(elevator.list.size()>0){
				elevator.run();
				elevator.list.clear();
			}
		}
	}

}
