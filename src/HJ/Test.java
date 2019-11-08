package lift;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		Elevator elevator = new Elevator();
		System.out.println("当前电梯总楼层为"+elevator.TOP_FLOOR+"层，第一层编号为第一层");
		while(true) {
			elevator.input();
			if(elevator.list.size()>0){
				elevator.run();
				elevator.list.clear();
			}
		}
	}

}
