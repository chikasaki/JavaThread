Thread异常处理机制和ThreadGroup源码解读：
1. Thread子线程发生异常时，程序的处理流程：
    - JVM调用子线程的`dispatchUncaughtException`方法
    - 检查该线程是否设置了`uncaughtExceptionHandler`属性
        - 设置了: 调用该`handler`的方法
        - 没设置: 调用所属线程组的方法，若其所有祖先线程组均没有实现相应方法
        ，则会检查Thread全局的`defaultUncaughtExceptionHandler`属性
        
2. ThreadGroup源码解读:
    - ThreadGroup本质是棵树结构
    - ThreadGroup可以拥有`子ThreadGroup`以及包含的`Thread线程`
    - 所有ThreadGroup方法，基本都是以下结构
````java
/**
* 这里建立快照的原因值得斟酌：
* 建立快照之后，可以马上释放锁，保证线程安全；
* 同时提升了并发效率
*/
private int enumerate(Thread list[], int n, boolean recurse) {
    int ngroupsSnapshot = 0;
    //建立子线程组的快照
    ThreadGroup[] groupsSnapshot = null;
    synchronized (this) {
        if (destroyed) {
            return 0;
        }
        int nt = nthreads;
        if (nt > list.length - n) {
            nt = list.length - n;
        }
        for (int i = 0; i < nt; i++) {
            if (threads[i].isAlive()) {
                list[n++] = threads[i];
            }
        }
        if (recurse) {
            ngroupsSnapshot = ngroups;
            //检查是否存在子线程组
            if (groups != null) {
                groupsSnapshot = Arrays.copyOf(groups, ngroupsSnapshot);
            } else {
                groupsSnapshot = null;
            }
        }
    }
    if (recurse) {
        //递归调用子线程组的相应方法
        for (int i = 0 ; i < ngroupsSnapshot ; i++) {
            n = groupsSnapshot[i].enumerate(list, n, true);
        }
    }
    return n;
}
````