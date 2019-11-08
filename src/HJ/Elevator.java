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
	private int currentFloor; //当前楼层
	private int goalFoor;	  //目标楼层
	private int weight;
	final int CAPACITY = 1000; //电梯最大重量
	final int TOP_FLOOR = 12;  //电梯总高度
	final int BOOTEM_FLOOR = 1; //电梯最低楼层
	// upstair变量保存上一次是上楼还是下楼 ，默认为真
	boolean upstairs = true;
	
	Random r = new Random();
	
	List<Integer> list = new ArrayList<Integer>();
	//构造函数初始化
	public Elevator() {
		isDoorOpen = false; //初始关门
		currentFloor = 1;
		goalFoor = 0;
		weight = 0;
	}
	//开门
	public void openDoor(){
		isDoorOpen = true;
	}
	//关门
	public void closeDoor() throws InterruptedException{
		System.out.println("**正在检查是否超重**");
		Thread.sleep(1000);
		System.out.print("当前乘客的总重量是:"+weight+"公斤");
		if(weight > CAPACITY){
			System.out.print("超重，无法关门！");
		}
		else{
			System.out.print("没有超重");
			isDoorOpen = false;
		}
		System.out.println();
	}
	//输入
	public void input(){
		System.out.println("请输入要前往的楼层，中间用空格分开，-1标志结束！");
		Scanner sc = new Scanner(System.in);
		int length = 0;
		while(true){ //输入
			int num = sc.nextInt();
			if(num != -1) {
				if(num<=0) num = 1;
				if(!list.contains(num)) //如果list里面没有，则保存list里
					list.add(num);
			}
			else  		  break;
		}
		System.out.println("请认真检查所输入的楼层！");
	}
	//运行
	public void run() throws InterruptedException{
		weight = r.nextInt(1200);
		Collections.sort(list); //对 list 进行排序
		closeDoor();
		int len = list.size();
		int maxFloor = list.get(len-1);
		//如果最大层大于当前所在层，并且上次电梯也为上时 执行上操作
		if(maxFloor > currentFloor && upstairs){ 
			up(maxFloor);
			if(list.size()>0){ //上完楼后如果list中还有元素说明还需要下楼
				down();
			}
		}else{                       //否则下
			down();
			if(list.size()>0){ //下完楼后如果list中还有元素说明还需要上楼
				up(maxFloor);
			}
		}
	}
	//上楼
	public void up(int maxFloor) throws InterruptedException{
		int beforeFloor = currentFloor;
		for(int i = currentFloor;i<=maxFloor;i++){
			Thread.sleep(1000);
			System.out.println("-----"+i+"楼-----");
			if(list.contains(i)) {
				System.out.println("到达"+i+"楼，开门！");
				weight = r.nextInt(1200);
				closeDoor();
				System.out.println("-----"+i+"楼-----");
				int index = list.indexOf(i);
				list.remove(index);
			}
			currentFloor = i;
		}
		upstairs = true;
	}
	//下楼
	public void down() throws InterruptedException{
		int minFloor = list.get(0); 
		for(int i = currentFloor;i>=minFloor;i--){
			Thread.sleep(1000);
			System.out.println("-"+i+"楼-");
			System.out.println("......");
			if(list.contains(i)) {
				System.out.println("到达"+i+"楼，开门！");
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
