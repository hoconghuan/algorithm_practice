class CircularQueue {
    constructor(k) {
        this.queue = new Array(k);
        this.head = -1;
        this.tail = -1;
        this.size = k;
    }

    // 入队
    enqueue(value) {
        if (this.isFull()) {
            return false; // 队列已满，入队失败
        }
        if (this.isEmpty()) {
            this.head = 0;
        }
        this.tail = (this.tail + 1) % this.size;
        this.queue[this.tail] = value;
        return true;
    }

    // 出队
    dequeue() {
        if (this.isEmpty()) {
            return false; // 队列为空，出队失败
        }
        if (this.head === this.tail) {
            this.head = -1;
            this.tail = -1;
            return true;
        }
        this.head = (this.head + 1) % this.size;
        return true;
    }

    // 获取队首元素
    front() {
        if (this.isEmpty()) {
            return -1; // 队列为空，返回-1
        }
        return this.queue[this.head];
    }

    // 获取队尾元素
    rear() {
        if (this.isEmpty()) {
            return -1; // 队列为空，返回-1
        }
        return this.queue[this.tail];
    }

    // 判断队列是否为空
    isEmpty() {
        return this.head === -1;
    }

    // 判断队列是否已满
    isFull() {
        return (this.tail + 1) % this.size === this.head;
    }
}

// 示例用法
const queue = new CircularQueue(5);
console.log(queue.isEmpty()); // 输出 true
console.log(queue.isFull()); // 输出 false
console.log(queue.enqueue(1)); // 输出 true
console.log(queue.enqueue(2)); // 输出 true
console.log(queue.enqueue(3)); // 输出 true
console.log(queue.enqueue(4)); // 输出 true
console.log(queue.enqueue(5)); // 输出 true
console.log(queue.enqueue(6)); // 输出 false，队列已满
console.log(queue.front()); // 输出 1
console.log(queue.rear()); // 输出 5
console.log(queue.dequeue()); // 输出 true
console.log(queue.enqueue(6)); // 输出 true
console.log(queue.front()); // 输出 2
console.log(queue.rear()); // 输出 6
