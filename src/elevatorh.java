import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class elevatorh {

	
	private boolean isDoorOpen; 
	private int currentFloor; //当前楼层
	private int goalFoor;	  //目标楼层
	private int weight;
	private int nowfloor=1;
	final int CAPACITY = 1000; //电梯最大重量
	final int TOP_FLOOR = 12;  //电梯总高度
	final int BOOTEM_FLOOR = 1; //电梯最低楼层
	// upstair变量保存上一次是上楼还是下楼 ，默认为真
	boolean upstairs = true;
	
	Random r = new Random();
	Set<Integer> allfloor = new TreeSet<>();
	//无参的构造方法，运行近就会使用
	public elevatorh() {
		//初始化参数
		isDoorOpen = false; //初始关门
		currentFloor = 1;
		goalFoor = 0;
		weight = 0;
		
	}
	public void addfloor() {
		// TODO Auto-generated method stub
		System.out.println("请输入要前往的楼层，中间用空格分开，-1标志结束！");
		Scanner sc = new Scanner(System.in);
		while(true) {
			int a=sc.nextInt();
			if(a!=-1) {
				if(a>=BOOTEM_FLOOR&&a<=TOP_FLOOR) {
					allfloor.add(a);
				}else {
					System.out.println("楼层输入错误，已经将错误楼层删除，请重新输入");
				}
			}else {
				if(allfloor.size()>0) {
					//当遇见-1就退出
					break;	
				}else {
					System.out.println("输入格式错误，重新输入");
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
		System.out.println("**正在检查是否超重**");
		Thread.sleep(1000);
		int weight = (int) (Math.random()*1200+1);
		System.out.print("当前乘客的总重量是:"+weight+"公斤");
		if(weight > CAPACITY){
			System.out.println("超重，无法关门！");
		}
		else{
			System.out.println("没有超重");
			isDoorOpen = false;
		}	
		System.out.println("电梯开始运作!");
	}
	public void FloorRun() throws Exception {
		// TODO Auto-generated method stub
		//如果为上升,获取最大的数字
		while(allfloor.size()>0) {
			if(upstairs) {
				System.out.println("电梯上升");			
				for(Integer value1: allfloor) {
					goalFoor=value1;
				}
				
				for(int i=nowfloor;i<=goalFoor;i++) {
					Thread.sleep(1000);
					System.out.println("-"+i+"楼-");
					Iterator<Integer> FRvalue2 = allfloor.iterator();
					while(FRvalue2.hasNext()) {
						int FRa=FRvalue2.next();
						if(i==FRa) {
		                    System.out.println("到达"+i+"楼，开门！");
		        			addweight();
							FRvalue2.remove();
							nowfloor =i;
						}
					}
					//开始下楼
					
				}
				upstairs=false;
				System.out.println("开始下楼");

		}else{//电梯下降
			System.out.println("============电梯下降");
			for(Integer value1: allfloor) {
				goalFoor=value1;
				System.out.println("value1="+value1);
				break;
			}
			for(int i=nowfloor;i>=goalFoor;i--) {
				Thread.sleep(1000);
				System.out.println("-"+i+"楼-");
				Iterator<Integer> FRvalue3 = allfloor.iterator();
				while(FRvalue3.hasNext()) {
					int FRa=FRvalue3.next();
					if(i==FRa) {
	                    System.out.println("到达"+i+"楼，开门！");
						FRvalue3.remove();
						nowfloor =i;
					}
				}
				//开始下楼
				
			}
			upstairs=true;
			//删除
			Thread.sleep(1000);
			System.out.println("开始上楼");
			}
		}
	}
}
