/**
 * title: 环形队列实现
 * 
 * 
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        // 创建一个队列
        CircleArrayQueue queue = new CircleArrayQueue(4);// 此处有效数据为3
        char key = ' '; // 接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);// 接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': // 取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': // 退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~");
    }

}

// 使用数组模拟一个环形队列：CArrayQueue类
class CircleArrayQueue {

    private int maxSize;// 数组最大的容量
    // front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
    private int front;
    // rear 指向队列的最后一个元素的后一个位置
    private int rear;
    private int[] arr;

    // 创建队列构造器
    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[arrMaxSize];
        // front rear 默认值为0，声明的默认值也为0，既不需要重复赋值
    }

    // 判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    // 判断你队列是否为空
    public boolean isEmpty() {
        return front == rear;// 头尾相等，说明没数据
    }

    // 添加数据到队列
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列已满,无法添加数据~~~~");
            return;
        }
        // 直接插入进来
        arr[rear] = n;
        // rear 后移, 得考虑取模(环形)
        rear = (rear + 1) % maxSize;
    }

    // 获取队列数据(出队列)
    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            // 通过异常抛出 null
            throw new RuntimeException("队列为空，无法取数据~~~");
        }
        // 分析得 front 是指向队列的第一个元素
        // 先 将 front 值保存到临时变量，用于返回
        // 然后再进行后移
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    // 显示队列的所有数据
    public void showQueue() {
        // 判空
        if (isEmpty()) {
            System.out.println("队列为空,无法显示数据~~~~");
            return;
        }
        // 遍历(从头队列 到 都队列 + 有效数据个数)
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 计算出有效数据个数
    public int size() {
        return (rear - front + maxSize) % maxSize;
    }

    // 显示头队列，而非取出
    public int headQueue() {
        // 判空
        if (isEmpty()) {
            // 通过异常抛出 null
            throw new RuntimeException("队列为空，无法取数据~~~");
        }
        // 显示
        return arr[front];
    }
}
